package com.xiaowei.mana.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentCwDO;

/**
 * The table 饮用水设备表
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class XwEquipmentCwDO{

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
     * streetId STREET_ID.
     */
    private Long streetId;
    /**
     * villageId VILLAGE_ID.
     */
    private Long villageId;
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
     * street 街道（来自小区表）.
     */
    private String street;
    /**
     * vendor 设备生产厂商名称.
     */
    private String vendor;
    /**
     * address 详细地址.
     */
    private String address;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * safeMan 维保人员.
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
     * latitude 纬度.
     */
    private String latitude;
    /**
     * province 省.
     */
    private String province;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * longitude 经度.
     */
    private String longitude;
    /**
     * safePhone 维保联系方式.
     */
    private String safePhone;
    /**
     * deviceCode 编号（例如 1#  2# ）.
     */
    private String deviceCode;
    /**
     * deviceDesc 设备描述.
     */
    private String deviceDesc;
    /**
     * deviceName 设备名称.
     */
    private String deviceName;
    /**
     * deviceType 设备类别.
     */
    private String deviceType;
    /**
     * installMan 安装人.
     */
    private String installMan;
    /**
     * safeCompany 维保公司.
     */
    private String safeCompany;
    /**
     * villageName 小区名.
     */
    private String villageName;
    /**
     * installPhone 安装人员电话.
     */
    private String installPhone;
    /**
     * equipmentUuid uuid.
     */
    private String equipmentUuid;
    /**
     * floor 所在楼层.
     */
    private Integer floor;
    /**
     * faultPush 故障通知（0未推  1推）.
     */
    private Integer faultPush;
    /**
     * safeTime 维保日期.
     */
    private Date safeTime;
    /**
     * setupTime 设备安装时间.
     */
    private Date setupTime;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    /**
     * productTime 生产时间.
     */
    private Date productTime;

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
     * Set streetId STREET_ID.
     */
    public void setStreetId(Long streetId){
        this.streetId = streetId;
    }

    /**
     * Get streetId STREET_ID.
     *
     * @return the string
     */
    public Long getStreetId(){
        return streetId;
    }

    /**
     * Set villageId VILLAGE_ID.
     */
    public void setVillageId(Long villageId){
        this.villageId = villageId;
    }

    /**
     * Get villageId VILLAGE_ID.
     *
     * @return the string
     */
    public Long getVillageId(){
        return villageId;
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
     * Set street 街道（来自小区表）.
     */
    public void setStreet(String street){
        this.street = street;
    }

    /**
     * Get street 街道（来自小区表）.
     *
     * @return the string
     */
    public String getStreet(){
        return street;
    }

    /**
     * Set vendor 设备生产厂商名称.
     */
    public void setVendor(String vendor){
        this.vendor = vendor;
    }

    /**
     * Get vendor 设备生产厂商名称.
     *
     * @return the string
     */
    public String getVendor(){
        return vendor;
    }

    /**
     * Set address 详细地址.
     */
    public void setAddress(String address){
        this.address = address;
    }

    /**
     * Get address 详细地址.
     *
     * @return the string
     */
    public String getAddress(){
        return address;
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
     * Set safeMan 维保人员.
     */
    public void setSafeMan(String safeMan){
        this.safeMan = safeMan;
    }

    /**
     * Get safeMan 维保人员.
     *
     * @return the string
     */
    public String getSafeMan(){
        return safeMan;
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
     * Set deviceNo 设备编号.
     */
    public void setDeviceNo(String deviceNo){
        this.deviceNo = deviceNo;
    }

    /**
     * Get deviceNo 设备编号.
     *
     * @return the string
     */
    public String getDeviceNo(){
        return deviceNo;
    }

    /**
     * Set latitude 纬度.
     */
    public void setLatitude(String latitude){
        this.latitude = latitude;
    }

    /**
     * Get latitude 纬度.
     *
     * @return the string
     */
    public String getLatitude(){
        return latitude;
    }

    /**
     * Set province 省.
     */
    public void setProvince(String province){
        this.province = province;
    }

    /**
     * Get province 省.
     *
     * @return the string
     */
    public String getProvince(){
        return province;
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
     * Set longitude 经度.
     */
    public void setLongitude(String longitude){
        this.longitude = longitude;
    }

    /**
     * Get longitude 经度.
     *
     * @return the string
     */
    public String getLongitude(){
        return longitude;
    }

    /**
     * Set safePhone 维保联系方式.
     */
    public void setSafePhone(String safePhone){
        this.safePhone = safePhone;
    }

    /**
     * Get safePhone 维保联系方式.
     *
     * @return the string
     */
    public String getSafePhone(){
        return safePhone;
    }

    /**
     * Set deviceCode 编号（例如 1#  2# ）.
     */
    public void setDeviceCode(String deviceCode){
        this.deviceCode = deviceCode;
    }

    /**
     * Get deviceCode 编号（例如 1#  2# ）.
     *
     * @return the string
     */
    public String getDeviceCode(){
        return deviceCode;
    }

    /**
     * Set deviceDesc 设备描述.
     */
    public void setDeviceDesc(String deviceDesc){
        this.deviceDesc = deviceDesc;
    }

    /**
     * Get deviceDesc 设备描述.
     *
     * @return the string
     */
    public String getDeviceDesc(){
        return deviceDesc;
    }

    /**
     * Set deviceName 设备名称.
     */
    public void setDeviceName(String deviceName){
        this.deviceName = deviceName;
    }

    /**
     * Get deviceName 设备名称.
     *
     * @return the string
     */
    public String getDeviceName(){
        return deviceName;
    }

    /**
     * Set deviceType 设备类别.
     */
    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }

    /**
     * Get deviceType 设备类别.
     *
     * @return the string
     */
    public String getDeviceType(){
        return deviceType;
    }

    /**
     * Set installMan 安装人.
     */
    public void setInstallMan(String installMan){
        this.installMan = installMan;
    }

    /**
     * Get installMan 安装人.
     *
     * @return the string
     */
    public String getInstallMan(){
        return installMan;
    }

    /**
     * Set safeCompany 维保公司.
     */
    public void setSafeCompany(String safeCompany){
        this.safeCompany = safeCompany;
    }

    /**
     * Get safeCompany 维保公司.
     *
     * @return the string
     */
    public String getSafeCompany(){
        return safeCompany;
    }

    /**
     * Set villageName 小区名.
     */
    public void setVillageName(String villageName){
        this.villageName = villageName;
    }

    /**
     * Get villageName 小区名.
     *
     * @return the string
     */
    public String getVillageName(){
        return villageName;
    }

    /**
     * Set installPhone 安装人员电话.
     */
    public void setInstallPhone(String installPhone){
        this.installPhone = installPhone;
    }

    /**
     * Get installPhone 安装人员电话.
     *
     * @return the string
     */
    public String getInstallPhone(){
        return installPhone;
    }

    /**
     * Set equipmentUuid uuid.
     */
    public void setEquipmentUuid(String equipmentUuid){
        this.equipmentUuid = equipmentUuid;
    }

    /**
     * Get equipmentUuid uuid.
     *
     * @return the string
     */
    public String getEquipmentUuid(){
        return equipmentUuid;
    }

    /**
     * Set floor 所在楼层.
     */
    public void setFloor(Integer floor){
        this.floor = floor;
    }

    /**
     * Get floor 所在楼层.
     *
     * @return the string
     */
    public Integer getFloor(){
        return floor;
    }

    /**
     * Set faultPush 故障通知（0未推  1推）.
     */
    public void setFaultPush(Integer faultPush){
        this.faultPush = faultPush;
    }

    /**
     * Get faultPush 故障通知（0未推  1推）.
     *
     * @return the string
     */
    public Integer getFaultPush(){
        return faultPush;
    }

    /**
     * Set safeTime 维保日期.
     */
    public void setSafeTime(Date safeTime){
        this.safeTime = safeTime;
    }

    /**
     * Get safeTime 维保日期.
     *
     * @return the string
     */
    public Date getSafeTime(){
        return safeTime;
    }

    /**
     * Set setupTime 设备安装时间.
     */
    public void setSetupTime(Date setupTime){
        this.setupTime = setupTime;
    }

    /**
     * Get setupTime 设备安装时间.
     *
     * @return the string
     */
    public Date getSetupTime(){
        return setupTime;
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
     * Set productTime 生产时间.
     */
    public void setProductTime(Date productTime){
        this.productTime = productTime;
    }

    /**
     * Get productTime 生产时间.
     *
     * @return the string
     */
    public Date getProductTime(){
        return productTime;
    }
}
