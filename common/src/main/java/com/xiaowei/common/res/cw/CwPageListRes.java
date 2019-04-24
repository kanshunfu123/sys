package com.xiaowei.common.res.cw;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CwPageListRes implements Serializable {
    /**
     * installMan 安装人.
     */
    private String installMan;
    /**
     * setupTime 设备安装时间.
     */
    private String setupTime;

    /**
     * 位置
     */
    private String localtion;

    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;

    /**
     * 设备类型
     * @return
     */
    private String deviceType;
    /**
     * 设备uuid
     * @return
     */
    private String equipmentUuid;
    /**
     * faultPush 故障通知（0未推  1推）.
     */
    private Integer faultPush;
    /**
     * deviceName 设备名称.
     */
    private String deviceName;
    /**
     * 设备别名
     */
    private String deviceCode;

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public Integer getFaultPush() {
        return faultPush;
    }

    public void setFaultPush(Integer faultPush) {
        this.faultPush = faultPush;
    }

    public String getEquipmentUuid() {
        return equipmentUuid;
    }

    public void setEquipmentUuid(String equipmentUuid) {
        this.equipmentUuid = equipmentUuid;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }


    public String getInstallMan() {
        return installMan;
    }

    public void setInstallMan(String installMan) {
        this.installMan = installMan;
    }


    public String getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(String setupTime) {
        this.setupTime = setupTime;
    }

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }
}
