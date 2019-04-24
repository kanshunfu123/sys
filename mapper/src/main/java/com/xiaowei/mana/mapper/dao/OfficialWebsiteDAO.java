package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.OfficialWebsiteDO;
import com.xiaowei.mana.mapper.mapper.OfficialWebsiteDOMapper;

/**
* The Table OFFICIAL_WEBSITE.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* OFFICIAL_WEBSITE
*/
@Repository
public class OfficialWebsiteDAO{

    @Autowired
    private OfficialWebsiteDOMapper officialWebsiteDOMapper;

    /**
     * desc:插入表:OFFICIAL_WEBSITE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO OFFICIAL_WEBSITE( COM ,TEL ,NAME ,UUID ,OFFICE ,DEL_FLAG ,MESSAGE ,CREATE_BY ,UPDATE_BY ,CREATE_TIME ,UPDATE_TIME )VALUES( #{com,jdbcType=VARCHAR} ,#{tel,jdbcType=VARCHAR} ,#{name,jdbcType=VARCHAR} ,#{uuid,jdbcType=VARCHAR} ,#{office,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{message,jdbcType=VARCHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(OfficialWebsiteDO entity){
        return officialWebsiteDOMapper.insert(entity);
    }
    /**
     * desc:更新表:OFFICIAL_WEBSITE.<br/>
     * descSql =  UPDATE OFFICIAL_WEBSITE SET COM = #{com,jdbcType=VARCHAR} ,TEL = #{tel,jdbcType=VARCHAR} ,NAME = #{name,jdbcType=VARCHAR} ,UUID = #{uuid,jdbcType=VARCHAR} ,OFFICE = #{office,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,MESSAGE = #{message,jdbcType=VARCHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(OfficialWebsiteDO entity){
        return officialWebsiteDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:OFFICIAL_WEBSITE.<br/>
     * descSql =  DELETE FROM OFFICIAL_WEBSITE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return officialWebsiteDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:OFFICIAL_WEBSITE.<br/>
     * descSql =  SELECT * FROM OFFICIAL_WEBSITE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return OfficialWebsiteDO
     */
    public OfficialWebsiteDO getById(Long id){
        return officialWebsiteDOMapper.getById(id);
    }
    /**
     * desc:新增表:OFFICIAL_WEBSITE.<br/>
     * descSql =  insert into official_website id, uuid, name, tel, com, office, message, del_flag, create_by, create_time, update_by, update_time, #{id,jdbcType=BIGINT}, #{uuid,jdbcType=VARCHAR}, #{name,jdbcType=VARCHAR}, #{tel,jdbcType=VARCHAR}, #{com,jdbcType=VARCHAR}, #{office,jdbcType=VARCHAR}, #{message,jdbcType=VARCHAR}, #{delFlag,jdbcType=CHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{updateTime,jdbcType=TIMESTAMP}, 
     * @param entity entity
     * @return OfficialWebsiteDO
     */
    public OfficialWebsiteDO saveOfficial(OfficialWebsiteDO entity){
        return officialWebsiteDOMapper.saveOfficial(entity);
    }
}
