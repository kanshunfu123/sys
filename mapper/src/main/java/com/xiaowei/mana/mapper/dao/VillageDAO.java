package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.VillageDO;
import java.util.List;
import com.xiaowei.mana.mapper.mapper.VillageDOMapper;

/**
* The Table SYS_VILLAGE.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 小区表
*/
@Repository
public class VillageDAO{

    @Autowired
    private VillageDOMapper villageDOMapper;

    /**
     * desc:插入表:SYS_VILLAGE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_VILLAGE( AREA_ID ,CITY_ID ,STREET_ID ,PROVINCE_ID ,AREA ,CITY ,STREET ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,VILLAGE_NAME ,VILLAGE_UUID ,PROVINCE_NAME ,CREATE_TIME ,UPDATE_TIME )VALUES( #{areaId,jdbcType=BIGINT} ,#{cityId,jdbcType=BIGINT} ,#{streetId,jdbcType=BIGINT} ,#{provinceId,jdbcType=BIGINT} ,#{area,jdbcType=VARCHAR} ,#{city,jdbcType=VARCHAR} ,#{street,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{villageName,jdbcType=VARCHAR} ,#{villageUuid,jdbcType=VARCHAR} ,#{provinceName,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(VillageDO entity){
        return villageDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_VILLAGE.<br/>
     * descSql =  UPDATE SYS_VILLAGE SET AREA_ID = #{areaId,jdbcType=BIGINT} ,CITY_ID = #{cityId,jdbcType=BIGINT} ,STREET_ID = #{streetId,jdbcType=BIGINT} ,PROVINCE_ID = #{provinceId,jdbcType=BIGINT} ,AREA = #{area,jdbcType=VARCHAR} ,CITY = #{city,jdbcType=VARCHAR} ,STREET = #{street,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,VILLAGE_NAME = #{villageName,jdbcType=VARCHAR} ,VILLAGE_UUID = #{villageUuid,jdbcType=VARCHAR} ,PROVINCE_NAME = #{provinceName,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(VillageDO entity){
        return villageDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_VILLAGE.<br/>
     * descSql =  DELETE FROM SYS_VILLAGE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return villageDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_VILLAGE.<br/>
     * descSql =  SELECT * FROM SYS_VILLAGE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return VillageDO
     */
    public VillageDO getById(Long id){
        return villageDOMapper.getById(id);
    }
    /**
     * desc:getAll.<br/>
     * descSql =  SELECT id,village_uuid,village_name,street_id FROM sys_village
     * @return List<VillageDO>
     */
    public List<VillageDO> getAll(){
        return villageDOMapper.getAll();
    }
    /**
     * desc:getAllTwo.<br/>
     * descSql =  SELECT vi.* FROM( SELECT area_id FROM sys_role_area WHERE role_id=#{roleId,jdbcType=BIGINT} AND area_level='4' AND device_type=#{device_type,jdbcType=VARCHAR} ) mmp INNER JOIN sys_village vi ON vi.street_id=mmp.area_id
     * @param roleId roleId
     * @param device_type device_type
     * @return List<VillageDO>
     */
    public List<VillageDO> getAllTwo(Long roleId,String device_type){
        return villageDOMapper.getAllTwo(roleId, device_type);
    }
}
