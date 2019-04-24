package com.xiaowei.web.controller.feign;

import com.alibaba.fastjson.JSONObject;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.feign.FeignECbyDeviceNoReq;
import com.xiaowei.service.feign.ECDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MOMO on 2019/1/26.
 */
@RestController
@RequestMapping("/platform/deviceInfo")
@Slf4j
public class ECDeviceController {
    @Autowired
    private ECDeviceService ecDeviceService;

    @PostMapping("/ec/v1")
    public String feignECbyDeviceNo(@Validated(FeignECbyDeviceNoReq.Query.class) @RequestBody FeignECbyDeviceNoReq feignECbyDeviceNoReq) {
        JSONResult jsonResult = ecDeviceService.feignECbyDeviceNo(feignECbyDeviceNoReq);
        String s=JSONObject.toJSONString(jsonResult);
        log.info("电梯feign调用=========={}", s);
        return s;
    }

    @PostMapping("/rw/v1")
    public String feignRWbyDeviceNo(@Validated(FeignECbyDeviceNoReq.Query.class) @RequestBody FeignECbyDeviceNoReq feignECbyDeviceNoReq) {
        JSONResult jsonResult = ecDeviceService.feignRWbyDeviceNo(feignECbyDeviceNoReq);
        String s=JSONObject.toJSONString(jsonResult);
        log.info("地表水feign调用=========={}", s);
        return s;
    }

    @PostMapping("/mh/v1")
    public String feignMHbyDeviceNo(@Validated(FeignECbyDeviceNoReq.Query.class) @RequestBody FeignECbyDeviceNoReq feignECbyDeviceNoReq) {
        JSONResult jsonResult = ecDeviceService.feignMHbyDeviceNo(feignECbyDeviceNoReq);
        String s=JSONObject.toJSONString(jsonResult);
        log.info("井盖feign调用=========={}", s);
        return s;
    }

    @PostMapping("/cw/v1")
    public String feignCWbyDeviceNo(@Validated(FeignECbyDeviceNoReq.Query.class) @RequestBody FeignECbyDeviceNoReq feignECbyDeviceNoReq) {
        JSONResult jsonResult = ecDeviceService.feignCWbyDeviceNo(feignECbyDeviceNoReq);
        String s=JSONObject.toJSONString(jsonResult);
        log.info("生活用水feign调用=========={}", s);
        return s;
    }
}
