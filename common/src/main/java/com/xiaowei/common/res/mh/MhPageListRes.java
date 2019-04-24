package com.xiaowei.common.res.mh;

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
public class MhPageListRes implements Serializable {

    /**
     * area 区.
     */
    private String area;
    /**
     * city 市.
     */
    private String city;
    /**
     * street 街道.
     */
    private String street;
    /**
     * vendor 设备生产厂商名称.
     */
    private String vendor;
    /**
     * address 详细地址（可填可不填）.
     */
    private String address;



    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * province 省.
     */
    private String province;

    /**
     * installMan 安装人.
     */
    private String installMan;

    /**
     * villageName 小区名.
     */
    private String villageName;
    /**
     * setupTime 设备安装时间.
     */
    private String setupTime;
    /**
     * 安装位置
     */
    private String localtion;

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

    public String getLocaltion() {
        return localtion;
    }

    public void setLocaltion(String localtion) {
        this.localtion = localtion;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(String setupTime) {
        this.setupTime = setupTime;
    }


}
