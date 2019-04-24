package com.xiaowei.common.res;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * Created by MOMO on 2019/1/18.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertUserGroupRes {

    /**
     * id ID.
     */
    private Long id;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * nameTop 顶部名称.
     */
    private String nameTop;
    /**
     * createBy 创建人.
     */
    private String createBy;


    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * nameBottom 版权.
     */
    private String nameBottom;



    /**
     * sysOpenAccountNum 已开通账号个数.
     */
    private Integer sysOpenAccountNum;

    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;

    /**
     * sysScene 授权场景 以逗号分隔0 地表水 1井盖 2生活用水 3电梯.
     */
    private String sysScene;
    /**
     * sysStart 是否被禁用  0否 1禁用.
     */
    private String sysStart;

    /**
     * sysOpenDay 开通的天数 -1 不限次数 小为公司所有.
     */
    private String sysOpenDay;
    /**
     * userGroupName 用户组名称/第三方公司名称.
     */
    private String userGroupName;
    /**
     * userGroupUuid uuid.
     */
    private String userGroupUuid;
    /**
     * sysReceDeviceNum 以接入设备总数.
     */
    private Integer sysReceDeviceNum;
    /**
     * sysAllowDeviceNum 允许接入设备总数.
     */
    private Integer sysAllowDeviceNum;
    /**
     * sysCreateAccountNum 可以开通子账户的个数 -1不限制次数  id为1 为小为公司，不限次数.
     */
    private Integer sysCreateAccountNum;

    /**
     * sysAccountEndTime 账号结束时间.
     */
    private Date sysAccountEndTime;
    /**
     * sysAccountStartTime 账号开通时间 (不传值，默认系统时间).
     */
    private Date sysAccountStartTime;

    private List<String> scenList;

}
