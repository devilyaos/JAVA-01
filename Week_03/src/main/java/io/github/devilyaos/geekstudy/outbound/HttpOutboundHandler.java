package io.github.devilyaos.geekstudy.outbound;

import io.github.devilyaos.geekstudy.exception.GatewayException;
import io.github.devilyaos.geekstudy.filter.HttpRequestFilter;
import io.github.devilyaos.geekstudy.filter.HttpResponseFilter;
import io.github.devilyaos.geekstudy.filter.HttpResponseHeaderFilter;
import io.github.devilyaos.geekstudy.router.HttpEndpointRouter;
import io.github.devilyaos.geekstudy.router.RandomHttpEndpointRouter;
import io.github.devilyaos.geekstudy.servicefound.ServiceFoundService;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 出口处理类
 */
public class HttpOutboundHandler implements OutboundHandler {

    /**
     * 设置请求的网络客户端
     */
    private OkHttpClient httpClient;

    /**
     * 路由策略
     */
    private HttpEndpointRouter router;

    /**
     * 响应体过滤器
     */
    private HttpResponseFilter responseFilter;

    /**
     * 使用自建线程池
     */
    private ExecutorService proxyPool;

    /**
     * 在初始化时定义好相关资源
     */
    public HttpOutboundHandler() {
        // 使用jvm可获得的cpu核心数进行线程池的初始化
        int coreNum = Runtime.getRuntime().availableProcessors();
        // OkHttp自带线程池, 此处先用OkHttp实现, 后续再用httpClient改写
        httpClient = new OkHttpClient();
        // 初始化路由策略
        router = new RandomHttpEndpointRouter();
        // 初始化请求体
        responseFilter = new HttpResponseHeaderFilter();
        // 初始化线程池
        // 此处仅为了学习与训练, 生产场景建议选择OkHttp自带的ConnectPool
        proxyPool = new ThreadPoolExecutor(
                coreNum, coreNum, 1000, TimeUnit.MILLISECONDS,
                new ArrayBlockingQueue<>(2048),
                new NamedThreadFactory("java0101Gateway"),
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    @Override
    public void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, HttpRequestFilter requestFilter) {
        String targetAddress = router.route(ServiceFoundService.getUrlList());
        String finalUrl = targetAddress + fullRequest.uri();
        requestFilter.filter(fullRequest, ctx);
        proxyPool.submit(() -> fetchGet(fullRequest, ctx, finalUrl));
    }

    /**
     * 发出get请求
     * @param fullRequest 请求报文
     * @param ctx 上下文
     * @param url 请求地址
     */
    private void fetchGet(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final String url) {
        Request request = new Request.Builder().url(url).get().addHeader("study-group", "java0101-req").build();
        Response response = null;
        try {
            response = httpClient.newCall(request).execute();
        } catch (IOException e) {
            System.out.println("请求发生异常");
            throw new GatewayException("当前请求无法处理");
        }
        FullHttpResponse fullHttpResponse = null;
        try {
            byte[] body = response.body().bytes();
            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            fullHttpResponse.headers().set("Content-Length", response.body().contentLength());
            responseFilter.filter(fullHttpResponse);
        } catch (IOException e) {
            e.printStackTrace();
            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.NO_CONTENT);
            ctx.close();
        } finally {
            if (fullRequest != null) {
                if (!HttpUtil.isKeepAlive(fullRequest)) {
                    ctx.write(fullHttpResponse).addListener(ChannelFutureListener.CLOSE);
                } else {
                    ctx.write(fullHttpResponse);
                }
            }
        }
    }
}
