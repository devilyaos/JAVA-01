package io.github.devilyaos.geekstudy.router;

import java.util.List;

/**
 * 负载路由接口
 */
public interface HttpEndpointRouter {

    /**
     * 负载路由操作
     * @param endpoints 端点列表
     * @return 选中的结果地址
     */
    String route(List<String> endpoints);
}
