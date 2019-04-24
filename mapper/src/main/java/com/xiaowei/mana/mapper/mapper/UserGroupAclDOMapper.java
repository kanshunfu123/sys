package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.UserGroupAclDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_USER_GROUP_ACL.
 * 组和权限点
 */
public interface UserGroupAclDOMapper{

    /**
     * desc:插入表:SYS_USER_GROUP_ACL.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_USER_GROUP_ACL( SYS_ACL_ID ,SYS_USER_GROUP_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysAclId,jdbcType=BIGINT} ,#{sysUserGroupId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(UserGroupAclDO entity);
    /**
     * desc:更新表:SYS_USER_GROUP_ACL.<br/>
     * descSql =  UPDATE SYS_USER_GROUP_ACL SET SYS_ACL_ID = #{sysAclId,jdbcType=BIGINT} ,SYS_USER_GROUP_ID = #{sysUserGroupId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(UserGroupAclDO entity);
    /**
     * desc:根据主键删除数据:SYS_USER_GROUP_ACL.<br/>
     * descSql =  DELETE FROM SYS_USER_GROUP_ACL WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_USER_GROUP_ACL.<br/>
     * descSql =  SELECT * FROM SYS_USER_GROUP_ACL WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return UserGroupAclDO
     */
    UserGroupAclDO getById(Long id);
}
