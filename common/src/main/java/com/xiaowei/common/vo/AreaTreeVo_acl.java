package com.xiaowei.common.vo;

import java.io.Serializable;
import java.util.List;

public class AreaTreeVo_acl implements Serializable {
    private List<AreaTreeVo_acl> children;
    // 是否要默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasAcl = false;
    /**
     * id ID.
     */
    private String id;
    private Long pryId;
    private Long trueId;

    public Long getTrueId() {
        return trueId;
    }

    public void setTrueId(Long trueId) {
        this.trueId = trueId;
    }

    /**
     * sysAreaParentId 父级id .
     */
    private Long sysAreaParentId;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;

    /**
     * sysAreaName 区域名称.
     */
    private String sysAreaName;
    /**
     * sysAreaUuid 唯一，32位字符串，查询用.
     */
    private String sysAreaUuid;
    /**
     * sysAreaLevel 当前模块层级.
     */
    private String sysAreaLevel;
    /**
     * sysWeathCode 天气码.
     */
    private String sysWeathCode;
    /**
     * sysAreaRemark 备注.
     */
    private String sysAreaRemark;
    /**
     * sysAreaSeq 排序.
     */
    private Integer sysAreaSeq;

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public boolean isHasAcl() {
        return hasAcl;
    }

    public void setHasAcl(boolean hasAcl) {
        this.hasAcl = hasAcl;
    }

    public List<AreaTreeVo_acl> getChildren() {
        return children;
    }

    public void setChildren(List<AreaTreeVo_acl> children) {
        this.children = children;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Long getPryId() {
        return pryId;
    }

    public void setPryId(Long pryId) {
        this.pryId = pryId;
    }

    public Long getSysAreaParentId() {
        return sysAreaParentId;
    }

    public void setSysAreaParentId(Long sysAreaParentId) {
        this.sysAreaParentId = sysAreaParentId;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSysAreaName() {
        return sysAreaName;
    }

    public void setSysAreaName(String sysAreaName) {
        this.sysAreaName = sysAreaName;
    }

    public String getSysAreaUuid() {
        return sysAreaUuid;
    }

    public void setSysAreaUuid(String sysAreaUuid) {
        this.sysAreaUuid = sysAreaUuid;
    }

    public String getSysAreaLevel() {
        return sysAreaLevel;
    }

    public void setSysAreaLevel(String sysAreaLevel) {
        this.sysAreaLevel = sysAreaLevel;
    }

    public String getSysWeathCode() {
        return sysWeathCode;
    }

    public void setSysWeathCode(String sysWeathCode) {
        this.sysWeathCode = sysWeathCode;
    }

    public String getSysAreaRemark() {
        return sysAreaRemark;
    }

    public void setSysAreaRemark(String sysAreaRemark) {
        this.sysAreaRemark = sysAreaRemark;
    }

    public Integer getSysAreaSeq() {
        return sysAreaSeq;
    }

    public void setSysAreaSeq(Integer sysAreaSeq) {
        this.sysAreaSeq = sysAreaSeq;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreaTreeVo_acl that = (AreaTreeVo_acl) o;

        if (checked != that.checked) return false;
        if (hasAcl != that.hasAcl) return false;
        if (children != null ? !children.equals(that.children) : that.children != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (sysAreaParentId != null ? !sysAreaParentId.equals(that.sysAreaParentId) : that.sysAreaParentId != null)
            return false;
        if (delFlag != null ? !delFlag.equals(that.delFlag) : that.delFlag != null) return false;
        if (sysAreaName != null ? !sysAreaName.equals(that.sysAreaName) : that.sysAreaName != null) return false;
        if (sysAreaUuid != null ? !sysAreaUuid.equals(that.sysAreaUuid) : that.sysAreaUuid != null) return false;
        if (sysAreaLevel != null ? !sysAreaLevel.equals(that.sysAreaLevel) : that.sysAreaLevel != null) return false;
        if (sysWeathCode != null ? !sysWeathCode.equals(that.sysWeathCode) : that.sysWeathCode != null) return false;
        if (sysAreaRemark != null ? !sysAreaRemark.equals(that.sysAreaRemark) : that.sysAreaRemark != null)
            return false;
        return sysAreaSeq != null ? sysAreaSeq.equals(that.sysAreaSeq) : that.sysAreaSeq == null;
    }

    @Override
    public int hashCode() {
        int result = children != null ? children.hashCode() : 0;
        result = 31 * result + (checked ? 1 : 0);
        result = 31 * result + (hasAcl ? 1 : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (sysAreaParentId != null ? sysAreaParentId.hashCode() : 0);
        result = 31 * result + (delFlag != null ? delFlag.hashCode() : 0);
        result = 31 * result + (sysAreaName != null ? sysAreaName.hashCode() : 0);
        result = 31 * result + (sysAreaUuid != null ? sysAreaUuid.hashCode() : 0);
        result = 31 * result + (sysAreaLevel != null ? sysAreaLevel.hashCode() : 0);
        result = 31 * result + (sysWeathCode != null ? sysWeathCode.hashCode() : 0);
        result = 31 * result + (sysAreaRemark != null ? sysAreaRemark.hashCode() : 0);
        result = 31 * result + (sysAreaSeq != null ? sysAreaSeq.hashCode() : 0);
        return result;
    }
}
