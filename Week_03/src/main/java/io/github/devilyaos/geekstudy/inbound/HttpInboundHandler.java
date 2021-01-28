package io.github.devilyaos.geekstudy.inbound;

import io.github.devilyaos.geekstudy.filter.HttpRequestFilter;
import io.github.devilyaos.geekstudy.filter.HttpRequestHeaderFilter;
import io.github.devilyaos.geekstudy.outbound.HttpOutboundHandler;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.util.ReferenceCountUtil;

/**
 * inbound处理
 */
public class HttpInboundHandler extends ChannelInboundHandlerAdapter {

    /**
     * outbound处理
     */
    private HttpOutboundHandler handler;

    /**
     * 请求处理过滤器
     */
    private HttpRequestFilter filter;

    public HttpInboundHandler() {
        // 初始化outbound的处理
        handler = new HttpOutboundHandler();
        // 初始化请求过滤器
        filter = new HttpRequestHeaderFilter();
    }

    /**
     * 读取完成后清空读取的内容
     * @param ctx 上下文内容
     * @throws Exception
     */
    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    /**
     * 处理读取的内容
     * @param ctx 读取的内容
     * @param msg 分发消息内容
     * @throws Exception
     */
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("开始Inbound的channelRead处理...");
        try {
            if (msg instanceof FullHttpRequest) {
                FullHttpRequest fullRequest = (FullHttpRequest) msg;
                // 调用outbound的处理逻辑
                handler.handle(fullRequest, ctx, filter);
            }
        } catch (Exception e) {
            System.err.println("发生异常信息: " + e.getMessage() + ", 读取中断");
        } finally {
            // 释放消息
            // 从InBound里读取的ByteBuf要手动释放，还有自己创建的ByteBuf要自己负责释放。这两处要调用这个release方法。
            // write Bytebuf到OutBound时由netty负责释放，不需要手动调用release
            // ReferenceCountUtil.release()其实是ByteBuf.release()方法（从ReferenceCounted接口继承而来）的包装。
            // netty4中的ByteBuf使用了引用计数（netty4实现了一个可选的ByteBuf池），每一个新分配的ByteBuf的引用计数值为1，
            // 每对这个ByteBuf对象增加一个引用，需要调用ByteBuf.retain()方法，而每减少一个引用，需要调用ByteBuf.release()方法。
            // 当这个ByteBuf对象的引用计数值为0时，表示此对象可回收。
            ReferenceCountUtil.release(msg);
            System.out.println("Inbound的channelRead调度完成...");
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        super.exceptionCaught(ctx, cause);
        Channel channel = ctx.channel();
        if (channel.isActive()) {
            ctx.close();
        }
    }
}
