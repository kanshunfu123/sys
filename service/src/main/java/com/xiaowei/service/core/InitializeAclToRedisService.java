package com.xiaowei.service.core;

import com.alibaba.fastjson.JSONObject;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MOMO on 2019/1/14.
 */
@Service
public class InitializeAclToRedisService {
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private RoleAclMapper roleAclMapper;
    @Autowired
    private AclMapper aclMapper;
    @Autowired
    private AclModuleMapper aclModuleMapper;
    @Autowired
    private UserGroupAclMapper userGroupAclMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;


    public JSONResult initializeAclRedis() {
        //得到所有用户
        List<UserDO> userDOList = sysUserMapper.getAll();
        userDOList.forEach(userDO -> {
            RedisUser redisUser = RedisUser.builder().sysUserEmail(userDO.getSysUserEmail()).sysUserLoginName(userDO.getSysUserLoginName())
                    .sysUserName(userDO.getSysUserName()).sysUserPhone(userDO.getSysUserPhone()).deptId(userDO.getDeptId()).groupId(userDO.getGroupId())
                    .id(userDO.getId()).isForbidden(userDO.getIsForbidden()).roleType(userDO.getRoleType()).build();
            //用户放入redis，实时刷新
            redisUtil.set(RedisKeyEnum.REDIS_USER_INFO.getKey() + userDO.getId(), JSONObject.toJSONString(redisUser));
            //用户id得到角色id列表
            List<Long> longs = roleUserMapper.getRolesByUserId(userDO.getId());
            if (!CollectionUtils.isEmpty(longs)) {
                redisUtil.set(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + userDO.getId(), JSONObject.toJSONString(longs));
            }

        });
        //得到所有角色
        List<RoleDO> roleDOS = roleMapper.getAllRoles();
        roleDOS.forEach(roleDO -> {
            //角色信息放入redis缓存，实时刷新
            redisUtil.set(RedisKeyEnum.REDIS_ROLE_INFO.getKey() + roleDO.getId(), JSONObject.toJSONString(roleDO));
            //一个角色拥有多个权限点
            List<Long> longs = roleAclMapper.getRolesByUserId(roleDO.getId());
            if (!CollectionUtils.isEmpty(longs)) {
                redisUtil.set(RedisKeyEnum.REDIS_ROLE_ACL_INFO.getKey() + roleDO.getId(), JSONObject.toJSONString(longs));
            }

        });
        //得到所有权限点
        List<SysAcl> sysAcls = aclMapper.getAll(null);
        sysAcls.forEach(sysAcl -> {
            redisUtil.set(RedisKeyEnum.REDIS_ACL_ACL_INFO.getKey() + sysAcl.getId(), JSONObject.toJSONString(sysAcl));
            redisUtil.hset(RedisKeyEnum.REDIS_MAP_ACL_INFO.getKey() + sysAcl.getSysAclPermissionType(), sysAcl.getId() + "", JSONObject.toJSONString(sysAcl));
            redisUtil.hset(RedisKeyEnum.REDIS_MAP_ACL_INFO.getKey(), sysAcl.getId() + "", JSONObject.toJSONString(sysAcl));
        });
        //得到所有权限模块
        List<AclModuleDO> aclModuleDOS = aclModuleMapper.getAllAclModule();
        aclModuleDOS.forEach(aclModuleDO -> {
            redisUtil.set(RedisKeyEnum.REDIS_ACL_MODULE_INFO.getKey() + aclModuleDO.getId(), JSONObject.toJSONString(aclModuleDO));
            redisUtil.hset(RedisKeyEnum.REDIS_MAP_ACL_MODULE_INFO.getKey()+aclModuleDO.getSysAclPermissionType(),aclModuleDO.getId()+"",JSONObject.toJSONString(aclModuleDO));
            redisUtil.hset(RedisKeyEnum.REDIS_MAP_ACL_MODULE_INFO.getKey(),aclModuleDO.getId()+"",JSONObject.toJSONString(aclModuleDO));
        });
        //得到所有组
        List<UserGroupDO> userGroupDOS = userGroupMapper.getUserGroupBygId(null, null);
        userGroupDOS.forEach(userGroupDO -> {
            //根据组的id得到改组的权限点列表
            List<Long> longs = userGroupAclMapper.getDeviceIdByGroupId(userGroupDO.getId());
            if (!CollectionUtils.isEmpty(longs)) {
                redisUtil.set(RedisKeyEnum.REDIS_GROUP_ACL_INFO.getKey() + userGroupDO.getId(), JSONObject.toJSONString(longs));
            }
        });
        return JSONResult.ok("刷新redis权限成功");

    }
}
