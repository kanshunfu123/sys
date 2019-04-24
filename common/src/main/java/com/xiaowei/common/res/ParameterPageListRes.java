package com.xiaowei.common.res;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParameterPageListRes implements Serializable {
    private Long id;


    private String parameterName;

    private Integer sysParameterSeq;

    private String delFlag;

    private Long dictionaryId;

    private String sysParameterUuid;

    public String getSysParameterUuid() {
        return sysParameterUuid;
    }

    public void setSysParameterUuid(String sysParameterUuid) {
        this.sysParameterUuid = sysParameterUuid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }
}
