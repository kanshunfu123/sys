package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.XwRwSenceDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_RW_SENCE.
 * 河道水场景
 */
public interface XwRwSenceDOMapper{

    /**
     * desc:插入表:XW_RW_SENCE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO XW_RW_SENCE( RW_NAME ,RW_TYPE ,DEL_FLAG ,RW_DEPTH ,RW_LEVEE ,RW_RANGE ,RW_WIDTH ,CREATE_BY ,DEVICE_NO ,RW_LENGTH ,RW_REMARK ,UPDATE_BY ,SENCE_UUID ,PRINCIPAL_PHONE ,SENCE_PRINCIPAL ,RW_POLLUTE_SOURCE ,CREATE_TIME ,UPDATE_TIME ,SENCE_INSTALL_TIME )VALUES( #{rwName,jdbcType=VARCHAR} ,#{rwType,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{rwDepth,jdbcType=VARCHAR} ,#{rwLevee,jdbcType=VARCHAR} ,#{rwRange,jdbcType=VARCHAR} ,#{rwWidth,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{rwLength,jdbcType=VARCHAR} ,#{rwRemark,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{senceUuid,jdbcType=VARCHAR} ,#{principalPhone,jdbcType=VARCHAR} ,#{sencePrincipal,jdbcType=VARCHAR} ,#{rwPolluteSource,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} ,#{senceInstallTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(XwRwSenceDO entity);
    /**
     * desc:更新表:XW_RW_SENCE.<br/>
     * descSql =  UPDATE XW_RW_SENCE SET RW_NAME = #{rwName,jdbcType=VARCHAR} ,RW_TYPE = #{rwType,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,RW_DEPTH = #{rwDepth,jdbcType=VARCHAR} ,RW_LEVEE = #{rwLevee,jdbcType=VARCHAR} ,RW_RANGE = #{rwRange,jdbcType=VARCHAR} ,RW_WIDTH = #{rwWidth,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,RW_LENGTH = #{rwLength,jdbcType=VARCHAR} ,RW_REMARK = #{rwRemark,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SENCE_UUID = #{senceUuid,jdbcType=VARCHAR} ,PRINCIPAL_PHONE = #{principalPhone,jdbcType=VARCHAR} ,SENCE_PRINCIPAL = #{sencePrincipal,jdbcType=VARCHAR} ,RW_POLLUTE_SOURCE = #{rwPolluteSource,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} ,SENCE_INSTALL_TIME = #{senceInstallTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(XwRwSenceDO entity);
    /**
     * desc:根据主键删除数据:XW_RW_SENCE.<br/>
     * descSql =  DELETE FROM XW_RW_SENCE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:XW_RW_SENCE.<br/>
     * descSql =  SELECT * FROM XW_RW_SENCE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return XwRwSenceDO
     */
    XwRwSenceDO getById(Long id);
    /**
     * desc:新增表:XW_RW_SENCE.<br/>
     * descSql =  insert into xw_rw_sence id, sence_uuid, device_no, rw_name, rw_width, rw_length, rw_depth, rw_levee, rw_pollute_source, sence_principal, principal_phone, rw_type, rw_range, rw_remark, sence_install_time, create_by, create_time, update_by, update_time, del_flag, rw_code, province_id, city_id, area_id, rw_grade, #{id,jdbcType=BIGINT}, #{senceUuid,jdbcType=VARCHAR}, #{deviceNo,jdbcType=VARCHAR}, #{rwName,jdbcType=VARCHAR}, #{rwWidth,jdbcType=VARCHAR}, #{rwLength,jdbcType=VARCHAR}, #{rwDepth,jdbcType=VARCHAR}, #{rwLevee,jdbcType=VARCHAR}, #{rwPolluteSource,jdbcType=VARCHAR}, #{sencePrincipal,jdbcType=VARCHAR}, #{principalPhone,jdbcType=VARCHAR}, #{rwType,jdbcType=VARCHAR}, #{rwRange,jdbcType=VARCHAR}, #{rwRemark,jdbcType=VARCHAR}, #{senceInstallTime,jdbcType=TIMESTAMP}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, #{delFlag,jdbcType=CHAR}, #{rwCode,jdbcType=VARCHAR}, #{provinceId,jdbcType=BIGINT}, #{cityId,jdbcType=BIGINT}, #{areaId,jdbcType=BIGINT}, #{rwGrade,jdbcType=INTEGER}, 
     * @param entity entity
     * @return int
     */
    int insertRwSence(XwRwSenceDO entity);
    /**
     * desc:更新地表水场景信息.<br/>
     * descSql =  update xw_rw_sence sence_uuid = #{senceUuid,jdbcType=VARCHAR}, device_no = #{deviceNo,jdbcType=VARCHAR}, rw_name = #{rwName,jdbcType=VARCHAR}, rw_width = #{rwWidth,jdbcType=VARCHAR}, rw_length = #{rwLength,jdbcType=VARCHAR}, rw_depth = #{rwDepth,jdbcType=VARCHAR}, rw_levee = #{rwLevee,jdbcType=VARCHAR}, rw_pollute_source = #{rwPolluteSource,jdbcType=VARCHAR}, sence_principal = #{sencePrincipal,jdbcType=VARCHAR}, principal_phone = #{principalPhone,jdbcType=VARCHAR}, rw_type = #{rwType,jdbcType=VARCHAR}, rw_range = #{rwRange,jdbcType=VARCHAR}, rw_remark = #{rwRemark,jdbcType=VARCHAR}, sence_install_time = #{senceInstallTime,jdbcType=TIMESTAMP}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, update_time = #{updateTime,jdbcType=TIMESTAMP}, del_flag = #{delFlag,jdbcType=CHAR}, rw_code=#{rwCode,jdbcType=VARCHAR}, province_id = #{provinceId,jdbcType=BIGINT}, city_id = #{cityId,jdbcType=BIGINT}, area_id = #{areaId,jdbcType=BIGINT}, rw_grade = #{rwGrade,jdbcType=INTEGER}, where device_no = #{deviceNo,jdbcType=VARCHAR}
     * @param entity entity
     * @return int
     */
    int updateRwSence(XwRwSenceDO entity);
    /**
     * desc:根据 地表水编码 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM xw_rw_sence rw_code=#{rwCode,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    Integer getRwCode(XwRwSenceDO entity);
    /**
     * desc:根据 地表水编码 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM xw_rw_sence rw_code=#{rwCode,jdbcType=VARCHAR} id!=#{id,jdbcType=BIGINT} 
     * @param entity entity
     * @return Integer
     */
    Integer getEditRwCode(XwRwSenceDO entity);
}
