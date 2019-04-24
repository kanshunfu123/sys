package com.xiaowei.web.controller.devicerolebyid;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.DeviceUserRoleReq;
import com.xiaowei.common.res.DeviceRoleRea;
import com.xiaowei.service.deviceroleById.DeviceRoleByUserIdService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MOMO on 2019/1/14.
 */
@RequestMapping("/platform/device")
@RestController
@Slf4j
public class DeviceRoleController {
    @Autowired
    private DeviceRoleByUserIdService deviceRoleByUserIdService;

    /**
     * 根据用户id----->角色----->设备列表
     * @param deviceUserRoleReq
     * @return
     */
    @PostMapping("/deviceRoleByUserId/v1")
    public JSONResult deviceRoleByUserIdService(@Validated(DeviceUserRoleReq.Query.class) @RequestBody DeviceUserRoleReq deviceUserRoleReq) {
        return JSONResult.ok(deviceRoleByUserIdService.getDeviceByUserId(deviceUserRoleReq));
    }
}
