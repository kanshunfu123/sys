package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.RoleUserDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface RoleUserMapper {


    int insertSelective(RoleUserDO record);

    RoleUserDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleUserDO record);

    /**
     * 批量新增用户和角色关系
     *
     * @param roleUserDOS
     * @return
     */
    public int batchaddRoleUser(List<RoleUserDO> roleUserDOS);

    /**
     * 根据用户id 得到角色id列表
     *
     * @param userid
     * @return
     */
    public List<Long> getRolesByUserId(@Param("userid") Long userid);

    /**
     * 根据用户id删除用户和角色关系
     *
     * @param userid
     * @return
     */
    public int deleteByUserId(@Param("userid") Long userid);

    /**
     * 得到第三方管理员角色 列表
     *
     * @param roleId 角色id
     * @param id     主键
     * @return
     */
    public List<Long> getThirdRoleByParam(@Param("roleId") Long roleId, @Param("id") Long id);

    /**
     * 管理员角色是否已经被分配出去
     * @param roleId
     * @param groupId
     * @return
     */
    public int countAdmin(@Param("roleId") Long roleId, @Param("groupId") Long groupId);

}
