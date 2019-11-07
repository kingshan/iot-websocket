package com.kingshan.iot.push.api.controller;

import com.kingshan.iot.push.api.vo.Result;
import com.kingshan.iot.push.netty.global.ChannelSupervise;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 16:24
 */
@Api(tags="用户管理")
@Slf4j
@RestController
@RequestMapping("/user")
public class UserController {

    @ApiOperation(value="用户列表", notes="用户列表")
    @GetMapping(value = "/list")
    public Result<List<String >> listAll(){
        Result result = new Result();
        result.setResult(ChannelSupervise.getUserList());
        result.ok();
        return  result;
    }

    @ApiOperation(value="用户数量", notes="用户数量")
    @GetMapping(value = "/count")
    public Result<Integer> countAll(){
        Result result = new Result();
        result.setResult(ChannelSupervise.getUserCount());
        result.ok();
        return  result;
    }

    @ApiOperation(value="判断是否在线", notes="判断是否在线")
    @GetMapping(value = "/isOnline")
    public Result<Integer> isOnline(@Validated @NotNull  @RequestParam(name="userId") String  userId){
        Result result = new Result();
        result.setResult(ChannelSupervise.isOnline(userId));
        result.ok();
        return  result;
    }



}
