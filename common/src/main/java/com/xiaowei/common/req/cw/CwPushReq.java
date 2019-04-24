package com.xiaowei.common.req.cw;

import com.xiaowei.common.error.BaseReq;

import java.io.Serializable;

public class CwPushReq extends BaseReq implements Serializable {
    /**
     * faultPush 故障通知（0未推  1推）.
     */
    private Integer faultPush;
    /**
     * equipmentUuid uuid.
     */
    private String equipmentUuid;
    /**
     * deviceNo 设备编号.
     */

    private String deviceNo;
    private Boolean isFlag;

    public Boolean getFlag() {
        return isFlag;
    }

    public void setFlag(Boolean flag) {
        isFlag = flag;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getEquipmentUuid() {
        return equipmentUuid;
    }

    public void setEquipmentUuid(String equipmentUuid) {
        this.equipmentUuid = equipmentUuid;
    }

    public Integer getFaultPush() {
        return faultPush;
    }

    public void setFaultPush(Integer faultPush) {
        this.faultPush = faultPush;
    }

}
