package com.xiaowei.common.req;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

/**
 * Created by MOMO on 2019/1/24.
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRoleReq {

    //当前页
    private int pageNum=1;
    //每页的数量
    private int pageSize=20;

    /**
     * sysRoleName 角色名称.
     */
    private String sysRoleName;

    public String getSysRoleName() {
        return sysRoleName;
    }

    public void setSysRoleName(String sysRoleName) {
        this.sysRoleName = sysRoleName;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
