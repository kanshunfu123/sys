package com.xiaowei.service.aclacl;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.google.common.collect.Maps;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.AclReq;
import com.xiaowei.common.res.redis.RedisAcl;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dataobject.AclDO;
import com.xiaowei.mana.mapper.mapper.AclMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

/**
 * Created by MOMO on 2019/1/9.
 */
@Service
@Transactional
public class AclAclService {
    @Autowired
    private AclMapper aclMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 新增权限点
     *
     * @param aclReq
     * @param redisUser
     * @return
     */
    public JSONResult insertSelective(AclReq aclReq, RedisUser redisUser) {
        if (redisUser.getGroupId() != 1) {
            return JSONResult.errorMap("只允许小为科技添加权限点");
        }
        int url = aclMapper.uniqueness(aclReq.getSysAclUrl(), null, null, null, null);
        if (url > 0) {
            return JSONResult.errorMap("请求的url重复");
        }
        /*int aclName = aclMapper.uniqueness(null, aclReq.getSysAclName(), null, null, null);
        if (aclName > 0) {
            return JSONResult.errorMap("权限名称重复");
        }
        int action = aclMapper.uniqueness(null, null, aclReq.getSysAclAction(), null, null);
        if (action > 0) {
            return JSONResult.errorMap("按钮动作类型重复");
        }
        int router = aclMapper.uniqueness(null, null, null, aclReq.getSysAclRouter(), null);
        if (router > 0) {
            return JSONResult.errorMap("所属页面重复");
        }*/
        AclDO aclDO = new AclDO();
        BeanUtils.copyProperties(aclReq, aclDO);
        //状态 0启用  1禁用.
        BeanUtils.copyProperties(aclReq, aclDO);
        if ("0".equals(aclReq.getSysAclStatus())) {
            aclDO.setSysAclStatus("1");
        } else {
            aclDO.setSysAclStatus("0");
        }
        aclDO.setCreateBy(redisUser.getSysUserName());
        aclDO.setUpdateBy(redisUser.getSysUserName());
        aclDO.setCreateTime(DateUtils.getCurrentDateTime());
        aclDO.setUpdateTime(DateUtils.getCurrentDateTime());
        aclDO.setSysAclUuid(StrUtil.genUUID());
        int i = aclMapper.insertSelective(aclDO);
        if (i > 0) {
            saveRedisAcl(aclDO,aclReq.getSysAclPermissionType());
            return JSONResult.ok("添加权限点成功");
        }
        return JSONResult.ok("添加权限点失败");
    }

    /**
     * 编辑权限点
     *
     * @param aclReq
     * @param redisUser
     * @return
     */
    public JSONResult modifySelective(AclReq aclReq, RedisUser redisUser) {
        AclDO aclDOa = aclMapper.getAclByUUID(aclReq.getSysAclUuid());
        if (aclDOa == null) {
            return JSONResult.errorMap("待查询的权限点不存在");
        }
        if (redisUser.getGroupId() != 1) {
            return JSONResult.errorMap("只允许小为科技编辑权限点");
        }
        int url = aclMapper.uniqueness(aclReq.getSysAclUrl(), null, null, null, aclDOa.getId());
        if (url > 0) {
            return JSONResult.errorMap("请求的url重复");
        }
        /*int aclName = aclMapper.uniqueness(null, aclReq.getSysAclName(), null, null, null);
        if (aclName > 0) {
            return JSONResult.errorMap("权限名称重复");
        }
        int action = aclMapper.uniqueness(null, null, aclReq.getSysAclAction(), null, null);
        if (action > 0) {
            return JSONResult.errorMap("按钮动作类型重复");
        }
        int router = aclMapper.uniqueness(null, null, null, aclReq.getSysAclRouter(), null);
        if (router > 0) {
            return JSONResult.errorMap("所属页面重复");
        }*/
        AclDO aclDO = new AclDO();
        //状态 0启用  1禁用.
        BeanUtils.copyProperties(aclReq, aclDO);

        aclDO.setId(aclDOa.getId());
        aclDO.setCreateBy(redisUser.getSysUserName());
        aclDO.setUpdateBy(redisUser.getSysUserName());
        aclDO.setCreateTime(DateUtils.getCurrentDateTime());
        aclDO.setUpdateTime(DateUtils.getCurrentDateTime());
        int i = aclMapper.updateByPrimaryKeySelective(aclDO);
        if (i > 0) {
            saveRedisAcl(aclDO,aclReq.getSysAclPermissionType());
            return JSONResult.ok("编辑权限点成功");
        }
        return JSONResult.ok("编辑权限点失败");
    }

    public void addRedisMapAcl() {
//        redisUtil.hmset(RedisKeyEnum.REDIS_MAP_ACL_INFO.getKey(),"");
    }

    public void modifyRedisMapAcl() {

    }

    private void saveRedisAcl(AclDO aclDO,Long moduleId) {
        RedisAcl redisAcl = new RedisAcl();
        redisAcl.setId(aclDO.getId());
        redisAcl.setName(aclDO.getName());
        redisAcl.setSysAclAction(aclDO.getSysAclAction());
        redisAcl.setSysAclModuleId(aclDO.getSysAclModuleId());
        redisAcl.setSysAclPermissionType(aclDO.getSysAclPermissionType());
        redisAcl.setSysAclName(aclDO.getSysAclName());
        redisAcl.setSysAclRouter(aclDO.getSysAclRouter());
        redisAcl.setSysAclSeq(aclDO.getSysAclSeq());
        redisAcl.setSysAclStatus(aclDO.getSysAclStatus());
        redisAcl.setSysAclType(aclDO.getSysAclType());
        redisAcl.setSysAclUrl(aclDO.getSysAclUrl());
        redisUtil.set(RedisKeyEnum.REDIS_ACL_ACL_INFO.getKey() + redisAcl.getId(), JSONObject.toJSONString(redisAcl, SerializerFeature.WriteNullStringAsEmpty));
        redisUtil.hset(RedisKeyEnum.REDIS_MAP_ACL_INFO.getKey()+moduleId,redisAcl.getId()+"",JSONObject.toJSONString(redisAcl));
        redisUtil.hset(RedisKeyEnum.REDIS_MAP_ACL_INFO.getKey(),redisAcl.getId()+"",JSONObject.toJSONString(redisAcl));
    }
}
