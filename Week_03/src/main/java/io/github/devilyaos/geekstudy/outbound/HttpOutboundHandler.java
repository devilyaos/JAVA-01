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
        System.out.println("开始Outbound的handle处理...");
        // 如果路径是http://127.0.0.1:8888/reportRoute?target=http://127.0.0.1:8801格式, 则添加进地址列表
        String uri = fullRequest.uri();
        String tmpUri = uri.contains("?") ? uri.substring(0, uri.indexOf("?")) : uri;
        if ("/reportRoute".equalsIgnoreCase(tmpUri)) {
            System.out.println("进入注册路由分支");
            proxyPool.submit(() -> dealAddRouteList(fullRequest, ctx, uri));
        } else if (tmpUri.endsWith(".ico") || tmpUri.endsWith(".js") || tmpUri.endsWith(".css")) {
            System.out.println("进入静态资源分支");
            proxyPool.submit(() -> dealResources(fullRequest, ctx));
        } else {
            System.out.println("进入正常调度分支");
            String targetAddress = router.route(ServiceFoundService.getUrlList());
            String finalUrl = targetAddress + fullRequest.uri();
            requestFilter.filter(fullRequest, ctx);
            proxyPool.submit(() -> fetchGet(fullRequest, ctx, finalUrl));
        }
        System.out.println("Outbound的handle调度完成...");
    }

    /**
     * 添加进路由列表
     * @param uri 请求路径
     */
    private void dealAddRouteList(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final String uri) {
        FullHttpResponse fullHttpResponse = null;
        try {
            System.out.println("开始注册服务...");
            String targetUrl = uri.substring(uri.indexOf("/reportRoute?target=") + 20);
            ServiceFoundService.addUrl(targetUrl);
            System.out.println("成功注册: " + targetUrl);
            byte[] byteArr = "SUCCESS".getBytes();
            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(byteArr));
            fullHttpResponse.headers().set("Content-Length", byteArr.length);
        } catch (Exception e) {
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
            ctx.flush();
            System.out.println("处理完成啦!!!");
            System.out.println("============================");
        }
    }

    /**
     * 静态资源处理
     */
    private void dealResources(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx) {
        FullHttpResponse fullHttpResponse = null;
        try {
            System.out.println("开始处理静态资源请求...");
            System.out.println("静态资源请求, 不处理");
            byte[] byteArr = new byte[0];
            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(byteArr));
            fullHttpResponse.headers().set("Content-Length", 0);
        } catch (Exception e) {
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
            ctx.flush();
            System.out.println("处理完成啦!!!");
            System.out.println("============================");
        }
    }

    /**
     * 发出get请求
     * @param fullRequest 请求报文
     * @param ctx 上下文
     * @param url 请求地址
     */
    private void fetchGet(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final String url) {
        System.out.println("Outbound的fetchGet开始执行...");
        Request request = new Request.Builder().url(url).get().addHeader("study-group", "java0101-req").build();
        Response response = null;
        FullHttpResponse fullHttpResponse = null;
        try {
            System.out.println("即将请求" + url + " ...");
            response = httpClient.newCall(request).execute();
            System.out.println("开始处理返回结果...");
            byte[] body = response.body().bytes();
            fullHttpResponse = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, HttpResponseStatus.OK, Unpooled.wrappedBuffer(body));
            fullHttpResponse.headers().set("Content-Length", body.length);
            responseFilter.filter(fullHttpResponse);
        } catch (Exception e) {
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
            ctx.flush();
            System.out.println("处理完成啦!!!");
            System.out.println("============================");
        }
    }
}
