package com.xiaowei.service.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.RoleTreeReq;
import com.xiaowei.common.res.redis.RedisAcl;
import com.xiaowei.common.res.redis.RedisRole;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dataobject.RoleDO;
import com.xiaowei.mana.mapper.dataobject.SysAcl;
import com.xiaowei.mana.mapper.mapper.AclMapper;
import com.xiaowei.mana.mapper.mapper.RoleAclMapper;
import com.xiaowei.mana.mapper.mapper.RoleMapper;
import com.xiaowei.mana.mapper.mapper.RoleUserMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/1/10.
 */
@Service
public class SysCoreService {
    @Value("${xiaowei.isSuperAdmin}")
    private String isSuperAdmin;
    @Autowired
    private AclMapper aclMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private RoleAclMapper roleAclMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoleMapper roleMapper;

    public List<SysAcl> getCurrentUserAclList(RedisUser redisUser, RoleTreeReq roleTreeReq) {
        return getUserAclList(redisUser, roleTreeReq);
    }

    public List<SysAcl> getUserAclList(RedisUser redisUser, RoleTreeReq roleTreeReq) {
        //是否是超级管理员
        if (isSuperAdmin(redisUser)) {
            Map<Object, Object> map = redisUtil.hmget(RedisKeyEnum.REDIS_MAP_ACL_INFO.getKey());
            List<SysAcl> sysAcls = map.values().stream().map(sysAcl -> JSON.parseObject(sysAcl + "", new TypeReference<SysAcl>() {
            })).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(sysAcls)) {
                return sysAcls;
            } else {
                return aclMapper.getAll(roleTreeReq.getSysAclPermissionType());
            }
        }

        List<Long> userRoleIdList = null;
        //redis-->根据用户id得到角色列表
        Object o = redisUtil.get(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + redisUser.getId());
        if (null != o) {
            userRoleIdList = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
            });
            if (CollectionUtils.isEmpty(userRoleIdList)) {
                return Lists.newArrayList();
            }
            List<String> userRoleIdListStr = userRoleIdList.stream().map(sysAcl -> RedisKeyEnum.REDIS_ROLE_ACL_INFO.getKey() + sysAcl).collect(Collectors.toList());
            //根据角色列表,得到权限点
            List<Object> objects = redisUtil.batchListByKeys(userRoleIdListStr);
            List<String> userAclIdListStr = Lists.newArrayList();
            if (CollectionUtils.isEmpty(objects)) {
                return Lists.newArrayList();
            }
            objects.forEach(o1 -> {
                if (null != o1) {
                    List<Long> longList = JSON.parseObject(o1 + "", new TypeReference<List<Long>>() {
                    });
                    if (!CollectionUtils.isEmpty(longList)) {
                        longList.forEach(aLong -> {
                            userAclIdListStr.add(RedisKeyEnum.REDIS_ACL_ACL_INFO.getKey() + aLong);
                        });

                    }
                }
            });
            //根据 权限点id列表获取权限点列表
            List<Object> acls = redisUtil.batchListByKeys(userAclIdListStr);
            if (CollectionUtils.isEmpty(acls)) {
                return Lists.newArrayList();
            }
            List<SysAcl> sysAcls = Lists.newArrayList();
            acls.forEach(o1 -> {
                if (null != o1) {
                    SysAcl sysAcl = JSON.parseObject(o1 + "", new TypeReference<SysAcl>() {
                    });
                    sysAcls.add(sysAcl);
                }
            });
            return sysAcls;
        } else {
            //根据用户id得到角色列表
            userRoleIdList = roleUserMapper.getRolesByUserId(redisUser.getId());
            if (CollectionUtils.isEmpty(userRoleIdList)) {
                return Lists.newArrayList();
            }
            //根据角色列表,得到权限点
            List<Long> userAclIdList = roleAclMapper.getAclIdListByRoleIdAndPerTypeList(userRoleIdList, roleTreeReq.getSysAclPermissionType());
            if (CollectionUtils.isEmpty(userAclIdList)) {
                return Lists.newArrayList();
            }
            //根据 权限点id列表获取权限点列表
            return aclMapper.getByIdList(userAclIdList);
        }
    }

    public List<SysAcl> getRoleAclList(Long roleId) {
        //根据角色列表,得到权限点
        Object o = redisUtil.get(RedisKeyEnum.REDIS_ROLE_ACL_INFO.getKey() + roleId);
        if (o != null) {
            List<Long> longs = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
            });
            if (CollectionUtils.isEmpty(longs)) {
                return Lists.newArrayList();
            }
            List<String> userAclIdListStr = Lists.newArrayList();
            longs.forEach(o1 -> {
                if (null != o1) {
                    userAclIdListStr.add(RedisKeyEnum.REDIS_ACL_ACL_INFO.getKey() + o1);
                }
            });
            //根据 权限点id列表获取权限点列表
            List<Object> acls = redisUtil.batchListByKeys(userAclIdListStr);
            List<SysAcl> sysAcls = Lists.newArrayList();
            acls.forEach(o1 -> {
                if (null != o1) {
                    SysAcl sysAcl = JSON.parseObject(o1 + "", new TypeReference<SysAcl>() {
                    });
                    sysAcls.add(sysAcl);
                }
            });
            return sysAcls;
        } else {
            //根据角色列表,得到权限点
            List<Long> aclIdList = roleAclMapper.getAclIdListByRoleIdList(Lists.<Long>newArrayList(roleId));
            if (CollectionUtils.isEmpty(aclIdList)) {
                return Lists.newArrayList();
            }
            //根据 权限点id列表获取权限点列表
            return aclMapper.getByIdList(aclIdList);
        }
    }

    public boolean isSuperAdmin(RedisUser redisUser) {
        if (isSuperAdmin.contains(redisUser.getSysUserPhone())) {
            return true;
        }
        return false;
    }
    public boolean hasUrlAcl(String url,RedisUser redisUser) {
        if (isSuperAdmin(redisUser)) {
            return true;
        }
        List<SysAcl> userAclList = getCurrentUserAclList(redisUser,null);
        // 规则：只要有一个权限点有权限，那么我们就认为有访问权限
        for (SysAcl acl : userAclList) {
            // 判断一个用户是否具有某个权限点的访问权限
            //状态 0启用  1禁用
            if (acl == null || "0".equals(acl.getSysAclStatus())) { // 权限点无效
                continue;
            }
            if (url.equals(acl.getSysAclUrl())) {
                return true;
            }
        }
        return false;
    }

}
