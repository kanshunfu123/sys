package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.XwEcSenceDO;
import java.util.List;
import com.xiaowei.mana.mapper.mapper.XwEcSenceDOMapper;

/**
* The Table XW_EC_SENCE.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 电梯场景表
*/
@Repository
public class XwEcSenceDAO{

    @Autowired
    private XwEcSenceDOMapper xwEcSenceDOMapper;

    /**
     * desc:插入表:XW_EC_SENCE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO XW_EC_SENCE( DEL_FLAG ,CREATE_BY ,DEVICE_NO ,UPDATE_BY ,SENCE_UUID ,ELEVATOR_USE ,EC_SUPERVISOR ,SENCE_SAFE_MAN ,SERIAL_NUMBER ,REGULATORY_UNIT ,SENCE_SAFE_PHONE ,SUPERVISOR_PHONE ,SENCE_SAFE_COMPANY ,EC_CREW ,EC_LOAD ,CREATE_TIME ,UPDATE_TIME ,PRODUCT_TIME ,SENCE_INSTALL_TIME )VALUES( #{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{senceUuid,jdbcType=VARCHAR} ,#{elevatorUse,jdbcType=VARCHAR} ,#{ecSupervisor,jdbcType=VARCHAR} ,#{senceSafeMan,jdbcType=VARCHAR} ,#{serialNumber,jdbcType=VARCHAR} ,#{regulatoryUnit,jdbcType=VARCHAR} ,#{senceSafePhone,jdbcType=VARCHAR} ,#{supervisorPhone,jdbcType=VARCHAR} ,#{senceSafeCompany,jdbcType=VARCHAR} ,#{ecCrew,jdbcType=INTEGER} ,#{ecLoad,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} ,#{productTime,jdbcType=TIMESTAMP} ,#{senceInstallTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(XwEcSenceDO entity){
        return xwEcSenceDOMapper.insert(entity);
    }
    /**
     * desc:更新表:XW_EC_SENCE.<br/>
     * descSql =  UPDATE XW_EC_SENCE SET DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SENCE_UUID = #{senceUuid,jdbcType=VARCHAR} ,ELEVATOR_USE = #{elevatorUse,jdbcType=VARCHAR} ,EC_SUPERVISOR = #{ecSupervisor,jdbcType=VARCHAR} ,SENCE_SAFE_MAN = #{senceSafeMan,jdbcType=VARCHAR} ,SERIAL_NUMBER = #{serialNumber,jdbcType=VARCHAR} ,REGULATORY_UNIT = #{regulatoryUnit,jdbcType=VARCHAR} ,SENCE_SAFE_PHONE = #{senceSafePhone,jdbcType=VARCHAR} ,SUPERVISOR_PHONE = #{supervisorPhone,jdbcType=VARCHAR} ,SENCE_SAFE_COMPANY = #{senceSafeCompany,jdbcType=VARCHAR} ,EC_CREW = #{ecCrew,jdbcType=INTEGER} ,EC_LOAD = #{ecLoad,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} ,PRODUCT_TIME = #{productTime,jdbcType=TIMESTAMP} ,SENCE_INSTALL_TIME = #{senceInstallTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(XwEcSenceDO entity){
        return xwEcSenceDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:XW_EC_SENCE.<br/>
     * descSql =  DELETE FROM XW_EC_SENCE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return xwEcSenceDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:XW_EC_SENCE.<br/>
     * descSql =  SELECT * FROM XW_EC_SENCE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return XwEcSenceDO
     */
    public XwEcSenceDO getById(Long id){
        return xwEcSenceDOMapper.getById(id);
    }
    /**
     * desc:新增表:XW_EC_SENCE.<br/>
     * descSql =  insert into xw_ec_sence id, sence_uuid, device_no, serial_number, regulatory_unit, ec_supervisor, supervisor_phone, elevator_use, ec_crew, ec_load, product_time, sence_install_time, sence_safe_company, sence_safe_man, sence_safe_phone, create_by, create_time, update_by, update_time, del_flag, elevator_code, ec_brand, years_of_user, province_id, city_id, area_id, #{id,jdbcType=BIGINT}, #{senceUuid,jdbcType=VARCHAR}, #{deviceNo,jdbcType=VARCHAR}, #{serialNumber,jdbcType=VARCHAR}, #{regulatoryUnit,jdbcType=VARCHAR}, #{ecSupervisor,jdbcType=VARCHAR}, #{supervisorPhone,jdbcType=VARCHAR}, #{elevatorUse,jdbcType=VARCHAR}, #{ecCrew,jdbcType=INTEGER}, #{ecLoad,jdbcType=INTEGER}, #{productTime,jdbcType=TIMESTAMP}, #{senceInstallTime,jdbcType=TIMESTAMP}, #{senceSafeCompany,jdbcType=VARCHAR}, #{senceSafeMan,jdbcType=VARCHAR}, #{senceSafePhone,jdbcType=VARCHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, #{delFlag,jdbcType=CHAR}, #{elevatorCode,jdbcType=VARCHAR}, #{ecBrand,jdbcType=VARCHAR}, #{yearsOfUser,jdbcType=INTEGER}, #{provinceId,jdbcType=BIGINT}, #{cityId,jdbcType=BIGINT}, #{areaId,jdbcType=BIGINT}, 
     * @param entity entity
     * @return int
     */
    public int insertEcSence(XwEcSenceDO entity){
        return xwEcSenceDOMapper.insertEcSence(entity);
    }
    /**
     * desc:更新电梯场景信息.<br/>
     * descSql =  update xw_ec_sence sence_uuid = #{senceUuid,jdbcType=VARCHAR}, device_no = #{deviceNo,jdbcType=VARCHAR}, serial_number = #{serialNumber,jdbcType=VARCHAR}, regulatory_unit = #{regulatoryUnit,jdbcType=VARCHAR}, ec_supervisor = #{ecSupervisor,jdbcType=VARCHAR}, supervisor_phone = #{supervisorPhone,jdbcType=VARCHAR}, elevator_use = #{elevatorUse,jdbcType=VARCHAR}, ec_crew = #{ecCrew,jdbcType=INTEGER}, ec_load = #{ecLoad,jdbcType=INTEGER}, product_time = #{productTime,jdbcType=TIMESTAMP}, sence_install_time = #{senceInstallTime,jdbcType=TIMESTAMP}, sence_safe_company = #{senceSafeCompany,jdbcType=VARCHAR}, sence_safe_man = #{senceSafeMan,jdbcType=VARCHAR}, sence_safe_phone = #{senceSafePhone,jdbcType=VARCHAR}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, update_time = #{updateTime,jdbcType=TIMESTAMP}, del_flag = #{delFlag,jdbcType=CHAR}, elevator_code = #{elevatorCode,jdbcType=VARCHAR}, ec_brand = #{ecBrand,jdbcType=VARCHAR}, years_of_user = #{yearsOfUser,jdbcType=INTEGER}, province_id = #{provinceId,jdbcType=BIGINT}, city_id = #{cityId,jdbcType=BIGINT}, area_id = #{areaId,jdbcType=BIGINT}, where device_no = #{deviceNo,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    public int updateEcSence(XwEcSenceDO entity){
        return xwEcSenceDOMapper.updateEcSence(entity);
    }
    /**
     * desc:根据 电梯编码 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM xw_ec_sence elevator_code=#{elevatorCode,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    public Integer getElevatorCode(XwEcSenceDO entity){
        return xwEcSenceDOMapper.getElevatorCode(entity);
    }
    /**
     * desc:根据 电梯编码 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM xw_ec_sence elevator_code=#{elevatorCode,jdbcType=VARCHAR} id!=#{id,jdbcType=BIGINT} 
     * @param entity entity
     * @return Integer
     */
    public Integer getEditElevatorCode(XwEcSenceDO entity){
        return xwEcSenceDOMapper.getEditElevatorCode(entity);
    }
    /**
     * desc:编辑时,查询已存在的数量,排除自身.<br/>
     * descSql =  select device_no, elevator_code from xw_ec_sence where elevator_code=#{elevatorCode,jdbcType=VARCHAR} AND device_no!=#{deviceNo,jdbcType=VARCHAR}
     * @param deviceNo deviceNo
     * @param elevatorCode elevatorCode
     * @return List<XwEcSenceDO>
     */
    public List<XwEcSenceDO> getEditCountsByCode(String deviceNo,String elevatorCode){
        return xwEcSenceDOMapper.getEditCountsByCode(deviceNo, elevatorCode);
    }
}
