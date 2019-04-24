package com.xiaowei.common.vo;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by kanshunfu on 2019/01/08.
 */
public class DictionaryVO implements Serializable {
    /**
     * id 字典id.
     */
    private Long id;
    /**
     * parentId 上级id.
     */
    private Long parentId;
    /**
     * uuid uuid.
     */
    private String sysDictionaryUuid;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * codeName 代码名称.
     */
    private String codeName;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * codeLevel 层级.
     */
    private String codeLevel;

    /**
     * sysDictionarySeq 排列序号.
     */
    private Integer sysDictionarySeq;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * 代码值
     */
    private Long codeValue;

    public Long getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(Long codeValue) {
        this.codeValue = codeValue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getSysDictionaryUuid() {
        return sysDictionaryUuid;
    }

    public void setSysDictionaryUuid(String sysDictionaryUuid) {
        this.sysDictionaryUuid = sysDictionaryUuid;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
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

    public String getCodeLevel() {
        return codeLevel;
    }

    public void setCodeLevel(String codeLevel) {
        this.codeLevel = codeLevel;
    }

    public Integer getSysDictionarySeq() {
        return sysDictionarySeq;
    }

    public void setSysDictionarySeq(Integer sysDictionarySeq) {
        this.sysDictionarySeq = sysDictionarySeq;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
