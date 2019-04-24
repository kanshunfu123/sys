package com.xiaowei.common.res.cw;

import java.io.Serializable;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public class CwSenceRes implements Serializable {
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * tankSeal 密封情况.
     */
    private String tankSeal;
    /**
     * tankSize 水箱尺寸.
     */
    private String tankSize;
    /**
     * tankMaterial 水箱材质.
     */
    private String tankMaterial;
    /**
     * principalPhone 负责人联系方式.
     */
    private String principalPhone;
    /**
     * sencePrincipal 负责人.
     */
    private String sencePrincipal;
    /**
     * cleaningFrequency 清洁频率.
     */
    private String cleaningFrequency;
    /**
     * waterSupportStatus 供水厂详情.
     */
    private String waterSupportStatus;
    /**
     * sencePropertyCompany 场景物业公司.
     */
    private String sencePropertyCompany;
    /**
     * lastCleaningTime 上次清洗时间.
     */
    private String lastCleaningTime;
    /**
     * senceInstallTime 场景安装时间.
     */
    private String senceInstallTime;
    /**
     * 位置
     */
    private String location;

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getTankSeal() {
        return tankSeal;
    }

    public void setTankSeal(String tankSeal) {
        this.tankSeal = tankSeal;
    }

    public String getTankSize() {
        return tankSize;
    }

    public void setTankSize(String tankSize) {
        this.tankSize = tankSize;
    }

    public String getTankMaterial() {
        return tankMaterial;
    }

    public void setTankMaterial(String tankMaterial) {
        this.tankMaterial = tankMaterial;
    }

    public String getPrincipalPhone() {
        return principalPhone;
    }

    public void setPrincipalPhone(String principalPhone) {
        this.principalPhone = principalPhone;
    }

    public String getSencePrincipal() {
        return sencePrincipal;
    }

    public void setSencePrincipal(String sencePrincipal) {
        this.sencePrincipal = sencePrincipal;
    }

    public String getCleaningFrequency() {
        return cleaningFrequency;
    }

    public void setCleaningFrequency(String cleaningFrequency) {
        this.cleaningFrequency = cleaningFrequency;
    }

    public String getWaterSupportStatus() {
        return waterSupportStatus;
    }

    public void setWaterSupportStatus(String waterSupportStatus) {
        this.waterSupportStatus = waterSupportStatus;
    }

    public String getSencePropertyCompany() {
        return sencePropertyCompany;
    }

    public void setSencePropertyCompany(String sencePropertyCompany) {
        this.sencePropertyCompany = sencePropertyCompany;
    }

    public String getLastCleaningTime() {
        return lastCleaningTime;
    }

    public void setLastCleaningTime(String lastCleaningTime) {
        this.lastCleaningTime = lastCleaningTime;
    }

    public String getSenceInstallTime() {
        return senceInstallTime;
    }

    public void setSenceInstallTime(String senceInstallTime) {
        this.senceInstallTime = senceInstallTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
