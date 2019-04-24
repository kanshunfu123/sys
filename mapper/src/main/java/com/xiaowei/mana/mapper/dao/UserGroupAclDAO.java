package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.UserGroupAclDO;
import com.xiaowei.mana.mapper.mapper.UserGroupAclDOMapper;

/**
* The Table SYS_USER_GROUP_ACL.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 组和权限点
*/
@Repository
public class UserGroupAclDAO{

    @Autowired
    private UserGroupAclDOMapper userGroupAclDOMapper;

    /**
     * desc:插入表:SYS_USER_GROUP_ACL.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER_GROUP_ACL( SYS_ACL_ID ,SYS_USER_GROUP_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysAclId,jdbcType=BIGINT} ,#{sysUserGroupId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(UserGroupAclDO entity){
        return userGroupAclDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_USER_GROUP_ACL.<br/>
     * descSql =  UPDATE SYS_USER_GROUP_ACL SET SYS_ACL_ID = #{sysAclId,jdbcType=BIGINT} ,SYS_USER_GROUP_ID = #{sysUserGroupId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(UserGroupAclDO entity){
        return userGroupAclDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_USER_GROUP_ACL.<br/>
     * descSql =  DELETE FROM SYS_USER_GROUP_ACL WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return userGroupAclDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_USER_GROUP_ACL.<br/>
     * descSql =  SELECT * FROM SYS_USER_GROUP_ACL WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserGroupAclDO
     */
    public UserGroupAclDO getById(Long id){
        return userGroupAclDOMapper.getById(id);
    }
}
