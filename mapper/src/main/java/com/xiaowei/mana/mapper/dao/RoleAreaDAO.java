package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.RoleAreaDO;
import java.util.List;
import com.xiaowei.mana.mapper.mapper.RoleAreaDOMapper;

/**
* The Table SYS_ROLE_AREA.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* SYS_ROLE_AREA
*/
@Repository
public class RoleAreaDAO{

    @Autowired
    private RoleAreaDOMapper roleAreaDOMapper;

    /**
     * desc:插入表:SYS_ROLE_AREA.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ROLE_AREA( AREA_ID ,ROLE_ID ,AREA_LEVEL )VALUES( #{areaId,jdbcType=BIGINT} ,#{roleId,jdbcType=BIGINT} ,#{areaLevel,jdbcType=INTEGER} )
     * @param entity entity
     * @return int
     */
    public int insert(RoleAreaDO entity){
        return roleAreaDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_ROLE_AREA.<br/>
     * descSql =  UPDATE SYS_ROLE_AREA SET AREA_ID = #{areaId,jdbcType=BIGINT} ,ROLE_ID = #{roleId,jdbcType=BIGINT} ,AREA_LEVEL = #{areaLevel,jdbcType=INTEGER} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(RoleAreaDO entity){
        return roleAreaDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_ROLE_AREA.<br/>
     * descSql =  DELETE FROM sys_role_area WHERE role_id = #{role_id,jdbcType=BIGINT}
     * @param role_id role_id
     * @return int
     */
    public int deleteById(Long role_id){
        return roleAreaDOMapper.deleteById(role_id);
    }
    /**
     * desc:根据主键获取数据:SYS_ROLE_AREA.<br/>
     * descSql =  SELECT * FROM SYS_ROLE_AREA WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return RoleAreaDO
     */
    public RoleAreaDO getById(Long id){
        return roleAreaDOMapper.getById(id);
    }
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
    public int insertSelective(Long id,Long areaId,Long roleId,Long groupId,Integer areaLevel){
        return roleAreaDOMapper.insertSelective(id, areaId, roleId, groupId, areaLevel);
    }
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
    public int updateByPrimaryKeySelective(Long id,Long areaId,Long roleId,Long groupId,Integer areaLevel){
        return roleAreaDOMapper.updateByPrimaryKeySelective(id, areaId, roleId, groupId, areaLevel);
    }
    /**
     * desc:selectByPrimaryKey.<br/>
     * descSql =  select id, role_id, area_id, area_level, group_id from sys_role_area where id = #{id,jdbcType=BIGINT}
     * @param id id
     * @return RoleAreaDO
     */
    public RoleAreaDO selectByPrimaryKey(Long id){
        return roleAreaDOMapper.selectByPrimaryKey(id);
    }
    /**
     * desc:selectByRoleId.<br/>
     * descSql =  select area_id from sys_role_area where role_id = #{roleId,jdbcType=BIGINT}
     * @param roleId roleId
     * @return List<Long>
     */
    public List<Long> selectByRoleId(Long roleId){
        return roleAreaDOMapper.selectByRoleId(roleId);
    }
    /**
     * desc:insertBatch.<br/>
     * descSql =  insert into sys_role_area ( role_id, area_id, area_level, group_id,device_type) values ( #{item.roleId,jdbcType=BIGINT}, #{item.areaId,jdbcType=BIGINT}, #{item.areaLevel,jdbcType=INTEGER}, #{item.groupId,jdbcType=BIGINT},#{item.deviceType,jdbcType=VARCHAR}) 
     * @param list list
     * @return int
     */
    public int insertBatch(List<RoleAreaDO> list){
        return roleAreaDOMapper.insertBatch(list);
    }
    /**
     * desc:deleteByParams.<br/>
     * descSql =  DELETE FROM sys_role_area WHERE role_id = #{role_id,jdbcType=BIGINT} and device_type=#{deviceType,jdbcType=VARCHAR}
     * @param role_id role_id
     * @param deviceType deviceType
     * @return int
     */
    public int deleteByParams(Long role_id,String deviceType){
        return roleAreaDOMapper.deleteByParams(role_id, deviceType);
    }
}
