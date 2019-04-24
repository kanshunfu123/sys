package com.xiaowei.mana.mapper.dataobject;


import com.google.common.collect.Lists;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;

/**
 * The table 第三方权限组
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class UserGroupPageDO {
    private List<String> stringList;

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
     * sysScene 授权场景 以逗号分隔0 地表水 1井盖 2生活用水 3电梯.
     */
    private String sysScene;
    /**
     * sysStart 是否被禁用  0否 1禁用.
     */
    private String sysStart;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * nameBottom 版权.
     */
    private String nameBottom;
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
     * sysOpenAccountNum 已开通账号个数.
     */
    private Integer sysOpenAccountNum;
    /**
     * sysCreateAccountNum 可以开通子账户的个数 -1不限制次数  id为1 为小为公司，不限次数.
     */
    private Integer sysCreateAccountNum;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * sysAccountEndTime 账号结束时间.
     */
    private Date sysAccountEndTime;
    /**
     * sysAccountStartTime 账号开通时间 (不传值，默认系统时间).
     */
    private Date sysAccountStartTime;

    /*public List<String> getStringList() {
        */

    public List<String> getStringList() {
        return stringList;
    }

    /**
         * sysScene 授权场景 以逗号分隔0 地表水 1井盖 2生活用水 3电梯.
         *//*
        // sysScene;
        if (StringUtils.isEmpty(getSysScene())) {
            return Lists.newArrayList();
        }
        String[] strings = getSysScene().split(",");
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < strings.length; i++) {
          String strings1=  strings[i];
          if ("0".equals(strings1)){
              list.add("地表水");
          }else if("1".equals(strings1)){
              list.add("井盖");
          }else if("2".equals(strings1)){
              list.add("生活用水");
          }else if("2".equals(strings1)){
              list.add("电梯");
          }
        }
        return list;
    }*/

    public Integer getSysOpenAccountNum() {
        return sysOpenAccountNum;
    }

    public void setSysOpenAccountNum(Integer sysOpenAccountNum) {
        this.sysOpenAccountNum = sysOpenAccountNum;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public String getNameTop() {
        return nameTop;
    }

    public void setNameTop(String nameTop) {
        this.nameTop = nameTop;
    }

    public String getNameBottom() {
        return nameBottom;
    }

    public void setNameBottom(String nameBottom) {
        this.nameBottom = nameBottom;
    }

    /**
     * Set id ID.
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Long getId() {
        return id;
    }

    /**
     * Set delFlag 删除状态(0-正常，1-删除).
     */
    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag 删除状态(0-正常，1-删除).
     *
     * @return the string
     */
    public String getDelFlag() {
        return delFlag;
    }

    /**
     * Set createBy 创建人.
     */
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    /**
     * Get createBy 创建人.
     *
     * @return the string
     */
    public String getCreateBy() {
        return createBy;
    }

    /**
     * Set sysScene 授权场景 以逗号分隔0 地表水 1井盖 2生活用水 3电梯.
     */
    public void setSysScene(String sysScene) {
        this.sysScene = sysScene;
    }

    /**
     * Get sysScene 授权场景 以逗号分隔0 地表水 1井盖 2生活用水 3电梯.
     *
     * @return the string
     */
    public String getSysScene() {
        return sysScene;
    }

    /**
     * Set sysStart 是否被禁用  0否 1禁用.
     */
    public void setSysStart(String sysStart) {
        this.sysStart = sysStart;
    }

    /**
     * Get sysStart 是否被禁用  0否 1禁用.
     *
     * @return the string
     */
    public String getSysStart() {
        return sysStart;
    }

    /**
     * Set updateBy 修改人.
     */
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    /**
     * Get updateBy 修改人.
     *
     * @return the string
     */
    public String getUpdateBy() {
        return updateBy;
    }

    /**
     * Set sysOpenDay 开通的天数 -1 不限次数 小为公司所有.
     */
    public void setSysOpenDay(String sysOpenDay) {
        this.sysOpenDay = sysOpenDay;
    }

    /**
     * Get sysOpenDay 开通的天数 -1 不限次数 小为公司所有.
     *
     * @return the string
     */
    public String getSysOpenDay() {
        return sysOpenDay;
    }

    /**
     * Set userGroupName 用户组名称/第三方公司名称.
     */
    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    /**
     * Get userGroupName 用户组名称/第三方公司名称.
     *
     * @return the string
     */
    public String getUserGroupName() {
        return userGroupName;
    }

    /**
     * Set userGroupUuid uuid.
     */
    public void setUserGroupUuid(String userGroupUuid) {
        this.userGroupUuid = userGroupUuid;
    }

    /**
     * Get userGroupUuid uuid.
     *
     * @return the string
     */
    public String getUserGroupUuid() {
        return userGroupUuid;
    }

    /**
     * Set sysReceDeviceNum 以接入设备总数.
     */
    public void setSysReceDeviceNum(Integer sysReceDeviceNum) {
        this.sysReceDeviceNum = sysReceDeviceNum;
    }

    /**
     * Get sysReceDeviceNum 以接入设备总数.
     *
     * @return the string
     */
    public Integer getSysReceDeviceNum() {
        return sysReceDeviceNum;
    }

    /**
     * Set sysAllowDeviceNum 允许接入设备总数.
     */
    public void setSysAllowDeviceNum(Integer sysAllowDeviceNum) {
        this.sysAllowDeviceNum = sysAllowDeviceNum;
    }

    /**
     * Get sysAllowDeviceNum 允许接入设备总数.
     *
     * @return the string
     */
    public Integer getSysAllowDeviceNum() {
        return sysAllowDeviceNum;
    }

    /**
     * Set sysCreateAccountNum 可以开通子账户的个数 -1不限制次数  id为1 为小为公司，不限次数.
     */
    public void setSysCreateAccountNum(Integer sysCreateAccountNum) {
        this.sysCreateAccountNum = sysCreateAccountNum;
    }

    /**
     * Get sysCreateAccountNum 可以开通子账户的个数 -1不限制次数  id为1 为小为公司，不限次数.
     *
     * @return the string
     */
    public Integer getSysCreateAccountNum() {
        return sysCreateAccountNum;
    }

    /**
     * Set createTime 创建时间.
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * Get createTime 创建时间.
     *
     * @return the string
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * Set updateTime 修改时间.
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * Get updateTime 修改时间.
     *
     * @return the string
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * Set sysAccountEndTime 账号结束时间.
     */
    public void setSysAccountEndTime(Date sysAccountEndTime) {
        this.sysAccountEndTime = sysAccountEndTime;
    }

    /**
     * Get sysAccountEndTime 账号结束时间.
     *
     * @return the string
     */
    public Date getSysAccountEndTime() {
        return sysAccountEndTime;
    }

    /**
     * Set sysAccountStartTime 账号开通时间 (不传值，默认系统时间).
     */
    public void setSysAccountStartTime(Date sysAccountStartTime) {
        this.sysAccountStartTime = sysAccountStartTime;
    }

    /**
     * Get sysAccountStartTime 账号开通时间 (不传值，默认系统时间).
     *
     * @return the string
     */
    public Date getSysAccountStartTime() {
        return sysAccountStartTime;
    }
}
