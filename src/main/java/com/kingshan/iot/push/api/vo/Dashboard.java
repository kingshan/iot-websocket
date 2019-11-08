package com.kingshan.iot.push.api.vo;

import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/8 8:57
 */
@Data
public class Dashboard {
    private Integer userCount;
    private Integer groupCount;
    private String systemRunTime;
    private Integer messageCount;

    private String cpu;
    private String memery;
    private String thread;
}
