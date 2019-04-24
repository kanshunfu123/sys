package com.xiaowei.common.req.cwsence;

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
public class CwSenceEditReq extends BaseReq implements Serializable {
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
     * tankSeal 密封情况.
     */
    private String tankSeal;
    /**
     * tankSize 水箱尺寸.
     */
    private String tankSize;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * senceUuid 饮用水场景uuid.
     */
    private String senceUuid;
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
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
         * lastCleaningTime 上次清洗时间.
     */
    private String lastCleaningTime;
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
}
