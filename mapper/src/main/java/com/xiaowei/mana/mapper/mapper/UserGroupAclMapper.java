package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.UserGroupAclDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface UserGroupAclMapper {

    UserGroupAclDO getGroupAclByUUID(@Param("uuid") String uuid);

    /**
     * 根据组的id得到改组的权限点列表
     * @param groupId
     * @return
     */
    List<Long> getDeviceIdByGroupId(@Param("groupId") Long groupId);

    int deleteByGroupId(@Param("groupId")Long groupId);
    int batchaddRoleUser(List<UserGroupAclDO> userGroupAclDOS);
}
