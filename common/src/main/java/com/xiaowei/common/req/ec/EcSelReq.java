package com.xiaowei.common.req.ec;

import com.xiaowei.common.error.BaseReq;

import java.io.Serializable;

public class EcSelReq extends BaseReq implements Serializable {
    /**
     * id 主键.
     */
    private Long id;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
