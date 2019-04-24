package com.xiaowei.mana.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mana.mapper.dataobject.UserDO;

/**
 * The table 用户
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class UserDO{

    /**
     * id 主键.
     */
    private Long id;
    /**
     * deptId 关联部门id.
     */
    private Long deptId;
    /**
     * groupId 第三方组 小为默认为1.
     */
    private Long groupId;
    /**
     * sysUserArea 区.
     */
    private Long sysUserArea;
    /**
     * sysUserCity 市.
     */
    private Long sysUserCity;
    /**
     * sysUserProvince 省.
     */
    private Long sysUserProvince;
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
     * roleType 冗余角色类型 0管理员(专为第三方老板而设置） 1普通员工.
     */
    private String roleType;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * nameBottom 版权.
     */
    private String nameBottom;
    /**
     * sysUserPwd 密码.
     */
    private String sysUserPwd;
    /**
     * isForbidden 是否被禁用  0否 1禁用.
     */
    private String isForbidden;
    /**
     * sysUserName 姓名.
     */
    private String sysUserName;
    /**
     * sysUserUuid 唯一，32位字符串，查询用.
     */
    private String sysUserUuid;
    /**
     * sysUserEmail 邮箱.
     */
    private String sysUserEmail;
    /**
     * sysUserPhone 手机号.
     */
    private String sysUserPhone;
    /**
     * sysLoginNumber 账号允许登录的次数 -1 不限次数 ，如需登录次数为0，请禁用该账号，减少代码复杂度.
     */
    private String sysLoginNumber;
    /**
     * sysUserAreaName 区名字.
     */
    private String sysUserAreaName;
    /**
     * sysUserAuthSalt 撒盐.
     */
    private String sysUserAuthSalt;
    /**
     * sysUserCityName 市名字.
     */
    private String sysUserCityName;
    /**
     * sysUserLoginName 登录名.
     */
    private String sysUserLoginName;
    /**
     * sysUserProvinceName 省 名字.
     */
    private String sysUserProvinceName;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;

    /**
     * Set id 主键.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id 主键.
     *
     * @return the string
     */
    public Long getId(){
        return id;
    }

    /**
     * Set deptId 关联部门id.
     */
    public void setDeptId(Long deptId){
        this.deptId = deptId;
    }

    /**
     * Get deptId 关联部门id.
     *
     * @return the string
     */
    public Long getDeptId(){
        return deptId;
    }

    /**
     * Set groupId 第三方组 小为默认为1.
     */
    public void setGroupId(Long groupId){
        this.groupId = groupId;
    }

    /**
     * Get groupId 第三方组 小为默认为1.
     *
     * @return the string
     */
    public Long getGroupId(){
        return groupId;
    }

    /**
     * Set sysUserArea 区.
     */
    public void setSysUserArea(Long sysUserArea){
        this.sysUserArea = sysUserArea;
    }

    /**
     * Get sysUserArea 区.
     *
     * @return the string
     */
    public Long getSysUserArea(){
        return sysUserArea;
    }

    /**
     * Set sysUserCity 市.
     */
    public void setSysUserCity(Long sysUserCity){
        this.sysUserCity = sysUserCity;
    }

    /**
     * Get sysUserCity 市.
     *
     * @return the string
     */
    public Long getSysUserCity(){
        return sysUserCity;
    }

    /**
     * Set sysUserProvince 省.
     */
    public void setSysUserProvince(Long sysUserProvince){
        this.sysUserProvince = sysUserProvince;
    }

    /**
     * Get sysUserProvince 省.
     *
     * @return the string
     */
    public Long getSysUserProvince(){
        return sysUserProvince;
    }

    /**
     * Set delFlag 删除状态(0-正常，1-删除).
     */
    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag 删除状态(0-正常，1-删除).
     *
     * @return the string
     */
    public String getDelFlag(){
        return delFlag;
    }

    /**
     * Set nameTop 顶部名称.
     */
    public void setNameTop(String nameTop){
        this.nameTop = nameTop;
    }

    /**
     * Get nameTop 顶部名称.
     *
     * @return the string
     */
    public String getNameTop(){
        return nameTop;
    }

    /**
     * Set createBy 创建人.
     */
    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    /**
     * Get createBy 创建人.
     *
     * @return the string
     */
    public String getCreateBy(){
        return createBy;
    }

    /**
     * Set roleType 冗余角色类型 0管理员(专为第三方老板而设置） 1普通员工.
     */
    public void setRoleType(String roleType){
        this.roleType = roleType;
    }

    /**
     * Get roleType 冗余角色类型 0管理员(专为第三方老板而设置） 1普通员工.
     *
     * @return the string
     */
    public String getRoleType(){
        return roleType;
    }

    /**
     * Set updateBy 修改人.
     */
    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    /**
     * Get updateBy 修改人.
     *
     * @return the string
     */
    public String getUpdateBy(){
        return updateBy;
    }

    /**
     * Set nameBottom 版权.
     */
    public void setNameBottom(String nameBottom){
        this.nameBottom = nameBottom;
    }

    /**
     * Get nameBottom 版权.
     *
     * @return the string
     */
    public String getNameBottom(){
        return nameBottom;
    }

    /**
     * Set sysUserPwd 密码.
     */
    public void setSysUserPwd(String sysUserPwd){
        this.sysUserPwd = sysUserPwd;
    }

    /**
     * Get sysUserPwd 密码.
     *
     * @return the string
     */
    public String getSysUserPwd(){
        return sysUserPwd;
    }

    /**
     * Set isForbidden 是否被禁用  0否 1禁用.
     */
    public void setIsForbidden(String isForbidden){
        this.isForbidden = isForbidden;
    }

    /**
     * Get isForbidden 是否被禁用  0否 1禁用.
     *
     * @return the string
     */
    public String getIsForbidden(){
        return isForbidden;
    }

    /**
     * Set sysUserName 姓名.
     */
    public void setSysUserName(String sysUserName){
        this.sysUserName = sysUserName;
    }

    /**
     * Get sysUserName 姓名.
     *
     * @return the string
     */
    public String getSysUserName(){
        return sysUserName;
    }

    /**
     * Set sysUserUuid 唯一，32位字符串，查询用.
     */
    public void setSysUserUuid(String sysUserUuid){
        this.sysUserUuid = sysUserUuid;
    }

    /**
     * Get sysUserUuid 唯一，32位字符串，查询用.
     *
     * @return the string
     */
    public String getSysUserUuid(){
        return sysUserUuid;
    }

    /**
     * Set sysUserEmail 邮箱.
     */
    public void setSysUserEmail(String sysUserEmail){
        this.sysUserEmail = sysUserEmail;
    }

    /**
     * Get sysUserEmail 邮箱.
     *
     * @return the string
     */
    public String getSysUserEmail(){
        return sysUserEmail;
    }

    /**
     * Set sysUserPhone 手机号.
     */
    public void setSysUserPhone(String sysUserPhone){
        this.sysUserPhone = sysUserPhone;
    }

    /**
     * Get sysUserPhone 手机号.
     *
     * @return the string
     */
    public String getSysUserPhone(){
        return sysUserPhone;
    }

    /**
     * Set sysLoginNumber 账号允许登录的次数 -1 不限次数 ，如需登录次数为0，请禁用该账号，减少代码复杂度.
     */
    public void setSysLoginNumber(String sysLoginNumber){
        this.sysLoginNumber = sysLoginNumber;
    }

    /**
     * Get sysLoginNumber 账号允许登录的次数 -1 不限次数 ，如需登录次数为0，请禁用该账号，减少代码复杂度.
     *
     * @return the string
     */
    public String getSysLoginNumber(){
        return sysLoginNumber;
    }

    /**
     * Set sysUserAreaName 区名字.
     */
    public void setSysUserAreaName(String sysUserAreaName){
        this.sysUserAreaName = sysUserAreaName;
    }

    /**
     * Get sysUserAreaName 区名字.
     *
     * @return the string
     */
    public String getSysUserAreaName(){
        return sysUserAreaName;
    }

    /**
     * Set sysUserAuthSalt 撒盐.
     */
    public void setSysUserAuthSalt(String sysUserAuthSalt){
        this.sysUserAuthSalt = sysUserAuthSalt;
    }

    /**
     * Get sysUserAuthSalt 撒盐.
     *
     * @return the string
     */
    public String getSysUserAuthSalt(){
        return sysUserAuthSalt;
    }

    /**
     * Set sysUserCityName 市名字.
     */
    public void setSysUserCityName(String sysUserCityName){
        this.sysUserCityName = sysUserCityName;
    }

    /**
     * Get sysUserCityName 市名字.
     *
     * @return the string
     */
    public String getSysUserCityName(){
        return sysUserCityName;
    }

    /**
     * Set sysUserLoginName 登录名.
     */
    public void setSysUserLoginName(String sysUserLoginName){
        this.sysUserLoginName = sysUserLoginName;
    }

    /**
     * Get sysUserLoginName 登录名.
     *
     * @return the string
     */
    public String getSysUserLoginName(){
        return sysUserLoginName;
    }

    /**
     * Set sysUserProvinceName 省 名字.
     */
    public void setSysUserProvinceName(String sysUserProvinceName){
        this.sysUserProvinceName = sysUserProvinceName;
    }

    /**
     * Get sysUserProvinceName 省 名字.
     *
     * @return the string
     */
    public String getSysUserProvinceName(){
        return sysUserProvinceName;
    }

    /**
     * Set createTime 创建时间.
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     * Get createTime 创建时间.
     *
     * @return the string
     */
    public Date getCreateTime(){
        return createTime;
    }

    /**
     * Set updateTime 修改时间.
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
     * Get updateTime 修改时间.
     *
     * @return the string
     */
    public Date getUpdateTime(){
        return updateTime;
    }
}
