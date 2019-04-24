package com.xiaowei.web.controller.devicerolebyid;

import com.xiaowei.common.DeviceUserRoleV2Req;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.service.deviceroleById.DeviceRoleByUserIdV2Service;
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
public class DeviceRoleV2Controller {
    @Autowired
    private DeviceRoleByUserIdV2Service deviceRoleByUserIdV2Service;

    /**
     * 根据用户id----->角色----->设备列表
     * @param deviceUserRoleV2Req
     * @return
     */
    @PostMapping("/deviceRoleByUserIdV2/v2")
    public JSONResult deviceRoleByUserIdServiceV2(@Validated(DeviceUserRoleV2Req.Query.class) @RequestBody DeviceUserRoleV2Req deviceUserRoleV2Req) {
        return JSONResult.ok(deviceRoleByUserIdV2Service.getDeviceByUserId(deviceUserRoleV2Req));
    }
}
