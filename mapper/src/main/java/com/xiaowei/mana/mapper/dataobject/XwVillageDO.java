package com.xiaowei.mana.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mana.mapper.dataobject.XwVillageDO;

/**
 * The table 小区表
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class XwVillageDO{

    /**
     * id 主键.
     */
    private Long id;
    /**
     * areaId 区id.
     */
    private Long areaId;
    /**
     * cityId 市id.
     */
    private Long cityId;
    /**
     * provinceId 省id.
     */
    private Long provinceId;
    /**
     * area 区.
     */
    private String area;
    /**
     * city 市.
     */
    private String city;
    /**
     * street 街道（设备表用此字段）.
     */
    private String street;
    /**
     * delFlag 0 正常 1删除.
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
     * villageName 小区名称.
     */
    private String villageName;
    /**
     * villageUuid 小区uuid.
     */
    private String villageUuid;
    /**
     * provinceName 省.
     */
    private String provinceName;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;

    /**
     * Set id 主键.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id 主键.
     *
     * @return the string
     */
    public Long getId(){
        return id;
    }

    /**
     * Set areaId 区id.
     */
    public void setAreaId(Long areaId){
        this.areaId = areaId;
    }

    /**
     * Get areaId 区id.
     *
     * @return the string
     */
    public Long getAreaId(){
        return areaId;
    }

    /**
     * Set cityId 市id.
     */
    public void setCityId(Long cityId){
        this.cityId = cityId;
    }

    /**
     * Get cityId 市id.
     *
     * @return the string
     */
    public Long getCityId(){
        return cityId;
    }

    /**
     * Set provinceId 省id.
     */
    public void setProvinceId(Long provinceId){
        this.provinceId = provinceId;
    }

    /**
     * Get provinceId 省id.
     *
     * @return the string
     */
    public Long getProvinceId(){
        return provinceId;
    }

    /**
     * Set area 区.
     */
    public void setArea(String area){
        this.area = area;
    }

    /**
     * Get area 区.
     *
     * @return the string
     */
    public String getArea(){
        return area;
    }

    /**
     * Set city 市.
     */
    public void setCity(String city){
        this.city = city;
    }

    /**
     * Get city 市.
     *
     * @return the string
     */
    public String getCity(){
        return city;
    }

    /**
     * Set street 街道（设备表用此字段）.
     */
    public void setStreet(String street){
        this.street = street;
    }

    /**
     * Get street 街道（设备表用此字段）.
     *
     * @return the string
     */
    public String getStreet(){
        return street;
    }

    /**
     * Set delFlag 0 正常 1删除.
     */
    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag 0 正常 1删除.
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
     * Set villageName 小区名称.
     */
    public void setVillageName(String villageName){
        this.villageName = villageName;
    }

    /**
     * Get villageName 小区名称.
     *
     * @return the string
     */
    public String getVillageName(){
        return villageName;
    }

    /**
     * Set villageUuid 小区uuid.
     */
    public void setVillageUuid(String villageUuid){
        this.villageUuid = villageUuid;
    }

    /**
     * Get villageUuid 小区uuid.
     *
     * @return the string
     */
    public String getVillageUuid(){
        return villageUuid;
    }

    /**
     * Set provinceName 省.
     */
    public void setProvinceName(String provinceName){
        this.provinceName = provinceName;
    }

    /**
     * Get provinceName 省.
     *
     * @return the string
     */
    public String getProvinceName(){
        return provinceName;
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
}
