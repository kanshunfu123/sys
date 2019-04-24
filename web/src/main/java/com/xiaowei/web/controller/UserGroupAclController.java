package com.xiaowei.web.controller;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.UserGroupAclReq;
import com.xiaowei.service.usergroupacl.UserGroupAclService;
import com.xiaowei.service.usergroupdevice.UserGroupDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by MOMO on 2019/1/14.
 */
@RestController
@RequestMapping("/platform/groupacl")
@Slf4j
public class UserGroupAclController extends BaseController {
    @Autowired
    private UserGroupAclService userGroupAclService;
    @Autowired
    private UserGroupDeviceService userGroupDeviceService;

    @PostMapping("/changeGroupAcl/v1")
    public JSONResult changeGroupAcl(@Validated(UserGroupAclReq.Add.class) @RequestBody UserGroupAclReq userGroupAclReq,
                                     @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return userGroupAclService.changeGroupAcl(userGroupAclReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("第三方权限授权失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("第三方权限授权失败");
        }
    }

    @PostMapping("/moveGroupAcl/v1")
    public JSONResult moveGroupAcl(@Validated(UserGroupAclReq.Delete.class) @RequestBody UserGroupAclReq userGroupAclReq,
                                   @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return userGroupDeviceService.moveGroupAcl(userGroupAclReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("删除组织和设备失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("删除组织和设备失败");
        }
    }

    @PostMapping("/showGroupAcl/v1")
    public JSONResult showGroupAcl(@Validated(UserGroupAclReq.Add.class) @RequestBody UserGroupAclReq userGroupAclReq,
                                   @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return userGroupAclService.showGroupAcl(userGroupAclReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("授权前，查看该第三方组织权限失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("授权前，查看该第三方组织权限失败");
        }
    }


}
