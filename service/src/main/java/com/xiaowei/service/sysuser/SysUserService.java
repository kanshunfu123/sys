package com.xiaowei.service.sysuser;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.config.JwtProperties;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.InserUserReq;
import com.xiaowei.common.req.ModifyUserReq;
import com.xiaowei.common.req.SysUserReq;
import com.xiaowei.common.res.SysUserRes;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.res.ResPonseUser;
import com.xiaowei.common.res.UserPageRes;
import com.xiaowei.common.util.Encrypt;
import com.xiaowei.common.util.JwtTokenUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.Req.UserPageReq;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.*;
import com.xiaowei.mana.mapper.res.userpage.UserPage;
import com.xiaowei.service.async.LoginLogAsync;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by MOMO on 2019/1/7.
 */
@Service
@Transactional
@Slf4j
public class SysUserService {

    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private JwtTokenUtil jwtTokenUtil;
    @Autowired
    private JwtProperties jwtProperties;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private LoginLogAsync loginLogAsync;


    /**
     * 登录
     *
     * @param sysUserReq
     * @return
     */
    public JSONResult userLogin(SysUserReq sysUserReq, HttpServletRequest request, HttpServletResponse response) {
        UserDO userDO = sysUserMapper.userLogin(sysUserReq.getUserName());
        if (null == userDO) {
            return JSONResult.errorMap("用户名不存在");
        }
        if ("1".equals(userDO.getIsForbidden())) {
            return JSONResult.errorMap("您的账户已被禁用");
        }
        if ("1".equals(userDO.getDelFlag())) {
            return JSONResult.errorMap("您的账户已被删除");
        }
        String pwd = Encrypt.getShiroPassword(sysUserReq.getUserPwd(), userDO.getSysUserAuthSalt(), 2);
        if (!userDO.getSysUserPwd().equals(pwd)) {
            return JSONResult.errorMap("用户名或者密码错误");
        }
        // TODO 验证码

        UserGroupDO userGroupDO = sysUserMapper.getUserGroupByUserId(userDO.getGroupId());
        if (null == userGroupDO) {
            return JSONResult.errorMap("您所在的用户组不存在");
        }
        if ("1".equals(userGroupDO.getSysStart())) {
            return JSONResult.errorMap("您所在的用户组已被禁用");
        }
        if ("1".equals(userGroupDO.getDelFlag())) {
            return JSONResult.errorMap("您所在的用户组已被删除");
        }
        if (DateUtils.timeDifference(userGroupDO.getSysAccountEndTime())) {
            return JSONResult.errorMap("您所在的用户组时间已到期,请续约后在次登录");
        }
        RedisUser redisUser = new RedisUser();
        redisUser.setDeptId(userDO.getDeptId());
        redisUser.setId(userDO.getId());
        redisUser.setGroupId(userDO.getGroupId());
        redisUser.setRoleType(userDO.getRoleType());
        redisUser.setSysUserEmail(userDO.getSysUserEmail());
        redisUser.setSysUserName(userDO.getSysUserName());
        redisUser.setSysLoginNumber(userDO.getSysLoginNumber());
        redisUser.setSysUserLoginName(userDO.getSysUserLoginName());
        redisUser.setSysUserPhone(userDO.getSysUserPhone());
        String redisUserKey = StrUtil.genUUID();
        redisUser.setRedisUserKey(redisUserKey);
        String jsonStr = JSONObject.toJSONString(redisUser);
        //生成token
        // randomKey和token已经生成完毕
        final String randomKey = jwtTokenUtil.getRandomKey();
        final String token = jwtTokenUtil.generateToken(jsonStr, randomKey);

        //header
//        response.setHeader(RedisKeyEnum.REDIS_KEY_HEADER_INFO.getKey(), token);
        //用户信息放入 redis
        redisUtil.set(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUserKey, token);
        redisUtil.expire(RedisKeyEnum.REDIS_KEY_USER_INFO.getKey() + redisUserKey, RedisKeyEnum.REDIS_KEY_USER_INFO.getExpireTime());
        //限制登录次数
        if (userDO.getGroupId() != 1) {//第三方  单点
            redisUtil.set(RedisKeyEnum.REDIS_KEY_USER_LIST_INFO.getKey() + userDO.getId(), redisUserKey);
            redisUtil.expire(RedisKeyEnum.REDIS_KEY_USER_LIST_INFO.getKey() + userDO.getId(), RedisKeyEnum.REDIS_KEY_USER_LIST_INFO.getExpireTime());
        } else {//小为科技

        }
        ResPonseUser resPonseUser = new ResPonseUser();
//        resPonseUser.setGroupId(userDO.getGroupId());
        resPonseUser.setRoleType(userDO.getRoleType());
        resPonseUser.setSysUserEmail(userDO.getSysUserEmail());
        resPonseUser.setSysUserName(userDO.getSysUserName());
        resPonseUser.setSysUserPhone(userDO.getSysUserPhone());
        resPonseUser.setAccess_token(redisUserKey);
        resPonseUser.setNameTop(userGroupDO.getNameTop());
        resPonseUser.setNameBottom(userGroupDO.getNameBottom());
       /* String s = userGroupDO.getSysScene();
        if (StringUtils.isNotBlank(s)) {
            String[] strings = s.split(",");
            resPonseUser.setScenes(Arrays.asList(strings));
        }*/
        LoginLogDO entity = new LoginLogDO();
        entity.setGroupId(resPonseUser.getGroupId());
        entity.setUserLoginName(userDO.getSysUserLoginName());
        entity.setUserUserName(userDO.getSysUserName());
        entity.setUserId(userDO.getId());
        loginLogAsync.loginLog(entity, request);
        return JSONResult.ok(resPonseUser);
    }

    /**
     * 新增用户
     *
     * @param inserUserReq
     * @param redisUser
     * @return
     */
    public JSONResult insertUser(InserUserReq inserUserReq, RedisUser redisUser) {
        // 当前用户所在组，确定开通子账号次数
        if (redisUser.getGroupId() != 1) {//第三方
            //是否有权限开通子账号
            //冗余角色类型 冗余角色类型 0管理员(专为第三方老板而设置） 1普通员工
            if (redisUser.getGroupId() != 1 && !"0".equals(redisUser.getRoleType())) {
                return JSONResult.errorMap("只有管理员才可以开通子账号");
            }
            List<UserGroupDO> userGroupDOS = userGroupMapper.getUserGroupBygId(redisUser.getGroupId(), "0");
            int groupCount = sysUserMapper.getUserCountBygroupId(redisUser.getGroupId());
            UserGroupDO userGroupDO = userGroupDOS.get(0);
            //查询可以开通子账号的个数，不包含自己
            if ((userGroupDO.getSysCreateAccountNum() - (groupCount - 1)) <= 0) {
                return JSONResult.errorMap("您开通的子账户的个数已达上限，可以开通最大子账户的个数为:" + userGroupDO.getSysCreateAccountNum() + "个");
            }
        }

        int loginName = sysUserMapper.userLoginCout(inserUserReq.getSysUserLoginName(), null, null, null);
        if (loginName > 0) {
            return JSONResult.errorMap("登录名已存在");
        }
        /*int email = sysUserMapper.userLoginCout(null, inserUserReq.getSysUserEmail(), null, null);
        if (email > 0) {
            return JSONResult.errorMap("邮箱已存在");
        }*/
        int phone = sysUserMapper.userLoginCout(null, null, inserUserReq.getSysUserPhone(), null);
        if (phone > 0) {
            return JSONResult.errorMap("手机号已存在");
        }
        RoleDO roleDO = roleMapper.getRoleById(inserUserReq.getRoleId());
        if (roleDO == null) {
            return JSONResult.errorMap("角色信息不存在");
        }
        UserDO userDO = new UserDO();
        if (roleDO.getGroupId() != 1 && "0".equals(roleDO.getSysRoleType())) {
            List<Long> roleDOS = roleUserMapper.getThirdRoleByParam(roleDO.getId(), null);
            if (!CollectionUtils.isEmpty(roleDOS)) {
                return JSONResult.errorMap("管理员角色已经被授权给用户了");
            }
        }
        BeanUtils.copyProperties(inserUserReq, userDO);
        final String randomKey = jwtTokenUtil.getRandomKey();
        userDO.setSysUserAuthSalt(randomKey);
        String pwd = Encrypt.getShiroPassword(inserUserReq.getSysUserPwd().trim(), randomKey, 2);
        userDO.setSysUserLoginName(inserUserReq.getSysUserLoginName().trim());
        userDO.setGroupId(roleDO.getGroupId());
        userDO.setSysUserUuid(StrUtil.genUUID());
        userDO.setSysUserPwd(pwd);
        userDO.setCreateBy(redisUser.getSysUserName());
        userDO.setUpdateBy(redisUser.getSysUserName());
        userDO.setCreateTime(DateUtils.getCurrentDateTime());
        userDO.setUpdateTime(DateUtils.getCurrentDateTime());
        userDO.setRoleType(roleDO.getSysRoleType());
        if (StringUtils.isBlank(userDO.getSysLoginNumber())) {
            userDO.setSysLoginNumber("1");
        }
        sysUserMapper.insertUser(userDO);

        List<RoleUserDO> roleAclList = Lists.newArrayList();
        RoleUserDO roleUserDO = new RoleUserDO();
        roleUserDO.setGroupId(roleDO.getGroupId());
        roleUserDO.setRoleId(roleDO.getId());
        roleUserDO.setSysRoleUserUuid(StrUtil.genUUID());
        roleUserDO.setCreateBy(redisUser.getSysUserName());
        roleUserDO.setUpdateBy(redisUser.getSysUserName());
        roleUserDO.setCreateTime(DateUtils.getCurrentDateTime());
        roleUserDO.setUpdateTime(DateUtils.getCurrentDateTime());
        roleUserDO.setUserId(userDO.getId());
        roleUserDO.setDelFlag("0");
        roleAclList.add(roleUserDO);
        roleUserMapper.batchaddRoleUser(roleAclList);
        saveRedisUser(userDO);
        saveRedisRoleUser(Lists.newArrayList(roleDO.getId()), userDO.getId());
        return JSONResult.ok("新增用户成功");
    }

    private void saveRedisRoleUser(List<Long> redisRoleUsers, Long userId) {
        if (org.apache.commons.collections.CollectionUtils.isEmpty(redisRoleUsers)) {
            redisUtil.del(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + userId);
            return;
        }
        redisUtil.set(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + userId, JSONObject.toJSONString(redisRoleUsers));
    }

    /**
     * 编辑用户
     *
     * @param modifyUserReq
     * @param redisUser
     * @return
     */
    public JSONResult editUser(ModifyUserReq modifyUserReq, RedisUser redisUser) {
        UserDO userDOa = sysUserMapper.getUserByUUID(modifyUserReq.getSysUserUuid());
        if (null == userDOa) {
            return JSONResult.errorMap("待编辑的用户不存在");
        }
        RoleDO roleDO = roleMapper.getRoleById(modifyUserReq.getRoleId());
        if (redisUser.getGroupId() != 1) {

            if ("0".equals(userDOa.getRoleType())) {
                if (!"0".equals(roleDO.getSysRoleType())) {
                    return JSONResult.errorMap("该用户的管理员角色不允许修改");
                }
            }
        }
        /*int email = sysUserMapper.userLoginCout(null, modifyUserReq.getSysUserEmail(), null, userDOa.getId());
        if (email > 0) {
            return JSONResult.errorMap("邮箱已存在");
        }*/
        int phone = sysUserMapper.userLoginCout(null, null, modifyUserReq.getSysUserPhone(), userDOa.getId());
        if (phone > 0) {
            return JSONResult.errorMap("手机号已存在");
        }
        if (roleDO == null) {
            return JSONResult.errorMap("角色信息不存在");
        }
        if (roleDO.getGroupId() != 1 && "0".equals(roleDO.getSysRoleType())) {
            List<Long> roleDOS = roleUserMapper.getThirdRoleByParam(roleDO.getId(), userDOa.getId());
            if (!CollectionUtils.isEmpty(roleDOS)) {
                return JSONResult.errorMap("管理员角色已经被授权给用户了");
            }
        }

        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(modifyUserReq, userDO);
        userDO.setCreateBy(redisUser.getSysUserName());
        userDO.setUpdateBy(redisUser.getSysUserName());
        userDO.setId(userDOa.getId());
        userDO.setCreateTime(DateUtils.getCurrentDateTime());
        userDO.setUpdateTime(DateUtils.getCurrentDateTime());
        userDO.setSysUserLoginName(null);
        userDO.setSysUserPwd(null);
        userDO.setSysUserAuthSalt(null);
        int i = sysUserMapper.editUser(userDO);

        List<RoleUserDO> roleAclList = Lists.newArrayList();
        RoleUserDO roleUserDO = new RoleUserDO();
        roleUserDO.setGroupId(roleDO.getGroupId());
        roleUserDO.setRoleId(roleDO.getId());
        roleUserDO.setSysRoleUserUuid(StrUtil.genUUID());
        roleUserDO.setCreateBy(redisUser.getSysUserName());
        roleUserDO.setUpdateBy(redisUser.getSysUserName());
        roleUserDO.setCreateTime(DateUtils.getCurrentDateTime());
        roleUserDO.setUpdateTime(DateUtils.getCurrentDateTime());
        roleUserDO.setUserId(userDO.getId());
        roleUserDO.setDelFlag("0");
        roleAclList.add(roleUserDO);
        roleUserMapper.deleteByUserId(roleUserDO.getUserId());
        roleUserMapper.batchaddRoleUser(roleAclList);

        if (i > 0) {
            userDO.setIsForbidden(userDOa.getIsForbidden());
            userDO.setGroupId(userDOa.getGroupId());
            userDO.setRoleType(userDOa.getRoleType());
            userDO.setSysLoginNumber(userDOa.getSysLoginNumber());
            saveRedisUser(userDO);
            return JSONResult.ok("编辑用户成功");
        }
        return JSONResult.errorMap("编辑用户失败");
    }

    /**
     * 删除用户
     *
     * @param modifyUserReq
     * @param redisUser
     * @return
     */
    public JSONResult delUser(ModifyUserReq modifyUserReq, RedisUser redisUser) {
        UserDO userDOa = sysUserMapper.getUserByUUID(modifyUserReq.getSysUserUuid());
        if (null == userDOa) {
            return JSONResult.errorMap("待删除的用户不存在");
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(modifyUserReq, userDO);
        userDO.setDelFlag("1");
        userDO.setId(userDOa.getId());
        userDO.setCreateBy(redisUser.getSysUserName());
        userDO.setUpdateBy(redisUser.getSysUserName());
        userDO.setCreateTime(DateUtils.getCurrentDateTime());
        userDO.setUpdateTime(DateUtils.getCurrentDateTime());
        int i = sysUserMapper.editUser(userDO);
        if (i > 0) {
            return JSONResult.ok("删除用户成功");
        }
        return JSONResult.ok("删除用户失败");
    }

    /**
     * 禁用用户
     *
     * @param modifyUserReq
     * @param redisUser
     * @return
     */
    public JSONResult forbUser(ModifyUserReq modifyUserReq, RedisUser redisUser) {
        UserDO userDOa = sysUserMapper.getUserByUUID(modifyUserReq.getSysUserUuid());
        if (null == userDOa) {
            return JSONResult.errorMap("该用户不存在");
        }
        UserDO userDO = new UserDO();
        BeanUtils.copyProperties(modifyUserReq, userDO);
        //是否被禁用  0否 1禁用
        if ("0".equals(modifyUserReq.getIsForbidden())) {
            userDO.setIsForbidden("1");
        } else {
            userDO.setIsForbidden("0");
        }

        userDO.setId(userDOa.getId());
        userDO.setCreateBy(redisUser.getSysUserName());
        userDO.setUpdateBy(redisUser.getSysUserName());
        userDO.setCreateTime(DateUtils.getCurrentDateTime());
        userDO.setUpdateTime(DateUtils.getCurrentDateTime());
        int i = sysUserMapper.editUser(userDO);
        if (i > 0) {
            userDOa.setIsForbidden(userDO.getIsForbidden());
            saveRedisUser(userDOa);
            return JSONResult.ok("编辑用户状态成功");
        }
        return JSONResult.ok("编辑用户状态失败");
    }

    /**
     * 用户 分页 查询
     *
     * @param userPageReq
     * @param redisUser
     * @return
     */
    public JSONResult userPage(UserPageReq userPageReq, RedisUser redisUser) {
        if (redisUser.getGroupId() == 1) {
            userPageReq.setGroupId(null);
        } else {
            userPageReq.setGroupId(redisUser.getGroupId());
        }


        PageHelper.startPage(userPageReq.getPageNum(), userPageReq.getPageSize());
        List<UserPage> list = sysUserMapper.userPage(userPageReq);
        PageInfo<UserPage> pageInfo = new PageInfo<>(list);
        List<UserPage> list1 = pageInfo.getList();
        List<UserPageRes> userPageRes = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(list1)) {
            list1.forEach(userPage -> {
                UserPageRes userPag = new UserPageRes();
                BeanUtils.copyProperties(userPage, userPag);
                userPageRes.add(userPag);
            });
        }
        PageInfo<UserPageRes> userPageRespageInfo = new PageInfo<>(userPageRes);
        userPageRespageInfo.setTotal(pageInfo.getTotal());
        userPageRespageInfo.setSize(userPageReq.getPageSize());
        userPageRespageInfo.setPageNum(userPageReq.getPageNum());
        return JSONResult.ok(userPageRespageInfo);
    }

    /**
     * 根据uuid查询用户
     *
     * @param modifyUserReq
     * @return
     */
    public JSONResult getUserByUUID(ModifyUserReq modifyUserReq) {
        UserDO userDOa = sysUserMapper.getUserByUUID(modifyUserReq.getSysUserUuid());
        if (null == userDOa) {
            return JSONResult.errorMap("待查询的用户不存在");
        }
        List<RoleDO> roleDOS = roleMapper.getRolesByUserId(userDOa.getId());
        userDOa.setSysUserPwd("");
        userDOa.setId(null);
        SysUserRes sysUserRes = new SysUserRes();
        BeanUtils.copyProperties(userDOa, sysUserRes);
        if (!CollectionUtils.isEmpty(roleDOS)) {
            sysUserRes.setRoleId(roleDOS.get(0).getId());
        }
        return JSONResult.ok(sysUserRes);
    }

    //用户放入redis缓存
    private void saveRedisUser(UserDO userDO) {
        RedisUser redisUser = new RedisUser();
//        BeanUtils.copyProperties(userDO,redisUser);
        redisUser.setDeptId(userDO.getDeptId());
        redisUser.setId(userDO.getId());
        redisUser.setGroupId(userDO.getGroupId());
        redisUser.setRoleType(userDO.getRoleType());
        redisUser.setSysUserEmail(userDO.getSysUserEmail());
        redisUser.setSysUserName(userDO.getSysUserName());
        redisUser.setSysLoginNumber(userDO.getSysLoginNumber());
        redisUser.setSysUserPhone(userDO.getSysUserPhone());
        redisUser.setIsForbidden(userDO.getIsForbidden());
        redisUtil.set(RedisKeyEnum.REDIS_USER_INFO.getKey() + redisUser.getId(), JSONObject.toJSONString(redisUser));
    }
}
