package com.xiaowei.service.roleuser;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.BatchRoleUserReq;
import com.xiaowei.common.res.redis.RedisRoleUser;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dao.RoleDAO;
import com.xiaowei.mana.mapper.dataobject.RoleDO;
import com.xiaowei.mana.mapper.dataobject.RoleUserDO;
import com.xiaowei.mana.mapper.dataobject.UserDO;
import com.xiaowei.mana.mapper.mapper.RoleMapper;
import com.xiaowei.mana.mapper.mapper.RoleUserMapper;
import com.xiaowei.mana.mapper.mapper.SysUserMapper;
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
public class RoleUserService {
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoleMapper roleMapper;

    /**
     * 把角色赋予给用户
     *
     * @param batchRoleUserReq
     * @param redisUser
     * @return
     */
    public JSONResult changeRoleUsers(BatchRoleUserReq batchRoleUserReq, RedisUser redisUser) {
        UserDO userDO = sysUserMapper.getUserByUUID(batchRoleUserReq.getUserUuid());
        if (null == userDO) {
            return JSONResult.errorMap("待查询的用户不存在");
        }
        List<Long> longs = batchRoleUserReq.getRoleIds();
        if (!CollectionUtils.isEmpty(longs)) {
            RoleDO roleDO = roleMapper.getRoleById(longs.get(0));
            //将管理员权限赋予给用户，只能赋值给一个人
            if ("0".equals(roleDO.getSysRoleType())) {
                int count = roleUserMapper.countAdmin(roleDO.getId(), roleDO.getGroupId());
                if (count >= 1) {
                    return JSONResult.errorMap("该组织的管理员角色已经被赋值给其他用户了");
                }
            }
        }
        //根据用户id 得到角色id列表
        List<Long> originAclIdList = roleUserMapper.getRolesByUserId(userDO.getId());
        //得到前端给的角色id
        List<Long> aclIdList = batchRoleUserReq.getRoleIds();
        if (!CollectionUtils.isEmpty(aclIdList)) {
            if (originAclIdList.size() == aclIdList.size()) {
                Set<Long> originAclIdSet = Sets.newHashSet(originAclIdList);
                Set<Long> aclIdSet = Sets.newHashSet(aclIdList);
                originAclIdSet.removeAll(aclIdSet);
                if (CollectionUtils.isEmpty(originAclIdSet)) {
                    return JSONResult.ok("把角色赋予给用户成功");
                }
            }
        }

        updateRoleAcls(userDO.getId(), aclIdList, userDO.getGroupId(), redisUser.getSysUserName());
        return JSONResult.ok("把角色赋予给用户成功");
    }

    @Transactional
    public void updateRoleAcls(Long userId, List<Long> roleIdList, Long groupId, String userName) {
        roleUserMapper.deleteByUserId(userId);

        if (CollectionUtils.isEmpty(roleIdList)) {
            return;
        }
        List<RoleUserDO> roleAclList = Lists.newArrayList();
        List<Long> redisRoleUsers = Lists.newArrayList();
        for (Long roleId : roleIdList) {
            RoleUserDO roleUserDO = new RoleUserDO();
            roleUserDO.setGroupId(groupId);
            roleUserDO.setRoleId(roleId);
            roleUserDO.setSysRoleUserUuid(StrUtil.genUUID());
            roleUserDO.setCreateBy(userName);
            roleUserDO.setUpdateBy(userName);
            roleUserDO.setCreateTime(DateUtils.getCurrentDateTime());
            roleUserDO.setUpdateTime(DateUtils.getCurrentDateTime());
            roleUserDO.setUserId(userId);
            roleUserDO.setDelFlag("0");
            roleAclList.add(roleUserDO);
            redisRoleUsers.add(roleId);
        }
        if (!CollectionUtils.isEmpty(roleAclList)) {
            roleUserMapper.batchaddRoleUser(roleAclList);
        }
        saveRedisRoleUser(redisRoleUsers, userId);

    }

    private void saveRedisRoleUser(List<Long> redisRoleUsers, Long userId) {
        if (CollectionUtils.isEmpty(redisRoleUsers)) {
            redisUtil.del(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + userId);
            return;
        }
        redisUtil.set(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + userId, JSONObject.toJSONString(redisRoleUsers));
    }
}
