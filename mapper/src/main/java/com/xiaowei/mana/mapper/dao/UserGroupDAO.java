package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.UserGroupDO;
import com.xiaowei.mana.mapper.mapper.UserGroupDOMapper;

/**
* The Table SYS_USER_GROUP.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 第三方权限组
*/
@Repository
public class UserGroupDAO{

    @Autowired
    private UserGroupDOMapper userGroupDOMapper;

    /**
     * desc:插入表:SYS_USER_GROUP.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER_GROUP( DEL_FLAG ,CREATE_BY ,SYS_START ,UPDATE_BY ,SYS_OPEN_DAY ,USER_GROUP_NAME ,USER_GROUP_UUID ,CREATE_TIME ,UPDATE_TIME ,SYS_ACCOUNT_END_TIME ,SYS_ACCOUNT_START_TIME )VALUES( #{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{sysStart,jdbcType=CHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysOpenDay,jdbcType=VARCHAR} ,#{userGroupName,jdbcType=VARCHAR} ,#{userGroupUuid,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} ,#{sysAccountEndTime,jdbcType=TIMESTAMP} ,#{sysAccountStartTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(UserGroupDO entity){
        return userGroupDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_USER_GROUP.<br/>
     * descSql =  UPDATE SYS_USER_GROUP SET DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,SYS_START = #{sysStart,jdbcType=CHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_OPEN_DAY = #{sysOpenDay,jdbcType=VARCHAR} ,USER_GROUP_NAME = #{userGroupName,jdbcType=VARCHAR} ,USER_GROUP_UUID = #{userGroupUuid,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} ,SYS_ACCOUNT_END_TIME = #{sysAccountEndTime,jdbcType=TIMESTAMP} ,SYS_ACCOUNT_START_TIME = #{sysAccountStartTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(UserGroupDO entity){
        return userGroupDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_USER_GROUP.<br/>
     * descSql =  DELETE FROM SYS_USER_GROUP WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return userGroupDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_USER_GROUP.<br/>
     * descSql =  SELECT * FROM SYS_USER_GROUP WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserGroupDO
     */
    public UserGroupDO getById(Long id){
        return userGroupDOMapper.getById(id);
    }
}
