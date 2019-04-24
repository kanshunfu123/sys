package com.xiaowei.service.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.RoleTreeReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.res.author.AclDto;
import com.xiaowei.common.res.author.AclModuleLevelDto;
import com.xiaowei.common.util.LevelUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dataobject.AclModuleDO;
import com.xiaowei.mana.mapper.dataobject.SysAcl;
import com.xiaowei.mana.mapper.mapper.AclModuleMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/1/11.
 */
@Service
public class UserAclTreeService {

    @Autowired
    private SysCoreService sysCoreService;
    @Autowired
    private AclModuleMapper aclModuleMapper;
    @Autowired
    private RedisUtil redisUtil;


    public List<AclModuleLevelDto> userAclTree(RoleTreeReq roleTreeReq, RedisUser redisUser) {
        List<SysAcl> userAclList = sysCoreService.getUserAclList(redisUser, roleTreeReq);
        List<AclDto> aclDtoList = Lists.newArrayList();
        for (SysAcl acl : userAclList) {
            AclDto dto = AclDto.adapt(acl);
            dto.setHasAcl(true);
            dto.setChecked(true);
            aclDtoList.add(dto);
        }
        return aclListToTree(aclDtoList);
    }

    private List<AclModuleLevelDto> aclListToTree(List<AclDto> aclDtoList) {
        if (CollectionUtils.isEmpty(aclDtoList)) {
            return Lists.newArrayList();
        }
        List<AclModuleLevelDto> aclModuleLevelList = aclModuleTree(aclDtoList);

        Multimap<Long, AclDto> moduleIdAclMap = ArrayListMultimap.create();
        for (AclDto acl : aclDtoList) {
            if ("0".equals(acl.getSysAclStatus())) {
                moduleIdAclMap.put(acl.getSysAclModuleId(), acl);
            }
        }
        bindAclsWithOrder(aclModuleLevelList, moduleIdAclMap);
        return aclModuleLevelList;
    }


    private void bindAclsWithOrder(List<AclModuleLevelDto> aclModuleLevelList, Multimap<Long, AclDto> moduleIdAclMap) {
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

    private List<AclModuleLevelDto> aclModuleTree(List<AclDto> aclDtoList) {
        Map<Object, Object> objectObjectMap = redisUtil.hmget(RedisKeyEnum.REDIS_MAP_ACL_MODULE_INFO.getKey());
        List<AclModuleDO> sysAcls = objectObjectMap.values().stream().map(sysAcl -> JSON.parseObject(sysAcl + "", new TypeReference<AclModuleDO>() {
        })).collect(Collectors.toList());
        List<AclModuleDO> aclModuleList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(sysAcls)) {
            aclModuleList.addAll(sysAcls);
        } else {
            aclModuleList.addAll(aclModuleMapper.getAllAclModule());
        }
        List<AclModuleLevelDto> dtoList = Lists.newArrayList();
        /*for (AclModuleDO aclModule : aclModuleList) {
            AclModuleLevelDto aclModuleLevelDto = AclModuleLevelDto.adapt(aclModule);
            dtoList.add(aclModuleLevelDto);
        }*/
        Multimap<Long, AclModuleDO> multimap = ArrayListMultimap.create();
        aclModuleList.forEach(aclModuleDO -> {
            multimap.put(aclModuleDO.getId(), aclModuleDO);
        });
        Set<Long> set = Sets.newHashSet();
        test(multimap, aclDtoList, set);
        set.forEach(aLong -> {
            List<AclModuleDO> aclModuleDOS = (List<AclModuleDO>) multimap.get(aLong);
            AclModuleLevelDto aclModuleLevelDto = AclModuleLevelDto.adapt(aclModuleDOS.get(0));
            aclModuleLevelDto.setChecked(true);
            aclModuleLevelDto.setHasAcl(true);
            dtoList.add(aclModuleLevelDto);
        });
        return aclModuleListToTree(dtoList);
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

    private List<AclModuleLevelDto> aclModuleListToTree(List<AclModuleLevelDto> dtoList) {
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

    private void transformAclModuleTree(List<AclModuleLevelDto> dtoList, String level, Multimap<String, AclModuleLevelDto> levelAclModuleMap) {
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

    private Comparator<AclModuleLevelDto> aclModuleSeqComparator = new Comparator<AclModuleLevelDto>() {
        @Override
        public int compare(AclModuleLevelDto o1, AclModuleLevelDto o2) {
            return o1.getSysAclModuleSeq() - o2.getSysAclModuleSeq();
        }
    };

    private Comparator<AclDto> aclSeqComparator = new Comparator<AclDto>() {
        @Override
        public int compare(AclDto o1, AclDto o2) {
            return o1.getSysAclSeq() - o2.getSysAclSeq();
        }
    };
}
