package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.RoleDO;
import com.xiaowei.mana.mapper.dataobject.RoleDeviceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleDeviceMapper {


    int insertSelective(RoleDeviceDO roleDeviceDO);

    RoleDeviceDO selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(RoleDeviceDO roleDeviceDO);

    /**
     * 根据角色id  得到设备id列表
     *
     * @param roleId
     * @return
     */
    List<String> getDevicesByRoleId(List<Long> roleId);
    /**
     * 根据角色id  得到设备id列表 0 地表水 1井盖 2生活用水 3电梯'
     *
     * @param roleId
     * @return
     */
    List<String> getDevicesByRoleIdAndDeviceType(@Param("list") List<Long> roleId,@Param("deviceType")String deviceType);

    List<RoleDeviceDO> getDevicesInfoByRoleId(List<Long> roleId);

    int deleteByRoleId(@Param("roleId") Long roleId);

    int batchaddRoleUser(List<RoleDeviceDO> roleDeviceDOS);

    /**
     * 批量删除角色和设备
     * @param groupId
     * @param deviceNos
     * @return
     */
    int deleteByGroupId(@Param("groupId") Long groupId, @Param("lists") List<String> deviceNos);

}
