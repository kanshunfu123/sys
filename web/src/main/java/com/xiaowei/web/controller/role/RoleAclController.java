package com.xiaowei.web.controller.role;

/**
 * Created by MOMO on 2019/1/9.
 */

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.BatchRoleAclReq;
import com.xiaowei.common.req.BatchRoleUserReq;
import com.xiaowei.service.roleacl.RoleAclService;
import com.xiaowei.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

@RestController
@RequestMapping("/platform/roleacl")
@Slf4j
public class RoleAclController extends BaseController {
    @Autowired
    private RoleAclService roleAclService;
    /**
     * 把权限点赋予给角色
     * @param batchRoleAclReq
     * @param userInfo
     * @return
     */
    @PostMapping("/changeRoleUsers/v1")
    public JSONResult changeRoleUsers(@Validated(BatchRoleAclReq.Add.class) @RequestBody BatchRoleAclReq batchRoleAclReq,
                                      @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return roleAclService.changeRoleUsers(batchRoleAclReq, this.redisUser(userInfo));

        } catch (UnsupportedEncodingException e) {
            log.error("把权限赋予给角色失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("把权限赋予给角色失败");
        }
    }
}
