package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.DeptDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface DeptMapper {
    int insertSelective(DeptDO record);

    int updateByPrimaryKeySelective(DeptDO record);

    DeptDO selectByPrimaryKey(Long id);

    DeptDO getDeptByUUID(@Param("uuid") String uuid);

    /**
     * 查询当前公司的部门
     *
     * @param groupId
     * @return
     */
    public List<DeptDO> deptTree(@Param("groupId") Long groupId);

    /**
     * 根据groupid查询 组织数量
     * @param groupId
     * @return
     */
    public int countDeptTree(@Param("groupId") Long groupId);

    /**
     * 同一层级下存在相同名称的部门
     *
     * @param parentId
     * @param name
     * @param id
     * @return
     */
    int countByNameAndParentId(@Param("parentId") Long parentId, @Param("name") String name, @Param("groupId") Long groupId, @Param("id") Long id);

    /**
     * 根据Leven查询是否有子部门
     *
     * @param level
     * @return
     */
    int countByLevel(@Param("level") String level);

    /**
     * 查询子部门
     * @param level
     * @return
     */
    List<DeptDO> getChildDeptListByLevel(@Param("level") String level);

    /**
     * 批量更新 子部门
     * @param sysDeptList
     */
    void batchUpdateLevel(@Param("sysDeptList") List<DeptDO> sysDeptList);
}
