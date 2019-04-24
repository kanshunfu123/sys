package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class ParameterDictReq extends BaseReq implements Serializable {
    @NotEmpty(message = "参数dictionaryId不能为空",groups = {Delete.class})
    private Long dictionaryId;

    public Long getDictionaryId() {
        return dictionaryId;
    }

    public void setDictionaryId(Long dictionaryId) {
        this.dictionaryId = dictionaryId;
    }
}
