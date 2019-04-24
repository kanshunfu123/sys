package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.AclModuleDO;
import org.apache.ibatis.annotations.Param;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table SYS_ACL_MODULE.
 * 权限模块表   引入权限模块就可以很容易把菜单层级定义出来，每个菜单项下面有哪些功能就可以在权限模块下面定义权限点，然后就可以根据每个人分配到的权限生成不同的基于权限的菜单
 */
public interface AclModuleDOMapper{

    /**
     * desc:插入表:SYS_ACL_MODULE.<br/>
     * descSql =  SELECT LAST_INSERT_ID() INSERT INTO SYS_ACL_MODULE( SYS_ACL_MODULE_PARENT_ID ,DEL_FLAG ,CREATE_BY ,UPDATE_BY ,SYS_ACL_MODULE_CODE ,SYS_ACL_MODULE_ICON ,SYS_ACL_MODULE_NAME ,SYS_ACL_MODULE_UUID ,SYS_ACL_MODULE_LEVEL ,SYS_ACL_MODULE_REMARK ,SYS_ACL_PERMISSION_TYPE ,SYS_ACL_MODULE_SEQ ,CREATE_TIME ,UPDATE_TIME )VALUES( #{sysAclModuleParentId,jdbcType=BIGINT} ,#{delFlag,jdbcType=CHAR} ,#{createBy,jdbcType=VARCHAR} ,#{updateBy,jdbcType=VARCHAR} ,#{sysAclModuleCode,jdbcType=VARCHAR} ,#{sysAclModuleIcon,jdbcType=VARCHAR} ,#{sysAclModuleName,jdbcType=VARCHAR} ,#{sysAclModuleUuid,jdbcType=VARCHAR} ,#{sysAclModuleLevel,jdbcType=VARCHAR} ,#{sysAclModuleRemark,jdbcType=VARCHAR} ,#{sysAclPermissionType,jdbcType=CHAR} ,#{sysAclModuleSeq,jdbcType=INTEGER} ,#{createTime,jdbcType=TIMESTAMP} ,#{updateTime,jdbcType=TIMESTAMP} )
     * @param entity entity
     * @return int
     */
    int insert(AclModuleDO entity);
    /**
     * desc:更新表:SYS_ACL_MODULE.<br/>
     * descSql =  UPDATE SYS_ACL_MODULE SET SYS_ACL_MODULE_PARENT_ID = #{sysAclModuleParentId,jdbcType=BIGINT} ,DEL_FLAG = #{delFlag,jdbcType=CHAR} ,CREATE_BY = #{createBy,jdbcType=VARCHAR} ,UPDATE_BY = #{updateBy,jdbcType=VARCHAR} ,SYS_ACL_MODULE_CODE = #{sysAclModuleCode,jdbcType=VARCHAR} ,SYS_ACL_MODULE_ICON = #{sysAclModuleIcon,jdbcType=VARCHAR} ,SYS_ACL_MODULE_NAME = #{sysAclModuleName,jdbcType=VARCHAR} ,SYS_ACL_MODULE_UUID = #{sysAclModuleUuid,jdbcType=VARCHAR} ,SYS_ACL_MODULE_LEVEL = #{sysAclModuleLevel,jdbcType=VARCHAR} ,SYS_ACL_MODULE_REMARK = #{sysAclModuleRemark,jdbcType=VARCHAR} ,SYS_ACL_PERMISSION_TYPE = #{sysAclPermissionType,jdbcType=CHAR} ,SYS_ACL_MODULE_SEQ = #{sysAclModuleSeq,jdbcType=INTEGER} ,CREATE_TIME = #{createTime,jdbcType=TIMESTAMP} ,UPDATE_TIME = #{updateTime,jdbcType=TIMESTAMP} WHERE ID = #{id,jdbcType=BIGINT}
     * @param entity entity
     * @return int
     */
    int update(AclModuleDO entity);
    /**
     * desc:根据主键删除数据:SYS_ACL_MODULE.<br/>
     * descSql =  DELETE FROM SYS_ACL_MODULE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return int
     */
    int deleteById(Long id);
    /**
     * desc:根据主键获取数据:SYS_ACL_MODULE.<br/>
     * descSql =  SELECT * FROM SYS_ACL_MODULE WHERE ID = #{id,jdbcType=BIGINT}
     * @param id id
     * @return AclModuleDO
     */
    AclModuleDO getById(Long id);
}
