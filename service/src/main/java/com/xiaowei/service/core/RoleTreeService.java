package com.xiaowei.service.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.RoleTreeReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.res.author.AclDto;
import com.xiaowei.common.res.author.AclModuleLevelDto;
import com.xiaowei.common.res.author.ModuleAclModuleLevelDto;
import com.xiaowei.common.util.LevelUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dataobject.AclModuleDO;
import com.xiaowei.mana.mapper.dataobject.AclTypeDO;
import com.xiaowei.mana.mapper.dataobject.RoleDO;
import com.xiaowei.mana.mapper.dataobject.SysAcl;
import com.xiaowei.mana.mapper.mapper.*;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/1/10.
 */
@Service
public class RoleTreeService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SysCoreService sysCoreService;
    @Autowired
    private AclMapper aclMapper;
    @Autowired
    private AclModuleMapper aclModuleMapper;
    @Autowired
    private RoleAclMapper roleAclMapper;
    @Autowired
    private AclTypeMapper aclTypeMapper;
    @Autowired
    private UserGroupAclMapper userGroupAclMapper;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 为角色授权前，进行数据展示
     *
     * @param roleTreeReq
     * @param redisUser
     * @return
     */
    public JSONResult roleTree(RoleTreeReq roleTreeReq, RedisUser redisUser) {
        RoleDO roleDO = roleMapper.getRoleByUUID(roleTreeReq.getRoleuuid());
        if (null == roleDO) {
            return JSONResult.errorMap("待查询的角色不存在");
        }
        // 1、当前用户已分配的权限点
        List<SysAcl> userAclList = sysCoreService.getCurrentUserAclList(redisUser, roleTreeReq);
        // 2、当前角色分配的权限点
        List<SysAcl> roleAclList = sysCoreService.getRoleAclList(roleDO.getId());
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
                List<SysAcl> sysAcls = Lists.newArrayList();
                if (!CollectionUtils.isEmpty(aclList)) {
                    //根据 权限点id列表获取权限点列表
                    sysAcls.addAll(aclMapper.getByIdList(aclList));
                    Set<Long> userAclModuleIdSet = sysAcls.stream().map(sysAcl -> sysAcl.getSysAclModuleId()).collect(Collectors.toSet());
                    aclModules.addAll(userAclModuleIdSet);
                    sysAclList.addAll(sysAcls);
                }
            }
        } else {
            Map<Object, Object> objectObjectMap = redisUtil.hmget(RedisKeyEnum.REDIS_MAP_ACL_INFO.getKey());
            if (objectObjectMap != null && objectObjectMap.size() != 0) {
                List<SysAcl> sysAcls = objectObjectMap.values().stream().map(sysAcl -> JSON.parseObject(sysAcl + "", new TypeReference<SysAcl>() {
                })).collect(Collectors.toList());
                sysAclList.addAll(sysAcls);
            } else {
                List<SysAcl> allAclList = aclMapper.getAll(roleTreeReq.getSysAclPermissionType());
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
        List<AclModuleLevelDto> aclModuleLevelList = aclModuleTree(aclModules, groupId, aclDtoList);

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

    private void test(Multimap<Long, AclModuleDO> multimap, List<AclDto> aclDtoList, Set<Long> set) {

        int size = aclDtoList.size();
        for (int i = 0; i < size; i++) {
            AclDto aclDto = aclDtoList.get(i);
            test2(multimap, set, aclDto.getSysAclModuleId());
        }
    }

    private void test2(Multimap<Long, AclModuleDO> multimap, Set<Long> set, Long moduleId) {
        List<AclModuleDO> aclModuleDOS = (List<AclModuleDO>) multimap.get(moduleId);
        if (!CollectionUtils.isEmpty(aclModuleDOS)) {
            AclModuleDO aclModuleDO = aclModuleDOS.get(0);
            set.add(aclModuleDO.getId());
            test2(multimap, set, aclModuleDO.getSysAclModuleParentId());
        }
    }


    public List<AclModuleLevelDto> aclModuleTree(Set<Long> aclModules, Long groupId, List<AclDto> aclDtoList) {
        Multimap<Long, AclModuleDO> longLongMultimap = ArrayListMultimap.create();
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
                    Set<Long> set = Sets.newHashSet();
                    List<AclModuleDO> aclModuleDOS1 = aclModuleMapper.getAllAclModule();
                    aclModuleDOS1.forEach(aclModuleDO -> {
                        longLongMultimap.put(aclModuleDO.getId(), aclModuleDO);
                    });
                    test(longLongMultimap, aclDtoList, set);
                    aclModuleList.addAll(aclModuleMapper.getAllAclModuleByModuleIds(set));
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
}
