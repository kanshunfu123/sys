package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.AreaDO;
import java.util.List;
import com.xiaowei.mana.mapper.mapper.AreaDOMapper;

/**
* The Table SYS_AREA.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 区域 省市县三级联动
*/
@Repository
public class AreaDAO{

    @Autowired
    private AreaDOMapper areaDOMapper;

    /**
     * desc:插入表:SYS_AREA.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_AREA( SYS_AREA_PARENT_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_AREA_NAME ,SYS_AREA_UUID ,SYS_AREA_LEVEL ,SYS_AREA_REMARK ,SYS_AREA_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysAreaParentId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysAreaName,jdbcType=VARCHAR} ,#{sysAreaUuid,jdbcType=VARCHAR} ,#{sysAreaLevel,jdbcType=VARCHAR} ,#{sysAreaRemark,jdbcType=VARCHAR} ,#{sysAreaSeq,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(AreaDO entity){
        return areaDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_AREA.<br/>
     * descSql =  UPDATE SYS_AREA SET SYS_AREA_PARENT_ID = #{sysAreaParentId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_AREA_NAME = #{sysAreaName,jdbcType=VARCHAR} ,SYS_AREA_UUID = #{sysAreaUuid,jdbcType=VARCHAR} ,SYS_AREA_LEVEL = #{sysAreaLevel,jdbcType=VARCHAR} ,SYS_AREA_REMARK = #{sysAreaRemark,jdbcType=VARCHAR} ,SYS_AREA_SEQ = #{sysAreaSeq,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(AreaDO entity){
        return areaDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_AREA.<br/>
     * descSql =  DELETE FROM SYS_AREA WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return areaDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_AREA.<br/>
     * descSql =  SELECT * FROM SYS_AREA WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return AreaDO
     */
    public AreaDO getById(Long id){
        return areaDOMapper.getById(id);
    }
    /**
     * desc:新增表:SYS_AREA.<br/>
     * descSql =  insert into sys_area id, sys_area_uuid, sys_area_name, sys_area_parent_id, sys_area_level, sys_area_seq, sys_area_remark, sys_weath_code, del_flag, create_by, create_time, update_time, update_by, longitude, latitude, #{id,jdbcType=BIGINT}, #{sysAreaUuid,jdbcType=VARCHAR}, #{sysAreaName,jdbcType=VARCHAR}, #{sysAreaParentId,jdbcType=BIGINT}, #{sysAreaLevel,jdbcType=VARCHAR}, #{sysAreaSeq,jdbcType=INTEGER}, #{sysAreaRemark,jdbcType=VARCHAR}, #{sysWeathCode,jdbcType=VARCHAR}, #{delFlag,jdbcType=CHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{longitude,jdbcType=DECIMAL}, #{latitude,jdbcType=DECIMAL}, 
     * @param entity entity
     * @return int
     */
    public int insertArea(AreaDO entity){
        return areaDOMapper.insertArea(entity);
    }
    /**
     * desc:根据 区域名称 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_area sys_area_name=#{sysAreaName,jdbcType=VARCHAR} sys_area_uuid=#{sysAreaUuid,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    public Integer getAreaName(AreaDO entity){
        return areaDOMapper.getAreaName(entity);
    }
    /**
     * desc:根据 区域id 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_area id=#{id,jdbcType=BIGINT} sys_area_uuid=#{sysAreaUuid,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    public Integer getAreaId(AreaDO entity){
        return areaDOMapper.getAreaId(entity);
    }
    /**
     * desc:更新区域信息.<br/>
     * descSql =  update sys_area sys_area_uuid = #{sysAreaUuid,jdbcType=VARCHAR}, sys_area_name = #{sysAreaName,jdbcType=VARCHAR}, sys_area_parent_id = #{sysAreaParentId,jdbcType=BIGINT}, sys_area_level = #{sysAreaLevel,jdbcType=VARCHAR}, sys_area_seq = #{sysAreaSeq,jdbcType=INTEGER}, sys_area_remark = #{sysAreaRemark,jdbcType=VARCHAR}, sys_weath_code = #{sysWeathCode,jdbcType=VARCHAR}, del_flag = #{delFlag,jdbcType=CHAR}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_time = #{updateTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, longitude=#{longitude,jdbcType=DECIMAL}, latitude=#{latitude,jdbcType=DECIMAL}, where id = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int updateArea(AreaDO entity){
        return areaDOMapper.updateArea(entity);
    }
    /**
     * desc:同一层级下，区域名称是否相同.<br/>
     * descSql =  SELECT COUNT(id) FROM sys_area AND sys_area_name=#{sysAreaName,jdbcType=VARCHAR} AND sys_area_parent_id=#{sysAreaParentId,jdbcType=BIGINT} AND id != #{id,jdbcType=BIGINT} AND del_flag=0 
     * @param id id
     * @param sysAreaParentId sysAreaParentId
     * @param sysAreaName sysAreaName
     * @return int
     */
    public int getBycodeNameAndByparentId(Long id,Long sysAreaParentId,String sysAreaName){
        return areaDOMapper.getBycodeNameAndByparentId(id, sysAreaParentId, sysAreaName);
    }
    /**
     * desc:根据 父级id 获取数据:SYS_DICTIONARY.<br/>
     * descSql =  SELECT id, sys_area_uuid, sys_area_name, sys_area_parent_id, sys_area_level, sys_area_seq, sys_area_remark, sys_weath_code, del_flag, create_by, create_time, update_time, update_by FROM sys_area WHERE id=#{id,jdbcType=BIGINT} and del_flag=0
     * @param id id
     * @return AreaDO
     */
    public AreaDO getLevelByParentId(Long id){
        return areaDOMapper.getLevelByParentId(id);
    }
    /**
     * desc:查询子级字典.<br/>
     * descSql =  SELECT id, sys_area_uuid, sys_area_name, sys_area_parent_id, sys_area_level, sys_area_seq,longitude,latitude FROM sys_area WHERE sys_area_level LIKE CONCAT( #{level,jdbcType=VARCHAR}, '%') AND del_flag=0
     * @param level level
     * @return List<AreaDO>
     */
    public List<AreaDO> getChildDictionaryListByLevel(String level){
        return areaDOMapper.getChildDictionaryListByLevel(level);
    }
    /**
     * desc:区域更新之后，进行批量子级区域.<br/>
     * descSql =  UPDATE sys_area SET sys_area_level = #{area.sysAreaLevel,jdbcType=VARCHAR} WHERE id = #{area.id,jdbcType=BIGINT} 
     * @param list list
     * @return int
     */
    public int updatebatchUpdateLevel(List<AreaDO> list){
        return areaDOMapper.updatebatchUpdateLevel(list);
    }
    /**
     * desc:根据区域uuid删除数据:sys_area.<br/>
     * descSql =  delete from sys_area where sys_area_uuid = #{sysAreaUuid,jdbcType=VARCHAR}
     * @param sysAreaUuid sysAreaUuid
     * @return int
     */
    public int deleteByUuid(String sysAreaUuid){
        return areaDOMapper.deleteByUuid(sysAreaUuid);
    }
    /**
     * desc:同一层级下，区域名称是否相同.<br/>
     * descSql =  SELECT COUNT(id) FROM sys_area AND sys_area_name=#{sysAreaName,jdbcType=VARCHAR} AND sys_area_parent_id=#{sysAreaParentId,jdbcType=BIGINT} AND id = #{id,jdbcType=BIGINT} AND del_flag=0 
     * @param id id
     * @param sysAreaParentId sysAreaParentId
     * @param sysAreaName sysAreaName
     * @return int
     */
    public int getNameAndParent(Long id,Long sysAreaParentId,String sysAreaName){
        return areaDOMapper.getNameAndParent(id, sysAreaParentId, sysAreaName);
    }
    /**
     * desc:adminAreaAL.<br/>
     * descSql =  SELECT id, sys_area_uuid, sys_area_name, sys_area_parent_id, sys_area_level, sys_area_seq,longitude,latitude FROM sys_area WHERE sys_area_parent_id=#{sys_area_parent_id,jdbcType=BIGINT} AND del_flag=0
     * @param sys_area_parent_id sys_area_parent_id
     * @return List<AreaDO>
     */
    public List<AreaDO> adminAreaAL(Long sys_area_parent_id){
        return areaDOMapper.adminAreaAL(sys_area_parent_id);
    }
    /**
     * desc:commonAreaAL.<br/>
     * descSql =  SELECT area.id,area.latitude,area.longitude,area.sys_area_name,area.sys_area_parent_id,area.sys_area_level FROM ( SELECT * FROM sys_role_area WHERE role_id IN ( SELECT role_id FROM sys_role_user WHERE user_id=#{sys_area_parent_id,jdbcType=BIGINT} ) ) mmp INNER JOIN sys_area area ON area.id=mmp.area_id
     * @param sys_area_parent_id sys_area_parent_id
     * @return List<AreaDO>
     */
    public List<AreaDO> commonAreaAL(Long sys_area_parent_id){
        return areaDOMapper.commonAreaAL(sys_area_parent_id);
    }
}
