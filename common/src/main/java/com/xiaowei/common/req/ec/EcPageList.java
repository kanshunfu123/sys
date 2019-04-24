package com.xiaowei.common.req.ec;

import com.xiaowei.common.error.BaseReq;

import javax.validation.constraints.Min;
import java.io.Serializable;

/**
 * Created by kanshunfu on 2019/01/14.
 */
public class EcPageList extends BaseReq implements Serializable {
    @Min(value = 1, message = "每页显示记录数不能为空，且只能为整数", groups = {Query.class})
    private Integer limit = 10;//每页显示记录数
    @Min(value = 1, message = "页码不能为空，且只能为整数", groups = {Query.class})
    private Integer currPageNo = 1;//当前页 页码

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
}
