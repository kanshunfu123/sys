package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.XwEquipmentMhDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_EQUIPMENT_MH.
 * 井盖设备表
 */
public interface XwEquipmentMhDOMapper{

    /**
     * desc:插入表:XW_EQUIPMENT_MH.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO XW_EQUIPMENT_MH( AREA_ID ,CITY_ID ,VILLAGE_ID ,PROVINCE_ID ,LATITUDE ,LONGITUDE ,AREA ,CITY ,STREET ,VENDOR ,ADDRESS ,DEL_FLAG ,SAFE_MAN ,CREATE_BY ,DEVICE_NO ,PROVINCE ,UPDATE_BY ,SAFE_PHONE ,DEVICE_CODE ,DEVICE_DESC ,DEVICE_NAME ,INSTALL_MAN ,SAFE_COMPANY ,VILLAGE_NAME ,EQUIPMENT_UUID ,HAVE_NH ,HAVE_GAS ,HAVE_H2S ,HAVE_WATER_LINE ,SETUP_TIME ,CREATE_TIME ,UPDATE_TIME ,PRODUCT_TIME )VALUES( #{areaId,jdbcType=BIGINT} ,#{cityId,jdbcType=BIGINT} ,#{villageId,jdbcType=BIGINT} ,#{provinceId,jdbcType=BIGINT} ,#{latitude,jdbcType=REAL} ,#{longitude,jdbcType=REAL} ,#{area,jdbcType=VARCHAR} ,#{city,jdbcType=VARCHAR} ,#{street,jdbcType=VARCHAR} ,#{vendor,jdbcType=VARCHAR} ,#{address,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{safeMan,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{province,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{safePhone,jdbcType=VARCHAR} ,#{deviceCode,jdbcType=VARCHAR} ,#{deviceDesc,jdbcType=VARCHAR} ,#{deviceName,jdbcType=VARCHAR} ,#{installMan,jdbcType=VARCHAR} ,#{safeCompany,jdbcType=VARCHAR} ,#{villageName,jdbcType=VARCHAR} ,#{equipmentUuid,jdbcType=VARCHAR} ,#{haveNh,jdbcType=INTEGER} ,#{haveGas,jdbcType=INTEGER} ,#{haveH2s,jdbcType=INTEGER} ,#{haveWaterLine,jdbcType=INTEGER} ,#{setupTime,jdbcType=TIMESTAMP} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} ,#{productTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(XwEquipmentMhDO entity);
    /**
     * desc:更新表:XW_EQUIPMENT_MH.<br/>
     * descSql =  UPDATE XW_EQUIPMENT_MH SET AREA_ID = #{areaId,jdbcType=BIGINT} ,CITY_ID = #{cityId,jdbcType=BIGINT} ,VILLAGE_ID = #{villageId,jdbcType=BIGINT} ,PROVINCE_ID = #{provinceId,jdbcType=BIGINT} ,LATITUDE = #{latitude,jdbcType=REAL} ,LONGITUDE = #{longitude,jdbcType=REAL} ,AREA = #{area,jdbcType=VARCHAR} ,CITY = #{city,jdbcType=VARCHAR} ,STREET = #{street,jdbcType=VARCHAR} ,VENDOR = #{vendor,jdbcType=VARCHAR} ,ADDRESS = #{address,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,SAFE_MAN = #{safeMan,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,PROVINCE = #{province,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SAFE_PHONE = #{safePhone,jdbcType=VARCHAR} ,DEVICE_CODE = #{deviceCode,jdbcType=VARCHAR} ,DEVICE_DESC = #{deviceDesc,jdbcType=VARCHAR} ,DEVICE_NAME = #{deviceName,jdbcType=VARCHAR} ,INSTALL_MAN = #{installMan,jdbcType=VARCHAR} ,SAFE_COMPANY = #{safeCompany,jdbcType=VARCHAR} ,VILLAGE_NAME = #{villageName,jdbcType=VARCHAR} ,EQUIPMENT_UUID = #{equipmentUuid,jdbcType=VARCHAR} ,HAVE_NH = #{haveNh,jdbcType=INTEGER} ,HAVE_GAS = #{haveGas,jdbcType=INTEGER} ,HAVE_H2S = #{haveH2s,jdbcType=INTEGER} ,HAVE_WATER_LINE = #{haveWaterLine,jdbcType=INTEGER} ,SETUP_TIME = #{setupTime,jdbcType=TIMESTAMP} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} ,PRODUCT_TIME = #{productTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(XwEquipmentMhDO entity);
    /**
     * desc:根据主键删除数据:XW_EQUIPMENT_MH.<br/>
     * descSql =  DELETE FROM XW_EQUIPMENT_MH WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:XW_EQUIPMENT_MH.<br/>
     * descSql =  SELECT * FROM XW_EQUIPMENT_MH WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return XwEquipmentMhDO
     */
    XwEquipmentMhDO getById(Long id);
    /**
     * desc:新增表:XW_EQUIPMENT_MH.<br/>
     * descSql =  insert into xw_equipment_mh id, equipment_uuid, device_no, device_name, device_code, device_desc, latitude, longitude, product_time, setup_time, village_id, village_name, install_man, vendor, province_id, street_id, province, city_id, city, area_id, area, street, address, have_water_line, have_gas, have_h2s, safe_company, safe_man, safe_phone, create_by, create_time, update_by, update_time, del_flag, device_type, safe_time, install_phone, have_humi, #{id,jdbcType=BIGINT}, #{equipmentUuid,jdbcType=VARCHAR}, #{deviceNo,jdbcType=VARCHAR}, #{deviceName,jdbcType=VARCHAR}, #{deviceCode,jdbcType=VARCHAR}, #{deviceDesc,jdbcType=VARCHAR}, #{latitude,jdbcType=VARCHAR}, #{longitude,jdbcType=VARCHAR}, #{productTime,jdbcType=TIMESTAMP}, #{setupTime,jdbcType=TIMESTAMP}, #{villageId,jdbcType=BIGINT}, #{villageName,jdbcType=VARCHAR}, #{installMan,jdbcType=VARCHAR}, #{vendor,jdbcType=VARCHAR}, #{provinceId,jdbcType=BIGINT}, #{streetId,jdbcType=BIGINT}, #{province,jdbcType=VARCHAR}, #{cityId,jdbcType=BIGINT}, #{city,jdbcType=VARCHAR}, #{areaId,jdbcType=BIGINT}, #{area,jdbcType=VARCHAR}, #{street,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{haveWaterLine,jdbcType=INTEGER}, #{haveGas,jdbcType=INTEGER}, #{haveH2s,jdbcType=INTEGER}, #{safeCompany,jdbcType=VARCHAR}, #{safeMan,jdbcType=VARCHAR}, #{safePhone,jdbcType=VARCHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, #{delFlag,jdbcType=CHAR}, #{deviceType,jdbcType=VARCHAR}, #{safeTime,jdbcType=TIMESTAMP}, #{installPhone,jdbcType=VARCHAR}, #{haveHumi,jdbcType=INTEGER}, 
     * @param entity entity
     * @return int
     */
    int insertMh(XwEquipmentMhDO entity);
    /**
     * desc:更新井盖设备.<br/>
     * descSql =  update xw_equipment_mh equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}, device_no = #{deviceNo,jdbcType=VARCHAR}, device_name = #{deviceName,jdbcType=VARCHAR}, device_code = #{deviceCode,jdbcType=VARCHAR}, device_desc = #{deviceDesc,jdbcType=VARCHAR}, latitude = #{latitude,jdbcType=VARCHAR}, longitude = #{longitude,jdbcType=VARCHAR}, product_time = #{productTime,jdbcType=TIMESTAMP}, setup_time = #{setupTime,jdbcType=TIMESTAMP}, village_id = #{villageId,jdbcType=BIGINT}, village_name = #{villageName,jdbcType=VARCHAR}, install_man = #{installMan,jdbcType=VARCHAR}, vendor = #{vendor,jdbcType=VARCHAR}, province_id = #{provinceId,jdbcType=BIGINT}, province = #{province,jdbcType=VARCHAR}, city_id = #{cityId,jdbcType=BIGINT}, city = #{city,jdbcType=VARCHAR}, area_id = #{areaId,jdbcType=BIGINT}, street_id = #{streetId,jdbcType=BIGINT}, area = #{area,jdbcType=VARCHAR}, street = #{street,jdbcType=VARCHAR}, address = #{address,jdbcType=VARCHAR}, have_water_line = #{haveWaterLine,jdbcType=INTEGER}, have_gas = #{haveGas,jdbcType=INTEGER}, have_h2s = #{haveH2s,jdbcType=INTEGER}, safe_company = #{safeCompany,jdbcType=VARCHAR}, safe_man = #{safeMan,jdbcType=VARCHAR}, safe_phone = #{safePhone,jdbcType=VARCHAR}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, update_time = #{updateTime,jdbcType=TIMESTAMP}, del_flag = #{delFlag,jdbcType=CHAR}, safe_time = #{safeTime,jdbcType=TIMESTAMP}, install_phone = #{installPhone,jdbcType=VARCHAR}, have_humi = #{haveHumi,jdbcType=INTEGER}, where equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    int updateMh(XwEquipmentMhDO entity);
    /**
     * desc:feignMHbyDeviceNo.<br/>
     * descSql =  SELECT id, equipment_uuid, device_no, device_name, device_code, device_desc, latitude, longitude, product_time, setup_time, village_id, village_name, install_man, vendor, province_id, province, city_id, city, area_id, area, street_id, street, address, have_water_line, have_gas, have_h2s, safe_company, safe_man, safe_phone, create_by, create_time, update_by, update_time, del_flag, device_type, install_phone, safe_time FROM xw_equipment_mh WHERE device_no=#{deviceNo,jdbcType=VARCHAR}
     * @param deviceNo deviceNo
     * @return XwEquipmentMhDO
     */
    XwEquipmentMhDO feignMHbyDeviceNo(String deviceNo);
    /**
     * desc:更新井盖推送状态.<br/>
     * descSql =  update xw_equipment_mh fault_push= #{faultPush,jdbcType=INTEGER}, where equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    int updatePush(XwEquipmentMhDO entity);
}
