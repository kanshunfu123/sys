package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

public class DictionaryParentReq extends BaseReq implements Serializable {
    @NotEmpty(message = "字典parentId不能为空",groups = {Delete.class})
    private Long parentId;

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
