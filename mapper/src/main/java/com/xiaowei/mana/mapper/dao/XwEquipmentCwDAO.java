package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentCwDO;
import com.xiaowei.mana.mapper.mapper.XwEquipmentCwDOMapper;

/**
* The Table XW_EQUIPMENT_CW.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 饮用水设备表
*/
@Repository
public class XwEquipmentCwDAO{

    @Autowired
    private XwEquipmentCwDOMapper xwEquipmentCwDOMapper;

    /**
     * desc:插入表:XW_EQUIPMENT_CW.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO XW_EQUIPMENT_CW( AREA_ID ,CITY_ID ,VILLAGE_ID ,PROVINCE_ID ,LATITUDE ,LONGITUDE ,AREA ,CITY ,STREET ,VENDOR ,ADDRESS ,DEL_FLAG ,SAFE_MAN ,CREATE_BY ,DEVICE_NO ,PROVINCE ,TANK_SEAL ,TANK_SIZE ,UPDATE_BY ,SAFE_PHONE ,DEVICE_NAME ,INSTALL_MAN ,SAFE_COMPANY ,VILLAGE_NAME ,TANK_MATERIAL ,EQUIPMENT_UUID ,WATER_MAN_PHONE ,PROPERTY_COMPANY ,CLEANING_FREQUENCY ,WATER_MAN_PRINCIPAL ,WATER_SUPPORT_STATUS ,SETUP_TIME ,CREATE_TIME ,UPDATE_TIME ,PRODUCT_TIME ,LAST_CLEANING_TIME )VALUES( #{areaId,jdbcType=BIGINT} ,#{cityId,jdbcType=BIGINT} ,#{villageId,jdbcType=BIGINT} ,#{provinceId,jdbcType=BIGINT} ,#{latitude,jdbcType=REAL} ,#{longitude,jdbcType=REAL} ,#{area,jdbcType=VARCHAR} ,#{city,jdbcType=VARCHAR} ,#{street,jdbcType=VARCHAR} ,#{vendor,jdbcType=VARCHAR} ,#{address,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{safeMan,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{province,jdbcType=VARCHAR} ,#{tankSeal,jdbcType=VARCHAR} ,#{tankSize,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{safePhone,jdbcType=VARCHAR} ,#{deviceName,jdbcType=VARCHAR} ,#{installMan,jdbcType=VARCHAR} ,#{safeCompany,jdbcType=VARCHAR} ,#{villageName,jdbcType=VARCHAR} ,#{tankMaterial,jdbcType=VARCHAR} ,#{equipmentUuid,jdbcType=VARCHAR} ,#{waterManPhone,jdbcType=VARCHAR} ,#{propertyCompany,jdbcType=VARCHAR} ,#{cleaningFrequency,jdbcType=VARCHAR} ,#{waterManPrincipal,jdbcType=VARCHAR} ,#{waterSupportStatus,jdbcType=VARCHAR} ,#{setupTime,jdbcType=TIMESTAMP} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} ,#{productTime,jdbcType=TIMESTAMP} ,#{lastCleaningTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(XwEquipmentCwDO entity){
        return xwEquipmentCwDOMapper.insert(entity);
    }
    /**
     * desc:更新表:XW_EQUIPMENT_CW.<br/>
     * descSql =  UPDATE XW_EQUIPMENT_CW SET AREA_ID = #{areaId,jdbcType=BIGINT} ,CITY_ID = #{cityId,jdbcType=BIGINT} ,VILLAGE_ID = #{villageId,jdbcType=BIGINT} ,PROVINCE_ID = #{provinceId,jdbcType=BIGINT} ,LATITUDE = #{latitude,jdbcType=REAL} ,LONGITUDE = #{longitude,jdbcType=REAL} ,AREA = #{area,jdbcType=VARCHAR} ,CITY = #{city,jdbcType=VARCHAR} ,STREET = #{street,jdbcType=VARCHAR} ,VENDOR = #{vendor,jdbcType=VARCHAR} ,ADDRESS = #{address,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,SAFE_MAN = #{safeMan,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,PROVINCE = #{province,jdbcType=VARCHAR} ,TANK_SEAL = #{tankSeal,jdbcType=VARCHAR} ,TANK_SIZE = #{tankSize,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SAFE_PHONE = #{safePhone,jdbcType=VARCHAR} ,DEVICE_NAME = #{deviceName,jdbcType=VARCHAR} ,INSTALL_MAN = #{installMan,jdbcType=VARCHAR} ,SAFE_COMPANY = #{safeCompany,jdbcType=VARCHAR} ,VILLAGE_NAME = #{villageName,jdbcType=VARCHAR} ,TANK_MATERIAL = #{tankMaterial,jdbcType=VARCHAR} ,EQUIPMENT_UUID = #{equipmentUuid,jdbcType=VARCHAR} ,WATER_MAN_PHONE = #{waterManPhone,jdbcType=VARCHAR} ,PROPERTY_COMPANY = #{propertyCompany,jdbcType=VARCHAR} ,CLEANING_FREQUENCY = #{cleaningFrequency,jdbcType=VARCHAR} ,WATER_MAN_PRINCIPAL = #{waterManPrincipal,jdbcType=VARCHAR} ,WATER_SUPPORT_STATUS = #{waterSupportStatus,jdbcType=VARCHAR} ,SETUP_TIME = #{setupTime,jdbcType=TIMESTAMP} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} ,PRODUCT_TIME = #{productTime,jdbcType=TIMESTAMP} ,LAST_CLEANING_TIME = #{lastCleaningTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(XwEquipmentCwDO entity){
        return xwEquipmentCwDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:XW_EQUIPMENT_CW.<br/>
     * descSql =  DELETE FROM XW_EQUIPMENT_CW WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return xwEquipmentCwDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:XW_EQUIPMENT_CW.<br/>
     * descSql =  SELECT * FROM XW_EQUIPMENT_CW WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return XwEquipmentCwDO
     */
    public XwEquipmentCwDO getById(Long id){
        return xwEquipmentCwDOMapper.getById(id);
    }
    /**
     * desc:新增表:XW_EQUIPMENT_CW.<br/>
     * descSql =  insert into xw_equipment_cw id, equipment_uuid, device_no, device_name, device_code, device_desc, latitude, longitude, product_time, setup_time, floor, village_id, village_name, install_man, vendor, province_id, province, city_id, city, area_id, area, street_id, street, address, safe_company, safe_man, safe_phone, create_by, create_time, update_by, update_time, del_flag, device_type, safe_time, install_phone, fault_push, #{id,jdbcType=BIGINT}, #{equipmentUuid,jdbcType=VARCHAR}, #{deviceNo,jdbcType=VARCHAR}, #{deviceName,jdbcType=VARCHAR}, #{deviceCode,jdbcType=VARCHAR}, #{deviceDesc,jdbcType=VARCHAR}, #{latitude,jdbcType=VARCHAR}, #{longitude,jdbcType=VARCHAR}, #{productTime,jdbcType=TIMESTAMP}, #{setupTime,jdbcType=TIMESTAMP}, #{floor,jdbcType=INTEGER}, #{villageId,jdbcType=BIGINT}, #{villageName,jdbcType=VARCHAR}, #{installMan,jdbcType=VARCHAR}, #{vendor,jdbcType=VARCHAR}, #{provinceId,jdbcType=BIGINT}, #{province,jdbcType=VARCHAR}, #{cityId,jdbcType=BIGINT}, #{city,jdbcType=VARCHAR}, #{areaId,jdbcType=BIGINT}, #{area,jdbcType=VARCHAR}, #{streetId,jdbcType=BIGINT}, #{street,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{safeCompany,jdbcType=VARCHAR}, #{safeMan,jdbcType=VARCHAR}, #{safePhone,jdbcType=VARCHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, #{delFlag,jdbcType=CHAR}, #{deviceType,jdbcType=VARCHAR}, #{safeTime,jdbcType=TIMESTAMP}, #{installPhone,jdbcType=VARCHAR}, #{faultPush,jdbcType=INTEGER} 
     * @param entity entity
     * @return XwEquipmentCwDO
     */
    public XwEquipmentCwDO saveCw(XwEquipmentCwDO entity){
        return xwEquipmentCwDOMapper.saveCw(entity);
    }
    /**
     * desc:更新引用水设备.<br/>
     * descSql =  update xw_equipment_cw equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}, device_no = #{deviceNo,jdbcType=VARCHAR}, device_name = #{deviceName,jdbcType=VARCHAR}, device_code = #{deviceCode,jdbcType=VARCHAR}, device_desc = #{deviceDesc,jdbcType=VARCHAR}, latitude = #{latitude,jdbcType=VARCHAR}, longitude = #{longitude,jdbcType=VARCHAR}, product_time = #{productTime,jdbcType=TIMESTAMP}, setup_time = #{setupTime,jdbcType=TIMESTAMP}, floor = #{floor,jdbcType=INTEGER}, village_id = #{villageId,jdbcType=BIGINT}, village_name = #{villageName,jdbcType=VARCHAR}, install_man = #{installMan,jdbcType=VARCHAR}, vendor = #{vendor,jdbcType=VARCHAR}, province_id = #{provinceId,jdbcType=BIGINT}, province = #{province,jdbcType=VARCHAR}, city_id = #{cityId,jdbcType=BIGINT}, city = #{city,jdbcType=VARCHAR}, area_id = #{areaId,jdbcType=BIGINT}, area = #{area,jdbcType=VARCHAR}, street_id = #{streetId,jdbcType=BIGINT}, street = #{street,jdbcType=VARCHAR}, address = #{address,jdbcType=VARCHAR}, safe_company = #{safeCompany,jdbcType=VARCHAR}, safe_man = #{safeMan,jdbcType=VARCHAR}, safe_phone = #{safePhone,jdbcType=VARCHAR}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, update_time = #{updateTime,jdbcType=TIMESTAMP}, del_flag = #{delFlag,jdbcType=CHAR}, safe_time = #{safeTime,jdbcType=TIMESTAMP}, install_phone = #{installPhone,jdbcType=VARCHAR}, fault_push= #{faultPush,jdbcType=INTEGER} where equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    public int updateCw(XwEquipmentCwDO entity){
        return xwEquipmentCwDOMapper.updateCw(entity);
    }
    /**
     * desc:feignCWbyDeviceNo.<br/>
     * descSql =  select id, equipment_uuid, device_no, device_name, device_code, device_desc, latitude, longitude, product_time, setup_time, floor, village_id, village_name, install_man, vendor, province_id, province, city_id, city, area_id, area, street_id, street, address, safe_company, safe_man, safe_phone, create_by, create_time, update_by, update_time, del_flag, device_type, install_phone, safe_time FROM xw_equipment_cw WHERE device_no=#{deviceNo,jdbcType=VARCHAR}
     * @param deviceNo deviceNo
     * @return XwEquipmentCwDO
     */
    public XwEquipmentCwDO feignCWbyDeviceNo(String deviceNo){
        return xwEquipmentCwDOMapper.feignCWbyDeviceNo(deviceNo);
    }
    /**
     * desc:更新引用水推送状态.<br/>
     * descSql =  update xw_equipment_cw equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}, fault_push= #{faultPush,jdbcType=INTEGER}, device_no = #{deviceNo,jdbcType=VARCHAR}, where equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    public int updatePush(XwEquipmentCwDO entity){
        return xwEquipmentCwDOMapper.updatePush(entity);
    }
}
