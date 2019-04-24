package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.OfficialWebsiteDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table OFFICIAL_WEBSITE.
 * OFFICIAL_WEBSITE
 */
public interface OfficialWebsiteDOMapper{

    /**
     * desc:插入表:OFFICIAL_WEBSITE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO OFFICIAL_WEBSITE( COM ,TEL ,NAME ,UUID ,OFFICE ,DEL_FLAG ,MESSAGE ,CREATE_BY ,UPDATE_BY ,CREATE_TIME ,UPDATE_TIME )VALUES( #{com,jdbcType=VARCHAR} ,#{tel,jdbcType=VARCHAR} ,#{name,jdbcType=VARCHAR} ,#{uuid,jdbcType=VARCHAR} ,#{office,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{message,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(OfficialWebsiteDO entity);
    /**
     * desc:更新表:OFFICIAL_WEBSITE.<br/>
     * descSql =  UPDATE OFFICIAL_WEBSITE SET COM = #{com,jdbcType=VARCHAR} ,TEL = #{tel,jdbcType=VARCHAR} ,NAME = #{name,jdbcType=VARCHAR} ,UUID = #{uuid,jdbcType=VARCHAR} ,OFFICE = #{office,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,MESSAGE = #{message,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(OfficialWebsiteDO entity);
    /**
     * desc:根据主键删除数据:OFFICIAL_WEBSITE.<br/>
     * descSql =  DELETE FROM OFFICIAL_WEBSITE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:OFFICIAL_WEBSITE.<br/>
     * descSql =  SELECT * FROM OFFICIAL_WEBSITE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return OfficialWebsiteDO
     */
    OfficialWebsiteDO getById(Long id);
    /**
     * desc:新增表:OFFICIAL_WEBSITE.<br/>
     * descSql =  insert into official_website id, uuid, name, tel, com, office, message, del_flag, create_by, create_time, update_by, update_time, #{id,jdbcType=BIGINT}, #{uuid,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{tel,jdbcType=VARCHAR}, #{com,jdbcType=VARCHAR}, #{office,jdbcType=VARCHAR}, #{message,jdbcType=VARCHAR}, #{delFlag,jdbcType=CHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, 
     * @param entity entity
     * @return OfficialWebsiteDO
     */
    OfficialWebsiteDO saveOfficial(OfficialWebsiteDO entity);
}
