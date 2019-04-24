package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.AclModuleDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * Created by MOMO on 2019/1/9.
 */
public interface AclModuleMapper {
    /**
     * 得到所有的权限模块
     * @return
     */
    public List<AclModuleDO> getAllAclModule();

    /**
     * 根据模块ids 得到权限模块列表
     * @param moduleIds
     * @return
     */
    public List<AclModuleDO> getAllAclModuleByModuleIds(@Param("set") Set<Long> moduleIds);


    /**
     * 根据主键查询
     *
     * @param id
     * @return
     */
    AclModuleDO selectByPrimaryKey(Long id);

    /**
     * uuid查询
     * @param uuid
     * @return
     */
    AclModuleDO selectByUUID(@Param("uuid")String uuid);

    /**
     * 新增权限模块
     *
     * @param aclModuleDO
     * @return
     */
    public int addAclModule(AclModuleDO aclModuleDO);

    /**
     * 判断 模块名称是否唯一
     *
     * @param parentId
     * @param name
     * @param id
     * @return
     */
    int countByNameAndParentId(@Param("parentId") Long parentId, @Param("name") String name, @Param("id") Long id);

    /**
     * 编辑 权限模块
     * @param record
     * @return
     */
    int updateByPrimaryKey(AclModuleDO record);

    /**
     *根据 level 层级  查询子部门
     * @param level
     * @return
     */
    List<AclModuleDO> getChildAclModuleListByLevel(@Param("level") String level);
    /**
     * 批量更新 权限模块
     * @param sysAclModuleList
     */
    void batchUpdateLevel(@Param("sysAclModuleList") List<AclModuleDO> sysAclModuleList);
}
