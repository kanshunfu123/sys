package com.xiaowei.service.dept;

import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.dept.DeptAddReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.LevelUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.dataobject.DeptDO;
import com.xiaowei.mana.mapper.mapper.DeptMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by MOMO on 2019/1/15.
 */
@Service
public class DeptService {
    @Autowired
    private DeptMapper deptMapper;

    /**
     * 新增部门
     *
     * @param deptAddReq
     * @param redisUser
     * @return
     */
    @Transactional
    public JSONResult addDept(DeptAddReq deptAddReq, RedisUser redisUser) {
        if (checkExist(deptAddReq.getSysDeptParentId(), deptAddReq.getSysDeptName(), redisUser.getGroupId(), null)) {
            return JSONResult.errorMap("同一层级下存在相同名称的部门");
        }
        DeptDO deptDO = new DeptDO();
        BeanUtils.copyProperties(deptAddReq, deptDO);
        deptDO.setSysDeptLevel(LevelUtil.calculateLevel(getLevel(deptAddReq.getSysDeptParentId()), deptAddReq.getSysDeptParentId()));
        deptDO.setSysDeptUuid(StrUtil.genUUID());
        deptDO.setCreateBy(redisUser.getSysUserName());
        deptDO.setUpdateBy(redisUser.getSysUserName());
        deptDO.setCreateTime(DateUtils.getCurrentDateTime());
        deptDO.setUpdateTime(DateUtils.getCurrentDateTime());
        deptDO.setSysIsLeaf(checkIsLeaf(deptDO.getSysDeptLevel() + "."));
        deptDO.setGroupId(redisUser.getGroupId());
        deptMapper.insertSelective(deptDO);
        return JSONResult.ok("新增部门成功");
    }


    public JSONResult modifyDept(DeptAddReq deptAddReq, RedisUser redisUser) {
        DeptDO before = deptMapper.getDeptByUUID(deptAddReq.getUuid());
        if (null == before) {
            return JSONResult.errorMap("待更新的部门不存在");
        }
        if (checkExist(deptAddReq.getSysDeptParentId(), deptAddReq.getSysDeptName(), redisUser.getGroupId(), before.getId())) {
            return JSONResult.errorMap("同一层级下存在相同名称的部门");
        }

        DeptDO after = new DeptDO();
        BeanUtils.copyProperties(deptAddReq, after);
        after.setId(before.getId());
        after.setSysDeptLevel(LevelUtil.calculateLevel(getLevel(deptAddReq.getSysDeptParentId()), deptAddReq.getSysDeptParentId()));
        after.setSysDeptUuid(StrUtil.genUUID());
        after.setCreateBy(redisUser.getSysUserName());
        after.setUpdateBy(redisUser.getSysUserName());
        after.setCreateTime(DateUtils.getCurrentDateTime());
        after.setUpdateTime(DateUtils.getCurrentDateTime());
        after.setSysIsLeaf(checkIsLeaf(before.getSysDeptLevel() + "."));
        after.setGroupId(redisUser.getGroupId());
        updateWithChild(before, after);
        return JSONResult.ok("编辑部门成功");
    }
    @Transactional
    private void updateWithChild(DeptDO before, DeptDO after) {
        String newLevelPrefix = after.getSysDeptLevel();
        String oldLevelPrefix = before.getSysDeptLevel();
        if (!after.getSysDeptLevel().equals(before.getSysDeptLevel())) {
            List<DeptDO> deptList = deptMapper.getChildDeptListByLevel(before.getSysDeptLevel()+".");
            if (CollectionUtils.isNotEmpty(deptList)) {
                for (DeptDO dept : deptList) {
                    String level = dept.getSysDeptLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dept.setSysDeptLevel(level);
                        dept.setSysIsLeaf(checkIsLeaf(level + "."));
                    }
                }
                deptMapper.batchUpdateLevel(deptList);
            }
        }
        deptMapper.updateByPrimaryKeySelective(after);
    }

    private boolean checkExist(Long parentId, String deptName, Long groupId, Long deptId) {
        return deptMapper.countByNameAndParentId(parentId, deptName, groupId, deptId) > 0;
    }

    private String checkIsLeaf(String level) {
        //是否是叶子节点 0 是   1不是
        return deptMapper.countByLevel(level) > 0 ? "1" : "0";
    }

    private String getLevel(Long deptId) {
        DeptDO dept = deptMapper.selectByPrimaryKey(deptId);
        if (dept == null) {
            return null;
        }
        return dept.getSysDeptLevel();
    }

}
