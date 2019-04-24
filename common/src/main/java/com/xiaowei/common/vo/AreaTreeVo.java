package com.xiaowei.common.vo;

import java.io.Serializable;
import java.util.List;

public class AreaTreeVo implements Serializable {
    private List<AreaTreeVo> children;
    /**
     * id ID.
     */
    private Long id;
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

    public List<AreaTreeVo> getChildren() {
        return children;
    }

    public void setChildren(List<AreaTreeVo> children) {
        this.children = children;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
}
