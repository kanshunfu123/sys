package com.xiaowei.service.usergroup;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.InsertUserGroupReq;
import com.xiaowei.common.req.PageUserGroupVO;
import com.xiaowei.common.req.UserGroupAclReq;
import com.xiaowei.common.res.InsertUserGroupRes;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.DeptMapper;
import com.xiaowei.mana.mapper.mapper.RoleMapper;
import com.xiaowei.mana.mapper.mapper.UserGroupMapper;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;

/**
 * Created by MOMO on 2019/1/9.
 */
@Service
@Transactional
public class SysUserGroupService {
    @Autowired
    private UserGroupMapper userGroupMapper;
    @Autowired
    private DeptMapper deptMapper;
    @Autowired
    private RoleMapper roleMapper;

    public static void main(String[] args) {
        String[] strings = "3".split(",");
        List<String> list = Lists.newArrayList();
        for (int i = 0; i < strings.length; i++) {
            String strings1 = strings[i];
            if ("0".equals(strings1)) {
                list.add("地表水");
            } else if ("1".equals(strings1)) {
                list.add("井盖");
            } else if ("2".equals(strings1)) {
                list.add("生活用水");
            } else if ("3".equals(strings1)) {
                list.add("电梯");
            }
        }
    }

    public JSONResult roles(UserGroupAclReq userGroupAclReq,RedisUser redisUser) {
        String roleType="";
        if (userGroupAclReq.getGroupId()==1){
            userGroupAclReq.setGroupId(null);
        }else {
            roleType="0";
        }
        List<RoleDO> roleDOS = roleMapper.getRoleByTyGroupType(userGroupAclReq.getGroupId(),roleType);
        return JSONResult.ok(roleDOSPackage(roleDOS));
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

    public JSONResult getAll() {
        List<UserGroupPageDO> list = userGroupMapper.getAll();
        return JSONResult.ok(list);
    }

    public JSONResult pageUserGroup(PageUserGroupVO pageUserGroupReq) {
        PageUserGroupReqVO userGroupDO = new PageUserGroupReqVO();
        BeanUtils.copyProperties(pageUserGroupReq, userGroupDO);
        PageHelper.startPage(pageUserGroupReq.getPageNum(), pageUserGroupReq.getPageSize());
        List<UserGroupPageDO> userGroupPageDOS = userGroupMapper.pageUserGroup(userGroupDO);
        PageInfo<UserGroupPageDO> pageInfo = new PageInfo<>(userGroupPageDOS);
        List<UserGroupPageDO> lista = pageInfo.getList();
        if (CollectionUtils.isEmpty(lista)) {
            return JSONResult.ok(new PageInfo<>());
        }
        List<UserGroupPageDO> userGroupPageDOS1 = Lists.newArrayList();
        lista.forEach(userGroupPageDO -> {
            String[] strings = userGroupPageDO.getSysScene().split(",");
            List<String> list = Lists.newArrayList();
            for (int i = 0; i < strings.length; i++) {
                String strings1 = strings[i];
                if ("0".equals(strings1)) {
                    list.add("地表水");
                } else if ("1".equals(strings1)) {
                    list.add("井盖");
                } else if ("2".equals(strings1)) {
                    list.add("生活用水");
                } else if ("3".equals(strings1)) {
                    list.add("电梯");
                }
            }
            UserGroupPageDO userGroupPageDO1 = new UserGroupPageDO();
            BeanUtils.copyProperties(userGroupPageDO, userGroupPageDO1);
            userGroupPageDO1.setStringList(list);
            userGroupPageDOS1.add(userGroupPageDO1);
        });
        PageInfo<UserGroupPageDO> pageInfo1 = new PageInfo<>(userGroupPageDOS1);
        pageInfo1.setTotal(pageInfo.getTotal());
        pageInfo1.setPageSize(pageUserGroupReq.getPageSize());
        pageInfo1.setPageNum(pageUserGroupReq.getPageNum());
        return JSONResult.ok(pageInfo1);

    }


    /**
     * 用户分页列表 下拉框选择各个组
     *
     * @param redisUser
     * @return
     */
    public JSONResult getUserGroupsBygId(RedisUser redisUser) {
        Long groupId = redisUser.getGroupId();
        if (groupId != 1) {
            return JSONResult.ok(userGroupMapper.getUserGroupBygId(groupId, "0"));
        } else {
            return JSONResult.ok(userGroupMapper.getUserGroupBygId(null, "0"));
        }
    }

    public JSONResult uuid(InsertUserGroupReq insertUserGroupReq) {
        InsertUserGroupRes insertUserGroupRes = new InsertUserGroupRes();
        UserGroupDO userGroupDO = userGroupMapper.getGroupByUUID(insertUserGroupReq.getUserGroupUuid());
        BeanUtils.copyProperties(userGroupDO, insertUserGroupReq);
        if (StringUtils.isNotBlank(userGroupDO.getSysScene())) {
            List<String> lis = Arrays.asList(userGroupDO.getSysScene().split(","));
            insertUserGroupRes.setScenList(lis);
        }
        BeanUtils.copyProperties(userGroupDO, insertUserGroupRes);
        return JSONResult.ok(insertUserGroupRes);
    }

    /**
     * 编辑
     *
     * @param insertUserGroupReq
     * @param redisUser
     * @return
     */
    public JSONResult modify(InsertUserGroupReq insertUserGroupReq, RedisUser redisUser) {
        if (redisUser.getGroupId() != 1) {
            return JSONResult.errorMap("您不是小为科技员工，无权开通第三方组织");
        }
        UserGroupDO userGroupDO = userGroupMapper.getGroupByUUID(insertUserGroupReq.getUserGroupUuid());
        if (null == userGroupDO) {
            return JSONResult.errorMap("待编辑的组织不存在");
        }
        int count = userGroupMapper.checkGroupName(insertUserGroupReq.getUserGroupName(), userGroupDO.getId());
        if (count > 0) {
            return JSONResult.errorMap("组织名称已存在");
        }
        UserGroupDO groupDO = new UserGroupDO();
        BeanUtils.copyProperties(insertUserGroupReq, groupDO);
        groupDO.setId(userGroupDO.getId());
        groupDO.setSysAccountEndTime(DateUtils.stringToDate(insertUserGroupReq.getSysAccountEndTime()));
        groupDO.setSysOpenDay("" + DateUtils.dateDiff(insertUserGroupReq.getSysAccountStartTime(), insertUserGroupReq.getSysAccountEndTime(), "yyyy-MM-DD HH:mm:ss", null));
        groupDO.setSysAllowDeviceNum(insertUserGroupReq.getSysAllowDeviceNum());
        groupDO.setSysCreateAccountNum(insertUserGroupReq.getSysCreateAccountNum());
        groupDO.setSysScene(insertUserGroupReq.getSysScene());
        groupDO.setSysStart(insertUserGroupReq.getSysStart());
        groupDO.setUserGroupName(insertUserGroupReq.getUserGroupName());
        groupDO.setCreateBy(redisUser.getSysUserName());
        groupDO.setUpdateBy(redisUser.getSysUserName());
        groupDO.setCreateTime(DateUtils.getCurrentDateTime());
        groupDO.setUpdateTime(DateUtils.getCurrentDateTime());
        userGroupMapper.updateByPrimaryKeySelective(groupDO);
        return JSONResult.ok("编辑组织成功");
    }

    /**
     * 新增
     *
     * @param insertUserGroupReq
     * @param redisUser
     * @return
     */
    public JSONResult insertUserGroup(InsertUserGroupReq insertUserGroupReq, RedisUser redisUser) {
        if (redisUser.getGroupId() != 1) {
            return JSONResult.errorMap("您不是小为科技员工，无权开通第三方组织");
        }
        int count = userGroupMapper.checkGroupName(insertUserGroupReq.getUserGroupName(), null);
        if (count > 0) {
            return JSONResult.errorMap("组织名称已存在");
        }


        UserGroupDO record = new UserGroupDO();
        BeanUtils.copyProperties(insertUserGroupReq, record);
        record.setSysAccountStartTime(DateUtils.stringToDate(insertUserGroupReq.getSysAccountStartTime()));
        record.setSysAccountEndTime(DateUtils.stringToDate(insertUserGroupReq.getSysAccountEndTime()));
        record.setSysOpenDay("" + DateUtils.dateDiff(insertUserGroupReq.getSysAccountStartTime(), insertUserGroupReq.getSysAccountEndTime(), "yyyy-MM-DD HH:mm:ss", null));
        record.setUserGroupUuid(StrUtil.genUUID());
        record.setCreateBy(redisUser.getSysUserName());
        record.setUpdateBy(redisUser.getSysUserName());
        record.setCreateTime(DateUtils.getCurrentDateTime());
        record.setUpdateTime(DateUtils.getCurrentDateTime());
        userGroupMapper.insertSelective(record);
        DeptDO deptDO = new DeptDO();
        deptDO.setSysDeptUuid(StrUtil.genUUID());
        deptDO.setSysDeptName("组织架构");
        deptDO.setGroupId(record.getId());
        deptDO.setSysDeptParentId(0L);
        deptDO.setSysDeptSeq(1);
        deptDO.setSysDeptLevel("0");
        //是否是叶子节点 0 是   1不是
        deptDO.setSysIsLeaf("1");
        deptDO.setCreateBy(redisUser.getSysUserName());
        deptDO.setUpdateBy(redisUser.getSysUserName());
        deptDO.setCreateTime(DateUtils.getCurrentDateTime());
        deptDO.setUpdateTime(DateUtils.getCurrentDateTime());
        deptMapper.insertSelective(deptDO);
        return JSONResult.ok("添加组织成功");
    }
}
