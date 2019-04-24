package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.XwSafeRecordDO;
import com.xiaowei.mana.mapper.mapper.XwSafeRecordDOMapper;

/**
* The Table XW_SAFE_RECORD.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* XW_SAFE_RECORD
*/
@Repository
public class XwSafeRecordDAO{

    @Autowired
    private XwSafeRecordDOMapper xwSafeRecordDOMapper;

    /**
     * desc:插入表:XW_SAFE_RECORD.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO XW_SAFE_RECORD( DEL_FLAG ,SAFE_MAN ,CREATE_BY ,DEVICE_NO ,SAFE_PHONE ,DEVICE_TYPE ,SAFE_COMPANY ,SAFE_RECORD_UUID ,SAFE_TIME ,CREATE_TIME )VALUES( #{delFlag,jdbcType=CHAR} ,#{safeMan,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{deviceNo,jdbcType=VARCHAR} ,#{safePhone,jdbcType=VARCHAR} ,#{deviceType,jdbcType=CHAR} ,#{safeCompany,jdbcType=VARCHAR} ,#{safeRecordUuid,jdbcType=VARCHAR} ,#{safeTime,jdbcType=TIMESTAMP} ,#{createTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(XwSafeRecordDO entity){
        return xwSafeRecordDOMapper.insert(entity);
    }
    /**
     * desc:更新表:XW_SAFE_RECORD.<br/>
     * descSql =  UPDATE XW_SAFE_RECORD SET DEL_FLAG = #{delFlag,jdbcType=CHAR} ,SAFE_MAN = #{safeMan,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,DEVICE_NO = #{deviceNo,jdbcType=VARCHAR} ,SAFE_PHONE = #{safePhone,jdbcType=VARCHAR} ,DEVICE_TYPE = #{deviceType,jdbcType=CHAR} ,SAFE_COMPANY = #{safeCompany,jdbcType=VARCHAR} ,SAFE_RECORD_UUID = #{safeRecordUuid,jdbcType=VARCHAR} ,SAFE_TIME = #{safeTime,jdbcType=TIMESTAMP} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(XwSafeRecordDO entity){
        return xwSafeRecordDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:XW_SAFE_RECORD.<br/>
     * descSql =  DELETE FROM XW_SAFE_RECORD WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return xwSafeRecordDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:XW_SAFE_RECORD.<br/>
     * descSql =  SELECT * FROM XW_SAFE_RECORD WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return XwSafeRecordDO
     */
    public XwSafeRecordDO getById(Long id){
        return xwSafeRecordDOMapper.getById(id);
    }
}
