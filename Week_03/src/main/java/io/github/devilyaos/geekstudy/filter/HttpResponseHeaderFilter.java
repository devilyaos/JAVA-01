package io.github.devilyaos.geekstudy.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * 响应体过滤器
 */
public class HttpResponseHeaderFilter implements HttpResponseFilter{

    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("Content-Type", "application/json");
        response.headers().set("study-group", "java0101-res");
    }
}
