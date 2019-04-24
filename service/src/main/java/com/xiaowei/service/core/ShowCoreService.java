package com.xiaowei.service.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.google.common.collect.Sets;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.res.author.AclDto;
import com.xiaowei.common.res.author.AclModuleLevelDto;
import com.xiaowei.common.res.showacl.AclType;
import com.xiaowei.common.res.showacl.ShowAclModuleLevelDto;
import com.xiaowei.common.util.LevelUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dataobject.AclModuleDO;
import com.xiaowei.mana.mapper.dataobject.AclTypeDO;
import com.xiaowei.mana.mapper.dataobject.ShowSysAcl;
import com.xiaowei.mana.mapper.dataobject.SysAcl;
import com.xiaowei.mana.mapper.mapper.AclMapper;
import com.xiaowei.mana.mapper.mapper.AclModuleMapper;
import com.xiaowei.mana.mapper.mapper.AclTypeMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/1/17.
 */
@Service
public class ShowCoreService {

//    @Autowired
//    private RedisUtil redisUtil;
    @Autowired
    private AclModuleMapper aclModuleMapper;
    @Autowired
    private AclMapper aclMapper;
    @Autowired
    private AclTypeMapper aclTypeMapper;

    public List<AclType> allTypeAclTree() {
        List<AclType> aclTypes = Lists.newArrayList();
        //TODO redis  数据字典
        List<AclTypeDO> aclTypeDOS = aclTypeMapper.getAll();
        List<ShowAclModuleLevelDto> allAclTre = allAclTree();
        Multimap<Long, ShowAclModuleLevelDto> longMultimap = ArrayListMultimap.create();

        allAclTre.forEach(showAclModuleLevelDto -> {
            longMultimap.put(showAclModuleLevelDto.getSysAclPermissionType(), showAclModuleLevelDto);
        });
        aclTypeDOS.forEach(aclTypeDO -> {
            AclType aclType = new AclType();
            aclType.setId(aclTypeDO.getId());
            aclType.setSysAclTypeName(aclTypeDO.getSysAclTypeName());
            List<ShowAclModuleLevelDto> showAclModuleLevelDtos = (List<ShowAclModuleLevelDto>) longMultimap.get(aclTypeDO.getId());
            aclType.setChild(showAclModuleLevelDtos);
            aclTypes.add(aclType);
        });
        return aclTypes;
    }


    public List<ShowAclModuleLevelDto> allAclTree() {
        List<SysAcl> sysAcls = getAllAcl();
        List<ShowSysAcl> showSysAcls = Lists.newArrayList();
        sysAcls.forEach(sysAcl -> {
            ShowSysAcl showSysAcl = ShowSysAcl.adapt(sysAcl);
            showSysAcls.add(showSysAcl);
        });
        List<ShowAclModuleLevelDto> showAclModuleLevelDtos = aclListToTree(showSysAcls);
        return showAclModuleLevelDtos;
    }

    public List<ShowAclModuleLevelDto> aclListToTree(List<ShowSysAcl> aclDtoList) {
        if (CollectionUtils.isEmpty(aclDtoList)) {
            return Lists.newArrayList();
        }
        List<ShowAclModuleLevelDto> aclModuleLevelList = aclModuleTree();

        Multimap<Long, ShowSysAcl> moduleIdAclMap = ArrayListMultimap.create();
        for (ShowSysAcl acl : aclDtoList) {
            //状态 0启用  1禁用
//            if ("0".equals(acl.getSysAclStatus())) {
                moduleIdAclMap.put(acl.getSysAclModuleId(), acl);
//            }
        }
        bindAclsWithOrder(aclModuleLevelList, moduleIdAclMap);
        return aclModuleLevelList;
    }

    public void bindAclsWithOrder(List<ShowAclModuleLevelDto> aclModuleLevelList, Multimap<Long, ShowSysAcl> moduleIdAclMap) {
        if (CollectionUtils.isEmpty(aclModuleLevelList)) {
            return;
        }
        for (ShowAclModuleLevelDto dto : aclModuleLevelList) {
            List<ShowSysAcl> aclDtoList = (List<ShowSysAcl>) moduleIdAclMap.get(dto.getPriKey());
            if (CollectionUtils.isNotEmpty(aclDtoList)) {
                Collections.sort(aclDtoList, aclSeqComparator);
                dto.setAclList(aclDtoList);
            }
            bindAclsWithOrder(dto.getChildren(), moduleIdAclMap);
        }
    }

    public Comparator<ShowSysAcl> aclSeqComparator = new Comparator<ShowSysAcl>() {
        @Override
        public int compare(ShowSysAcl o1, ShowSysAcl o2) {
            return o1.getSysAclSeq() - o2.getSysAclSeq();
        }
    };

    public List<ShowAclModuleLevelDto> aclModuleTree() {
        List<AclModuleDO> aclModuleList = getAllAclModule();
        List<ShowAclModuleLevelDto> dtoList = Lists.newArrayList();
        for (AclModuleDO aclModule : aclModuleList) {
            dtoList.add(ShowAclModuleLevelDto.adapt(aclModule));
        }
        return aclModuleListToTree(dtoList);
    }

    public List<ShowAclModuleLevelDto> aclModuleListToTree(List<ShowAclModuleLevelDto> dtoList) {
        if (CollectionUtils.isEmpty(dtoList)) {
            return Lists.newArrayList();
        }
        // level -> [aclmodule1, aclmodule2, ...] Map<String, List<Object>>
        Multimap<String, ShowAclModuleLevelDto> levelAclModuleMap = ArrayListMultimap.create();
        List<ShowAclModuleLevelDto> rootList = Lists.newArrayList();

        for (ShowAclModuleLevelDto dto : dtoList) {
            levelAclModuleMap.put(dto.getSysAclModuleLevel(), dto);
            if (LevelUtil.ROOT.equals(dto.getSysAclModuleLevel())) {
                rootList.add(dto);
            }
        }
        Collections.sort(rootList, aclModuleSeqComparator);
        transformAclModuleTree(rootList, LevelUtil.ROOT, levelAclModuleMap);
        return rootList;
    }

    public void transformAclModuleTree(List<ShowAclModuleLevelDto> dtoList, String level, Multimap<String, ShowAclModuleLevelDto> levelAclModuleMap) {
        for (int i = 0; i < dtoList.size(); i++) {
            ShowAclModuleLevelDto dto = dtoList.get(i);
            String nextLevel = LevelUtil.calculateLevel(level, dto.getPriKey());
            List<ShowAclModuleLevelDto> tempList = (List<ShowAclModuleLevelDto>) levelAclModuleMap.get(nextLevel);
            if (CollectionUtils.isNotEmpty(tempList)) {
                Collections.sort(tempList, aclModuleSeqComparator);
                dto.setChildren(tempList);
                transformAclModuleTree(tempList, nextLevel, levelAclModuleMap);
            }
        }
    }

    public Comparator<ShowAclModuleLevelDto> aclModuleSeqComparator = new Comparator<ShowAclModuleLevelDto>() {
        @Override
        public int compare(ShowAclModuleLevelDto o1, ShowAclModuleLevelDto o2) {
            return o1.getSysAclModuleSeq() - o2.getSysAclModuleSeq();
        }
    };

    /**
     * 得到系统所有权限模块
     */
    public List<AclModuleDO> getAllAclModule() {
        //得到系统所有权限模块
       /* Map<Object, Object> objectObjectMap = redisUtil.hmget(RedisKeyEnum.REDIS_MAP_ACL_MODULE_INFO.getKey());
        List<AclModuleDO> sysAcls = objectObjectMap.values().stream().map(sysAcl -> JSON.parseObject(sysAcl + "", new TypeReference<AclModuleDO>() {
        })).collect(Collectors.toList());*/
//        if (CollectionUtils.isEmpty(sysAcls)) {
            List<AclModuleDO> aclModuleDOS = aclModuleMapper.getAllAclModule();
            return aclModuleDOS;
//        }
//        return sysAcls;
    }

    /**
     * 得到系统所有权限点
     *
     * @return
     */
    public List<SysAcl> getAllAcl() {
//        Map<Object, Object> objectObjectMap = redisUtil.hmget(RedisKeyEnum.REDIS_MAP_ACL_INFO.getKey());
//        if (objectObjectMap != null && objectObjectMap.size() != 0) {
//            List<SysAcl> sysAcls = objectObjectMap.values().stream().map(sysAcl -> JSON.parseObject(sysAcl + "", new TypeReference<SysAcl>() {
//            })).collect(Collectors.toList());
//            if (CollectionUtils.isEmpty(sysAcls)) {
//                List<SysAcl> allAclList = aclMapper.getAll(null);
//                return allAclList;
//            }
//            return sysAcls;
//        } else {
            List<SysAcl> allAclList = aclMapper.getAll(null);
            return allAclList;
//        }

    }
}
