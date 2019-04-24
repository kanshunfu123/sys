package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.Max;
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
public class AddRoleReq extends BaseReq {

    /**
     * sysRoleStatus 是否被禁用  0否 1禁用.
     */
    @NotNull(message = " sysRoleStatus 是否被禁用必填 0否 1禁用.",groups = {Add.class})
    private String sysRoleStatus;

    /**
     * groupId 第三方组 小为默认为1.
     */
    @NotNull(message = " groupId 第三方组必填必填",groups = {Add.class})
    private Long groupId;
    /**
     * remark 备注.
     */
    private String remark;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;

    /**
     * sysRoleName 角色名称.
     */
    @NotBlank(message = " sysRoleName 角色名称必填",groups = {Add.class})
    private String sysRoleName;
    /**
     * sysRoleType 角色的类型，0：管理员角色，1：普通用户 2其他.
     */
    @NotBlank(message = " sysRoleType 角色的类型必填",groups = {Add.class})
    @Min(value = 0,message = " sysRoleType 角色的类型 最小值为0",groups = {Add.class})
    @Max(value = 1,message = " sysRoleType 角色的类型 最大值为1",groups = {Add.class})
    private String sysRoleType;

}
