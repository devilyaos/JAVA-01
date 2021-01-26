package io.github.devilyaos.geekstudy.outbound;

import io.github.devilyaos.geekstudy.filter.HttpRequestFilter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 出口处理接口
 */
public interface OutboundHandler {

    /**
     * 处理方法
     * @param fullRequest 请求体
     * @param ctx 上下文
     * @param requestFilter 请求过滤器
     */
    void handle(final FullHttpRequest fullRequest, final ChannelHandlerContext ctx, final HttpRequestFilter requestFilter);
}
