package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.RoleAreaDO;
import java.util.List;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_ROLE_AREA.
 * SYS_ROLE_AREA
 */
public interface RoleAreaDOMapper{

    /**
     * desc:插入表:SYS_ROLE_AREA.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ROLE_AREA( AREA_ID ,ROLE_ID ,AREA_LEVEL )VALUES( #{areaId,jdbcType=BIGINT} ,#{roleId,jdbcType=BIGINT} ,#{areaLevel,jdbcType=INTEGER} )
     * @param entity entity
     * @return int
     */
    int insert(RoleAreaDO entity);
    /**
     * desc:更新表:SYS_ROLE_AREA.<br/>
     * descSql =  UPDATE SYS_ROLE_AREA SET AREA_ID = #{areaId,jdbcType=BIGINT} ,ROLE_ID = #{roleId,jdbcType=BIGINT} ,AREA_LEVEL = #{areaLevel,jdbcType=INTEGER} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(RoleAreaDO entity);
    /**
     * desc:根据主键删除数据:SYS_ROLE_AREA.<br/>
     * descSql =  DELETE FROM sys_role_area WHERE role_id = #{role_id,jdbcType=BIGINT}
     * @param role_id role_id
     * @return int
     */
    int deleteById(Long role_id);
    /**
     * desc:根据主键获取数据:SYS_ROLE_AREA.<br/>
     * descSql =  SELECT * FROM SYS_ROLE_AREA WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return RoleAreaDO
     */
    RoleAreaDO getById(Long id);
    /**
     * desc:insertSelective.<br/>
     * descSql =  insert into sys_role_area id, role_id, area_id, area_level, group_id, #{id,jdbcType=BIGINT}, #{roleId,jdbcType=BIGINT}, #{areaId,jdbcType=BIGINT}, #{areaLevel,jdbcType=INTEGER}, #{groupId,jdbcType=BIGINT}, 
     * @param id id
     * @param areaId areaId
     * @param roleId roleId
     * @param groupId groupId
     * @param areaLevel areaLevel
     * @return int
     */
    int insertSelective(@Param("id")Long id,@Param("areaId")Long areaId,@Param("roleId")Long roleId,@Param("groupId")Long groupId,@Param("areaLevel")Integer areaLevel);
    /**
     * desc:updateByPrimaryKeySelective.<br/>
     * descSql =  update sys_role_area role_id = #{roleId,jdbcType=BIGINT}, area_id = #{areaId,jdbcType=BIGINT}, area_level = #{areaLevel,jdbcType=INTEGER}, group_id = #{groupId,jdbcType=BIGINT}, where id = #{id,jdbcType=BIGINT}
     * @param id id
     * @param areaId areaId
     * @param roleId roleId
     * @param groupId groupId
     * @param areaLevel areaLevel
     * @return int
     */
    int updateByPrimaryKeySelective(@Param("id")Long id,@Param("areaId")Long areaId,@Param("roleId")Long roleId,@Param("groupId")Long groupId,@Param("areaLevel")Integer areaLevel);
    /**
     * desc:selectByPrimaryKey.<br/>
     * descSql =  select id, role_id, area_id, area_level, group_id from sys_role_area where id = #{id,jdbcType=BIGINT}
     * @param id id
     * @return RoleAreaDO
     */
    RoleAreaDO selectByPrimaryKey(Long id);
    /**
     * desc:selectByRoleId.<br/>
     * descSql =  select area_id from sys_role_area where role_id = #{roleId,jdbcType=BIGINT}
     * @param roleId roleId
     * @return List<Long>
     */
    List<Long> selectByRoleId(Long roleId);
    /**
     * desc:insertBatch.<br/>
     * descSql =  insert into sys_role_area ( role_id, area_id, area_level, group_id,device_type) values ( #{item.roleId,jdbcType=BIGINT}, #{item.areaId,jdbcType=BIGINT}, #{item.areaLevel,jdbcType=INTEGER}, #{item.groupId,jdbcType=BIGINT},#{item.deviceType,jdbcType=VARCHAR}) 
     * @param list list
     * @return int
     */
    int insertBatch(List<RoleAreaDO> list);
    /**
     * desc:deleteByParams.<br/>
     * descSql =  DELETE FROM sys_role_area WHERE role_id = #{role_id,jdbcType=BIGINT} and device_type=#{deviceType,jdbcType=VARCHAR}
     * @param role_id role_id
     * @param deviceType deviceType
     * @return int
     */
    int deleteByParams(@Param("role_id")Long role_id,@Param("deviceType")String deviceType);
}
