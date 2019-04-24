package com.xiaowei.web.controller.home;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.DeviceUserRoleReq;
import com.xiaowei.common.req.home.AccessDeviceReq;
import com.xiaowei.common.req.home.AccessReq;
import com.xiaowei.common.req.home.AreaV2Req;
import com.xiaowei.common.req.home.HomeOneReq;
import com.xiaowei.service.home.HomeService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/16
 */
@RequestMapping("/platform/home")
@RestController
@Slf4j
public class HomeController {
    @Autowired
    private HomeService homeService;

    /**
     * 统计总台数和运行总时长
     */
    @PostMapping("/homeTotal/v1")
    public JSONResult homeTotal() {
        return homeService.homeTotal();
    }

    /**
     * 统计单个场景总台数和运行总时长
     */
    @PostMapping("/homeTotalOne/v1")
    public JSONResult homeTotalOne(@RequestBody HomeOneReq req) {
        return homeService.homeTotalOne(req.getType());
    }

    /**
     * 最新接入设备
     */
    @PostMapping("/latestDevice/v1")
    public JSONResult latestDevice() {
        return homeService.latestDevice();
    }

    /**
     * 查询电梯的设备编号集合
     */
    @PostMapping("/deviceNoListEc/v1")
    public JSONResult deviceNoListEc() {
        return homeService.deviceNoListEc();
    }

    /**
     * 查询井盖的全部设备
     */
    @PostMapping("/deviceNoListMh/v1")
    public JSONResult deviceNoListMh() {
        return homeService.deviceNoListMh();
    }

    /**
     * 查询地表水的全部设备
     */
    @PostMapping("/deviceNoListRw/v1")
    public JSONResult deviceNoListRw() {
        return homeService.deviceNoListRw();
    }

    /**
     * 查询饮用水的全部设备
     */
    @PostMapping("/deviceNoListCw/v1")
    public JSONResult deviceNoListCw() {
        return homeService.deviceNoListCw();
    }

    /**
     * 查询设备数量
     */
    @PostMapping("/totalSize/v1")
    public JSONResult totalSize() {
        return homeService.totalSize();
    }

    /**
     * 本年接入设备趋势图
     */
    @PostMapping("/accessDeviceV2/v2")
    public JSONResult accessDeviceV2(@RequestBody AccessReq accessReq) {
        return homeService.accessDevice(accessReq);
    }

}
