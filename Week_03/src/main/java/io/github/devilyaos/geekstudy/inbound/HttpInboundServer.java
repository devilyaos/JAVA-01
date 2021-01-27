package io.github.devilyaos.geekstudy.inbound;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.epoll.EpollChannelOption;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;

/**
 * 网关接收请求服务
 */
public class HttpInboundServer {

    /**
     * 监听端口
     */
    private final int watchPort;
    /**
     * 工作线程数
     */
    private int workLoopNum = 2;

    /**
     * 启动时指定监听端口
     *
     * @param watchPort 指定的监听端口
     */
    public HttpInboundServer(int watchPort) {
        this.watchPort = watchPort;
    }

    /**
     * 增加一个启动时指定工作线程数的构造函数
     *
     * @param watchPort   监听端口号
     * @param workLoopNum 工作线程数
     */
    public HttpInboundServer(int watchPort, int workLoopNum) {
        this.watchPort = watchPort;
        this.workLoopNum = workLoopNum;
    }

    /**
     * 启动监听
     */
    public void start() throws InterruptedException {
        System.out.println("网关服务正在启动...");
        EventLoopGroup parentGroup = new NioEventLoopGroup(1);
        EventLoopGroup childGroup = new NioEventLoopGroup(workLoopNum);

        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            // BACKLOG用于构造服务端套接字ServerSocket对象
            // 标识当服务器请求处理线程全满时，用于临时存放已完成三次握手的请求的队列的最大长度。
            // 如果未设置或所设置的值小于1，Java将使用默认值50。
            // option主要设置的是ServerChannel的一些选项
            // 而childOption主要设置的是ServerChannel的子Channel选项
            // 如果是客户端, 因为是Bootstrap, 所以没有childOption, 此时option设置的是客户端Channel选项
            // Link: https://www.jianshu.com/p/0bff7c020af2
            serverBootstrap.option(ChannelOption.SO_BACKLOG, 128)
                    // 用于启用或关于Nagle算法
                    // 如果要求高实时性，有数据发送时就马上发送，就将该选项设置为true关闭Nagle算法
                    // 如果要减少发送次数减少网络交互，就设置为false等累积一定大小后再发送。默认为false
                    .option(ChannelOption.TCP_NODELAY, true)
                    // 是否启用心跳保活机制
                    // 在双方TCP套接字建立连接后（即都进入ESTABLISHED状态）并且在两个小时左右上层没有任何数据传输的情况下，这套机制才会被激活
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    // 1. 允许启动一个监听服务器并捆绑其众所周知端口，即使以前建立的将此端口用做他们的本地端口的连接仍存在
                    //    这通常是重启监听服务器时出现，若不设置此选项，则bind时将出错
                    // 2. 允许在同一端口上启动同一服务器的多个实例，只要每个实例捆绑一个不同的本地IP地址即可
                    //    对于TCP，我们根本不可能启动捆绑相同IP地址和相同端口号的多个服务器
                    // 3. 允许单个进程捆绑同一端口到多个套接口上，只要每个捆绑指定不同的本地IP地址即可
                    //    这一般不用于TCP服务器
                    // 4. 允许完全重复的捆绑：当一个IP地址和端口绑定到某个套接口上时，还允许此IP地址和端口捆绑到另一个套接口上
                    //    一般来说，这个特性仅在支持多播的系统上才有，而且只对UDP套接口而言（TCP不支持多播）
                    .option(ChannelOption.SO_REUSEADDR, true)
                    // 定义接收或者传输的系统缓冲区buf的大小 SO_RCVBUF 与 SO_SNDBUF
                    .option(ChannelOption.SO_RCVBUF, 32 * 1024)
                    .option(ChannelOption.SO_SNDBUF, 32 * 1024)
                    // 支持多个进程或者线程绑定到同一端口，提高服务器程序的性能
                    .option(EpollChannelOption.SO_REUSEPORT, true)
                    // 子Channel设置保活
                    .childOption(ChannelOption.SO_KEEPALIVE, true)
                    // Netty4使用对象池，重用缓冲区
                    .option(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);
            // 分配group
            // 看资料官方建议用parent和child来理解两个group
            serverBootstrap.group(parentGroup, childGroup)
                    // 设置通道
                    .channel(NioServerSocketChannel.class)
                    // 设置handler
                    // 指定日志级别
                    .handler(new LoggingHandler(LogLevel.DEBUG))
                    .childHandler(new HttpInboundChannelProxy());
            Channel ch = serverBootstrap.bind(watchPort).sync().channel();
            System.out.println("服务已启动, 监听地址为: http://127.0.0.1:" + watchPort + '/');
            // 当服务停止时, 需要优雅关闭
            ch.closeFuture().sync();
        } finally {
            // 无论如何, 最终要关闭线程池
            parentGroup.shutdownGracefully();
            childGroup.shutdownGracefully();
            System.out.println("网关关闭!!!");
        }
    }
}
