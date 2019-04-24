package com.xiaowei.web.controller.role;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.BatchRoleUserReq;
import com.xiaowei.service.roleuser.RoleUserService;
import com.xiaowei.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by MOMO on 2019/1/9.
 */
@RestController
@RequestMapping("/platform/roleuser")
@Slf4j
public class RoleUserController extends BaseController {
    @Autowired
    private RoleUserService roleUserService;

    /**
     * 把角色赋予给用户
     * @param batchRoleUserReq
     * @param userInfo
     * @return
     */
    @PostMapping("/changeRoleUsers/v1")
    public JSONResult changeRoleUsers(@Validated(BatchRoleUserReq.Add.class) @RequestBody BatchRoleUserReq batchRoleUserReq,
                                      @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return roleUserService.changeRoleUsers(batchRoleUserReq, this.redisUser(userInfo));

        } catch (UnsupportedEncodingException e) {
            log.error("把角色赋予给用户失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("把角色赋予给用户失败");
        }
    }
}
