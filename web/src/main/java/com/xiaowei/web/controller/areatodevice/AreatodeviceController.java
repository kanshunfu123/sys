package com.xiaowei.web.controller.areatodevice;

import com.alibaba.fastjson.JSONObject;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.AreaToDeviceReq;
import com.xiaowei.common.req.AreaToDeviceReq_v1;
import com.xiaowei.service.areatodevice.AreaToDevice;
import com.xiaowei.service.areatodevice.AreaToDevice_v1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MOMO on 2019/1/18.
 */
@RestController
@RequestMapping("/platform/selectDevice")
@Slf4j
public class AreatodeviceController {
    @Autowired
    private AreaToDevice areaToDevice;
    @Autowired
    private AreaToDevice_v1 areaToDevice_v1;

    /**
     * 地表水
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/rw")
    public JSONResult rw(@Validated(AreaToDeviceReq.Query.class) @RequestBody AreaToDeviceReq areaToDeviceReq) {
        return JSONResult.ok(areaToDevice.rw(areaToDeviceReq));
    }

    /**
     * 井盖
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/mh")
    public JSONResult mh(@Validated(AreaToDeviceReq.Query.class) @RequestBody AreaToDeviceReq areaToDeviceReq) {
        return JSONResult.ok(areaToDevice.mh(areaToDeviceReq));
    }

    /**
     * 电梯
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/ec")
    public JSONResult ec(@Validated(AreaToDeviceReq.Query.class) @RequestBody AreaToDeviceReq areaToDeviceReq) {
        return JSONResult.ok(areaToDevice.ec(areaToDeviceReq));
    }

    /**
     * 电梯
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/cw")
    public JSONResult cw(@Validated(AreaToDeviceReq.Query.class) @RequestBody AreaToDeviceReq areaToDeviceReq) {
        return JSONResult.ok(areaToDevice.cw(areaToDeviceReq));
    }


    /**
     * 地表水
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/rw/v1")
    public JSONResult rw_v1(@Validated(AreaToDeviceReq_v1.Query.class) @RequestBody AreaToDeviceReq_v1 areaToDeviceReq) {
        JSONResult jsonResult = JSONResult.ok(areaToDevice_v1.rw(areaToDeviceReq));
        log.info("/rw/v1============={}", JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }


    /**
     * 井盖
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/mh/v1")
    public JSONResult mh_v1(@Validated(AreaToDeviceReq_v1.Query.class) @RequestBody AreaToDeviceReq_v1 areaToDeviceReq) {
        JSONResult jsonResult = JSONResult.ok(areaToDevice_v1.mh(areaToDeviceReq));
        log.info("/mh/v1==={}", JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 电梯
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/ec/v1")
    public JSONResult ec_v1(@Validated(AreaToDeviceReq_v1.Query.class) @RequestBody AreaToDeviceReq_v1 areaToDeviceReq) {
        JSONResult jsonResult = JSONResult.ok(areaToDevice_v1.ec(areaToDeviceReq));
        log.info("/ec/v1==={}", JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }

    /**
     * 电梯
     *
     * @param areaToDeviceReq
     * @return
     */
    @PostMapping("/cw/v1")
    public JSONResult cw_v1(@Validated(AreaToDeviceReq_v1.Query.class) @RequestBody AreaToDeviceReq_v1 areaToDeviceReq) {

        JSONResult jsonResult = JSONResult.ok(areaToDevice_v1.cw(areaToDeviceReq));
        log.info("//cw/v1==={}", JSONObject.toJSONString(jsonResult));
        return jsonResult;
    }
}
