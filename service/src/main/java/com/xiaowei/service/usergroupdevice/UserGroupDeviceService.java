package com.xiaowei.service.usergroupdevice;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.AclsReq;
import com.xiaowei.common.req.GroupDeviceReq;
import com.xiaowei.common.req.UserGroupAclReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.RoleDeviceMapper;
import com.xiaowei.mana.mapper.mapper.RoleMapper;
import com.xiaowei.mana.mapper.mapper.UserGroupDeviceMapper;
import com.xiaowei.mana.mapper.mapper.UserGroupMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by MOMO on 2019/1/14.
 */
@Service
public class UserGroupDeviceService {
    @Autowired
    private UserGroupDeviceMapper userGroupDeviceMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RoleDeviceMapper roleDeviceMapper;
    @Autowired
    private RedisUtil redisUtil;

    public JSONResult moveGroupAcl(UserGroupAclReq userGroupAclReq, RedisUser redisUser) {
        if (1 != redisUser.getGroupId()) {
            return JSONResult.errorMap("只有小为科技才可以");
        }
        if (CollectionUtils.isEmpty(userGroupAclReq.getDeviceNos())) {
            return JSONResult.ok("删除设备成功");
        }
        roleDeviceMapper.deleteByGroupId(userGroupAclReq.getGroupId(), userGroupAclReq.getDeviceNos());
        userGroupDeviceMapper.deleteByGroupId(userGroupAclReq.getGroupId(), userGroupAclReq.getDeviceNos());
        return JSONResult.ok("删除设备成功");
    }

    /**
     * 把设备授权给第三方组织 同时吧设备授权给管理员
     */
    public void thirdRoleDevices(Long groupId, List<GroupDeviceReq> sysDeviceId, RedisUser redisUser, RoleDO roleDO) {
        //得到第三方管理员的角色

        List<RoleDeviceDO> roleDeviceDOS = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(sysDeviceId)) {
            sysDeviceId.forEach(groupDeviceReq -> {
                RoleDeviceDO roleDeviceDO = new RoleDeviceDO();
                roleDeviceDO.setCreateBy(redisUser.getSysUserName());
                roleDeviceDO.setUpdateBy(redisUser.getSysUserName());
                roleDeviceDO.setCreateTime(DateUtils.getCurrentDateTime());
                roleDeviceDO.setUpdateTime(DateUtils.getCurrentDateTime());
                roleDeviceDO.setDeviceNo(groupDeviceReq.getSysDeviceNo());
                roleDeviceDO.setDelFlag("0");
                roleDeviceDO.setGroupId(groupId);
                roleDeviceDO.setSysRoleId(roleDO.getId());
                roleDeviceDO.setDeviceType(groupDeviceReq.getDeviceType());
                roleDeviceDOS.add(roleDeviceDO);
            });
        }
        roleDeviceMapper.batchaddRoleUser(roleDeviceDOS);
    }

    /**
     * 第三方设备授权
     *
     * @param userGroupAclReq
     * @param redisUser
     * @return
     */
    public JSONResult changeGroupAcl(UserGroupAclReq userGroupAclReq, RedisUser redisUser) {
        UserGroupDO userGroupAclDO = userGroupMapper.getGroupByUUID(userGroupAclReq.getSysUserGroupUuid());
        if (null == userGroupAclDO) {
            return JSONResult.errorMap("待查询的第三方组不存在");
        }
//        List<String> getDeviceIdByGroupId = userGroupDeviceMapper.getDeviceIdByGroupId(userGroupAclDO.getId());
        //得到前端给的权限点id
        List<GroupDeviceReq> aclIdList = userGroupAclReq.getSysDeviceId();
        RoleDO roleDO = roleMapper.getThirdRoleByParam(userGroupAclDO.getId(), "0", null);
        if (roleDO == null) {
            return JSONResult.errorMap("请先为该组织设置管理员角色");
        }
        thirdRoleDevices(userGroupAclDO.getId(), aclIdList, redisUser, roleDO);
        /*if (!CollectionUtils.isEmpty(aclIdList)) {
            if (getDeviceIdByGroupId.size() == aclIdList.size()) {
                List<String> longs = Lists.newArrayList();
                aclIdList.forEach(aclsReq -> {
                    longs.add(aclsReq.getSysDeviceNo());
                });
                Set<String> originAclIdSet = Sets.newHashSet(getDeviceIdByGroupId);
                Set<String> aclIdSet = Sets.newHashSet(longs);
                originAclIdSet.removeAll(aclIdSet);
                if (CollectionUtils.isEmpty(originAclIdSet)) {
                    return JSONResult.ok("第三方设备授权成功");
                }
            }
        }*/
        updateRoleAcls(userGroupAclDO.getId(), aclIdList, userGroupAclDO.getId(), redisUser.getSysUserName());
        return JSONResult.ok("第三方设备授权成功");
    }

    @Transactional
    public void updateRoleAcls(Long groupPriId, List<GroupDeviceReq> aclIdList, Long groupId, String userName) {
//        userGroupDeviceMapper.deleteByGroupId(groupId);

        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }
        List<UserGroupDeviceDO> roleAclList = Lists.newArrayList();
        for (GroupDeviceReq aclId : aclIdList) {
            UserGroupDeviceDO roleUserDO = new UserGroupDeviceDO();
            roleUserDO.setUuid(StrUtil.genUUID());
            roleUserDO.setCreateBy(userName);
            roleUserDO.setUpdateBy(userName);
            roleUserDO.setCreateTime(DateUtils.getCurrentDateTime());
            roleUserDO.setUpdateTime(DateUtils.getCurrentDateTime());
            roleUserDO.setDelFlag("0");
            roleUserDO.setSysDeviceNo(aclId.getSysDeviceNo());
            roleUserDO.setDeviceType(aclId.getDeviceType());
            roleUserDO.setSysUserGroupId(groupPriId);
            roleUserDO.setSysUserGroupId(groupId);
            roleAclList.add(roleUserDO);
        }
        userGroupDeviceMapper.batchaddRoleUser(roleAclList);
//        saveDeviceAcl(aclIdList, groupId);
    }
}
