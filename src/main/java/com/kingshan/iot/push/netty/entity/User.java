package com.kingshan.iot.push.netty.entity;

import lombok.Data;

import java.nio.channels.Channel;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 16:34
 */
@Data
public class User {
    private  String id;
    private Channel channel;
}
