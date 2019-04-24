package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.Req.UserPageReq;
import com.xiaowei.mana.mapper.dataobject.UserDO;
import com.xiaowei.mana.mapper.dataobject.UserGroupDO;
import com.xiaowei.mana.mapper.res.userpage.UserPage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MOMO on 2019/1/7.
 */
public interface SysUserMapper {
    /**
     * 用户登录
     * @param userName
     * @return
     */
    public UserDO userLogin(@Param("userName")String userName);

    /**
     * 用户分页查询
     * @param userPageReq
     * @return
     */
    public List<UserPage> userPage(UserPageReq userPageReq);
    /**
     *
     * @param userName  根据登录名，查看登录名是否唯一
     * @param email  邮箱是否唯一
     * @param phone 手机号是否唯一
     * @return
     */
    public int userLoginCout(@Param("userName")String userName,@Param("email") String email, @Param("phone") String phone,@Param("userId")Long userId);



    /**
     * 根据用户的id的用户所在的组
     * @param userId
     * @return
     */
    public UserGroupDO  getUserGroupByUserId(@Param("userId")Long userId);

    /**
     * 新增用户
     * @param userDO
     * @return
     */
    public int insertUser(UserDO userDO);

    /**
     * 编辑用户
     * @param userDO
     * @return
     */
    public int editUser(UserDO userDO);

    /**
     * 根据uuid查询用户
     * @param uuid
     * @return
     */
    public UserDO getUserByUUID(@Param("uuid")String uuid);

    /**
     * 查询当前组下的所有员工次数
     * @param groupid
     * @return
     */
    public int getUserCountBygroupId(@Param("groupid") Long groupid);

    /**
     * 得到当前第三方组管理员的个数
     * @param groupid
     * @param sysRoleType
     * @return
     */
    public int getAdminUser(@Param("groupid") Long groupid,@Param("sysRoleType") String sysRoleType,@Param("id")Long id);
    public List<UserDO> getAll();
}
