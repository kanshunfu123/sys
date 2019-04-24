package com.xiaowei.service.role;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.AddRoleReq;
import com.xiaowei.common.req.ModifyRoleReq;
import com.xiaowei.common.req.PageRoleReq;
import com.xiaowei.common.req.PageRoleRes;
import com.xiaowei.common.res.UserPageRes;
import com.xiaowei.common.res.redis.RedisRole;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dataobject.PageRoleDO;
import com.xiaowei.mana.mapper.dataobject.RoleDO;
import com.xiaowei.mana.mapper.mapper.RoleMapper;
import com.xiaowei.mana.mapper.mapper.SysUserMapper;
import com.xiaowei.service.core.SysCoreService;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/1/9.
 */
@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private SysUserMapper sysUserMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SysCoreService sysCoreService;

    public JSONResult pageRole(PageRoleReq pageRoleReq, RedisUser redisUser) {
        if (redisUser.getGroupId() == 1) {
            redisUser.setGroupId(null);
        }
        PageHelper.startPage(pageRoleReq.getPageNum(), pageRoleReq.getPageSize());
        List<PageRoleDO> roleDOS = roleMapper.pageRole(pageRoleReq.getSysRoleName(), redisUser.getGroupId());
        PageInfo<PageRoleDO> pageInfo = new PageInfo<>(roleDOS);
        List<PageRoleDO> roleDOList = pageInfo.getList();
        if (CollectionUtils.isEmpty(roleDOList)) {
            return JSONResult.ok(pageInfo);
        }
        List<PageRoleRes> pageRoleRes = Lists.newArrayList();
        roleDOList.forEach(roleDO -> {
            PageRoleRes pageRoleRes1 = new PageRoleRes();
            BeanUtils.copyProperties(roleDO, pageRoleRes1);
            pageRoleRes.add(pageRoleRes1);
        });
        PageInfo<PageRoleRes> userPageRespageInfo = new PageInfo<>(pageRoleRes);
        userPageRespageInfo.setTotal(pageInfo.getTotal());
        userPageRespageInfo.setSize(pageRoleReq.getPageSize());
        userPageRespageInfo.setPageNum(pageRoleReq.getPageNum());
        return JSONResult.ok(userPageRespageInfo);
    }

    /**
     * 当前登录用户 角色类型
     *
     * @param redisUser
     * @return
     */
    public List<RoleDO> roleType(RedisUser redisUser) {
        List<RoleDO> roleDOS = Lists.newArrayList();
        if (sysCoreService.isSuperAdmin(redisUser)) {
            RoleDO roleDO = new RoleDO();
            roleDO.setSysRoleName("admin");
            roleDOS.add(roleDO);
            return roleDOS;
        }

        //redis-->根据用户id得到角色id列表
        Object o = redisUtil.get(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + redisUser.getId());
        List<Long> userRoleIdList = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
        });
        if (CollectionUtils.isEmpty(userRoleIdList)) {
            return Lists.newArrayList();
        }
        List<String> userRoleIdListStr = userRoleIdList.stream().map(sysAcl -> RedisKeyEnum.REDIS_ROLE_INFO.getKey() + sysAcl).collect(Collectors.toList());
        //根据角色id列表,得到角色信息列表
        List<Object> objects = redisUtil.batchListByKeys(userRoleIdListStr);
        if (CollectionUtils.isEmpty(objects)) {
            return Lists.newArrayList();
        }
        objects.forEach(o1 -> {
            if (o1 != null) {
                RoleDO roleDO = JSON.parseObject(o1 + "", new TypeReference<RoleDO>() {
                });
                roleDO.setSysRoleName("ppp");
                roleDOS.add(roleDO);
            }
        });
        return roleDOS;
    }

    /**
     * 新增角色
     *
     * @param addRole
     * @param redisUser
     * @return
     */
    public JSONResult addRole(AddRoleReq addRole, RedisUser redisUser) {
        if (addRole.getGroupId() != 1) {
            //角色的类型，0：管理员角色，1：普通用户 2其他.
            if ("0".equals(addRole.getSysRoleType())) {
                int adminCount = sysUserMapper.getAdminUser(addRole.getGroupId(), addRole.getSysRoleType(), null);
                if (adminCount >= 1) {
                    return JSONResult.errorMap("此角色所在的公司已经有管理员角色了");
                }
            }
        }
        int roleNameCount = roleMapper.getRoleCountByRoleName(addRole.getSysRoleName(), addRole.getGroupId(), null);
        if (roleNameCount > 0) {
            return JSONResult.errorMap("角色名称已存在");
        }
        RoleDO roleDO = new RoleDO();
        roleDO.setSysRoleUuid(StrUtil.genUUID());
        roleDO.setGroupId(addRole.getGroupId());
        BeanUtils.copyProperties(addRole, roleDO);
        roleDO.setCreateBy(redisUser.getSysUserName());
        roleDO.setUpdateBy(redisUser.getSysUserName());
        roleDO.setCreateTime(DateUtils.getCurrentDateTime());
        roleDO.setUpdateTime(DateUtils.getCurrentDateTime());
        int i = roleMapper.addRole(roleDO);
        if (i > 0) {
            saveRedisRole(roleDO);
            return JSONResult.ok("新增角色成功");
        }
        return JSONResult.errorMap("新增角色失败");
    }

    /**
     * 编辑角色
     *
     * @param modifyRoleReq
     * @param redisUser
     * @return
     */
    public JSONResult modifyRole(ModifyRoleReq modifyRoleReq, RedisUser redisUser) {
        RoleDO roleDOa = roleMapper.getRoleByUUID(modifyRoleReq.getSysRoleUuid());
        if (null == roleDOa) {
            return JSONResult.errorMap("待编辑的角色不存在");
        }
        if (modifyRoleReq.getGroupId() != 1) {
            //角色的类型，0：管理员角色，1：普通用户 2其他.
            if ("0".equals(modifyRoleReq.getSysRoleType())) {
                int adminCount = sysUserMapper.getAdminUser(modifyRoleReq.getGroupId(), modifyRoleReq.getSysRoleType(), roleDOa.getId());
                if (adminCount >= 1) {
                    return JSONResult.errorMap("您所在的公司已经有管理员角色了");
                }
            }
        }

        //不允许第三方编辑管理员角色权限
        if (redisUser.getGroupId() != 1 && "0".equals(roleDOa.getSysRoleType())) {
            return JSONResult.errorMap("管理员角色不允许修改");
        }

        int roleNameCount = roleMapper.getRoleCountByRoleName(modifyRoleReq.getSysRoleName(), redisUser.getGroupId(), roleDOa.getId());
        if (roleNameCount > 0) {
            return JSONResult.errorMap("角色名称已存在");
        }
        RoleDO roleDO = new RoleDO();
        roleDO.setId(roleDOa.getId());
        BeanUtils.copyProperties(modifyRoleReq, roleDO);
        roleDO.setUpdateBy(redisUser.getSysUserName());
        roleDO.setUpdateTime(DateUtils.getCurrentDateTime());
        int i = roleMapper.modifyRole(roleDO);
        if (i > 0) {
            saveRedisRole(roleDO);
            return JSONResult.ok("编辑角色成功");
        }
        return JSONResult.errorMap("编辑角色失败");
    }

    public JSONResult roleSelect(RedisUser redisUser) {
        if (redisUser.getGroupId() != 1) {//第三方
            List<RoleDO> roleDOS = roleMapper.getRoleByTyGroupType(redisUser.getGroupId(),null);
            List<RoleDO> roleDOS1 = roleDOSPackage(roleDOS);
            List<RoleDO> fresult = Lists.newArrayList();
            roleDOS1.forEach(roleDO -> {
                //角色的类型，0：管理员角色，1：普通用户 2其他
                fresult.add(roleDO);
            });
            return JSONResult.ok(fresult);
        } else {
            List<RoleDO> roleDOS = roleMapper.getRoleByTyGroupType(null,null);
            return JSONResult.ok(roleDOSPackage(roleDOS));
        }

    }

    private List<RoleDO> roleDOSPackage(List<RoleDO> roleDOS) {
        if (CollectionUtils.isEmpty(roleDOS)) {
            return Lists.newArrayList();
        }
        roleDOS.forEach(roleDO -> {
            //角色的类型，0：管理员角色，1：普通用户 2其他'
            if ("0".equals(roleDO.getSysRoleType())) {
                //是否被禁用  0否 1禁用
                if ("0".equals(roleDO.getSysRoleStatus())) {
                    roleDO.setSysRoleName(roleDO.getSysRoleName() + "(管理员-已启用)");
                } else if ("1".equals(roleDO.getSysRoleStatus())) {
                    roleDO.setSysRoleName(roleDO.getSysRoleName() + "(管理员-已禁用)");
                }

            } else if ("1".equals(roleDO.getSysRoleType())) {
                //是否被禁用  0否 1禁用
                if ("0".equals(roleDO.getSysRoleStatus())) {
                    roleDO.setSysRoleName(roleDO.getSysRoleName() + "(普通用户-已启用)");
                } else if ("1".equals(roleDO.getSysRoleStatus())) {
                    roleDO.setSysRoleName(roleDO.getSysRoleName() + "(普通用户-已禁用)");
                }
            }
        });
        return roleDOS;
    }

    private void saveRedisRole(RoleDO roleDO) {
        RedisRole redisRole = new RedisRole();
        redisRole.setId(roleDO.getId());
        redisRole.setDelFlag(roleDO.getDelFlag());
        redisRole.setGroupId(roleDO.getGroupId());
        redisRole.setSysRoleName(roleDO.getSysRoleName());
        redisRole.setSysRoleType(roleDO.getSysRoleType());
        redisRole.setSysRoleStatus(roleDO.getSysRoleStatus());
        redisUtil.set(RedisKeyEnum.REDIS_ROLE_INFO.getKey() + redisRole.getId(), JSONObject.toJSONString(redisRole));
//        角色的类型，0：管理员角色，1：普通用户 2其他
        if ("0".equals(redisRole.getSysRoleType())) {
            redisUtil.set(RedisKeyEnum.REDIS_ADMIN_ROLE_INFO.getKey() + redisRole.getGroupId(), JSONObject.toJSONString(redisRole));
        }
    }
}
