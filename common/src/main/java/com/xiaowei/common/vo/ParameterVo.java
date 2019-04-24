package com.xiaowei.common.vo;

import com.xiaowei.common.error.BaseReq;

import java.io.Serializable;
import java.util.Date;

public class ParameterVo extends BaseReq implements Serializable {
    private Long id;

    private String sysParameterUuid;

    private String parameterName;

    private Integer sysParameterSeq;

    private String delFlag;

    private String sysParameterRemark;

    private Long dictionaryId;

    private String createBy;

    private Date createTime;

    private Date updateTime;

    private String updateBy;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSysParameterUuid() {
        return sysParameterUuid;
    }

    public void setSysParameterUuid(String sysParameterUuid) {
        this.sysParameterUuid = sysParameterUuid;
    }

    public String getParameterName() {
        return parameterName;
    }

    public void setParameterName(String parameterName) {
        this.parameterName = parameterName;
    }

    public Integer getSysParameterSeq() {
        return sysParameterSeq;
    }

    public void setSysParameterSeq(Integer sysParameterSeq) {
        this.sysParameterSeq = sysParameterSeq;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getSysParameterRemark() {
        return sysParameterRemark;
    }

    public void setSysParameterRemark(String sysParameterRemark) {
        this.sysParameterRemark = sysParameterRemark;
    }

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
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

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
