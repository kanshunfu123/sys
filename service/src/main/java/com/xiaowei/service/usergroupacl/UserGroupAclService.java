package com.xiaowei.service.usergroupacl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.*;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.AclsReq;
import com.xiaowei.common.req.BatchRoleAclReq;
import com.xiaowei.common.req.UserGroupAclReq;
import com.xiaowei.common.res.author.AclDto;
import com.xiaowei.common.res.author.AclModuleLevelDto;
import com.xiaowei.common.res.author.ModuleAclModuleLevelDto;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.LevelUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.*;
import com.xiaowei.service.core.SysCoreService;
import com.xiaowei.service.roleacl.RoleAclService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.PublicKey;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/1/14.
 */
@Service
public class UserGroupAclService {
    @Autowired
    private UserGroupAclMapper userGroupAclMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AclMapper aclMapper;
    @Autowired
    private AclTypeMapper aclTypeMapper;
    @Autowired
    private AclModuleMapper aclModuleMapper;
    @Autowired
    private SysCoreService sysCoreService;
    @Autowired
    private RoleAclService roleAclService;
    @Autowired
    private RoleAclMapper roleAclMapper;
    @Autowired
    private RoleMapper roleMapper;

    public JSONResult showGroupAcl(UserGroupAclReq userGroupAclReq, RedisUser redisUser) {
        UserGroupDO userGroupDO = userGroupMapper.getGroupByUUID(userGroupAclReq.getSysUserGroupUuid());
        if (null == userGroupDO) {
            return JSONResult.errorMap("带查看的组织权限不存在");
        }
        // 1、当前用户已分配的权限点
        List<SysAcl> userAclList = sysCoreService.getCurrentUserAclList(redisUser, null);
        //当前第三方组织拥有的权限点列表
        List<Long> longs = userGroupAclMapper.getDeviceIdByGroupId(userGroupDO.getId());
        List<SysAcl> roleAclList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(longs)) {
            roleAclList.addAll(aclMapper.getByIdList(longs));
        }

// 3、当前系统所有权限点
        List<AclDto> aclDtoList = Lists.newArrayList();
        Set<Long> userAclIdSet = userAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        Set<Long> roleAclIdSet = roleAclList.stream().map(sysAcl -> sysAcl.getId()).collect(Collectors.toSet());
        //获取系统所有权限点
        List<SysAcl> sysAclList = Lists.newArrayList();
        //根据角色id列表 获取权限模块列表
        Set<Long> aclModules = Sets.newHashSet();
        //第三方组
        Long groupId = redisUser.getGroupId();
        if (groupId != 1) {//第三方权限
            //得到第三方管理员权限
            // 第三方管理员角色id列表
            /*List<Long> roleDOS = roleMapper.getThirdRoleByParam(groupId, "0");
            //根据角色id列表 获取权限点列表
            List<Long> aclList = roleAclMapper.getAclIdListByRoleIdList(roleDOS);*/
            Object o = redisUtil.get(RedisKeyEnum.REDIS_GROUP_ACL_INFO.getKey() + groupId);
            if (o != null) {
                List<Long> groupAcl = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
                });
                List<String> userRoleIdListStr = groupAcl.stream().map(sysAcl -> RedisKeyEnum.REDIS_ACL_ACL_INFO.getKey() + sysAcl).collect(Collectors.toList());
                if (!CollectionUtils.isEmpty(groupAcl)) {
                    //根据 权限点id列表获取权限点列表
                    List<Object> acls = redisUtil.batchListByKeys(userRoleIdListStr);
                    List<SysAcl> sysAcls = Lists.newArrayList();
                    Set<Long> userAclModuleIdSet = Sets.newHashSet();
                    acls.forEach(aaaa -> {
                        if (aaaa != null) {
                            SysAcl sysAcls1 = JSON.parseObject((String) aaaa, new TypeReference<SysAcl>() {
                            });
                            userAclModuleIdSet.add(sysAcls1.getSysAclModuleId());
                            sysAcls.add(sysAcls1);
                        }

                    });
                    aclModules.addAll(userAclModuleIdSet);
                    sysAclList.addAll(sysAcls);
                }
            } else {
                List<Long> aclList = userGroupAclMapper.getDeviceIdByGroupId(groupId);
                //根据 权限点id列表获取权限点列表
                List<SysAcl> sysAcls = aclMapper.getByIdList(aclList);
                Set<Long> userAclModuleIdSet = sysAcls.stream().map(sysAcl -> sysAcl.getSysAclModuleId()).collect(Collectors.toSet());
                aclModules.addAll(userAclModuleIdSet);
                sysAclList.addAll(sysAcls);
            }
        } else {
            Map<Object, Object> objectObjectMap = redisUtil.hmget(RedisKeyEnum.REDIS_MAP_ACL_INFO.getKey());
            if (objectObjectMap != null && objectObjectMap.size() != 0) {
                List<SysAcl> sysAcls = objectObjectMap.values().stream().map(sysAcl -> JSON.parseObject(sysAcl + "", new TypeReference<SysAcl>() {
                })).collect(Collectors.toList());
                sysAclList.addAll(sysAcls);
            } else {
                List<SysAcl> allAclList = aclMapper.getAll(null);
                sysAclList.addAll(allAclList);
            }
        }

        for (SysAcl acl : sysAclList) {
            AclDto dto = AclDto.adapt(acl);
            if (userAclIdSet.contains(acl.getId())) {
                dto.setHasAcl(true);
            }
            if (roleAclIdSet.contains(acl.getId())) {
                dto.setChecked(true);
            }
            aclDtoList.add(dto);
        }
        List<AclModuleLevelDto> aclModuleLevelDtos = aclListToTree(aclDtoList, aclModules, groupId);

        //权限模块id
        Set<Long> aclModuleSet = Sets.newHashSet();
        //key 权限类型 id   v   AclModuleLevelDto
        Multimap<Long, AclModuleLevelDto> longAclModuleLevelDtoMultimap = ArrayListMultimap.create();
        aclModuleLevelDtos.forEach(aclModuleLevelDto -> {
            aclModuleSet.add(aclModuleLevelDto.getSysAclModuleParentId());
            longAclModuleLevelDtoMultimap.put(aclModuleLevelDto.getSysAclPermissionType(), aclModuleLevelDto);
        });
        //TODO redis  数据字典
        List<AclTypeDO> aclTypeDOS = aclTypeMapper.getAll();

        //最终返回结果
        List<ModuleAclModuleLevelDto> moduleAclModuleLevelDtos = Lists.newArrayList();
        aclTypeDOS.forEach(aclTypeDO -> {
            List<AclModuleLevelDto> moduleLevelDtos = (List<AclModuleLevelDto>) longAclModuleLevelDtoMultimap.get(aclTypeDO.getId());
            if (!CollectionUtils.isEmpty(moduleLevelDtos)) {
                ModuleAclModuleLevelDto moduleAclModuleLevelDto = new ModuleAclModuleLevelDto();
                moduleAclModuleLevelDto.setModuleName(aclTypeDO.getSysAclTypeName());
                moduleAclModuleLevelDto.setAclModuleLevelDtos(moduleLevelDtos);
                moduleAclModuleLevelDtos.add(moduleAclModuleLevelDto);
            }
        });


        return JSONResult.ok(moduleAclModuleLevelDtos);
    }

    public List<AclModuleLevelDto> aclListToTree(List<AclDto> aclDtoList, Set<Long> aclModules, Long groupId) {
        if (CollectionUtils.isEmpty(aclDtoList)) {
            return Lists.newArrayList();
        }
        List<AclModuleLevelDto> aclModuleLevelList = aclModuleTree(aclModules, groupId);

        Multimap<Long, AclDto> moduleIdAclMap = ArrayListMultimap.create();
        for (AclDto acl : aclDtoList) {
            //临时状态 启用
            if ("0".equals(acl.getSysAclStatus())) {
                moduleIdAclMap.put(acl.getSysAclModuleId(), acl);
            }
        }
        bindAclsWithOrder(aclModuleLevelList, moduleIdAclMap);
        return aclModuleLevelList;
    }

    public void bindAclsWithOrder(List<AclModuleLevelDto> aclModuleLevelList, Multimap<Long, AclDto> moduleIdAclMap) {
        if (CollectionUtils.isEmpty(aclModuleLevelList)) {
            return;
        }
        for (AclModuleLevelDto dto : aclModuleLevelList) {
            List<AclDto> aclDtoList = (List<AclDto>) moduleIdAclMap.get(dto.getId());
            if (CollectionUtils.isNotEmpty(aclDtoList)) {
                Collections.sort(aclDtoList, aclSeqComparator);
                dto.setAclList(aclDtoList);
            }
            bindAclsWithOrder(dto.getAclModuleList(), moduleIdAclMap);
        }
    }

    public List<AclModuleLevelDto> aclModuleTree(Set<Long> aclModules, Long groupId) {
        List<AclModuleDO> aclModuleList = Lists.newArrayList();
        if (groupId != 1) {
            if (!CollectionUtils.isEmpty(aclModules)) {
                List<String> userRoleIdListStr = aclModules.stream().map(sysAcl -> RedisKeyEnum.REDIS_ACL_MODULE_INFO.getKey() + sysAcl).collect(Collectors.toList());
                //根据模块ids 得到权限模块列表
                List<Object> objects = redisUtil.batchListByKeys(userRoleIdListStr);
                List<AclModuleDO> aclModuleDOS = Lists.newArrayList();
                if (!CollectionUtils.isEmpty(objects)) {
                    for (int i = 0; i < objects.size(); i++) {
                        Object o = objects.get(i);
                        if (null != o) {
                            AclModuleDO aclModuleDO = JSON.parseObject(o + "", new TypeReference<AclModuleDO>() {
                            });
                            aclModuleDOS.add(aclModuleDO);
                        }
                    }
                }
                if (!CollectionUtils.isEmpty(aclModuleDOS)) {
                    aclModuleList.addAll(aclModuleMapper.getAllAclModuleByModuleIds(aclModules));
                }
            }
        } else {
            Map<Object, Object> objectObjectMap = redisUtil.hmget(RedisKeyEnum.REDIS_MAP_ACL_MODULE_INFO.getKey());
            List<AclModuleDO> sysAcls = objectObjectMap.values().stream().map(sysAcl -> JSON.parseObject(sysAcl + "", new TypeReference<AclModuleDO>() {
            })).collect(Collectors.toList());
            if (!CollectionUtils.isEmpty(sysAcls)) {
                aclModuleList.addAll(sysAcls);
            } else {
                List<AclModuleDO> aclModuleDOS = aclModuleMapper.getAllAclModule();
                aclModuleList.addAll(aclModuleDOS);
            }

        }

        List<AclModuleLevelDto> dtoList = Lists.newArrayList();
        for (AclModuleDO aclModule : aclModuleList) {
            dtoList.add(AclModuleLevelDto.adapt(aclModule));
        }
        //权限模块以及权限点
        List<AclModuleLevelDto> aclModuleLevelDtos = aclModuleListToTree(dtoList);

        /*//权限模块id
        Set<Long> aclModuleSet = Sets.newHashSet();
        //key 权限类型 id   v   AclModuleLevelDto
        Multimap<Long, AclModuleLevelDto> longAclModuleLevelDtoMultimap = ArrayListMultimap.create();
        aclModuleLevelDtos.forEach(aclModuleLevelDto -> {
            aclModuleSet.add(aclModuleLevelDto.getSysAclModuleParentId());
            longAclModuleLevelDtoMultimap.put(aclModuleLevelDto.getSysAclPermissionType(), aclModuleLevelDto);
        });
        //得到所有的系统模块
        List<AclTypeDO> aclTypeDOS = aclTypeMapper.getAll();

        //最终返回结果
        List<ModuleAclModuleLevelDto> moduleAclModuleLevelDtos = Lists.newArrayList();
        aclTypeDOS.forEach(aclTypeDO -> {
            List<AclModuleLevelDto> moduleLevelDtos = (  List<AclModuleLevelDto> )longAclModuleLevelDtoMultimap.get(aclTypeDO.getId());
            if (!CollectionUtils.isEmpty(moduleLevelDtos)){
                ModuleAclModuleLevelDto moduleAclModuleLevelDto=new ModuleAclModuleLevelDto();
                moduleAclModuleLevelDto.setModuleName(aclTypeDO.getSysAclTypeName());
                moduleAclModuleLevelDto.setAclModuleLevelDtos(moduleLevelDtos);
                moduleAclModuleLevelDtos.add(moduleAclModuleLevelDto);
            }
        });*/


        return aclModuleLevelDtos;
    }

    public List<AclModuleLevelDto> aclModuleListToTree(List<AclModuleLevelDto> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        // level -> [aclmodule1, aclmodule2, ...] Map<String, List<Object>>
        Multimap<String, AclModuleLevelDto> levelAclModuleMap = ArrayListMultimap.create();
        List<AclModuleLevelDto> rootList = Lists.newArrayList();

        for (AclModuleLevelDto dto : dtoList) {
            levelAclModuleMap.put(dto.getSysAclModuleLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getSysAclModuleLevel())) {
                rootList.add(dto);
            }
        }
        Collections.sort(rootList, aclModuleSeqComparator);
        transformAclModuleTree(rootList, LevelUtil.ROOT, levelAclModuleMap);
        return rootList;
    }

    public void transformAclModuleTree(List<AclModuleLevelDto> dtoList, String level, Multimap<String, AclModuleLevelDto> levelAclModuleMap) {
        for (int i = 0; i < dtoList.size(); i++) {
            AclModuleLevelDto dto = dtoList.get(i);
            String nextLevel = LevelUtil.calculateLevel(level, dto.getId());
            List<AclModuleLevelDto> tempList = (List<AclModuleLevelDto>) levelAclModuleMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempList)) {
                Collections.sort(tempList, aclModuleSeqComparator);
                dto.setAclModuleList(tempList);
                transformAclModuleTree(tempList, nextLevel, levelAclModuleMap);
            }
        }
    }

    public Comparator<AclModuleLevelDto> aclModuleSeqComparator = new Comparator<AclModuleLevelDto>() {
        @Override
        public int compare(AclModuleLevelDto o1, AclModuleLevelDto o2) {
            return o1.getSysAclModuleSeq() - o2.getSysAclModuleSeq();
        }
    };
    public Comparator<AclDto> aclSeqComparator = new Comparator<AclDto>() {
        @Override
        public int compare(AclDto o1, AclDto o2) {
            return o1.getSysAclSeq() - o2.getSysAclSeq();
        }
    };


    public JSONResult changeGroupAcl(UserGroupAclReq userGroupAclReq, RedisUser redisUser) {

        UserGroupDO userGroupAclDO = userGroupMapper.getGroupByUUID(userGroupAclReq.getSysUserGroupUuid());
        if (null == userGroupAclDO) {
            return JSONResult.errorMap("待查询的第三方组不存在");
        }
        //角色的类型，0：管理员角色，1：普通用户 2其他
        RoleDO roleDO = roleMapper.getThirdRoleByParam(userGroupAclDO.getId(), "0", null);
        if (null == roleDO) {
            return JSONResult.errorMap("请先为第三方组织设置管理员角色");
        }
        //修改第三方组的权限时，同时修改 第三方管理员的权限
        BatchRoleAclReq batchRoleAclReq = new BatchRoleAclReq();
        batchRoleAclReq.setRoleUuid(roleDO.getSysRoleUuid());
        batchRoleAclReq.setAclIds(userGroupAclReq.getSysAclId());
        roleAclService.changeRoleUsers(batchRoleAclReq, redisUser);

        List<Long> getDeviceIdByGroupId = userGroupAclMapper.getDeviceIdByGroupId(userGroupAclDO.getId());
        //得到前端给的权限点id
        List<AclsReq> aclIdList = userGroupAclReq.getSysAclId();
        if (!CollectionUtils.isEmpty(aclIdList)) {
            if (getDeviceIdByGroupId.size() == aclIdList.size()) {
                List<Long> longs = Lists.newArrayList();
                aclIdList.forEach(aclsReq -> {
                    longs.add(aclsReq.getAclId());
                });
                Set<Long> originAclIdSet = Sets.newHashSet(getDeviceIdByGroupId);
                Set<Long> aclIdSet = Sets.newHashSet(longs);
                originAclIdSet.removeAll(aclIdSet);
                if (CollectionUtils.isEmpty(originAclIdSet)) {
                    return JSONResult.ok("第三方权限授权成功");
                }
            }
        }
        updateRoleAcls(userGroupAclDO.getId(), aclIdList, userGroupAclDO.getId(), redisUser.getSysUserName(), redisUser, roleDO);
        return JSONResult.ok("第三方权限授权成功");
    }

    @Transactional
    public void updateRoleAcls(Long groupPriId, List<AclsReq> aclIdList, Long groupId, String userName, RedisUser redisUser, RoleDO roleDO) {

        userGroupAclMapper.deleteByGroupId(groupId);
        roleAclMapper.deleteByUserId(roleDO.getId());

        List<UserGroupAclDO> roleAclList = Lists.newArrayList();
        List<Long> groupAcls = Lists.newArrayList();
        for (AclsReq aclId : aclIdList) {
            UserGroupAclDO roleUserDO = new UserGroupAclDO();
            roleUserDO.setUuid(StrUtil.genUUID());
            roleUserDO.setCreateBy(userName);
            roleUserDO.setUpdateBy(userName);
            roleUserDO.setCreateTime(DateUtils.getCurrentDateTime());
            roleUserDO.setUpdateTime(DateUtils.getCurrentDateTime());
            roleUserDO.setDelFlag("0");
            roleUserDO.setSysAclId(aclId.getAclId());
            roleUserDO.setSysAclPermissionType(aclId.getSysAclPermissionType());
            roleUserDO.setSysUserGroupId(groupId);
            roleAclList.add(roleUserDO);
            groupAcls.add(aclId.getAclId());
        }
        if (!CollectionUtils.isEmpty(aclIdList)) {
            userGroupAclMapper.batchaddRoleUser(roleAclList);
        }
        saveDeviceAcl(groupAcls, groupId);
        BatchRoleAclReq batchRoleAclReq = new BatchRoleAclReq();

        batchRoleAclReq.setRoleUuid(roleDO.getSysRoleUuid());
        batchRoleAclReq.setAclIds(aclIdList);
        roleAclService.changeRoleUsers(batchRoleAclReq, redisUser);
    }

    private void saveDeviceAcl(List<Long> groupAcls, Long groupId) {
        redisUtil.set(RedisKeyEnum.REDIS_GROUP_ACL_INFO.getKey() + groupId, JSONObject.toJSONString(groupAcls));
        /*Multimap<Long, AclsReq> multimap = ArrayListMultimap.create();
        Set<Long> set = Sets.newHashSet();
        aclIdList.forEach(aclsReq -> {
            multimap.put(aclsReq.getSysAclPermissionType(), aclsReq);
            set.add(aclsReq.getSysAclPermissionType());
        });
        Map<String, Object> map = Maps.newHashMap();
        set.forEach(aLong -> {
            map.put(aLong + "", multimap.get(aLong));
            redisUtil.hset(RedisKeyEnum.REDIS_GROUP_ACL_INFO.getKey() + groupId, aLong + "", JSONObject.toJSONString(map));
        });*/

    }/*private void saveDeviceAcl(List<AclsReq> aclIdList, Long groupId) {
//        redisUtil.set(RedisKeyEnum.REDIS_GROUP_ACL_INFO.getKey() + groupId, JSONObject.toJSONString(aclIdList));
        Multimap<Long, AclsReq> multimap = ArrayListMultimap.create();
        Set<Long> set = Sets.newHashSet();
        aclIdList.forEach(aclsReq -> {
            multimap.put(aclsReq.getSysAclPermissionType(), aclsReq);
            set.add(aclsReq.getSysAclPermissionType());
        });
        Map<String, Object> map = Maps.newHashMap();
        set.forEach(aLong -> {
            map.put(aLong + "", multimap.get(aLong));
            redisUtil.hset(RedisKeyEnum.REDIS_GROUP_ACL_INFO.getKey() + groupId, aLong + "", JSONObject.toJSONString(map));
        });

    }*/
}
