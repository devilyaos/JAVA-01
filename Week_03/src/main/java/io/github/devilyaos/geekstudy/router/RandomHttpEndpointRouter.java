package io.github.devilyaos.geekstudy.router;

import io.github.devilyaos.geekstudy.exception.GatewayException;

import java.util.List;
import java.util.Random;

/**
 * 随机负载负载路由实现
 */
public class RandomHttpEndpointRouter implements HttpEndpointRouter{

    @Override
    public String route(List<String> endpoints) {
        if (endpoints == null || endpoints.isEmpty()) {
            throw new GatewayException("缺少路由列表");
        }
        Random random = new Random(System.currentTimeMillis());
        return endpoints.get(random.nextInt(endpoints.size()));
    }
}
