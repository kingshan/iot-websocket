package com.kingshan.iot.push.runner;

import com.kingshan.iot.push.netty.server.NioWebSocketServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 14:14
 */
@Component
public class WebsocketRunner implements ApplicationRunner {

    @Autowired
    NioWebSocketServer nioWebSocketServer;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        new Thread(new Runnable() {
            public void run() {
                nioWebSocketServer.init();
            }
        }).start();

    }
}
