package io.github.devilyaos.geekstudy.inbound;

import io.netty.channel.ChannelInitializer;

import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;

/**
 * 子Handler初始化
 */
public class HttpInboundChannelProxy extends ChannelInitializer<SocketChannel> {

    @Override
    public void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipLine = socketChannel.pipeline();
        // 处理HTTP GET请求的处理类
        pipLine.addLast(new HttpServerCodec());
        // 处理POST请求, 需要使用HttpObjectAggregator, 初始化的时候需要制定maxContentLength
        pipLine.addLast(new HttpObjectAggregator(1024 * 1024));
        // 增加自定义的业务处理handler
        pipLine.addLast(new HttpInboundHandler());
    }
}
