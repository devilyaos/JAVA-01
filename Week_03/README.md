## DEMO说明

该项目为Netty的学习项目

1. 启动 `/GatewayDemoApplication.java`, 该类即为网关服务类
2. 启动 `/testclient/` 目录下的客户端, 客户端启动时会自动注册到网关服务的url列表中

目前访问策略是随机访问, 请求`http://127.0.0.1:8888`会随机负载到三个客户端中