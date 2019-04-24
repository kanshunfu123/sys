package com.xiaowei.mana.mapper.dataobject;

import lombok.*;
import org.springframework.beans.BeanUtils;

/**
 * Created by MOMO on 2019/1/10.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
@Setter
@Getter
public class ShowSysAcl {

    public static ShowSysAcl adapt(SysAcl acl) {
        ShowSysAcl dto = ShowSysAcl.builder().id(acl.getSysAclUuid()).label(acl.getSysAclName())
                .name(acl.getName()).priKey(acl.getId()).remark(acl.getRemark()).sysAclAction(acl.getSysAclAction())
                .sysAclCode(acl.getSysAclCode()).sysAclModuleId(acl.getSysAclModuleId()).sysAclPermissionType(acl.getSysAclPermissionType())
                .sysAclStatus(acl.getSysAclStatus()).sysAclSeq(acl.getSysAclSeq()).muneType(1).sysAclUrl(acl.getSysAclUrl()).sysAclRouter(acl.getSysAclRouter())
                .sysAclType(acl.getSysAclType())
                .build();
        return dto;
    }

    /**
     * 菜单类型 0 权限模块 1  菜单
     */
    private Integer muneType = 1;
    /**
     * id 权限模块id.
     */
    private Long priKey;
    /**
     * sysAclStatus 状态 0启用  1禁用.
     */
    private String sysAclStatus;
    /**
     * sysAclModuleId 权限所在的权限模块id.
     */
    private Long sysAclModuleId;
    /**
     * name 页面名称(前端控制).
     */
    private String name;
    /**
     * remark 备注.
     */
    private String remark;

    /**
     * sysAclUrl 请求的url, 可以填正则表达式.
     */
    private String sysAclUrl;
    /**
     * sysAclCode 权限码.
     */
    private String sysAclCode;

    /**
     * sysAclName 权限名称.
     */
    private String label;
    /**
     * sysAclType 类型，1：菜单，2：按钮，3：其他.
     */
    private String sysAclType;
    /**
     * sysAclUuid 唯一，32位字符串，查询用.
     */
    private String id;
    /**
     * sysAclAction 按钮动作类型(交给前端处理）.
     */
    private String sysAclAction;
    /**
     * sysAclRouter 所属页面(交给前端处理).
     */
    private String sysAclRouter;
    /**
     * sysAclPermissionType 菜单系统类型 1 系统管理 2资产管理.
     */
    private Long sysAclPermissionType;
    /**
     * sysAclSeq 权限在当前模块下的顺序，由小到大.
     */
    private Integer sysAclSeq;

}
