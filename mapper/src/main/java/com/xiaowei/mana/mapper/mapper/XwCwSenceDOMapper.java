package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.XwCwSenceDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_CW_SENCE.
 * 饮用水场景表
 */
public interface XwCwSenceDOMapper{

    /**
     * desc:插入表:XW_CW_SENCE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO XW_CW_SENCE( DEL_FLAG ,CREATE_BY ,DEVICE_NO ,TANK_SEAL ,TANK_SIZE ,UPDATE_BY ,SENCE_UUID ,TANK_MATERIAL ,PRINCIPAL_PHONE ,SENCE_PRINCIPAL ,CLEANING_FREQUENCY ,WATER_SUPPORT_STATUS ,SENCE_PROPERTY_COMPANY ,CREATE_TIME ,UPDATE_TIME ,LAST_CLEANING_TIME ,SENCE_INSTALL_TIME )VALUES( #{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{tankSeal,jdbcType=VARCHAR} ,#{tankSize,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{senceUuid,jdbcType=VARCHAR} ,#{tankMaterial,jdbcType=VARCHAR} ,#{principalPhone,jdbcType=VARCHAR} ,#{sencePrincipal,jdbcType=VARCHAR} ,#{cleaningFrequency,jdbcType=VARCHAR} ,#{waterSupportStatus,jdbcType=VARCHAR} ,#{sencePropertyCompany,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} ,#{lastCleaningTime,jdbcType=TIMESTAMP} ,#{senceInstallTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(XwCwSenceDO entity);
    /**
     * desc:更新表:XW_CW_SENCE.<br/>
     * descSql =  UPDATE XW_CW_SENCE SET DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,TANK_SEAL = #{tankSeal,jdbcType=VARCHAR} ,TANK_SIZE = #{tankSize,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SENCE_UUID = #{senceUuid,jdbcType=VARCHAR} ,TANK_MATERIAL = #{tankMaterial,jdbcType=VARCHAR} ,PRINCIPAL_PHONE = #{principalPhone,jdbcType=VARCHAR} ,SENCE_PRINCIPAL = #{sencePrincipal,jdbcType=VARCHAR} ,CLEANING_FREQUENCY = #{cleaningFrequency,jdbcType=VARCHAR} ,WATER_SUPPORT_STATUS = #{waterSupportStatus,jdbcType=VARCHAR} ,SENCE_PROPERTY_COMPANY = #{sencePropertyCompany,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} ,LAST_CLEANING_TIME = #{lastCleaningTime,jdbcType=TIMESTAMP} ,SENCE_INSTALL_TIME = #{senceInstallTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(XwCwSenceDO entity);
    /**
     * desc:根据主键删除数据:XW_CW_SENCE.<br/>
     * descSql =  DELETE FROM XW_CW_SENCE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:XW_CW_SENCE.<br/>
     * descSql =  SELECT * FROM XW_CW_SENCE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return XwCwSenceDO
     */
    XwCwSenceDO getById(Long id);
    /**
     * desc:新增表:XW_CW_SENCE.<br/>
     * descSql =  insert into xw_cw_sence id, sence_uuid, device_no, tank_size, tank_material, tank_seal, sence_principal, principal_phone, cleaning_frequency, water_support_status, last_cleaning_time, sence_install_time, sence_property_company, create_by, create_time, update_by, update_time, del_flag, cw_code, province_id, city_id, area_id, #{id,jdbcType=BIGINT}, #{senceUuid,jdbcType=VARCHAR}, #{deviceNo,jdbcType=VARCHAR}, #{tankSize,jdbcType=VARCHAR}, #{tankMaterial,jdbcType=VARCHAR}, #{tankSeal,jdbcType=VARCHAR}, #{sencePrincipal,jdbcType=VARCHAR}, #{principalPhone,jdbcType=VARCHAR}, #{cleaningFrequency,jdbcType=VARCHAR}, #{waterSupportStatus,jdbcType=VARCHAR}, #{lastCleaningTime,jdbcType=TIMESTAMP}, #{senceInstallTime,jdbcType=TIMESTAMP}, #{sencePropertyCompany,jdbcType=VARCHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, #{delFlag,jdbcType=CHAR}, #{cwCode,jdbcType=VARCHAR}, #{provinceId,jdbcType=BIGINT}, #{cityId,jdbcType=BIGINT}, #{areaId,jdbcType=BIGINT}, 
     * @param entity entity
     * @return XwCwSenceDO
     */
    XwCwSenceDO saveCwSence(XwCwSenceDO entity);
    /**
     * desc:更新引用水设备.<br/>
     * descSql =  update xw_cw_sence sence_uuid = #{senceUuid,jdbcType=VARCHAR}, device_no = #{deviceNo,jdbcType=VARCHAR}, tank_size = #{tankSize,jdbcType=VARCHAR}, tank_material = #{tankMaterial,jdbcType=VARCHAR}, tank_seal = #{tankSeal,jdbcType=VARCHAR}, sence_principal = #{sencePrincipal,jdbcType=VARCHAR}, principal_phone = #{principalPhone,jdbcType=VARCHAR}, cleaning_frequency = #{cleaningFrequency,jdbcType=VARCHAR}, water_support_status = #{waterSupportStatus,jdbcType=VARCHAR}, last_cleaning_time = #{lastCleaningTime,jdbcType=TIMESTAMP}, sence_install_time = #{senceInstallTime,jdbcType=TIMESTAMP}, sence_property_company = #{sencePropertyCompany,jdbcType=VARCHAR}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, update_time = #{updateTime,jdbcType=TIMESTAMP}, del_flag = #{delFlag,jdbcType=CHAR}, cw_code = #{cwCode,jdbcType=CHAR}, province_id = #{provinceId,jdbcType=BIGINT}, city_id = #{cityId,jdbcType=BIGINT}, area_id = #{areaId,jdbcType=BIGINT}, where device_no = #{deviceNo,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    int updateCwSence(XwCwSenceDO entity);
    /**
     * desc:根据 电梯编码 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM xw_cw_sence cw_code=#{cwCode,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    Integer getCwCode(XwCwSenceDO entity);
    /**
     * desc:根据 电梯编码 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM xw_cw_sence cw_code=#{cwCode,jdbcType=VARCHAR} id!=#{id,jdbcType=BIGINT} 
     * @param entity entity
     * @return Integer
     */
    Integer getEditCwCode(XwCwSenceDO entity);
}
