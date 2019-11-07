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
import java.util.List;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 16:24
 */
@Api(tags="群组管理")
@Slf4j
@RestController
@RequestMapping("/group")
public class GroupController {

    @ApiOperation(value="群组列表", notes="群组列表")
    @GetMapping(value = "/list")
    public Result<List<String >> listAll(){
        Result result = new Result();
        result.setResult(ChannelSupervise.getGroupList());
        result.ok();
        return  result;
    }

    @ApiOperation(value="群组数量", notes="群组数量")
    @GetMapping(value = "/count")
    public Result<Integer> countAll(){
        Result result = new Result();
        result.setResult(ChannelSupervise.getGroupCount());
        result.ok();
        return  result;
    }

    @ApiOperation(value="群组在线用户", notes="群组在线用户")
    @GetMapping(value = "/listUser")
    public Result<List<String >> listUser(@Validated @NotNull  @RequestParam(name="groupId") String  groupId){
        Result result = new Result();
        result.setResult(ChannelSupervise.getGroupUserList(groupId));
        result.ok();
        return  result;
    }

    @ApiOperation(value="群组在线数量", notes="群组在线数量")
    @GetMapping(value = "/countUser")
    public Result<Integer> countUser(@Validated @NotNull  @RequestParam(name="groupId") String  groupId){
        Result result = new Result();
        result.setResult(ChannelSupervise.getGroupUserCount(groupId));
        result.ok();
        return  result;
    }



}
