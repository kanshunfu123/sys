package com.xiaowei.service.roleacl;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.AclsReq;
import com.xiaowei.common.req.BatchRoleAclReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dataobject.RoleAclDO;
import com.xiaowei.mana.mapper.dataobject.RoleDO;
import com.xiaowei.mana.mapper.mapper.RoleAclMapper;
import com.xiaowei.mana.mapper.mapper.RoleMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by MOMO on 2019/1/9.
 */
@Service
@Transactional
public class RoleAclService {
    @Autowired
    private RoleAclMapper roleAclMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisUtil redisUtil;

    public JSONResult changeRoleUsers(BatchRoleAclReq batchRoleAclReq, RedisUser redisUser) {
        RoleDO roleDO = roleMapper.getRoleByUUID(batchRoleAclReq.getRoleUuid());
        if (null == roleDO) {
            return JSONResult.errorMap("待查询的角色不存在");
        }
        //不允许第三方编辑管理员角色权限
        if (redisUser.getGroupId() != 1 && "0".equals(roleDO.getSysRoleType())) {
            return JSONResult.errorMap("管理员权限不允许修改");
        }
        //根据角色id 得到权限点id列表
        List<Long> originAclIdList = roleAclMapper.getRolesByUserId(roleDO.getId());
        //得到前端给的权限点id
        List<AclsReq> aclIdList = batchRoleAclReq.getAclIds();
        if (!CollectionUtils.isEmpty(aclIdList)) {
            if (originAclIdList.size() == aclIdList.size()) {
                List<Long> longs = Lists.newArrayList();
                aclIdList.forEach(aclDO -> {
                    longs.add(aclDO.getAclId());
                });
                Set<Long> originAclIdSet = Sets.newHashSet(originAclIdList);
                Set<Long> aclIdSet = Sets.newHashSet(longs);
                originAclIdSet.removeAll(aclIdSet);
                if (CollectionUtils.isEmpty(originAclIdSet)) {
                    return JSONResult.ok("把权限点赋予给角色成功");
                }
            }
        }
        updateRoleAcls(roleDO.getId(), aclIdList, roleDO.getGroupId(), redisUser.getSysUserName());
        return JSONResult.ok("把权限点赋予给角色成功");
    }

    @Transactional
    public void updateRoleAcls(Long roleId, List<AclsReq> aclIdList, Long groupId, String userName) {
        roleAclMapper.deleteByUserId(roleId);

        List<RoleAclDO> roleAclList = Lists.newArrayList();
        List<Long> longs = Lists.newArrayList();
        for (AclsReq aclId : aclIdList) {
            RoleAclDO roleUserDO = new RoleAclDO();
            roleUserDO.setGroupId(groupId);
            roleUserDO.setSysRoleAclUuid(StrUtil.genUUID());
            roleUserDO.setCreateBy(userName);
            roleUserDO.setUpdateBy(userName);
            roleUserDO.setCreateTime(DateUtils.getCurrentDateTime());
            roleUserDO.setUpdateTime(DateUtils.getCurrentDateTime());
            roleUserDO.setSysRoleId(roleId);
            roleUserDO.setSysAclId(aclId.getAclId());
            roleUserDO.setSysAclPermissionType(aclId.getSysAclPermissionType());
            roleUserDO.setDelFlag("0");
            roleAclList.add(roleUserDO);
            longs.add(aclId.getAclId());
        }
        if (!CollectionUtils.isEmpty(roleAclList)) {
            roleAclMapper.batchaddRoleUser(roleAclList);
        }
        saveRoleAcl(longs, roleId);

    }

    public void saveRoleAcl(List<Long> longs, Long roleId) {
        if (CollectionUtils.isEmpty(longs)) {
            redisUtil.del(RedisKeyEnum.REDIS_ROLE_ACL_INFO.getKey() + roleId, JSONObject.toJSONString(Lists.newArrayList()));
            return;
        }
        redisUtil.set(RedisKeyEnum.REDIS_ROLE_ACL_INFO.getKey() + roleId, JSONObject.toJSONString(longs));
    }
}
