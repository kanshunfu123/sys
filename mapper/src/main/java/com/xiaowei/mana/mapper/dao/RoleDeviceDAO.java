package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.RoleDeviceDO;
import com.xiaowei.mana.mapper.mapper.RoleDeviceDOMapper;

/**
* The Table SYS_ROLE_DEVICE.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 设备和角色关系
*/
@Repository
public class RoleDeviceDAO{

    @Autowired
    private RoleDeviceDOMapper roleDeviceDOMapper;

    /**
     * desc:插入表:SYS_ROLE_DEVICE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ROLE_DEVICE( GROUP_ID ,DEVICE_ID ,SYS_ROLE_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_ROLE_TYPE ,CREATE_TIME ,UPDATE_TIME )VALUES( #{groupId,jdbcType=BIGINT} ,#{deviceId,jdbcType=BIGINT} ,#{sysRoleId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysRoleType,jdbcType=CHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(RoleDeviceDO entity){
        return roleDeviceDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_ROLE_DEVICE.<br/>
     * descSql =  UPDATE SYS_ROLE_DEVICE SET GROUP_ID = #{groupId,jdbcType=BIGINT} ,DEVICE_ID = #{deviceId,jdbcType=BIGINT} ,SYS_ROLE_ID = #{sysRoleId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_ROLE_TYPE = #{sysRoleType,jdbcType=CHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(RoleDeviceDO entity){
        return roleDeviceDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_ROLE_DEVICE.<br/>
     * descSql =  DELETE FROM SYS_ROLE_DEVICE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return roleDeviceDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_ROLE_DEVICE.<br/>
     * descSql =  SELECT * FROM SYS_ROLE_DEVICE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return RoleDeviceDO
     */
    public RoleDeviceDO getById(Long id){
        return roleDeviceDOMapper.getById(id);
    }
}
