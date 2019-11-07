package com.kingshan.iot.push.api.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 15:07
 */
@Data
@ApiModel(value="推送消息对象", description="推送消息对象")
public class Message {

    @ApiModelProperty(value = "推送ID")
    private String id;
    @ApiModelProperty(value = "消息内容")
    private String message;

}
