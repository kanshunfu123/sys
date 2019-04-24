package com.xiaowei.web.controller.role;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.AddRoleReq;
import com.xiaowei.common.req.ModifyRoleReq;
import com.xiaowei.common.req.PageRoleReq;
import com.xiaowei.common.req.RoleTreeReq;
import com.xiaowei.service.core.RoleTreeService;
import com.xiaowei.service.role.RoleService;
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
@Slf4j
@RequestMapping("/platform/role")
public class RoleController extends BaseController {
    @Autowired
    private RoleService roleService;
    @Autowired
    private RoleTreeService roleTree;

    @PostMapping("/pageRole/v1")
    public JSONResult pageRole(@RequestBody PageRoleReq pageRoleReq,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return roleService.pageRole(pageRoleReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("角色分页查询失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("角色分页查询失败");
        }
    }

    /**
     * 为角色授权前，进行数据展示
     *
     * @param roleTreeReq
     * @param userInfo
     * @return
     */
    @PostMapping("/roleTree/v1")
    public JSONResult roleTree(@Validated(RoleTreeReq.Query.class) @RequestBody RoleTreeReq roleTreeReq,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return roleTree.roleTree(roleTreeReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("为角色授权前，进行数据展示失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("获取当前角色权限失败");
        }
    }

    /**
     * 新增角色
     *
     * @param addRole
     * @param userInfo
     * @return
     */
    @PostMapping("/addRole/v1")
    public JSONResult addRole(@Validated(AddRoleReq.Add.class) @RequestBody AddRoleReq addRole,
                              @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return roleService.addRole(addRole, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，角色新增失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("角色新增失败");
        }
    }

    /**
     * 角色编辑
     *
     * @param modifyRoleReq
     * @param userInfo
     * @return
     */
    @PostMapping("/modifyRole/v1")
    public JSONResult modifyRole(@Validated(ModifyRoleReq.Modify.class) @RequestBody ModifyRoleReq modifyRoleReq,
                                 @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return roleService.modifyRole(modifyRoleReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，角色编辑失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("角色编辑失败");
        }
    }

    /**
     * 角色下拉框加载
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/roleSelect/v1")
    public JSONResult roleSelect(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return roleService.roleSelect(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，角色下拉框加载失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("角色下拉框加载失败");
        }
    }
}
