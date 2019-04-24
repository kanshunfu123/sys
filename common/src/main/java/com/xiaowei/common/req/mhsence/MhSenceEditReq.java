package com.xiaowei.common.req.mhsence;

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
public class MhSenceEditReq extends BaseReq implements Serializable {
    /**
     * id ID.
     */
    private Long id;
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
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
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

    public String getSenceInstallTime() {
        return senceInstallTime;
    }

    public void setSenceInstallTime(String senceInstallTime) {
        this.senceInstallTime = senceInstallTime;
    }
}
