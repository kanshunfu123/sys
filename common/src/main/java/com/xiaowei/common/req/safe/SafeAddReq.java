package com.xiaowei.common.req.safe;

import com.xiaowei.common.error.BaseReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by kanshunfu on 2019/02/27.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SafeAddReq extends BaseReq implements Serializable {
    /**
     * id 主键.
     */
    private Long id;
    /**
     * delFlag 0正常 1删除.
     */
    private String delFlag;
    /**
     * safeMan 维保人.
     */
    private String safeMan;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * safePhone 维保人手机号.
     */
    private String safePhone;
    /**
     * deviceType 0 地表水 1井盖 2生活用水 3电梯.
     */
    private String deviceType;
    /**
     * safeCompany 维保公司.
     */
    private String safeCompany;
    /**
     * safeRecordUuid 维保记录uuid.
     */
    private String safeRecordUuid;
    /**
     * safeTime 维保时间.
     */
    private String safeTime;
    /**
     * createTime 创建时间.
     */
    private Date createTime;

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

    public String getSafeMan() {
        return safeMan;
    }

    public void setSafeMan(String safeMan) {
        this.safeMan = safeMan;
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

    public String getSafePhone() {
        return safePhone;
    }

    public void setSafePhone(String safePhone) {
        this.safePhone = safePhone;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getSafeCompany() {
        return safeCompany;
    }

    public void setSafeCompany(String safeCompany) {
        this.safeCompany = safeCompany;
    }

    public String getSafeRecordUuid() {
        return safeRecordUuid;
    }

    public void setSafeRecordUuid(String safeRecordUuid) {
        this.safeRecordUuid = safeRecordUuid;
    }

    public String getSafeTime() {
        return safeTime;
    }

    public void setSafeTime(String safeTime) {
        this.safeTime = safeTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
