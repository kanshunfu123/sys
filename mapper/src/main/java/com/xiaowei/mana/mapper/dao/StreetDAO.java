package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.StreetDO;
import com.xiaowei.mana.mapper.mapper.StreetDOMapper;

/**
* The Table SYS_STREET.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 省市区------》街道
*/
@Repository
public class StreetDAO{

    @Autowired
    private StreetDOMapper streetDOMapper;

    /**
     * desc:插入表:SYS_STREET.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_STREET( AREA_ID ,CITY_ID ,PROVINCE_ID ,AREA ,CITY ,NAME ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,PROVINCE_NAME ,CREATE_TIME ,UPDATE_TIME )VALUES( #{areaId,jdbcType=BIGINT} ,#{cityId,jdbcType=BIGINT} ,#{provinceId,jdbcType=BIGINT} ,#{area,jdbcType=VARCHAR} ,#{city,jdbcType=VARCHAR} ,#{name,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{provinceName,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(StreetDO entity){
        return streetDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_STREET.<br/>
     * descSql =  UPDATE SYS_STREET SET AREA_ID = #{areaId,jdbcType=BIGINT} ,CITY_ID = #{cityId,jdbcType=BIGINT} ,PROVINCE_ID = #{provinceId,jdbcType=BIGINT} ,AREA = #{area,jdbcType=VARCHAR} ,CITY = #{city,jdbcType=VARCHAR} ,NAME = #{name,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,PROVINCE_NAME = #{provinceName,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(StreetDO entity){
        return streetDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_STREET.<br/>
     * descSql =  DELETE FROM SYS_STREET WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return streetDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_STREET.<br/>
     * descSql =  SELECT * FROM SYS_STREET WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return StreetDO
     */
    public StreetDO getById(Long id){
        return streetDOMapper.getById(id);
    }
}
