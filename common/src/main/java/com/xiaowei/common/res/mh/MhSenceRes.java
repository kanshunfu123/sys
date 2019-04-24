package com.xiaowei.common.res.mh;

import java.io.Serializable;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public class MhSenceRes implements Serializable {
    /**
     * mhUse 用途.
     */
    private String mhUse;
    /**
     * mhLoad 载荷.
     */
    private String mhLoad;
    /**
     * mhSize 井盖尺寸.
     */
    private String mhSize;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;

    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;

    /**
     * senceUuid 井盖场景信息uuid.
     */
    private String senceUuid;
    /**
     * mhMaterial 井盖材质.
     */
    private String mhMaterial;
    /**
     * trafficFlow 车流量.
     */
    private String trafficFlow;
    /**
     * principalPhone 负责人联系方式.
     */
    private String principalPhone;
    /**
     * sencePrincipal 负责人.
     */
    private String sencePrincipal;
    /**
     * mhYears 使用年限.
     */
    private Integer mhYears;
    /**
     * mhEndTime 更换时间.
     */
    private String mhEndTime;

    /**
     * senceInstallTime 场景安装时间.
     */
    private String senceInstallTime;
    /**
     * 位置
     */
    private String location;
    /**
     * mhDiameter 井盖直径.
     */
    private String mhDiameter;
    /**
     * mhType 井盖使用类型.
     */
    private String mhType;

    public String getMhDiameter() {
        return mhDiameter;
    }

    public void setMhDiameter(String mhDiameter) {
        this.mhDiameter = mhDiameter;
    }

    public String getMhType() {
        return mhType;
    }

    public void setMhType(String mhType) {
        this.mhType = mhType;
    }

    public String getMhUse() {
        return mhUse;
    }

    public void setMhUse(String mhUse) {
        this.mhUse = mhUse;
    }

    public String getMhLoad() {
        return mhLoad;
    }

    public void setMhLoad(String mhLoad) {
        this.mhLoad = mhLoad;
    }

    public String getMhSize() {
        return mhSize;
    }

    public void setMhSize(String mhSize) {
        this.mhSize = mhSize;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getSenceUuid() {
        return senceUuid;
    }

    public void setSenceUuid(String senceUuid) {
        this.senceUuid = senceUuid;
    }

    public String getMhMaterial() {
        return mhMaterial;
    }

    public void setMhMaterial(String mhMaterial) {
        this.mhMaterial = mhMaterial;
    }

    public String getTrafficFlow() {
        return trafficFlow;
    }

    public void setTrafficFlow(String trafficFlow) {
        this.trafficFlow = trafficFlow;
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

    public Integer getMhYears() {
        return mhYears;
    }

    public void setMhYears(Integer mhYears) {
        this.mhYears = mhYears;
    }

    public String getMhEndTime() {
        return mhEndTime;
    }

    public void setMhEndTime(String mhEndTime) {
        this.mhEndTime = mhEndTime;
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
