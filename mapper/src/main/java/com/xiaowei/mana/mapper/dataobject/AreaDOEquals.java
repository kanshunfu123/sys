package com.xiaowei.mana.mapper.dataobject;

import java.math.BigDecimal;
import java.util.Date;

/**
 * The table 区域 省市县三级联动
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class AreaDOEquals {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AreaDOEquals that = (AreaDOEquals) o;

        return sysAreaName.equals(that.sysAreaName);
    }

    @Override
    public int hashCode() {
        return sysAreaName.hashCode();
    }

    /**
     * id ID.
     */
    private Long id;
    /**
     * sysAreaParentId 父级id .
     */
    private Long sysAreaParentId;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * sysAreaName 区域名称.
     */
    private String sysAreaName;
    /**
     * sysAreaUuid 唯一，32位字符串，查询用.
     */
    private String sysAreaUuid;
    /**
     * sysAreaLevel 当前模块层级.
     */
    private String sysAreaLevel;
    /**
     * sysWeathCode 天气码.
     */
    private String sysWeathCode;
    /**
     * sysAreaRemark 备注.
     */
    private String sysAreaRemark;
    /**
     * sysAreaSeq 排序.
     */
    private Integer sysAreaSeq;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * latitude 纬度.
     */
    private BigDecimal latitude;
    /**
     * longitude 经度.
     */
    private BigDecimal longitude;

    /**
     * Set id ID.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Long getId(){
        return id;
    }

    /**
     * Set sysAreaParentId 父级id .
     */
    public void setSysAreaParentId(Long sysAreaParentId){
        this.sysAreaParentId = sysAreaParentId;
    }

    /**
     * Get sysAreaParentId 父级id .
     *
     * @return the string
     */
    public Long getSysAreaParentId(){
        return sysAreaParentId;
    }

    /**
     * Set delFlag 删除状态(0-正常，1-删除).
     */
    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag 删除状态(0-正常，1-删除).
     *
     * @return the string
     */
    public String getDelFlag(){
        return delFlag;
    }

    /**
     * Set createBy 创建人.
     */
    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    /**
     * Get createBy 创建人.
     *
     * @return the string
     */
    public String getCreateBy(){
        return createBy;
    }

    /**
     * Set updateBy 修改人.
     */
    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    /**
     * Get updateBy 修改人.
     *
     * @return the string
     */
    public String getUpdateBy(){
        return updateBy;
    }

    /**
     * Set sysAreaName 区域名称.
     */
    public void setSysAreaName(String sysAreaName){
        this.sysAreaName = sysAreaName;
    }

    /**
     * Get sysAreaName 区域名称.
     *
     * @return the string
     */
    public String getSysAreaName(){
        return sysAreaName;
    }

    /**
     * Set sysAreaUuid 唯一，32位字符串，查询用.
     */
    public void setSysAreaUuid(String sysAreaUuid){
        this.sysAreaUuid = sysAreaUuid;
    }

    /**
     * Get sysAreaUuid 唯一，32位字符串，查询用.
     *
     * @return the string
     */
    public String getSysAreaUuid(){
        return sysAreaUuid;
    }

    /**
     * Set sysAreaLevel 当前模块层级.
     */
    public void setSysAreaLevel(String sysAreaLevel){
        this.sysAreaLevel = sysAreaLevel;
    }

    /**
     * Get sysAreaLevel 当前模块层级.
     *
     * @return the string
     */
    public String getSysAreaLevel(){
        return sysAreaLevel;
    }

    /**
     * Set sysWeathCode 天气码.
     */
    public void setSysWeathCode(String sysWeathCode){
        this.sysWeathCode = sysWeathCode;
    }

    /**
     * Get sysWeathCode 天气码.
     *
     * @return the string
     */
    public String getSysWeathCode(){
        return sysWeathCode;
    }

    /**
     * Set sysAreaRemark 备注.
     */
    public void setSysAreaRemark(String sysAreaRemark){
        this.sysAreaRemark = sysAreaRemark;
    }

    /**
     * Get sysAreaRemark 备注.
     *
     * @return the string
     */
    public String getSysAreaRemark(){
        return sysAreaRemark;
    }

    /**
     * Set sysAreaSeq 排序.
     */
    public void setSysAreaSeq(Integer sysAreaSeq){
        this.sysAreaSeq = sysAreaSeq;
    }

    /**
     * Get sysAreaSeq 排序.
     *
     * @return the string
     */
    public Integer getSysAreaSeq(){
        return sysAreaSeq;
    }

    /**
     * Set createTime 创建时间.
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     * Get createTime 创建时间.
     *
     * @return the string
     */
    public Date getCreateTime(){
        return createTime;
    }

    /**
     * Set updateTime 修改时间.
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
     * Get updateTime 修改时间.
     *
     * @return the string
     */
    public Date getUpdateTime(){
        return updateTime;
    }

    /**
     * Set latitude 纬度.
     */
    public void setLatitude(BigDecimal latitude){
        this.latitude = latitude;
    }

    /**
     * Get latitude 纬度.
     *
     * @return the string
     */
    public BigDecimal getLatitude(){
        return latitude;
    }

    /**
     * Set longitude 经度.
     */
    public void setLongitude(BigDecimal longitude){
        this.longitude = longitude;
    }

    /**
     * Get longitude 经度.
     *
     * @return the string
     */
    public BigDecimal getLongitude(){
        return longitude;
    }
}
