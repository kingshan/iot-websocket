package com.kingshan.iot.push.api.controller;

import com.kingshan.iot.push.api.vo.Dashboard;
import com.kingshan.iot.push.config.StaticConfig;
import com.kingshan.iot.push.netty.supervise.ChannelSupervise;
import com.kingshan.iot.push.utils.DateUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Date;

/**
 * <p>
 *
 * </p>
 *
 * @author: Kingshan
 * @since: 2019/11/7 22:35
 */
@Controller
@RequestMapping
public class WebController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }

    @RequestMapping("/dashboard")
    public String dashboard(Dashboard dashboard) {
        dashboard.setUserCount(ChannelSupervise.getUserCount());
        dashboard.setGroupCount(ChannelSupervise.getGroupCount());
        dashboard.setMessageCount(StaticConfig.messageCount.intValue());
        dashboard.setSystemRunTime(DateUtil.diff(StaticConfig.systemStartDate,new Date()));
        dashboard.setCpu("20%");
        dashboard.setMemery("30%");
        dashboard.setThread("2%");
        return "dashboard";
    }
}
