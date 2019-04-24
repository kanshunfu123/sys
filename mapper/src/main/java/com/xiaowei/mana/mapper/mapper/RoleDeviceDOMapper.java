package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.RoleDeviceDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_ROLE_DEVICE.
 * 设备和角色关系
 */
public interface RoleDeviceDOMapper{

    /**
     * desc:插入表:SYS_ROLE_DEVICE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ROLE_DEVICE( GROUP_ID ,DEVICE_ID ,SYS_ROLE_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_ROLE_TYPE ,CREATE_TIME ,UPDATE_TIME )VALUES( #{groupId,jdbcType=BIGINT} ,#{deviceId,jdbcType=BIGINT} ,#{sysRoleId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysRoleType,jdbcType=CHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(RoleDeviceDO entity);
    /**
     * desc:更新表:SYS_ROLE_DEVICE.<br/>
     * descSql =  UPDATE SYS_ROLE_DEVICE SET GROUP_ID = #{groupId,jdbcType=BIGINT} ,DEVICE_ID = #{deviceId,jdbcType=BIGINT} ,SYS_ROLE_ID = #{sysRoleId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_ROLE_TYPE = #{sysRoleType,jdbcType=CHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(RoleDeviceDO entity);
    /**
     * desc:根据主键删除数据:SYS_ROLE_DEVICE.<br/>
     * descSql =  DELETE FROM SYS_ROLE_DEVICE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_ROLE_DEVICE.<br/>
     * descSql =  SELECT * FROM SYS_ROLE_DEVICE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return RoleDeviceDO
     */
    RoleDeviceDO getById(Long id);
}
