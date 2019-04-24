package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.UserGroupDeviceDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_USER_GROUP_DEVICE.
 * SYS_USER_GROUP_DEVICE
 */
public interface UserGroupDeviceDOMapper{

    /**
     * desc:插入表:SYS_USER_GROUP_DEVICE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER_GROUP_DEVICE( SYS_DEVICE_ID ,SYS_USER_GROUP_ID ,UUID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,DEVICE_TYPE ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysDeviceId,jdbcType=BIGINT} ,#{sysUserGroupId,jdbcType=BIGINT} ,#{uuid,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{deviceType,jdbcType=CHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(UserGroupDeviceDO entity);
    /**
     * desc:更新表:SYS_USER_GROUP_DEVICE.<br/>
     * descSql =  UPDATE SYS_USER_GROUP_DEVICE SET SYS_DEVICE_ID = #{sysDeviceId,jdbcType=BIGINT} ,SYS_USER_GROUP_ID = #{sysUserGroupId,jdbcType=BIGINT} ,UUID = #{uuid,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,DEVICE_TYPE = #{deviceType,jdbcType=CHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(UserGroupDeviceDO entity);
    /**
     * desc:根据主键删除数据:SYS_USER_GROUP_DEVICE.<br/>
     * descSql =  DELETE FROM SYS_USER_GROUP_DEVICE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_USER_GROUP_DEVICE.<br/>
     * descSql =  SELECT * FROM SYS_USER_GROUP_DEVICE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserGroupDeviceDO
     */
    UserGroupDeviceDO getById(Long id);
}
