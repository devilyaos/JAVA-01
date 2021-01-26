package io.github.devilyaos.geekstudy.filter;

import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

/**
 * 请求头过滤器
 */
public class HttpRequestHeaderFilter implements HttpRequestFilter{

    @Override
    public void filter(FullHttpRequest fullRequest, ChannelHandlerContext ctx) {
        fullRequest.headers().set("study-group", "java0101-req");
    }
}
