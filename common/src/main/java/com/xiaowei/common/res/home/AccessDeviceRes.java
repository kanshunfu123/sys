package com.xiaowei.common.res.home;

import java.io.Serializable;

/**
 * created by 韩金群 2019/2/19
 */
public class AccessDeviceRes implements Serializable {
    //数量
    private Integer number;
    //月份
    private String month;

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getMonth() {
        return month;
    }

    public void setMonth(String month) {
        this.month = month;
    }
}
