package com.xiaowei.web.controller.acl;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.AclReq;
import com.xiaowei.service.aclacl.AclAclService;
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
@RequestMapping("/platform/acl")
@Slf4j
public class AclAclController extends BaseController {
    @Autowired
    private AclAclService aclAclService;

    /**
     * 权限点新增
     * @param aclReq
     * @param userInfo
     * @return
     */
    @PostMapping("/addacl/v1")
    public JSONResult addAcl(@Validated(AclReq.Add.class) @RequestBody AclReq aclReq,
                             @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
           return aclAclService.insertSelective(aclReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，权限点新增失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增权限点失败");
        }
    }

    /**
     * 权限点更新
     * @param aclReq
     * @param userInfo
     * @return
     */
    @PostMapping("/modifyacl/v1")
    public JSONResult modifyacl(@Validated(AclReq.Modify.class) @RequestBody AclReq aclReq,
                             @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return aclAclService.modifySelective(aclReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，权限点更新失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("权限点更新失败");
        }
    }
}
