package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.Min;
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
public class AclReq extends BaseReq{

    /**
     * sysAclUuid 唯一，32位字符串，查询用.
     */
    @NotNull(message = "权限所在的权限模块uui必填 sysAclUuid",groups = {Modify.class})
    private String sysAclUuid;
    /**
     * sysAclModuleId 权限所在的权限模块id.
     */
    @NotNull(message = "权限所在的权限模块id必填 sysAclModuleId",groups = {Add.class,Modify.class})
    private Long sysAclModuleId;
    /**
     * sysAclStatus 状态 0启用  1禁用.
     */
    @NotNull(message = "状态 0启用  1禁用必填 sysAclStatus",groups = {Add.class,Modify.class})
    private String sysAclStatus;

    /**
     * remark 备注.
     */
    private String remark;

    /**
     * sysAclUrl 请求的url, 可以填正则表达式.
     */
    @NotBlank(message = "请求的url必填 sysAclUrl",groups = {Add.class,Modify.class})
    private String sysAclUrl;

    /**
     * sysAclName 权限名称.
     */
    @NotBlank(message = "权限名称必填 sysAclName",groups = {Add.class,Modify.class})
    private String sysAclName;
    /**
     * sysAclType 类型，1：菜单，2：按钮，3：其他.
     */
    @NotBlank(message = "类型，1：菜单，2：按钮，3：其他必填 sysAclType",groups = {Add.class,Modify.class})
    private String sysAclType;

    /**
     * sysAclAction 按钮动作类型(交给前端处理）.
     */
//    @NotBlank(message = "按钮动作类型(交给前端处理）必填 sysAclAction",groups = {Add.class})
    private String sysAclAction;
    /**
     * sysAclRouter 所属页面(交给前端处理).
     */
//    @NotBlank(message = "所属页面(交给前端处理). 必填 sysAclRouter",groups = {Add.class})
    private String sysAclRouter;
    /**
     * name 页面名称(前端控制).
     */
    @NotBlank(message = "页面名称(前端控制).. 必填 name",groups = {Add.class,Modify.class})
    private String name;
    /**
     * sysAclPermissionType 菜单系统类型 1 系统管理 2资产管理.
     */
    @NotNull(message = "菜单系统类型 必填 sysAclPermissionType",groups = {Add.class,Modify.class})
    private Long sysAclPermissionType;
    /**
     * sysAclSeq 权限在当前模块下的顺序，由小到大.
     */
    @NotNull(message = "权限在当前模块下的顺序，由小到大必填 sysAclSeq",groups = {Add.class,Modify.class})
    @Min(message = "权限在当前模块下的顺序最小值为1",value = 1)
    private Integer sysAclSeq;
}
