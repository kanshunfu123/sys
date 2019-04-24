package com.xiaowei.mana.mapper.resultmap;

import java.util.Date;

public class GetDictionaryInfoByUuid {
    private static final long serialVersionUID = -1L;

    /**
     * id .
     */
    private Long id;
    /**
     * parentId .
     */
    private Long parentId;
    /**
     * delFlag .
     */
    private String delFlag;

    /**
     * sysDictionaryUuid .
     */
    private String sysDictionaryUuid;
    /**
     * codeLevel .
     */
    private String codeLevel;
    /**
     * sysDictionarySeq .
     */
    private Integer sysDictionarySeq;
    /**
     * codeName .
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
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;

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

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSysDictionaryUuid() {
        return sysDictionaryUuid;
    }

    public void setSysDictionaryUuid(String sysDictionaryUuid) {
        this.sysDictionaryUuid = sysDictionaryUuid;
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

    public String getCodeName() {
        return codeName;
    }

    public void setCodeName(String codeName) {
        this.codeName = codeName;
    }
}
