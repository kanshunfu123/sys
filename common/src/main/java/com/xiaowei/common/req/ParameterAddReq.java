package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
/**
 * Created by kanshunfu on 2019/01/08.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParameterAddReq extends BaseReq implements Serializable {
    private Long id;

    private String sysParameterUuid;

    private String parameterName;

    private Integer sysParameterSeq;

    private String delFlag;

    private String sysParameterRemark;

    private Long dictionaryId;

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
}
