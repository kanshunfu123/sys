package com.xiaowei.common.req.home;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/21
 */
public class AreaV2Req implements Serializable {
    private Integer level;
    private Long parentId;

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }
}
