package com.kingshan.iot.push.netty.server;

import com.kingshan.iot.push.netty.websocket.NioWebSocketChannelInitializer;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 14:44
 */

@Slf4j
@Component
public class NioWebSocketServer {

    @Value(value = "${iot.websocket.port}")
    private Integer websocketPort;

    public  void init(){
        log.info("Starting websocket service...");
        NioEventLoopGroup boss=new NioEventLoopGroup();
        NioEventLoopGroup work=new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap=new ServerBootstrap();
            bootstrap.group(boss,work);
            bootstrap.channel(NioServerSocketChannel.class);
            bootstrap.childHandler(new NioWebSocketChannelInitializer());
            Channel channel = bootstrap.bind(websocketPort).sync().channel();
            log.info("websocket service Started!  port: " + websocketPort + " channel:" +channel );
            channel.closeFuture().sync();
        } catch (InterruptedException e) {
            log.error("start websocket service error", e);
        }finally {
            boss.shutdownGracefully();
            work.shutdownGracefully();
            log.info("websocket servic closed");
        }
    }

}
