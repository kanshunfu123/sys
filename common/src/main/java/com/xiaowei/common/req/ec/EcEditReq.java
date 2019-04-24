package com.xiaowei.common.req.ec;

import com.xiaowei.common.error.BaseReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by kanshunfu on 2019/01/08.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EcEditReq extends BaseReq implements Serializable {
    /**
     * id 主键.
     */
    private Long id;
    /**
     * areaId 区id.
     */
    @NotNull(message = "areaId 区id必填",groups = {Add.class,Modify.class})
    private Long areaId;
    /**
     * cityId 市id.
     */
    @NotNull(message = "cityId 市id必填",groups = {Add.class,Modify.class})
    private Long cityId;
    /**
     * villageId 小区id.
     */
    @NotNull(message = "villageId 小区id必填",groups = {Add.class,Modify.class})
    private Long villageId;
    /**
     * provinceId 省id.
     */
    @NotNull(message = "provinceId 省id必填",groups = {Add.class,Modify.class})
    private Long provinceId;
    /**
     * 街道id
     */
    @NotNull(message = "streetId 街道id必填",groups = {Add.class,Modify.class})
    private Long streetId;
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
     * street 街道（与小区表保持一致）.
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
    @NotBlank(message = "deviceNo 设备编号",groups = {Add.class,Modify.class})
    private String deviceNo;
    /**
     * province 省.
     */
    private String province;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * safePhone 联系电话.
     */
    private String safePhone;
    /**
     * deviceCode 编号（比如1#左）.
     */
    @NotBlank(message = "deviceCode 编号必填",groups = {Add.class,Modify.class})
    private String deviceCode;
    /**
     * deviceDesc 设备描述.
     */
    private String deviceDesc;
    /**
     * deviceName 设备名称.
     */
    @NotBlank(message = "deviceName 设备名称必填",groups = {Add.class,Modify.class})
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
     * floorNum 楼层数量.
     */
    @NotNull(message = "floorNum 楼层数量必须填写",groups = {Add.class,Modify.class})
    private Integer floorNum;
    /*
     * floorDownnum 下楼层数量.
     */
    @NotNull(message = "floorDownnum 下楼层数必须填写",groups = {Add.class,Modify.class})
    private Integer floorDownnum;
    /**
     * setupTime 设备安装时间.
     */
    private String setupTime;
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
     * elevatorBrand 电梯品牌.
     */
    private String elevatorBrand;
    /**
     * yearsOfUser 使用年限.
     */
    private Integer yearsOfUser;

    public String getElevatorBrand() {
        return elevatorBrand;
    }

    public void setElevatorBrand(String elevatorBrand) {
        this.elevatorBrand = elevatorBrand;
    }

    public Integer getYearsOfUser() {
        return yearsOfUser;
    }

    public void setYearsOfUser(Integer yearsOfUser) {
        this.yearsOfUser = yearsOfUser;
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

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
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

    public Integer getFloorNum() {
        return floorNum;
    }

    public void setFloorNum(Integer floorNum) {
        this.floorNum = floorNum;
    }

    public Integer getFloorDownnum() {
        return floorDownnum;
    }

    public void setFloorDownnum(Integer floorDownnum) {
        this.floorDownnum = floorDownnum;
    }

    public String getSetupTime() {
        return setupTime;
    }

    public void setSetupTime(String setupTime) {
        this.setupTime = setupTime;
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

    public Long getStreetId() {
        return streetId;
    }

    public void setStreetId(Long streetId) {
        this.streetId = streetId;
    }
}
