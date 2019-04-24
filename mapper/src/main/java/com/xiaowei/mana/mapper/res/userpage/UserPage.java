package com.xiaowei.mana.mapper.res.userpage;

import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dataobject.RoleDO;
import com.xiaowei.mana.mapper.dataobject.UserDO;

import java.security.cert.TrustAnchor;
import java.util.Date;
import java.util.List;

/**
 * Created by MOMO on 2019/1/8.
 */
public class UserPage extends UserDO{
    //用户创建时间
//    private String ucreateTimeStr;
    //角色列表
    private List<RoleDO> roleNameList;
    //用户所在的组

    //部门名称
    private String deptName;
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
     * sysCreateAccountNum 可以开通子账户的个数 -1不限制次数  id为0 为小为公司，不限次数.
     */
    private Integer sysCreateAccountNum;
    /**
     * createTime 创建时间.
     */
    private Date gcreateTime;

    /**
     * sysAccountEndTime 账号结束时间.
     */
    private Date sysAccountEndTime;
    /**
     * sysAccountStartTime 账号开通时间 (不传值，默认系统时间).
     */
    private Date sysAccountStartTime;

    public List<RoleDO> getRoleNameList() {
        return roleNameList;
    }

    public void setRoleNameList(List<RoleDO> roleNameList) {
        this.roleNameList = roleNameList;
    }

    public String getSysStart() {
        return sysStart;
    }

    public void setSysStart(String sysStart) {
        this.sysStart = sysStart;
    }

    public String getSysOpenDay() {
        return sysOpenDay;
    }

    public void setSysOpenDay(String sysOpenDay) {
        this.sysOpenDay = sysOpenDay;
    }

    public String getUserGroupName() {
        return userGroupName;
    }

    public void setUserGroupName(String userGroupName) {
        this.userGroupName = userGroupName;
    }

    public Integer getSysCreateAccountNum() {
        return sysCreateAccountNum;
    }

    public void setSysCreateAccountNum(Integer sysCreateAccountNum) {
        this.sysCreateAccountNum = sysCreateAccountNum;
    }

    public Date getGcreateTime() {
        return gcreateTime;
    }

    public void setGcreateTime(Date gcreateTime) {
        this.gcreateTime = gcreateTime;
    }

    public Date getSysAccountEndTime() {
        return sysAccountEndTime;
    }

    public void setSysAccountEndTime(Date sysAccountEndTime) {
        this.sysAccountEndTime = sysAccountEndTime;
    }

    public Date getSysAccountStartTime() {
        return sysAccountStartTime;
    }

    public void setSysAccountStartTime(Date sysAccountStartTime) {
        this.sysAccountStartTime = sysAccountStartTime;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }
}
