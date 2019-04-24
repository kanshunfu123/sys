package com.xiaowei.web.controller.acl;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.AddAclModuleReq;
import com.xiaowei.common.req.ModifyAclModuleReq;
import com.xiaowei.service.aclmodule.AclModuleService;
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
@RequestMapping("/platform/aclmodule")
@Slf4j
public class AclModuleController extends BaseController {
    @Autowired
    private AclModuleService aclModuleService;

    /**
     * 权限模块新增
     * @param addAclModuleReq
     * @param userInfo
     * @return
     */
    @PostMapping("/addAclModule/v1")
    public JSONResult addAclModule(@Validated(AddAclModuleReq.Add.class) @RequestBody AddAclModuleReq addAclModuleReq,
                                   @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return aclModuleService.addAclModule(addAclModuleReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，权限模块新增失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("权限模块新增失败");
        }
    }

    /**
     * 权限模块编辑
     * @param modifyAclModuleReq
     * @param userInfo
     * @return
     */
    @PostMapping("/modifyAclModule/v1")
    public JSONResult modifyAclModule(@Validated(ModifyAclModuleReq.Modify.class) @RequestBody ModifyAclModuleReq modifyAclModuleReq,
                                   @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return aclModuleService.modifyAclModule(modifyAclModuleReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，权限模块编辑失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("权限模块编辑失败");
        }
    }
}
