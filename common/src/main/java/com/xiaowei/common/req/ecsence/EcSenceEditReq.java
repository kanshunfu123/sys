package com.xiaowei.common.req.ecsence;

import com.xiaowei.common.error.BaseReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by kanshunfu on 2019/01/08.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EcSenceEditReq extends BaseReq implements Serializable {
    /**
     * id 主键.
     */
    private Long id;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
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
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * productTime 生产日期.
     */
    private String productTime;
    /**
     * senceInstallTime 场景安装时间.
     */
    private String senceInstallTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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
}
