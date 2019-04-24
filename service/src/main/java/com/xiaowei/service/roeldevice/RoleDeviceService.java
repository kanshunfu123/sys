package com.xiaowei.service.roeldevice;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.AclsReq;
import com.xiaowei.common.req.GroupDeviceReq;
import com.xiaowei.common.req.InsertRoleDeviceReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dataobject.RoleAclDO;
import com.xiaowei.mana.mapper.dataobject.RoleDO;
import com.xiaowei.mana.mapper.dataobject.RoleDeviceDO;
import com.xiaowei.mana.mapper.mapper.RoleDeviceMapper;
import com.xiaowei.mana.mapper.mapper.RoleMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Set;

/**
 * Created by MOMO on 2019/1/12.
 */
@Service
@Slf4j
@Transactional
public class RoleDeviceService {
    @Autowired
    private RoleDeviceMapper roleDeviceMapper;
    @Autowired
    private RoleMapper roleMapper;

    public JSONResult changeRoleDevice(InsertRoleDeviceReq insertRoleDeviceReq, RedisUser redisUser) {
        RoleDeviceDO roleDeviceDO = new RoleDeviceDO();
        RoleDO roleDO = roleMapper.getRoleByUUID(insertRoleDeviceReq.getRoleUuid());
        if (null == roleDO) {
            return JSONResult.errorMap("待查询的角色不存在");
        }
        //不允许第三方编辑管理员角色权限
        if (redisUser.getGroupId() != 1&& "0".equals(roleDO.getSysRoleType())) {
            return JSONResult.errorMap("管理员权限不允许修改");
        }
        //根据角色id  得到设备id列表
        List<String> originAclIdList = roleDeviceMapper.getDevicesByRoleId(Lists.<Long>newArrayList(roleDO.getId()));
        //得到前端给的设备id
        List<GroupDeviceReq> aclIdList = insertRoleDeviceReq.getDeviceNos();
        if (!CollectionUtils.isEmpty(aclIdList)) {
            if (originAclIdList.size() == aclIdList.size()) {
                List<String> longs = Lists.newArrayList();
                aclIdList.forEach(aclDO -> {
                    longs.add(aclDO.getSysDeviceNo());
                });
                Set<String> originAclIdSet = Sets.newHashSet(originAclIdList);
                Set<String> aclIdSet = Sets.newHashSet(longs);
                originAclIdSet.removeAll(aclIdSet);
                if (CollectionUtils.isEmpty(originAclIdSet)) {
                    return JSONResult.ok("把设备赋予给角色成功");
                }
            }
        }
        updateRoleAcls(roleDO.getId(), aclIdList, roleDO.getGroupId(), redisUser.getSysUserName());
        return JSONResult.ok("把设备赋予给角色成功");
    }

    @Transactional
    public void updateRoleAcls(Long roleId, List<GroupDeviceReq> aclIdList, Long groupId, String userName) {
        roleDeviceMapper.deleteByRoleId(roleId);

        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }
        List<RoleDeviceDO> roleAclList = Lists.newArrayList();
        List<Long> longs = Lists.newArrayList();
        for (GroupDeviceReq aclId : aclIdList) {
            RoleDeviceDO roleUserDO = new RoleDeviceDO();
            roleUserDO.setGroupId(groupId);
            roleUserDO.setCreateBy(userName);
            roleUserDO.setUpdateBy(userName);
            roleUserDO.setCreateTime(DateUtils.getCurrentDateTime());
            roleUserDO.setUpdateTime(DateUtils.getCurrentDateTime());
            roleUserDO.setSysRoleId(roleId);
            roleUserDO.setDeviceType(aclId.getDeviceType());
            roleUserDO.setDeviceNo(aclId.getSysDeviceNo());
            roleUserDO.setDelFlag("0");
            roleAclList.add(roleUserDO);
        }
        roleDeviceMapper.batchaddRoleUser(roleAclList);
        saveRedisRoleDevice(roleId, longs);
    }

    public void saveRedisRoleDevice(Long roleId, List<Long> longs) {

    }

}
