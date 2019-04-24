package com.xiaowei.web.controller.home;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.home.AreaV2Req;
import com.xiaowei.common.req.home.HomeOneReq;
import com.xiaowei.service.home.HomeV2Service;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * created by 韩金群 2019/2/21
 */
@RequestMapping("/platform/home")
@RestController
@Slf4j
public class HomeV2Controller {
    @Autowired
    private HomeV2Service homeV2Service;

    /**
     * 统计总台数和运行总时长
     */
    @PostMapping("/homeTotalV2/v2")
    public JSONResult homeTotalV2(@RequestBody AreaV2Req req) {
        return homeV2Service.homeTotalV2(req);
    }

    /**
     * 统计单个场景总台数和运行总时长
     */
    @PostMapping("/homeTotalOneV2/v2")
    public JSONResult homeTotalOneV2(@RequestBody HomeOneReq req) {
        return homeV2Service.homeTotalOneV2(req);
    }
    /**
     * 统计单个场景设备列表
     */
    @PostMapping("/homeDeviceOneV2/v2")
    public JSONResult homeDeviceOneV2(@RequestBody HomeOneReq req) {
        return homeV2Service.homeDeviceOneV2(req);
    }

    /**
     * 统计各个场景的设备数量
     */
    @PostMapping("/totalSizeV2/v2")
    public JSONResult totalSizeV2(@RequestBody AreaV2Req req) {
        return homeV2Service.totalSizeV2(req);
    }
}
