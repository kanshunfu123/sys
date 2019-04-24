package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by MOMO on 2019/1/18.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertUserGroupReq extends BaseReq {

    /**
     * sysScene 授权场景 以逗号分隔0 地表水 1井盖 2生活用水 3电梯.
     */
    @NotBlank(message = "授权场景 sysScene 必填", groups = {Add.class, Modify.class})
    private String sysScene;
    /**
     * sysStart 是否被禁用  0否 1禁用.
     */
    @NotBlank(message = "是否被禁用 必填 sysStart", groups = {Add.class, Modify.class})
    private String sysStart;

    /**
     * sysOpenDay 开通的天数 -1 不限次数 小为公司所有.
     */
    private String sysOpenDay;
    /**
     * userGroupName 用户组名称/第三方公司名称.
     */
    @NotBlank(message = "用户组名称/第三方公司名称 必填", groups = {Add.class, Modify.class})
    private String userGroupName;
    /**
     * userGroupUuid uuid.
     */
    @NotBlank(message = "userGroupUuid 必填", groups = {Modify.class, Detail.class})
    private String userGroupUuid;
    /**
     * sysReceDeviceNum 以接入设备总数.
     */
    private Integer sysReceDeviceNum;
    /**
     * sysAllowDeviceNum 允许接入设备总数.
     */
    @NotNull(message = "允许接入设备总数 必填", groups = {Add.class, Modify.class})
    @Min(value = 0, message = "允许接入设备总数 最小值为0")
    private Integer sysAllowDeviceNum;
    /**
     * sysCreateAccountNum 可以开通子账户的个数 -1不限制次数  id为1 为小为公司，不限次数.
     */
    @NotNull(message = "可以开通子账户的个数", groups = {Add.class})
    private Integer sysCreateAccountNum;

    /**
     * sysAccountEndTime 账号结束时间.
     */
    @NotBlank(message = "账号结束时间 sysAccountEndTime 必填", groups = {Add.class, Modify.class})
    private String sysAccountEndTime;
    /**
     * sysAccountStartTime 账号开通时间 (不传值，默认系统时间).
     */
    @NotBlank(message = "账号开通时间 sysAccountEndTime 必填", groups = {Add.class, Modify.class})
    private String sysAccountStartTime;

    /**
     * nameBottom 版权.
     */
    @NotBlank(message = "底部版权必填  nameBottom  版权", groups = {Add.class, Modify.class})
    private String nameBottom;
    /**
     * nameTop 顶部名称.
     */
    @NotBlank(message = "顶部名称. 必填  nameTop  版权", groups = {Add.class})
    private String nameTop;

}
