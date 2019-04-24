package com.xiaowei.common.res.showacl;

import com.google.common.collect.Lists;
import com.xiaowei.common.res.author.AclDto;
import com.xiaowei.common.res.author.AclModuleLevelDto;
import com.xiaowei.mana.mapper.dataobject.AclModuleDO;
import com.xiaowei.mana.mapper.dataobject.ShowSysAcl;
import lombok.*;
import org.springframework.beans.BeanUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by MOMO on 2019/1/17.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ShowAclModuleLevelDto {

    private List<ShowAclModuleLevelDto> children = Lists.newArrayList();
    private List<ShowSysAcl> aclList = Lists.newArrayList();
    public static ShowAclModuleLevelDto adapt(AclModuleDO aclModule) {
        ShowAclModuleLevelDto dto = ShowAclModuleLevelDto.builder().id(aclModule.getSysAclModuleUuid())
                .label(aclModule.getSysAclModuleName()).priKey(aclModule.getId()).sysAclModuleCode(aclModule.getSysAclModuleCode())
                .sysAclModuleLevel(aclModule.getSysAclModuleLevel()).sysAclModuleParentId(aclModule.getSysAclModuleParentId())
                .sysAclModuleRemark(aclModule.getSysAclModuleRemark()).sysAclModuleSeq(aclModule.getSysAclModuleSeq())
                .sysAclPermissionType(aclModule.getSysAclPermissionType()).muneType(0).build();
        return dto;
    }

    /**
     * 菜单类型 0 权限模块 1  菜单
     */
    private Integer muneType=0;
    /**
     * id 权限模块id.
     */
    private Long priKey;
    /**
     * sysAclModuleParentId 上级权限模块id.
     */
    private Long sysAclModuleParentId;
    /**
     * sysAclPermissionType 菜单系统类型 1 系统管理 2资产管理.
     */
    private Long sysAclPermissionType;

    /**
     * sysAclModuleCode 权限模块码.
     */
    private String sysAclModuleCode;

    /**
     * sysAclModuleName 权限模块名称.
     */
    private String label;
    /**
     * sysAclModuleUuid 唯一，32位字符串，查询用.
     */
    private String id;
    /**
     * sysAclModuleLevel 权限模块层级.
     */
    private String sysAclModuleLevel;
    /**
     * sysAclModuleRemark 备注.
     */
    private String sysAclModuleRemark;
    /**
     * sysAclModuleSeq 权限模块在当前层级下的顺序，由小到大.
     */
    private Integer sysAclModuleSeq;

}
