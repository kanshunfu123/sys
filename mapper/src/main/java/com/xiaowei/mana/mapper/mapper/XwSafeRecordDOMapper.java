package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.XwSafeRecordDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_SAFE_RECORD.
 * XW_SAFE_RECORD
 */
public interface XwSafeRecordDOMapper{

    /**
     * desc:插入表:XW_SAFE_RECORD.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO XW_SAFE_RECORD( DEL_FLAG ,SAFE_MAN ,CREATE_BY ,DEVICE_NO ,SAFE_PHONE ,DEVICE_TYPE ,SAFE_COMPANY ,SAFE_RECORD_UUID ,SAFE_TIME ,CREATE_TIME )VALUES( #{delFlag,jdbcType=CHAR} ,#{safeMan,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{safePhone,jdbcType=VARCHAR} ,#{deviceType,jdbcType=CHAR} ,#{safeCompany,jdbcType=VARCHAR} ,#{safeRecordUuid,jdbcType=VARCHAR} ,#{safeTime,jdbcType=TIMESTAMP} ,#{createTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(XwSafeRecordDO entity);
    /**
     * desc:更新表:XW_SAFE_RECORD.<br/>
     * descSql =  UPDATE XW_SAFE_RECORD SET DEL_FLAG = #{delFlag,jdbcType=CHAR} ,SAFE_MAN = #{safeMan,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,SAFE_PHONE = #{safePhone,jdbcType=VARCHAR} ,DEVICE_TYPE = #{deviceType,jdbcType=CHAR} ,SAFE_COMPANY = #{safeCompany,jdbcType=VARCHAR} ,SAFE_RECORD_UUID = #{safeRecordUuid,jdbcType=VARCHAR} ,SAFE_TIME = #{safeTime,jdbcType=TIMESTAMP} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(XwSafeRecordDO entity);
    /**
     * desc:根据主键删除数据:XW_SAFE_RECORD.<br/>
     * descSql =  DELETE FROM XW_SAFE_RECORD WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:XW_SAFE_RECORD.<br/>
     * descSql =  SELECT * FROM XW_SAFE_RECORD WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return XwSafeRecordDO
     */
    XwSafeRecordDO getById(Long id);
}
