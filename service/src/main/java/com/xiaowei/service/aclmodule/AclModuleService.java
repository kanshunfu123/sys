package com.xiaowei.service.aclmodule;

import com.alibaba.fastjson.JSONObject;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.AddAclModuleReq;
import com.xiaowei.common.req.ModifyAclModuleReq;
import com.xiaowei.common.res.redis.RedisAclModue;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.LevelUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dataobject.AclModuleDO;
import com.xiaowei.mana.mapper.mapper.AclModuleMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MOMO on 2019/1/9.
 */
@Service
@Transactional
public class AclModuleService {
    @Autowired
    private AclModuleMapper aclModuleMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 新增权限模块
     *
     * @param addAclModuleReq
     * @param redisUser
     * @return
     */
    public JSONResult addAclModule(AddAclModuleReq addAclModuleReq, RedisUser redisUser) {
        if (redisUser.getGroupId() != 1) {
            return JSONResult.errorMap("只有小为科技才可以新增权限模块");
        }
        if (checkExist(addAclModuleReq.getSysAclModuleParentId(), addAclModuleReq.getSysAclModuleName(), null)) {
            return JSONResult.errorMap("同一层级下存在相同名称的权限模块");
        }

        AclModuleDO aclModuleDO = new AclModuleDO();
        BeanUtils.copyProperties(addAclModuleReq, aclModuleDO);
        aclModuleDO.setCreateBy(redisUser.getSysUserName());
        aclModuleDO.setUpdateBy(redisUser.getSysUserName());
        aclModuleDO.setCreateTime(DateUtils.getCurrentDateTime());
        aclModuleDO.setUpdateTime(DateUtils.getCurrentDateTime());
        aclModuleDO.setSysAclModuleUuid(StrUtil.genUUID());
        aclModuleDO.setSysAclModuleLevel(LevelUtil.calculateLevel(getLevel(addAclModuleReq.getSysAclModuleParentId()), addAclModuleReq.getSysAclModuleParentId()));
        int i = aclModuleMapper.addAclModule(aclModuleDO);
        if (i > 0) {
            saveRedisAclModue(aclModuleDO,addAclModuleReq.getSysAclPermissionType());
            return JSONResult.ok("新增权限模块成功");
        }
        return JSONResult.ok("新增权限模块失败");
    }

    /**
     * 编辑权限模块
     *
     * @param modifyAclModuleReq
     * @param redisUser
     * @return
     */
    public JSONResult modifyAclModule(ModifyAclModuleReq modifyAclModuleReq, RedisUser redisUser) {
        if (redisUser.getGroupId() != 1) {
            return JSONResult.errorMap("只有小为科技才可以编辑权限模块");
        }
        AclModuleDO before = aclModuleMapper.selectByUUID(modifyAclModuleReq.getSysAclModuleUuid());
        if (null == before) {
            return JSONResult.errorMap("待更新的权限模块不存在");
        }
        if (checkExist(modifyAclModuleReq.getSysAclModuleParentId(), modifyAclModuleReq.getSysAclModuleName(), before.getId())) {
            return JSONResult.errorMap("同一层级下存在相同名称的权限模块");
        }

        AclModuleDO after = new AclModuleDO();
        BeanUtils.copyProperties(modifyAclModuleReq, after);
        after.setSysAclModuleParentId(modifyAclModuleReq.getSysAclModuleParentId());
        after.setId(before.getId());
        after.setCreateBy(redisUser.getSysUserName());
        after.setUpdateBy(redisUser.getSysUserName());
        after.setCreateTime(DateUtils.getCurrentDateTime());
        after.setUpdateTime(DateUtils.getCurrentDateTime());
        after.setSysAclModuleLevel(LevelUtil.calculateLevel(getLevel(modifyAclModuleReq.getSysAclModuleParentId()), modifyAclModuleReq.getSysAclModuleParentId()));
        updateWithChild(before, after);
        return JSONResult.ok("编辑权限模块成功");
    }

    @Transactional
    private void updateWithChild(AclModuleDO before, AclModuleDO after) {
        String newLevelPrefix = after.getSysAclModuleLevel();
        String oldLevelPrefix = before.getSysAclModuleLevel();
        if (!after.getSysAclModuleLevel().equals(before.getSysAclModuleLevel())) {
            List<AclModuleDO> aclModuleList = aclModuleMapper.getChildAclModuleListByLevel(before.getSysAclModuleLevel() + ".");
            if (CollectionUtils.isNotEmpty(aclModuleList)) {
                for (AclModuleDO aclModule : aclModuleList) {
                    String level = aclModule.getSysAclModuleLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        aclModule.setSysAclModuleLevel(level);
                        Long aLong = Long.valueOf(level.substring(level.lastIndexOf(".") + 1));
                        aclModule.setSysAclModuleParentId(aLong);
                    }
                }
                aclModuleMapper.batchUpdateLevel(aclModuleList);
                aclModuleList.forEach(aclModuleDO -> {
                    saveRedisAclModue(aclModuleDO,after.getSysAclPermissionType());
                });
            }
        }
        aclModuleMapper.updateByPrimaryKey(after);
        saveRedisAclModue(after,after.getSysAclPermissionType());

    }


    private boolean checkExist(Long parentId, String aclModuleName, Long deptId) {
        return aclModuleMapper.countByNameAndParentId(parentId, aclModuleName, deptId) > 0;
    }

    private String getLevel(Long aclModuleId) {
        AclModuleDO aclModule = aclModuleMapper.selectByPrimaryKey(aclModuleId);
        if (aclModule == null) {
            return null;
        }
        return aclModule.getSysAclModuleLevel();
    }

    private void saveRedisAclModue(AclModuleDO aclModuleDO,Long moduleId) {
        RedisAclModue redisAclModue=new RedisAclModue();
        redisAclModue.setId(aclModuleDO.getId());
        redisAclModue.setSysAclModuleParentId(aclModuleDO.getSysAclModuleParentId());
        redisAclModue.setDelFlag(aclModuleDO.getDelFlag());
        redisAclModue.setSysAclModuleLevel(aclModuleDO.getSysAclModuleLevel());
        redisAclModue.setSysAclModuleName(aclModuleDO.getSysAclModuleName());
        redisAclModue.setSysAclModuleSeq(aclModuleDO.getSysAclModuleSeq());
        redisAclModue.setSysAclPermissionType(aclModuleDO.getSysAclPermissionType());
        redisAclModue.setSysAclModuleUuid(aclModuleDO.getSysAclModuleUuid());
        redisUtil.set(RedisKeyEnum.REDIS_ACL_MODULE_INFO.getKey()+redisAclModue.getId(), JSONObject.toJSONString(redisAclModue));
        redisUtil.hset(RedisKeyEnum.REDIS_MAP_ACL_MODULE_INFO.getKey()+moduleId,aclModuleDO.getId()+"",JSONObject.toJSONString(redisAclModue));
        redisUtil.hset(RedisKeyEnum.REDIS_MAP_ACL_MODULE_INFO.getKey(),aclModuleDO.getId()+"",JSONObject.toJSONString(redisAclModue));
    }


    public static void main(String[] args) {
        System.out.println("0".substring("0".lastIndexOf(".") + 1));
        System.out.println("0".lastIndexOf("."));
    }

}
