package com.xiaowei.common.res.mh;

import java.io.Serializable;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public class MhEquipmentRes implements Serializable {
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
     * villageId 小区id.
     */
    private Long villageId;
    /**
     * 街道id
     */
    private Long streetId;
    /**
     * provinceId 省id.
     */
    private Long provinceId;
    /**
     * latitude 纬度.
     */
    private String latitude;
    /**
     * longitude 经度.
     */
    private String longitude;
    /**
     * area 区.
     */
    private String area;
    /**
     * city 市.
     */
    private String city;
    /**
     * street 街道.
     */
    private String street;
    /**
     * vendor 设备生产厂商名称.
     */
    private String vendor;
    /**
     * address 详细地址（可填可不填）.
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
     * deviceNo 设备编号.
     */
    private String deviceNo;
    /**
     * province 省.
     */
    private String province;

    /**
     * safePhone 维保人联系电话.
     */
    private String safePhone;
    /**
     * deviceCode 编号(1# or  2#).
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
     * equipmentUuid uuid.
     */
    private String equipmentUuid;
    /**
     * haveGas 是否有可燃气(0没有  1有).
     */
    private Integer haveGas;
    /**
     * haveH2s 是否有硫化氢(0没有 1有).
     */
    private Integer haveH2s;
    /**
     * haveWaterLine 是否有液位(0没有  1有).
     */
    private Integer haveWaterLine;
    /**
     * setupTime 设备安装时间.
     */
    private String setupTime;
    /**
     * productTime 生产时间.
     */
    private String productTime;
    /**
     * installPhone 安装人员电话.
     */
    private String installPhone;
    /**
     * safeTime 维保日期.
     */
    private String safeTime;

    /**
     * haveHumi 是否有湿度(0没有 1有).
     */

    private Integer haveHumi;

    public Integer getHaveHumi() {
        return haveHumi;
    }

    public void setHaveHumi(Integer haveHumi) {
        this.haveHumi = haveHumi;
    }

    public String getInstallPhone() {
        return installPhone;
    }

    public void setInstallPhone(String installPhone) {
        this.installPhone = installPhone;
    }

    public String getSafeTime() {
        return safeTime;
    }

    public void setSafeTime(String safeTime) {
        this.safeTime = safeTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getVillageId() {
        return villageId;
    }

    public void setVillageId(Long villageId) {
        this.villageId = villageId;
    }

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getSafePhone() {
        return safePhone;
    }

    public void setSafePhone(String safePhone) {
        this.safePhone = safePhone;
    }

    public String getDeviceCode() {
        return deviceCode;
    }

    public void setDeviceCode(String deviceCode) {
        this.deviceCode = deviceCode;
    }

    public String getDeviceDesc() {
        return deviceDesc;
    }

    public void setDeviceDesc(String deviceDesc) {
        this.deviceDesc = deviceDesc;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getInstallMan() {
        return installMan;
    }

    public void setInstallMan(String installMan) {
        this.installMan = installMan;
    }

    public String getSafeCompany() {
        return safeCompany;
    }

    public void setSafeCompany(String safeCompany) {
        this.safeCompany = safeCompany;
    }

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public String getEquipmentUuid() {
        return equipmentUuid;
    }

    public void setEquipmentUuid(String equipmentUuid) {
        this.equipmentUuid = equipmentUuid;
    }
    public Integer getHaveGas() {
        return haveGas;
    }

    public void setHaveGas(Integer haveGas) {
        this.haveGas = haveGas;
    }

    public Integer getHaveH2s() {
        return haveH2s;
    }

    public void setHaveH2s(Integer haveH2s) {
        this.haveH2s = haveH2s;
    }

    public Integer getHaveWaterLine() {
        return haveWaterLine;
    }

    public void setHaveWaterLine(Integer haveWaterLine) {
        this.haveWaterLine = haveWaterLine;
    }

    public String getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(String setupTime) {
        this.setupTime = setupTime;
    }

    public String getProductTime() {
        return productTime;
    }

    public void setProductTime(String productTime) {
        this.productTime = productTime;
    }

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }
}
