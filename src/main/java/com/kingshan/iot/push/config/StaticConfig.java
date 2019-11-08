package com.kingshan.iot.push.config;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 11:50
 */

@Configuration
public class StaticConfig {

    public static Date systemStartDate;

    public static AtomicInteger messageCount = new AtomicInteger(0);

    @Bean
    public void initConfig(){
        systemStartDate = new Date();
    }

    public static void  addMessageCount(){
        messageCount.getAndIncrement();
    }

}
