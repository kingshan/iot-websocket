package com.kingshan.iot.push.api.controller;

import com.kingshan.iot.push.api.vo.Message;
import com.kingshan.iot.push.api.vo.Result;
import com.kingshan.iot.push.netty.supervise.ChannelSupervise;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *
 * </p>

 * @author: Kingshan
 * @since: 2019/11/7 15:58
 */
@Api(tags="消息推送")
@Slf4j
@RestController
@RequestMapping("/push")
public class PushController {
    @ApiOperation(value="用户推送", notes="用户推送")
    @PostMapping(value = "/user")
    public Result pushUser(@RequestBody @Validated Message message) {
        ChannelSupervise.send2User(message.getId(), message.getMessage());
        return Result.ok();
    }

    @ApiOperation(value="群组推送", notes="群组推送")
    @PostMapping(value = "/group")
    public Result pushGroup(@RequestBody @Validated Message message) {
        ChannelSupervise.send2Group(message.getId(), message.getMessage());
        return Result.ok();
    }

    @ApiOperation(value="全部推送", notes="全部推送")
    @PostMapping(value = "/all")
    public Result pushAll(@RequestBody @Validated String message) {
        ChannelSupervise.send2All(message);
        return Result.ok();
    }

}
