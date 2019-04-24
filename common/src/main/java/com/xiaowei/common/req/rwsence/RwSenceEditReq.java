package com.xiaowei.common.req.rwsence;

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
public class RwSenceEditReq extends BaseReq implements Serializable {
    /**
     * id 主键.
     */
    private Long id;
    /**
     * rwName 河道名称.
     */
    private String rwName;
    /**
         * rwType 河道使用类型.
     */
    private String rwType;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * rwDepth 水深.
     */
    private String rwDepth;
    /**
     * rwLevee 岸堤情况.
     */
    private String rwLevee;
    /**
     * rwRange 河道范围.
     */
    private String rwRange;
    /**
     * rwWidth 河宽.
     */
    private String rwWidth;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * rwLength 河长.
     */
    private String rwLength;
    /**
     * rwRemark 水源信息.
     */
    private String rwRemark;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * senceUuid 河道水场景uuid.
     */
    private String senceUuid;
    /**
     * principalPhone 负责人联系方式.
     */
    private String principalPhone;
    /**
     * sencePrincipal 河道负责人.
     */
    private String sencePrincipal;
    /**
     * rwPolluteSource 周围污染源.
     */
    private String rwPolluteSource;
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

    public String getRwName() {
        return rwName;
    }

    public void setRwName(String rwName) {
        this.rwName = rwName;
    }

    public String getRwType() {
        return rwType;
    }

    public void setRwType(String rwType) {
        this.rwType = rwType;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getRwDepth() {
        return rwDepth;
    }

    public void setRwDepth(String rwDepth) {
        this.rwDepth = rwDepth;
    }

    public String getRwLevee() {
        return rwLevee;
    }

    public void setRwLevee(String rwLevee) {
        this.rwLevee = rwLevee;
    }

    public String getRwRange() {
        return rwRange;
    }

    public void setRwRange(String rwRange) {
        this.rwRange = rwRange;
    }

    public String getRwWidth() {
        return rwWidth;
    }

    public void setRwWidth(String rwWidth) {
        this.rwWidth = rwWidth;
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

    public String getRwLength() {
        return rwLength;
    }

    public void setRwLength(String rwLength) {
        this.rwLength = rwLength;
    }

    public String getRwRemark() {
        return rwRemark;
    }

    public void setRwRemark(String rwRemark) {
        this.rwRemark = rwRemark;
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

    public String getRwPolluteSource() {
        return rwPolluteSource;
    }

    public void setRwPolluteSource(String rwPolluteSource) {
        this.rwPolluteSource = rwPolluteSource;
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
