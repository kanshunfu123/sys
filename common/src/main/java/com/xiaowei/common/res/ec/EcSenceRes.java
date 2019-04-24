package com.xiaowei.common.res.ec;

import java.io.Serializable;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public class EcSenceRes implements Serializable {


    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;

    /**
     * senceUuid 电梯场景uuid.
     */
    private String senceUuid;
    /**
     * elevatorUse 电梯用途.
     */
    private String elevatorUse;
    /**
     * ecSupervisor 监管人员.
     */
    private String ecSupervisor;
    /**
     * senceSafeMan 场景维保人员.
     */
    private String senceSafeMan;
    /**
     * serialNumber 出厂编号.
     */
    private String serialNumber;
    /**
     * regulatoryUnit 监管单位.
     */
    private String regulatoryUnit;
    /**
     * senceSafePhone 场景维保人联系方式.
     */
    private String senceSafePhone;
    /**
     * supervisorPhone 监管人联系方式.
     */
    private String supervisorPhone;
    /**
     * senceSafeCompany 场景维保公司.
     */
    private String senceSafeCompany;
    /**
     * ecCrew 乘员.
     */
    private Integer ecCrew;
    /**
     * ecLoad 载重.
     */
    private Integer ecLoad;
    /**
     * productTime 生产日期.
     */
    private String productTime;
    /**
     * senceInstallTime 场景安装时间.
     */
    private String senceInstallTime;
    /**
     * 位置
     */
    private String location;


    /**
     * 电梯品牌
     * @return
     */
    private String ecBrand;
    /**
     * 电梯位置码
     */
    private String elevatorCode;
    /**
     * yearsOfUser 电梯使用年限.
     */
    private Integer yearsOfUser;

    public Integer getYearsOfUser() {
        return yearsOfUser;
    }

    public void setYearsOfUser(Integer yearsOfUser) {
        this.yearsOfUser = yearsOfUser;
    }

    public String getElevatorCode() {
        return elevatorCode;
    }

    public void setElevatorCode(String elevatorCode) {
        this.elevatorCode = elevatorCode;
    }

    public String getEcBrand() {
        return ecBrand;
    }

    public void setEcBrand(String ecBrand) {
        this.ecBrand = ecBrand;
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

    public String getElevatorUse() {
        return elevatorUse;
    }

    public void setElevatorUse(String elevatorUse) {
        this.elevatorUse = elevatorUse;
    }

    public String getEcSupervisor() {
        return ecSupervisor;
    }

    public void setEcSupervisor(String ecSupervisor) {
        this.ecSupervisor = ecSupervisor;
    }

    public String getSenceSafeMan() {
        return senceSafeMan;
    }

    public void setSenceSafeMan(String senceSafeMan) {
        this.senceSafeMan = senceSafeMan;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getRegulatoryUnit() {
        return regulatoryUnit;
    }

    public void setRegulatoryUnit(String regulatoryUnit) {
        this.regulatoryUnit = regulatoryUnit;
    }

    public String getSenceSafePhone() {
        return senceSafePhone;
    }

    public void setSenceSafePhone(String senceSafePhone) {
        this.senceSafePhone = senceSafePhone;
    }

    public String getSupervisorPhone() {
        return supervisorPhone;
    }

    public void setSupervisorPhone(String supervisorPhone) {
        this.supervisorPhone = supervisorPhone;
    }

    public String getSenceSafeCompany() {
        return senceSafeCompany;
    }

    public void setSenceSafeCompany(String senceSafeCompany) {
        this.senceSafeCompany = senceSafeCompany;
    }

    public Integer getEcCrew() {
        return ecCrew;
    }

    public void setEcCrew(Integer ecCrew) {
        this.ecCrew = ecCrew;
    }

    public Integer getEcLoad() {
        return ecLoad;
    }

    public void setEcLoad(Integer ecLoad) {
        this.ecLoad = ecLoad;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
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
