package com.xiaowei.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.*;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.JwtTokenUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.Req.UserPageReq;
import com.xiaowei.mana.mapper.dataobject.UserGroupDO;
import com.xiaowei.mana.mapper.mapper.SysUserMapper;
import com.xiaowei.service.core.SysCoreService;
import com.xiaowei.service.core.UserAclTreeService;
import com.xiaowei.service.role.RoleService;
import com.xiaowei.service.sysuser.SysUserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * Created by MOMO on 2019/1/7.
 */
@RestController
@RequestMapping("/platform/user")
@Slf4j
public class SysUserController extends BaseController {
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private UserAclTreeService userAclTreeService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private SysCoreService sysCoreService;
    @Autowired
    private SysUserMapper sysUserMapper;

    /**
     * 用户登录
     *
     * @param sysUserReq
     * @param response
     * @return
     */
    @PostMapping("/login/v1")
    public JSONResult userLogin(@Validated(SysUserReq.Query.class) @RequestBody SysUserReq sysUserReq, HttpServletRequest request, HttpServletResponse response) {
        return sysUserService.userLogin(sysUserReq, request, response);
    }

    /**
     * 用户退出
     *
     * @param token
     * @param userInfo
     * @return
     */
    @PostMapping("/logout/v1")
    public JSONResult userLogOut(@RequestHeader(value = "token", required = false) String token,
                                 @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            redisUtil.del(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + token);
            return JSONResult.ok("退出成功");
        } catch (Exception e) {
            e.getMessage();
            log.error("token解析错误:{}，用户退出失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("退出失败");
        }
    }

    @PostMapping("/acls/v1")
    public JSONResult userAclTree(@Validated(RoleTreeReq.Permission.class) @RequestBody RoleTreeReq roleTreeReq,
                                  @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            Map<String, Object> map = Maps.newHashMap();
            RedisUser redisUser= redisUser(userInfo);
            map.put("acls", userAclTreeService.userAclTree(roleTreeReq,redisUser));
            map.put("roles", roleService.roleType(redisUser(userInfo)));
            UserGroupDO userGroupDO = sysUserMapper.getUserGroupByUserId(redisUser.getGroupId());
            String s = userGroupDO.getSysScene();
            if (StringUtils.isNotBlank(s)) {
                String[] strings = s.split(",");
                map.put("scenes",Arrays.asList(strings));
            }else {
                map.put("scenes",new ArrayList<String>());
            }
            map.put("groupId",userGroupDO.getId());
            return JSONResult.ok(map);
        } catch (UnsupportedEncodingException e) {
            log.error("拦截用户权限失败{}===：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("拦截用户权限异常");
        }
    }

    @PostMapping("/hasUrlAcl/v1")
    public JSONResult hasUrlAcl(@RequestBody HasUrlAclReq hasUrlAclReq) {
        try {
            log.info("开始权限认证");
            boolean hasUrlAcl = sysCoreService.hasUrlAcl(hasUrlAclReq.getUrl(), redisUser(hasUrlAclReq.getUserInfo()));
            if (hasUrlAcl){
                return JSONResult.ok();
            }else{
                return JSONResult.errorException(HttpStatus.FORBIDDEN.value(),"","无权限访问本系统资源");
            }
        } catch (UnsupportedEncodingException e) {
            log.error("权限拦截异常{}===：{}", JSONObject.toJSONString(hasUrlAclReq),e.getMessage());
            return JSONResult.errorMap("权限拦截失败,您无权访问");
        }
    }

    /**
     * 新增用户
     *
     * @param inserUserReq
     * @param userInfo
     * @return
     */
    @PostMapping("/useradd/v1")
    public JSONResult insertUser(@Validated(InserUserReq.Add.class) @RequestBody InserUserReq inserUserReq,
                                 @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return sysUserService.insertUser(inserUserReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("新增用户失败{}===：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增用户失败");
        }
    }

    /**
     * 用户编辑
     *
     * @param modifyUserReq
     * @param userInfo
     * @return
     */
    @PostMapping("/useredit/v1")
    public JSONResult editUser(@Validated(ModifyUserReq.Modify.class) @RequestBody ModifyUserReq modifyUserReq,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return sysUserService.editUser(modifyUserReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("编辑用户失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑用户失败");
        }
    }

    /**
     * 用户删除
     *
     * @param modifyUserReq
     * @param userInfo
     * @return
     */
    @PostMapping("/userdel/v1")
    public JSONResult delUser(@Validated(ModifyUserReq.Delete.class) @RequestBody ModifyUserReq modifyUserReq,
                              @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {

            return sysUserService.delUser(modifyUserReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("删除用户失败:{}=====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("删除用户失败");
        }
    }

    /**
     * 禁用用户
     *
     * @param modifyUserReq
     * @param userInfo
     * @return
     */
    @PostMapping("/forbUser/v1")
    public JSONResult forbUser(@Validated(ModifyUserReq.Status.class) @RequestBody ModifyUserReq modifyUserReq,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {

            return sysUserService.forbUser(modifyUserReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("禁用户失败:{}=====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("禁用用户失败");
        }
    }

    /**
     * 用户分页查询
     *
     * @param userPageReq
     * @param userInfo
     * @return
     */
    @PostMapping("/userpage/v1")
    public JSONResult userPage(@RequestBody UserPageReq userPageReq,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return sysUserService.userPage(userPageReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("用户分页失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("用户分页失败");
        }
    }

    /**
     * 根据uuid查询用户
     *
     * @param modifyUserReq
     * @return
     */
    @PostMapping("/useruuid/v1")
    public JSONResult getUserByUUID(@Validated(ModifyUserReq.Query.class) @RequestBody ModifyUserReq modifyUserReq) {
        return sysUserService.getUserByUUID(modifyUserReq);
    }

}
