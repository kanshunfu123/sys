package com.xiaowei.mana.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mana.mapper.dataobject.AclModuleDO;

/**
 * The table 权限模块表   引入权限模块就可以很容易把菜单层级定义出来，每个菜单项下面有哪些功能就可以在权限模块下面定义权限点，然后就可以根据每个人分配到的权限生成不同的基于权限的菜单
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class AclModuleDO{

    /**
     * id 权限模块id.
     */
    private Long id;
    /**
     * sysAclModuleParentId 上级权限模块id.
     */
    private Long sysAclModuleParentId;
    /**
     * sysAclPermissionType 菜单系统类型 1 系统管理 2资产管理.
     */
    private Long sysAclPermissionType;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
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
    private String sysAclModuleName;
    /**
     * sysAclModuleUuid 唯一，32位字符串，查询用.
     */
    private String sysAclModuleUuid;
    /**
     * sysAclModuleLevel 权限模块层级.
     */
    private String sysAclModuleLevel;
    /**
     * sysAclModuleRemark 备注.
     */
    private String sysAclModuleRemark;
    /**
     * sysAclModuleSeq 权限模块在当前层级下的顺序，由小到大.
     */
    private Integer sysAclModuleSeq;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;

    /**
     * Set id 权限模块id.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id 权限模块id.
     *
     * @return the string
     */
    public Long getId(){
        return id;
    }

    /**
     * Set sysAclModuleParentId 上级权限模块id.
     */
    public void setSysAclModuleParentId(Long sysAclModuleParentId){
        this.sysAclModuleParentId = sysAclModuleParentId;
    }

    /**
     * Get sysAclModuleParentId 上级权限模块id.
     *
     * @return the string
     */
    public Long getSysAclModuleParentId(){
        return sysAclModuleParentId;
    }

    /**
     * Set sysAclPermissionType 菜单系统类型 1 系统管理 2资产管理.
     */
    public void setSysAclPermissionType(Long sysAclPermissionType){
        this.sysAclPermissionType = sysAclPermissionType;
    }

    /**
     * Get sysAclPermissionType 菜单系统类型 1 系统管理 2资产管理.
     *
     * @return the string
     */
    public Long getSysAclPermissionType(){
        return sysAclPermissionType;
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
     * Set sysAclModuleCode 权限模块码.
     */
    public void setSysAclModuleCode(String sysAclModuleCode){
        this.sysAclModuleCode = sysAclModuleCode;
    }

    /**
     * Get sysAclModuleCode 权限模块码.
     *
     * @return the string
     */
    public String getSysAclModuleCode(){
        return sysAclModuleCode;
    }

    /**
     * Set sysAclModuleIcon 图标class.
     */
    public void setSysAclModuleIcon(String sysAclModuleIcon){
        this.sysAclModuleIcon = sysAclModuleIcon;
    }

    /**
     * Get sysAclModuleIcon 图标class.
     *
     * @return the string
     */
    public String getSysAclModuleIcon(){
        return sysAclModuleIcon;
    }

    /**
     * Set sysAclModuleName 权限模块名称.
     */
    public void setSysAclModuleName(String sysAclModuleName){
        this.sysAclModuleName = sysAclModuleName;
    }

    /**
     * Get sysAclModuleName 权限模块名称.
     *
     * @return the string
     */
    public String getSysAclModuleName(){
        return sysAclModuleName;
    }

    /**
     * Set sysAclModuleUuid 唯一，32位字符串，查询用.
     */
    public void setSysAclModuleUuid(String sysAclModuleUuid){
        this.sysAclModuleUuid = sysAclModuleUuid;
    }

    /**
     * Get sysAclModuleUuid 唯一，32位字符串，查询用.
     *
     * @return the string
     */
    public String getSysAclModuleUuid(){
        return sysAclModuleUuid;
    }

    /**
     * Set sysAclModuleLevel 权限模块层级.
     */
    public void setSysAclModuleLevel(String sysAclModuleLevel){
        this.sysAclModuleLevel = sysAclModuleLevel;
    }

    /**
     * Get sysAclModuleLevel 权限模块层级.
     *
     * @return the string
     */
    public String getSysAclModuleLevel(){
        return sysAclModuleLevel;
    }

    /**
     * Set sysAclModuleRemark 备注.
     */
    public void setSysAclModuleRemark(String sysAclModuleRemark){
        this.sysAclModuleRemark = sysAclModuleRemark;
    }

    /**
     * Get sysAclModuleRemark 备注.
     *
     * @return the string
     */
    public String getSysAclModuleRemark(){
        return sysAclModuleRemark;
    }

    /**
     * Set sysAclModuleSeq 权限模块在当前层级下的顺序，由小到大.
     */
    public void setSysAclModuleSeq(Integer sysAclModuleSeq){
        this.sysAclModuleSeq = sysAclModuleSeq;
    }

    /**
     * Get sysAclModuleSeq 权限模块在当前层级下的顺序，由小到大.
     *
     * @return the string
     */
    public Integer getSysAclModuleSeq(){
        return sysAclModuleSeq;
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
