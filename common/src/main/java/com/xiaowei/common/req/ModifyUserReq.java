package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by MOMO on 2019/1/7.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ModifyUserReq extends BaseReq{

    /**
     * id 主键.
     */
    private Long id;
    /**
     * deptId 关联部门id.
     */
//    @NotNull(message = "deptIdStr 部门id必填",groups = {Modify.class})
    private Long deptId;
    /**
     * groupId 第三方组 小为默认为0.
     */
//    @NotNull(message = "groupIdStr 第三方组groupId必填",groups = {Modify.class})
    private Long groupId;
    /**
     * sysUserArea 区.
     */
//    @NotNull(message = "sysUserArea 区域id必填",groups = {Modify.class})
    private Long sysUserArea;
//    @NotBlank(message = "sysUserAreaName 区名称必填",groups = {Add.class,Modify.class})
    private String sysUserAreaName;
    /**
     * sysUserCity 市.
     */
//    @NotNull(message = "sysUserCity 市id必填",groups = {Modify.class})
    private Long sysUserCity;
//    @NotBlank(message = "sysUserCityName 市名称必填",groups = {Add.class,Modify.class})
    private String sysUserCityName;
    /**
     * sysUserProvince 省.
     */
//    @NotNull(message = "sysUserProvince 省id必填",groups = {Modify.class})
    private Long sysUserProvince;
//    @NotBlank(message = "sysUserProvinceName 省名称必填",groups = {Add.class,Modify.class})
    private String sysUserProvinceName;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
//    @NotBlank(message = "delFlag 删除状态必填",groups = {Modify.class})
    private String delFlag;
    /**
     * nameTop 顶部名称.
     */
//    @NotBlank(message = "nameTop 顶部名称必填",groups = {Modify.class})
    private String nameTop;

    /**
     * nameBottom 版权.
     */
//    @NotBlank(message = "nameBottom 底部版权名称必填",groups = {Modify.class})
    private String nameBottom;
    /**
     * sysUserPwd 密码.
     */
    private String sysUserPwd;
    /**
     * isForbidden 是否被禁用  0否 1禁用.
     */
    @NotBlank(message = "isForbidden 是否被禁用必填",groups = {Status.class})
    private String isForbidden;
    /**
     * sysUserName 姓名.
     */
    @NotBlank(message = "sysUserName 姓名必填",groups = {Modify.class})
    private String sysUserName;
    /**
     * sysUserUuid 唯一，32位字符串，查询用.
     */
    @NotBlank(message = "sysUserUuid 用户uuid必填",groups = {Modify.class,Status.class,Delete.class,Query.class})
    private String sysUserUuid;
    /**
     * sysUserEmail 邮箱.
     */
//    @NotBlank(message = "sysUserEmail 邮箱必填",groups = {Modify.class})
//    @Email(message = "邮箱格式不正确",groups = {Modify.class})
    private String sysUserEmail;
    /**
     * sysUserPhone 手机号.
     */
    @NotBlank(message = "sysUserPhone 手机号必填",groups = {Modify.class})
    private String sysUserPhone;
    /**
     * sysUserLoginName 登录名.
     */
//    @NotBlank(message = "sysUserLoginName 登录名.必填",groups = {Modify.class})
//    private String sysUserLoginName;
    @NotNull(message = "roleId 角色id.必填",groups = {Add.class,Modify.class})
    private Long roleId;
}
