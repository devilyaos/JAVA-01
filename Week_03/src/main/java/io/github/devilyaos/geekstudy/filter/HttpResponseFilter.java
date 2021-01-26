package io.github.devilyaos.geekstudy.filter;

import io.netty.handler.codec.http.FullHttpResponse;

/**
 * 响应处理过滤器
 */
public interface HttpResponseFilter {
    /**
     * 过滤响应
     * @param response 响应体
     */
    void filter(FullHttpResponse response);
}
