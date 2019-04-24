package com.xiaowei.common.req.village;

import com.xiaowei.common.error.BaseReq;

import javax.validation.constraints.Min;
import java.io.Serializable;

public class VaPageListReq extends BaseReq implements Serializable {
    @Min(value = 1, message = "每页显示记录数不能为空，且只能为整数", groups = {Query.class})
    private Integer limit = 10;//每页显示记录数
    @Min(value = 1, message = "页码不能为空，且只能为整数", groups = {Query.class})
    private Integer currPageNo = 1;//当前页 页码
    /**
     * 小区名称
     */
    private String villageName;

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(Integer currPageNo) {
        this.currPageNo = currPageNo;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }
}
