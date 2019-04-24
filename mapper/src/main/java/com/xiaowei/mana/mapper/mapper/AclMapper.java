package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.AclDO;
import com.xiaowei.mana.mapper.dataobject.SysAcl;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface AclMapper {
    /**
     * 根据 权限点id列表获取权限点列表
     * @param idList
     * @return
     */
    List<SysAcl> getByIdList(@Param("idList") List<Long> idList);

    /**
     *  获取当前模块的 所有权限点
     * @param sysAclPermissionType
     * @return
     */
   List<SysAcl> getAll(@Param("sysAclPermissionType") Long sysAclPermissionType);

    /**
     * 新增
     * @param aclDO
     * @return
     */
    int insertSelective(AclDO aclDO);

    /**
     * 编辑
     * @param aclDO
     * @return
     */
    int updateByPrimaryKeySelective(AclDO aclDO);

    /**
     * 保证系统唯一
     * @param sysAclUrl
     * @param sysAclName
     * @param sysAclAction
     * @param sysAclRouter
     * @param id
     * @return
     */
    public int uniqueness(@Param("sysAclUrl")String sysAclUrl,@Param("sysAclName")String sysAclName,@Param("sysAclAction")String sysAclAction,@Param("sysAclRouter")String sysAclRouter,@Param("id")Long id);

    /**
     * 根据uuid查询
     * @param uuid
     * @return
     */
    public AclDO getAclByUUID(@Param("uuid")String uuid);
}
