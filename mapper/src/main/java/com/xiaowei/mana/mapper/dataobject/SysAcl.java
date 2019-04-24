package com.xiaowei.mana.mapper.dataobject;

import lombok.*;

/**
 * Created by MOMO on 2019/1/10.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = {"id"})
public class SysAcl {
    /**
     * id 权限id.
     */
    private Long id;
    /**
     * sysAclStatus 状态 0启用  1禁用.
     */
    private String sysAclStatus;
    /**
     * sysAclModuleId 权限所在的权限模块id.
     */
    private Long sysAclModuleId;
    /**
     * name 页面名称(前端控制).
     */
    private String name;
    /**
     * remark 备注.
     */
    private String remark;
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
     * sysAclUrl 请求的url, 可以填正则表达式.
     */
    private String sysAclUrl;
    /**
     * sysAclCode 权限码.
     */
    private String sysAclCode;
    /**
     * sysAclIcon 图标class.
     */
    private String sysAclIcon;
    /**
     * sysAclName 权限名称.
     */
    private String sysAclName;
    /**
     * sysAclType 类型，1：菜单，2：按钮，3：其他.
     */
    private String sysAclType;
    /**
     * sysAclUuid 唯一，32位字符串，查询用.
     */
    private String sysAclUuid;
    /**
     * sysAclAction 按钮动作类型(交给前端处理）.
     */
    private String sysAclAction;
    /**
     * sysAclRouter 所属页面(交给前端处理).
     */
    private String sysAclRouter;
    /**
     * sysAclPermissionType 菜单系统类型 1 系统管理 2资产管理.
     */
    private Long sysAclPermissionType;
    /**
     * sysAclSeq 权限在当前模块下的顺序，由小到大.
     */
    private Integer sysAclSeq;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysAclStatus() {
        return sysAclStatus;
    }

    public void setSysAclStatus(String sysAclStatus) {
        this.sysAclStatus = sysAclStatus;
    }

    public Long getSysAclModuleId() {
        return sysAclModuleId;
    }

    public void setSysAclModuleId(Long sysAclModuleId) {
        this.sysAclModuleId = sysAclModuleId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getSysAclUrl() {
        return sysAclUrl;
    }

    public void setSysAclUrl(String sysAclUrl) {
        this.sysAclUrl = sysAclUrl;
    }

    public String getSysAclCode() {
        return sysAclCode;
    }

    public void setSysAclCode(String sysAclCode) {
        this.sysAclCode = sysAclCode;
    }

    public String getSysAclIcon() {
        return sysAclIcon;
    }

    public void setSysAclIcon(String sysAclIcon) {
        this.sysAclIcon = sysAclIcon;
    }

    public String getSysAclName() {
        return sysAclName;
    }

    public void setSysAclName(String sysAclName) {
        this.sysAclName = sysAclName;
    }

    public String getSysAclType() {
        return sysAclType;
    }

    public void setSysAclType(String sysAclType) {
        this.sysAclType = sysAclType;
    }

    public String getSysAclUuid() {
        return sysAclUuid;
    }

    public void setSysAclUuid(String sysAclUuid) {
        this.sysAclUuid = sysAclUuid;
    }

    public String getSysAclAction() {
        return sysAclAction;
    }

    public void setSysAclAction(String sysAclAction) {
        this.sysAclAction = sysAclAction;
    }

    public String getSysAclRouter() {
        return sysAclRouter;
    }

    public void setSysAclRouter(String sysAclRouter) {
        this.sysAclRouter = sysAclRouter;
    }


    public Integer getSysAclSeq() {
        return sysAclSeq;
    }

    public void setSysAclSeq(Integer sysAclSeq) {
        this.sysAclSeq = sysAclSeq;
    }

    public Long getSysAclPermissionType() {
        return sysAclPermissionType;
    }

    public void setSysAclPermissionType(Long sysAclPermissionType) {
        this.sysAclPermissionType = sysAclPermissionType;
    }
}
