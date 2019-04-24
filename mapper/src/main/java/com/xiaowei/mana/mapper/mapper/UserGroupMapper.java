package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.PageUserGroupReqVO;
import com.xiaowei.mana.mapper.dataobject.UserGroupDO;
import com.xiaowei.mana.mapper.dataobject.UserGroupPageDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MOMO on 2019/1/8.
 */
public interface UserGroupMapper {


    int insertSelective(UserGroupDO record);

    int updateByPrimaryKeySelective(UserGroupDO record);

    /**
     * 根据组id查询组信息
     *
     * @param gid
     * @return
     */
    public List<UserGroupDO> getUserGroupBygId(@Param("gid") Long gid, @Param("delflag") String delflag);

    /**
     * 根据uuid查询组信息
     *
     * @param uuid
     * @return
     */
    UserGroupDO getGroupByUUID(@Param("uuid") String uuid);

    /**
     * 校验组织名称是否存在
     *
     * @param groupName
     * @param id
     * @return
     */
    int checkGroupName(@Param("groupName") String groupName, @Param("id") Long id);

    /**
     * 第三方组织分页
     *
     * @param userGroupDO
     * @return
     */
    public List<UserGroupPageDO> pageUserGroup(PageUserGroupReqVO userGroupDO);

    /**
     * 下拉框查询出全部 第三方
     * @return
     */
    public List<UserGroupPageDO> getAll();
}
