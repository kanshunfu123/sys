package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.UserGroupDeviceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserGroupDeviceMapper {
    /**
     * 根据组的id查看设备
     *
     * @param groupId
     * @return
     */
    List<String> getDeviceIdByGroupId(@Param("groupId") Long groupId);

    /**
     * 根据组id和设备类型查看设备
     *
     * @param groupId
     * @param deviceType
     * @return
     */
    List<String> getDeviceIdByGroupIdANDType(@Param("groupId") Long groupId, @Param("deviceType") String deviceType);

    int deleteByGroupId(@Param("groupId") Long groupId);

    int batchaddRoleUser(List<UserGroupDeviceDO> userGroupAclDOS);

    /**
     * 批量删除设备和组
     * @param groupId
     * @param deviceNos
     * @return
     */
    int deleteByGroupId(@Param("groupId") Long groupId, @Param("lists") List<String> deviceNos);

}
