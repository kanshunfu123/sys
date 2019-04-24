package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentRwDO;
import com.xiaowei.mana.mapper.mapper.XwEquipmentRwDOMapper;

/**
* The Table XW_EQUIPMENT_RW.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 河道水设备
*/
@Repository
public class XwEquipmentRwDAO{

    @Autowired
    private XwEquipmentRwDOMapper xwEquipmentRwDOMapper;

    /**
     * desc:插入表:XW_EQUIPMENT_RW.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO XW_EQUIPMENT_RW( AREA_ID ,CITY_ID ,PROVINCE_ID ,LATITUDE ,LONGITUDE ,AREA ,CITY ,VENDOR ,ADDRESS ,DEL_FLAG ,SAFE_MAN ,CREATE_BY ,DEVICE_NO ,PROVINCE ,UPDATE_BY ,SAFE_PHONE ,DEVICE_CODE ,DEVICE_DESC ,DEVICE_NAME ,INSTALL_MAN ,SAFE_COMPANY ,EQUIPMENT_UUID ,ALARM_DEPTH ,SETUP_TIME ,CREATE_TIME ,UPDATE_TIME ,PRODUCT_TIME )VALUES( #{areaId,jdbcType=BIGINT} ,#{cityId,jdbcType=BIGINT} ,#{provinceId,jdbcType=BIGINT} ,#{latitude,jdbcType=REAL} ,#{longitude,jdbcType=REAL} ,#{area,jdbcType=VARCHAR} ,#{city,jdbcType=VARCHAR} ,#{vendor,jdbcType=VARCHAR} ,#{address,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{safeMan,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{province,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{safePhone,jdbcType=VARCHAR} ,#{deviceCode,jdbcType=VARCHAR} ,#{deviceDesc,jdbcType=VARCHAR} ,#{deviceName,jdbcType=VARCHAR} ,#{installMan,jdbcType=VARCHAR} ,#{safeCompany,jdbcType=VARCHAR} ,#{equipmentUuid,jdbcType=VARCHAR} ,#{alarmDepth,jdbcType=INTEGER} ,#{setupTime,jdbcType=TIMESTAMP} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} ,#{productTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(XwEquipmentRwDO entity){
        return xwEquipmentRwDOMapper.insert(entity);
    }
    /**
     * desc:更新表:XW_EQUIPMENT_RW.<br/>
     * descSql =  UPDATE XW_EQUIPMENT_RW SET AREA_ID = #{areaId,jdbcType=BIGINT} ,CITY_ID = #{cityId,jdbcType=BIGINT} ,PROVINCE_ID = #{provinceId,jdbcType=BIGINT} ,LATITUDE = #{latitude,jdbcType=REAL} ,LONGITUDE = #{longitude,jdbcType=REAL} ,AREA = #{area,jdbcType=VARCHAR} ,CITY = #{city,jdbcType=VARCHAR} ,VENDOR = #{vendor,jdbcType=VARCHAR} ,ADDRESS = #{address,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,SAFE_MAN = #{safeMan,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,PROVINCE = #{province,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SAFE_PHONE = #{safePhone,jdbcType=VARCHAR} ,DEVICE_CODE = #{deviceCode,jdbcType=VARCHAR} ,DEVICE_DESC = #{deviceDesc,jdbcType=VARCHAR} ,DEVICE_NAME = #{deviceName,jdbcType=VARCHAR} ,INSTALL_MAN = #{installMan,jdbcType=VARCHAR} ,SAFE_COMPANY = #{safeCompany,jdbcType=VARCHAR} ,EQUIPMENT_UUID = #{equipmentUuid,jdbcType=VARCHAR} ,ALARM_DEPTH = #{alarmDepth,jdbcType=INTEGER} ,SETUP_TIME = #{setupTime,jdbcType=TIMESTAMP} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} ,PRODUCT_TIME = #{productTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(XwEquipmentRwDO entity){
        return xwEquipmentRwDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:XW_EQUIPMENT_RW.<br/>
     * descSql =  DELETE FROM XW_EQUIPMENT_RW WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return xwEquipmentRwDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:XW_EQUIPMENT_RW.<br/>
     * descSql =  SELECT * FROM XW_EQUIPMENT_RW WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return XwEquipmentRwDO
     */
    public XwEquipmentRwDO getById(Long id){
        return xwEquipmentRwDOMapper.getById(id);
    }
    /**
     * desc:新增表:XW_EQUIPMENT_RW.<br/>
     * descSql =  insert into xw_equipment_rw id, equipment_uuid, device_no, device_name, device_code, device_desc, latitude, longitude, alarm_depth, product_time, setup_time, install_man, vendor, have_nh, province_id, province, city_id, city, area_id, area, address, safe_company, safe_man, safe_phone, create_by, create_time, update_by, update_time, del_flag, device_type, safe_time, install_phone, have_turbidity, #{id,jdbcType=BIGINT}, #{equipmentUuid,jdbcType=VARCHAR}, #{deviceNo,jdbcType=VARCHAR}, #{deviceName,jdbcType=VARCHAR}, #{deviceCode,jdbcType=VARCHAR}, #{deviceDesc,jdbcType=VARCHAR}, #{latitude,jdbcType=VARCHAR}, #{longitude,jdbcType=VARCHAR}, #{alarmDepth,jdbcType=INTEGER}, #{productTime,jdbcType=TIMESTAMP}, #{setupTime,jdbcType=TIMESTAMP}, #{installMan,jdbcType=VARCHAR}, #{vendor,jdbcType=VARCHAR}, #{haveNh,jdbcType=INTEGER}, #{provinceId,jdbcType=BIGINT}, #{province,jdbcType=VARCHAR}, #{cityId,jdbcType=BIGINT}, #{city,jdbcType=VARCHAR}, #{areaId,jdbcType=BIGINT}, #{area,jdbcType=VARCHAR}, #{address,jdbcType=VARCHAR}, #{safeCompany,jdbcType=VARCHAR}, #{safeMan,jdbcType=VARCHAR}, #{safePhone,jdbcType=VARCHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, #{delFlag,jdbcType=CHAR}, #{deviceType,jdbcType=VARCHAR}, #{safeTime,jdbcType=TIMESTAMP}, #{installPhone,jdbcType=VARCHAR}, #{haveTurbidity,jdbcType=INTEGER}, 
     * @param entity entity
     * @return int
     */
    public int insertRw(XwEquipmentRwDO entity){
        return xwEquipmentRwDOMapper.insertRw(entity);
    }
    /**
     * desc:更新井盖设备.<br/>
     * descSql =  update xw_equipment_rw equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}, device_no = #{deviceNo,jdbcType=VARCHAR}, device_name = #{deviceName,jdbcType=VARCHAR}, device_code = #{deviceCode,jdbcType=VARCHAR}, device_desc = #{deviceDesc,jdbcType=VARCHAR}, latitude = #{latitude,jdbcType=VARCHAR}, longitude = #{longitude,jdbcType=VARCHAR}, alarm_depth = #{alarmDepth,jdbcType=INTEGER}, product_time = #{productTime,jdbcType=TIMESTAMP}, setup_time = #{setupTime,jdbcType=TIMESTAMP}, install_man = #{installMan,jdbcType=VARCHAR}, vendor = #{vendor,jdbcType=VARCHAR}, have_nh = #{haveNh,jdbcType=INTEGER}, province_id = #{provinceId,jdbcType=BIGINT}, province = #{province,jdbcType=VARCHAR}, city_id = #{cityId,jdbcType=BIGINT}, city = #{city,jdbcType=VARCHAR}, area_id = #{areaId,jdbcType=BIGINT}, area = #{area,jdbcType=VARCHAR}, address = #{address,jdbcType=VARCHAR}, safe_company = #{safeCompany,jdbcType=VARCHAR}, safe_man = #{safeMan,jdbcType=VARCHAR}, safe_phone = #{safePhone,jdbcType=VARCHAR}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, update_time = #{updateTime,jdbcType=TIMESTAMP}, del_flag = #{delFlag,jdbcType=CHAR}, safe_time = #{safeTime,jdbcType=TIMESTAMP}, install_phone = #{installPhone,jdbcType=VARCHAR}, have_turbidity = #{haveTurbidity,jdbcType=INTEGER}, where equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    public int updateRw(XwEquipmentRwDO entity){
        return xwEquipmentRwDOMapper.updateRw(entity);
    }
    /**
     * desc:feignRWbyDeviceNo.<br/>
     * descSql =  SELECT id, equipment_uuid, device_no, device_name, device_code, device_desc, latitude, longitude, alarm_depth, product_time, setup_time, install_man, vendor, have_nh, province_id, province, city_id, city, area_id, area, address, safe_company, safe_man, safe_phone, create_by, create_time, update_by, update_time, del_flag, device_type, install_phone, safe_time FROM xw_equipment_rw WHERE device_no=#{deviceNo,jdbcType=VARCHAR}
     * @param deviceNo deviceNo
     * @return XwEquipmentRwDO
     */
    public XwEquipmentRwDO feignRWbyDeviceNo(String deviceNo){
        return xwEquipmentRwDOMapper.feignRWbyDeviceNo(deviceNo);
    }
    /**
     * desc:更新井盖推送状态.<br/>
     * descSql =  update xw_equipment_rw equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}, fault_push= #{faultPush,jdbcType=INTEGER}, device_no = #{deviceNo,jdbcType=VARCHAR}, where equipment_uuid = #{equipmentUuid,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    public int updatePush(XwEquipmentRwDO entity){
        return xwEquipmentRwDOMapper.updatePush(entity);
    }
}
