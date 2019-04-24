package com.xiaowei.web.controller;

import com.google.common.collect.Lists;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.InsertUserGroupReq;
import com.xiaowei.common.req.PageUserGroupVO;
import com.xiaowei.common.req.UserGroupAclReq;
import com.xiaowei.common.res.SelectSeinRes;
import com.xiaowei.mana.mapper.dataobject.UserGroupDO;
import com.xiaowei.mana.mapper.mapper.UserGroupMapper;
import com.xiaowei.service.usergroup.SysUserGroupService;
import com.xiaowei.service.usergroupdevice.UserGroupDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * Created by MOMO on 2019/1/9.
 */
@RestController
@RequestMapping("/platform/group")
@Slf4j
public class SysUserGroupController extends BaseController {

    @Autowired
    private SysUserGroupService sysUserGroupService;
    @Autowired
    private UserGroupDeviceService userGroupDeviceService;
    @Autowired
    private UserGroupMapper userGroupMapper;

    @PostMapping("/acl/getAll")
    public JSONResult getAll() {
        return sysUserGroupService.getAll();
    }

    @PostMapping("acl/roles")
    public JSONResult roles(@Validated(UserGroupAclReq.Delete.class) @RequestBody UserGroupAclReq userGroupAclReq,
                            @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
        return sysUserGroupService.roles(userGroupAclReq,this.redisUser(userInfo));
    } catch (UnsupportedEncodingException e) {
        log.error("新增小区失败{}====：{}", userInfo, e.getMessage());
        return JSONResult.errorMap("新增小区失败");
    }
    }


    @PostMapping("/selectSein/v1")
    public JSONResult selectSein(@Validated(UserGroupAclReq.select.class) @RequestBody UserGroupAclReq userGroupAclReq) {
        UserGroupDO userGroupDO = userGroupMapper.getGroupByUUID(userGroupAclReq.getSysUserGroupUuid());
        if (null == userGroupDO) {
            return JSONResult.errorMap("带查询的组织不存在");
        }
        String s = userGroupDO.getSysScene();
        String[] strings = s.split(",");
        List<SelectSeinRes> list = Lists.newArrayList();
        for (int i = 0; i < strings.length; i++) {
            SelectSeinRes selectSeinRes = new SelectSeinRes();
            //设备场景 0 地表水 1井盖 2生活用水 3电梯
            if ("0".equals(strings[i])) {
                selectSeinRes.setDeviceType("0");
                selectSeinRes.setName("地表水");
            } else if ("1".equals(strings[i])) {
                selectSeinRes.setDeviceType("1");
                selectSeinRes.setName("井盖");
            } else if ("2".equals(strings[i])) {
                selectSeinRes.setDeviceType("2");
                selectSeinRes.setName("生活用水");
            } else if ("3".equals(strings[i])) {
                selectSeinRes.setDeviceType("3");
                selectSeinRes.setName("电梯");
            }
            list.add(selectSeinRes);
        }
        return JSONResult.ok(list);
    }


    @PostMapping("/pageUserGroup/v1")
    public JSONResult pageUserGroup(@RequestBody PageUserGroupVO pageUserGroupReq) {
        JSONResult jsonResult = sysUserGroupService.pageUserGroup(pageUserGroupReq);
        return jsonResult;
    }

    /**
     * 用户分页列表 下拉框选择各个组
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/usergrouplist/v1")
    public JSONResult getUserGroupsBygId(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return sysUserGroupService.getUserGroupsBygId(this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("获取用户组列表失败：{}===={}", userInfo, e.getMessage());
            return JSONResult.errorMap("获取用户组列表失败");
        }
    }

    /**
     * 第三方设备授权
     *
     * @param userGroupAclReq
     * @param userInfo
     * @return
     */
    @PostMapping("/changeGroupDevice/v1")
    public JSONResult changeGroupAcl(@Validated(UserGroupAclReq.Add.class) @RequestBody UserGroupAclReq userGroupAclReq,
                                     @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return userGroupDeviceService.changeGroupAcl(userGroupAclReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("第三方设备授权失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("第三方设备授权失败");
        }
    }

    @PostMapping("/insertUserGroup/v1")
    public JSONResult insertUserGroup(@Validated(InsertUserGroupReq.Add.class) @RequestBody InsertUserGroupReq insertUserGroupReq,
                                      @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return sysUserGroupService.insertUserGroup(insertUserGroupReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("新增第三方组织失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增第三方组织失败");
        }
    }

    @PostMapping("/modify/v1")
    public JSONResult modify(@Validated(InsertUserGroupReq.Modify.class) @RequestBody InsertUserGroupReq insertUserGroupReq,
                             @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return sysUserGroupService.modify(insertUserGroupReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("更新第三方组织失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("更新第三方组织失败");
        }
    }

    @PostMapping("/uuid/v1")
    public JSONResult uuid(@Validated(InsertUserGroupReq.Detail.class) @RequestBody InsertUserGroupReq insertUserGroupReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        return sysUserGroupService.uuid(insertUserGroupReq);
    }
}
