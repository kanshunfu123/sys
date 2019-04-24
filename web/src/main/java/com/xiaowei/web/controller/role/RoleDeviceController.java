package com.xiaowei.web.controller.role;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.InsertRoleDeviceReq;
import com.xiaowei.service.roeldevice.RoleDeviceService;
import com.xiaowei.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by MOMO on 2019/1/14.
 */
@RestController
@RequestMapping("/platform/roleDevice")
@Slf4j
public class RoleDeviceController extends BaseController {
    @Autowired
    private RoleDeviceService roleDeviceService;

    /**
     * 把设备赋予给角色
     * @param insertRoleDeviceReq
     * @param userInfo
     * @return
     */
    @PostMapping("/changeRoleDevice/v1")
    public JSONResult changeRoleDevice(@Validated(InsertRoleDeviceReq.Add.class) @RequestBody InsertRoleDeviceReq insertRoleDeviceReq,
                                       @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return roleDeviceService.changeRoleDevice(insertRoleDeviceReq, this.redisUser(userInfo));

        } catch (UnsupportedEncodingException e) {
            log.error("把设备赋予给角色失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("把设备赋予给角色失败");
        }
    }

}
