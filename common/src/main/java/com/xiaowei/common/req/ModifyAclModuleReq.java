package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by MOMO on 2019/1/9.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyAclModuleReq extends BaseReq {
    /**
     * sysAclModuleUuid 唯一，32位字符串，查询用.
     */
    @NotBlank(message = "uuid不能为空 sysAclModuleUuid", groups = {Modify.class})
    private String sysAclModuleUuid;

    /**
     * sysAclModuleParentId 上级权限模块id.
     */
    private Long sysAclModuleParentId = 0L;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;

    /**
     * sysAclModuleCode 权限模块码.
     */
    private String sysAclModuleCode;
    /**
     * sysAclModuleIcon 图标class.
     */
    private String sysAclModuleIcon;
    /**
     * sysAclModuleName 权限模块名称.
     */
    @NotBlank(message = "权限模块名称必填 sysAclModuleName", groups = {Modify.class})
    private String sysAclModuleName;

    /**
     * sysAclModuleRemark 备注.
     */
    private String sysAclModuleRemark;
    /**
     * sysAclPermissionType 菜单系统类型 1 系统管理 2资产管理.
     */
    @NotNull(message = "菜单系统类型必填 sysAclPermissionType", groups = {Modify.class})
    private Long sysAclPermissionType;
    /**
     * sysAclModuleSeq 权限模块在当前层级下的顺序，由小到大.
     */
    @NotNull(message = "权限模块在当前层级下的顺序，由小到大必填  sysAclModuleSeq", groups = {Modify.class})
    private Integer sysAclModuleSeq;

}
