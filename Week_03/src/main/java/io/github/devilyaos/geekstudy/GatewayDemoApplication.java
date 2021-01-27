package io.github.devilyaos.geekstudy;

import io.github.devilyaos.geekstudy.inbound.HttpInboundServer;

/**
 * 网关demo启动入口
 */
public class GatewayDemoApplication {

    public static void main(String[] args) {
        String watchPort = System.getProperty("watchPort", "8888");
        int port = Integer.parseInt(watchPort);
        HttpInboundServer server = new HttpInboundServer(port);
        try {
            server.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
