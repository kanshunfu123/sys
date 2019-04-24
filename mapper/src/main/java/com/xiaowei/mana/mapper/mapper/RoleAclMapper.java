package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.RoleAclDO;
import com.xiaowei.mana.mapper.dataobject.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleAclMapper {


    int insertSelective(RoleAclDO roleAclDO);

    RoleAclDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleAclDO roleAclDO);

    /**
     * 批量新增角色和权限关系
     *
     * @param roleUserDOS
     * @return
     */
    public int batchaddRoleUser(List<RoleAclDO> roleUserDOS);

    /**
     * 根据角色id 得到权限点id列表
     *
     * @param roleid
     * @return
     */
    public List<Long> getRolesByUserId(@Param("roleid") Long roleid);

    /**
     * 根据角色列表,得到权限点
     * @param roles
     * @return
     */
    public List<Long> getAclIdListByRoleIdList(@Param("roles") List<Long> roles);
    public List<Long> getAclIdListByRoleIdAndPerTypeList(@Param("roles") List<Long> roles,@Param("sysAclPermissionType") Long sysAclPermissionType);

    /**
     * 根据角色id删除角色和权限关系
     *
     * @param roleid
     * @return
     */
    public int deleteByUserId(@Param("roleid") Long roleid);
}
