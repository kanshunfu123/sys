package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TestUserReq extends BaseReq implements Serializable {
    /**
     * id ID.
     */
    private Long id;
    /**
     * name NAME.
     */
    private String name;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
