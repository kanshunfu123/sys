package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.PageRoleDO;
import com.xiaowei.mana.mapper.dataobject.RoleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MOMO on 2019/1/9.
 */
public interface RoleMapper {


    /**
     * 角色分页查询
     * @param roleName
     * @param groupId
     * @return
     */
    public List<PageRoleDO> pageRole(@Param("roleName") String roleName, @Param("groupId") Long groupId);

    /**
     * 新增角色
     *
     * @param roleDO
     * @return
     */
    public int addRole(RoleDO roleDO);

    /**
     * 编辑角色
     *
     * @param roleDO
     * @return
     */
    public int modifyRole(RoleDO roleDO);

    /**
     * 根据uuid查询角色
     *
     * @param uuid
     * @return
     */
    public RoleDO getRoleByUUID(@Param("uuid") String uuid);

    /**
     * 角色名称唯一
     *
     * @param rolename
     * @return
     */
    public int getRoleCountByRoleName(@Param("rolename") String rolename, @Param("groupid") Long groupid, @Param("roleid") Long roleid);

    /**
     * 得到第三方管理员角色 列表
     * @param groupid  第三方组id
     * @param roleType  角色的类型，0：管理员角色，1：普通用户 2其他'
     * @return
     */
    public RoleDO getThirdRoleByParam(@Param("groupid")Long groupid,@Param("roleType")String roleType,@Param("id")Long id);

    /**
     * 根据id查询角色
     *
     * @param id
     * @return
     */
    public RoleDO getRoleById(@Param("id") Long id);

    public List<RoleDO> getRoleByTyGroupType(@Param("groupid") Long groupid,@Param("roleType")String roleType);

    /**
     * 根据用户id得到角色列表
     *
     * @param userId
     * @return
     */
    public List<RoleDO> getRolesByUserId(@Param("userId") Long userId);

    /**
     * 得到所有的角色
     *
     * @return
     */
    public List<RoleDO> getAllRoles();
}
