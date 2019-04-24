package com.xiaowei.mana.mapper.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.xiaowei.mana.mapper.dataobject.AclTypeDO;
import com.xiaowei.mana.mapper.mapper.AclTypeDOMapper;

/**
* The Table SYS_ACL_TYPE.
* 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
* 权限类型表
*/
@Repository
public class AclTypeDAO{

    @Autowired
    private AclTypeDOMapper aclTypeDOMapper;

    /**
     * desc:插入表:SYS_ACL_TYPE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ACL_TYPE( REMARK ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_ACL_TYPE_NAME ,CREATE_TIME ,UPDATE_TIME )VALUES( #{remark,jdbcType=VARCHAR} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysAclTypeName,jdbcType=VARCHAR} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    public int insert(AclTypeDO entity){
        return aclTypeDOMapper.insert(entity);
    }
    /**
     * desc:更新表:SYS_ACL_TYPE.<br/>
     * descSql =  UPDATE SYS_ACL_TYPE SET REMARK = #{remark,jdbcType=VARCHAR} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_ACL_TYPE_NAME = #{sysAclTypeName,jdbcType=VARCHAR} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    public int update(AclTypeDO entity){
        return aclTypeDOMapper.update(entity);
    }
    /**
     * desc:根据主键删除数据:SYS_ACL_TYPE.<br/>
     * descSql =  DELETE FROM SYS_ACL_TYPE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    public int deleteById(Long id){
        return aclTypeDOMapper.deleteById(id);
    }
    /**
     * desc:根据主键获取数据:SYS_ACL_TYPE.<br/>
     * descSql =  SELECT * FROM SYS_ACL_TYPE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return AclTypeDO
     */
    public AclTypeDO getById(Long id){
        return aclTypeDOMapper.getById(id);
    }
}
