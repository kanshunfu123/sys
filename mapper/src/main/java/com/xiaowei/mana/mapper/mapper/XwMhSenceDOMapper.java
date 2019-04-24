package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.XwMhSenceDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_MH_SENCE.
 * 井盖场景信息表
 */
public interface XwMhSenceDOMapper{

    /**
     * desc:插入表:XW_MH_SENCE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO XW_MH_SENCE( MH_USE ,MH_LOAD ,MH_SIZE ,DEL_FLAG ,CREATE_BY ,DEVICE_NO ,UPDATE_BY ,SENCE_UUID ,MH_MATERIAL ,TRAFFIC_FLOW ,PRINCIPAL_PHONE ,SENCE_PRINCIPAL ,MH_YEARS ,MH_END_TIME ,CREATE_TIME ,UPDATE_TIME ,SENCE_INSTALL_TIME )VALUES( #{mhUse,jdbcType=VARCHAR} ,#{mhLoad,jdbcType=VARCHAR} ,#{mhSize,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{senceUuid,jdbcType=VARCHAR} ,#{mhMaterial,jdbcType=VARCHAR} ,#{trafficFlow,jdbcType=VARCHAR} ,#{principalPhone,jdbcType=VARCHAR} ,#{sencePrincipal,jdbcType=VARCHAR} ,#{mhYears,jdbcType=INTEGER} ,#{mhEndTime,jdbcType=TIMESTAMP} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} ,#{senceInstallTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(XwMhSenceDO entity);
    /**
     * desc:更新表:XW_MH_SENCE.<br/>
     * descSql =  UPDATE XW_MH_SENCE SET MH_USE = #{mhUse,jdbcType=VARCHAR} ,MH_LOAD = #{mhLoad,jdbcType=VARCHAR} ,MH_SIZE = #{mhSize,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SENCE_UUID = #{senceUuid,jdbcType=VARCHAR} ,MH_MATERIAL = #{mhMaterial,jdbcType=VARCHAR} ,TRAFFIC_FLOW = #{trafficFlow,jdbcType=VARCHAR} ,PRINCIPAL_PHONE = #{principalPhone,jdbcType=VARCHAR} ,SENCE_PRINCIPAL = #{sencePrincipal,jdbcType=VARCHAR} ,MH_YEARS = #{mhYears,jdbcType=INTEGER} ,MH_END_TIME = #{mhEndTime,jdbcType=TIMESTAMP} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} ,SENCE_INSTALL_TIME = #{senceInstallTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(XwMhSenceDO entity);
    /**
     * desc:根据主键删除数据:XW_MH_SENCE.<br/>
     * descSql =  DELETE FROM XW_MH_SENCE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:XW_MH_SENCE.<br/>
     * descSql =  SELECT * FROM XW_MH_SENCE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return XwMhSenceDO
     */
    XwMhSenceDO getById(Long id);
    /**
     * desc:新增表:XW_MH_SENCE.<br/>
     * descSql =  insert into xw_mh_sence id, sence_uuid, device_no, mh_years, sence_principal, principal_phone, mh_load, mh_use, mh_material, traffic_flow, mh_size, mh_end_time, sence_install_time, create_by, create_time, update_by, update_time, del_flag, mh_code, province_id, city_id, area_id, mh_diameter, mh_type, #{id,jdbcType=BIGINT}, #{senceUuid,jdbcType=VARCHAR}, #{deviceNo,jdbcType=VARCHAR}, #{mhYears,jdbcType=INTEGER}, #{sencePrincipal,jdbcType=VARCHAR}, #{principalPhone,jdbcType=VARCHAR}, #{mhLoad,jdbcType=VARCHAR}, #{mhUse,jdbcType=VARCHAR}, #{mhMaterial,jdbcType=VARCHAR}, #{trafficFlow,jdbcType=VARCHAR}, #{mhSize,jdbcType=VARCHAR}, #{mhEndTime,jdbcType=TIMESTAMP}, #{senceInstallTime,jdbcType=TIMESTAMP}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, #{delFlag,jdbcType=CHAR}, #{mhCode,jdbcType=VARCHAR}, #{provinceId,jdbcType=BIGINT}, #{cityId,jdbcType=BIGINT}, #{areaId,jdbcType=BIGINT}, #{mhDiameter,jdbcType=VARCHAR}, #{mhType,jdbcType=VARCHAR}, 
     * @param entity entity
     * @return int
     */
    int insertMhSence(XwMhSenceDO entity);
    /**
     * desc:更新井盖场景信息.<br/>
     * descSql =  update xw_mh_sence sence_uuid = #{senceUuid,jdbcType=VARCHAR}, device_no = #{deviceNo,jdbcType=VARCHAR}, mh_years = #{mhYears,jdbcType=INTEGER}, sence_principal = #{sencePrincipal,jdbcType=VARCHAR}, principal_phone = #{principalPhone,jdbcType=VARCHAR}, mh_load = #{mhLoad,jdbcType=VARCHAR}, mh_use = #{mhUse,jdbcType=VARCHAR}, mh_material = #{mhMaterial,jdbcType=VARCHAR}, traffic_flow = #{trafficFlow,jdbcType=VARCHAR}, mh_size = #{mhSize,jdbcType=VARCHAR}, mh_end_time = #{mhEndTime,jdbcType=TIMESTAMP}, sence_install_time = #{senceInstallTime,jdbcType=TIMESTAMP}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, update_time = #{updateTime,jdbcType=TIMESTAMP}, del_flag = #{delFlag,jdbcType=CHAR}, mh_code = #{mhCode,jdbcType=VARCHAR}, province_id = #{provinceId,jdbcType=BIGINT}, city_id = #{cityId,jdbcType=BIGINT}, area_id = #{areaId,jdbcType=BIGINT}, mh_diameter = #{mhDiameter,jdbcType=VARCHAR}, mh_type = #{mhType,jdbcType=VARCHAR}, where device_no = #{deviceNo,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    int updateMhSence(XwMhSenceDO entity);
    /**
     * desc:根据 电梯编码 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM xw_mh_sence mh_code=#{mhCode,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    Integer getMhCode(XwMhSenceDO entity);
    /**
     * desc:根据 电梯编码 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM xw_mh_sence mh_code=#{mhCode,jdbcType=VARCHAR} id!=#{id,jdbcType=BIGINT} 
     * @param entity entity
     * @return Integer
     */
    Integer getEditMhCode(XwMhSenceDO entity);
}
