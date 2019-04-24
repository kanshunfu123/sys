package com.xiaowei.web.controller.areatodevice;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MOMO on 2019/1/27.
 */
@RestController
@RequestMapping("/platform/tongBuDevices")
@Slf4j
public class TongBuDevices {
    @Autowired
    private RedisUtil redisUtil;

    public JSONResult tongBuDevices() {
        return JSONResult.ok();
    }
}
