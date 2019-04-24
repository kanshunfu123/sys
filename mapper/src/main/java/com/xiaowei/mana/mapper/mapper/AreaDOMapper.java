package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.AreaDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_AREA.
 * 区域 省市县三级联动
 */
public interface AreaDOMapper{

    /**
     * desc:插入表:SYS_AREA.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_AREA( SYS_AREA_PARENT_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_AREA_NAME ,SYS_AREA_UUID ,SYS_AREA_LEVEL ,SYS_AREA_REMARK ,SYS_AREA_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysAreaParentId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysAreaName,jdbcType=VARCHAR} ,#{sysAreaUuid,jdbcType=VARCHAR} ,#{sysAreaLevel,jdbcType=VARCHAR} ,#{sysAreaRemark,jdbcType=VARCHAR} ,#{sysAreaSeq,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(AreaDO entity);
    /**
     * desc:更新表:SYS_AREA.<br/>
     * descSql =  UPDATE SYS_AREA SET SYS_AREA_PARENT_ID = #{sysAreaParentId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_AREA_NAME = #{sysAreaName,jdbcType=VARCHAR} ,SYS_AREA_UUID = #{sysAreaUuid,jdbcType=VARCHAR} ,SYS_AREA_LEVEL = #{sysAreaLevel,jdbcType=VARCHAR} ,SYS_AREA_REMARK = #{sysAreaRemark,jdbcType=VARCHAR} ,SYS_AREA_SEQ = #{sysAreaSeq,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(AreaDO entity);
    /**
     * desc:根据主键删除数据:SYS_AREA.<br/>
     * descSql =  DELETE FROM SYS_AREA WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_AREA.<br/>
     * descSql =  SELECT * FROM SYS_AREA WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return AreaDO
     */
    AreaDO getById(Long id);
    /**
     * desc:新增表:SYS_AREA.<br/>
     * descSql =  insert into sys_area id, sys_area_uuid, sys_area_name, sys_area_parent_id, sys_area_level, sys_area_seq, sys_area_remark, sys_weath_code, del_flag, create_by, create_time, update_time, update_by, longitude, latitude, #{id,jdbcType=BIGINT}, #{sysAreaUuid,jdbcType=VARCHAR}, #{sysAreaName,jdbcType=VARCHAR}, #{sysAreaParentId,jdbcType=BIGINT}, #{sysAreaLevel,jdbcType=VARCHAR}, #{sysAreaSeq,jdbcType=INTEGER}, #{sysAreaRemark,jdbcType=VARCHAR}, #{sysWeathCode,jdbcType=VARCHAR}, #{delFlag,jdbcType=CHAR}, #{createBy,jdbcType=VARCHAR}, #{createTime,jdbcType=TIMESTAMP}, #{updateTime,jdbcType=TIMESTAMP}, #{updateBy,jdbcType=VARCHAR}, #{longitude,jdbcType=DECIMAL}, #{latitude,jdbcType=DECIMAL}, 
     * @param entity entity
     * @return int
     */
    int insertArea(AreaDO entity);
    /**
     * desc:根据 区域名称 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_area sys_area_name=#{sysAreaName,jdbcType=VARCHAR} sys_area_uuid=#{sysAreaUuid,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    Integer getAreaName(AreaDO entity);
    /**
     * desc:根据 区域id 全局唯一.<br/>
     * descSql =  SELECT count(id) FROM sys_area id=#{id,jdbcType=BIGINT} sys_area_uuid=#{sysAreaUuid,jdbcType=VARCHAR} 
     * @param entity entity
     * @return Integer
     */
    Integer getAreaId(AreaDO entity);
    /**
     * desc:更新区域信息.<br/>
     * descSql =  update sys_area sys_area_uuid = #{sysAreaUuid,jdbcType=VARCHAR}, sys_area_name = #{sysAreaName,jdbcType=VARCHAR}, sys_area_parent_id = #{sysAreaParentId,jdbcType=BIGINT}, sys_area_level = #{sysAreaLevel,jdbcType=VARCHAR}, sys_area_seq = #{sysAreaSeq,jdbcType=INTEGER}, sys_area_remark = #{sysAreaRemark,jdbcType=VARCHAR}, sys_weath_code = #{sysWeathCode,jdbcType=VARCHAR}, del_flag = #{delFlag,jdbcType=CHAR}, create_by = #{createBy,jdbcType=VARCHAR}, create_time = #{createTime,jdbcType=TIMESTAMP}, update_time = #{updateTime,jdbcType=TIMESTAMP}, update_by = #{updateBy,jdbcType=VARCHAR}, longitude=#{longitude,jdbcType=DECIMAL}, latitude=#{latitude,jdbcType=DECIMAL}, where id = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int updateArea(AreaDO entity);
    /**
     * desc:同一层级下，区域名称是否相同.<br/>
     * descSql =  SELECT COUNT(id) FROM sys_area AND sys_area_name=#{sysAreaName,jdbcType=VARCHAR} AND sys_area_parent_id=#{sysAreaParentId,jdbcType=BIGINT} AND id != #{id,jdbcType=BIGINT} AND del_flag=0 
     * @param id id
     * @param sysAreaParentId sysAreaParentId
     * @param sysAreaName sysAreaName
     * @return int
     */
    int getBycodeNameAndByparentId(@Param("id")Long id,@Param("sysAreaParentId")Long sysAreaParentId,@Param("sysAreaName")String sysAreaName);
    /**
     * desc:根据 父级id 获取数据:SYS_DICTIONARY.<br/>
     * descSql =  SELECT id, sys_area_uuid, sys_area_name, sys_area_parent_id, sys_area_level, sys_area_seq, sys_area_remark, sys_weath_code, del_flag, create_by, create_time, update_time, update_by FROM sys_area WHERE id=#{id,jdbcType=BIGINT} and del_flag=0
     * @param id id
     * @return AreaDO
     */
    AreaDO getLevelByParentId(Long id);
    /**
     * desc:查询子级字典.<br/>
     * descSql =  SELECT id, sys_area_uuid, sys_area_name, sys_area_parent_id, sys_area_level, sys_area_seq,longitude,latitude FROM sys_area WHERE sys_area_level LIKE CONCAT( #{level,jdbcType=VARCHAR}, '%') AND del_flag=0
     * @param level level
     * @return List<AreaDO>
     */
    List<AreaDO> getChildDictionaryListByLevel(String level);
    /**
     * desc:区域更新之后，进行批量子级区域.<br/>
     * descSql =  UPDATE sys_area SET sys_area_level = #{area.sysAreaLevel,jdbcType=VARCHAR} WHERE id = #{area.id,jdbcType=BIGINT} 
     * @param list list
     * @return int
     */
    int updatebatchUpdateLevel(List<AreaDO> list);
    /**
     * desc:根据区域uuid删除数据:sys_area.<br/>
     * descSql =  delete from sys_area where sys_area_uuid = #{sysAreaUuid,jdbcType=VARCHAR}
     * @param sysAreaUuid sysAreaUuid
     * @return int
     */
    int deleteByUuid(String sysAreaUuid);
    /**
     * desc:同一层级下，区域名称是否相同.<br/>
     * descSql =  SELECT COUNT(id) FROM sys_area AND sys_area_name=#{sysAreaName,jdbcType=VARCHAR} AND sys_area_parent_id=#{sysAreaParentId,jdbcType=BIGINT} AND id = #{id,jdbcType=BIGINT} AND del_flag=0 
     * @param id id
     * @param sysAreaParentId sysAreaParentId
     * @param sysAreaName sysAreaName
     * @return int
     */
    int getNameAndParent(@Param("id")Long id,@Param("sysAreaParentId")Long sysAreaParentId,@Param("sysAreaName")String sysAreaName);
    /**
     * desc:adminAreaAL.<br/>
     * descSql =  SELECT id, sys_area_uuid, sys_area_name, sys_area_parent_id, sys_area_level, sys_area_seq,longitude,latitude FROM sys_area WHERE sys_area_parent_id=#{sys_area_parent_id,jdbcType=BIGINT} AND del_flag=0
     * @param sys_area_parent_id sys_area_parent_id
     * @return List<AreaDO>
     */
    List<AreaDO> adminAreaAL(Long sys_area_parent_id);
    /**
     * desc:commonAreaAL.<br/>
     * descSql =  SELECT area.id,area.latitude,area.longitude,area.sys_area_name,area.sys_area_parent_id,area.sys_area_level FROM ( SELECT * FROM sys_role_area WHERE role_id IN ( SELECT role_id FROM sys_role_user WHERE user_id=#{sys_area_parent_id,jdbcType=BIGINT} ) ) mmp INNER JOIN sys_area area ON area.id=mmp.area_id
     * @param sys_area_parent_id sys_area_parent_id
     * @return List<AreaDO>
     */
    List<AreaDO> commonAreaAL(Long sys_area_parent_id);
}
