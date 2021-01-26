package io.github.devilyaos.geekstudy.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 请求过滤器
 */
public interface HttpRequestFilter {
    /**
     * 过滤请求
     * @param fullRequest 请求体
     * @param ctx 上下文
     */
    void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx);
}
