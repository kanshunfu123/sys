package com.xiaowei.service.area;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.AreaReq;
import com.xiaowei.common.req.area.AreaAddReq;
import com.xiaowei.common.req.area.AreaDReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.LevelUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.common.vo.AreaTreeVo;
import com.xiaowei.common.vo.AreaVo;
import com.xiaowei.mana.mapper.dao.AreaDAO;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.AreaMapper;
import com.xiaowei.mana.mapper.mapper.StreetMapper;
import com.xiaowei.mana.mapper.mapper.VillageMapper;
import com.xiaowei.mana.mapper.resultmap.AreaTreeResult;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by MOMO on 2019/1/9.
 */
@Service
@Transactional
public class AreaService {
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private StreetMapper streetMapper;
    @Autowired
    private VillageMapper villageMapper;
    @Autowired
    private AreaDAO areaDAO;

    /**
     * 根据id查询区域
     *
     * @param areaReq
     * @return
     */
    public JSONResult getAreaById(AreaReq areaReq) {
        return JSONResult.ok(areaMapper.getAreaByID(areaReq.getId()));
    }

    /**
     * 下拉框级联操作
     *
     * @param areaReq
     * @return
     */
    public JSONResult getAreaByParentId(AreaReq areaReq) {
        return JSONResult.ok(areaMapper.getAreaByParentId(areaReq.getParentId()));
    }

    /**
     * 下拉框级联操作
     * 街道
     *
     * @param areaReq
     * @return
     */
    public JSONResult getAllByAreaId(AreaReq areaReq) {
        return JSONResult.ok(streetMapper.getAllByAreaId(areaReq.getParentId()));
    }

    /**
     * 下拉框级联操作
     * 小区
     *
     * @param areaReq
     * @return
     */
    public JSONResult getAllVilByAreaId(AreaReq areaReq) {
        return JSONResult.ok(villageMapper.getAllByAreaId(areaReq.getParentId()));
    }

    public JSONResult addArea(AreaAddReq areaAddReq, RedisUser redisUser) {
        AreaDO areaDO = new AreaDO();

        BeanUtils.copyProperties(areaAddReq, areaDO);
        if (areaDO.getSysAreaParentId() == null) {
            areaDO.setSysAreaParentId(0L);
        } else {
            AreaDO areaDO1 = new AreaDO();
            areaDO1.setId(areaDO.getSysAreaParentId());
            int count1 = areaDAO.getAreaId(areaDO1);
            if (count1 < 0) {
                return JSONResult.errorMap("上级区域id不存在");
            }
        }

        if (checkExist(null, areaAddReq.getSysAreaParentId(), areaAddReq.getSysAreaName())) {
            return JSONResult.errorMap("同一层级下存在相同名称的区域");
        }
        //根据uuid计算  level层级
        String level = getLevel(areaDO.getSysAreaParentId());
        String newLevel = LevelUtil.calculateLevelString(level, areaDO.getSysAreaParentId());
        areaDO.setSysAreaLevel(newLevel);
        areaDO.setDelFlag("0");
        areaDO.setCreateTime(DateUtils.getCurrentDateTime());
        areaDO.setCreateBy(redisUser.getSysUserName());
        areaDO.setUpdateTime(DateUtils.getCurrentDateTime());
        areaDO.setUpdateBy(redisUser.getSysUserName());
        areaDO.setSysAreaUuid(StrUtil.genUUID());
        areaDAO.insertArea(areaDO);
        return JSONResult.ok("添加成功", areaDO.getSysAreaUuid());

    }

    private boolean checkExist(Long id, Long sysAreaParentId, String sysAreaName) {
        return areaDAO.getBycodeNameAndByparentId(id, sysAreaParentId, sysAreaName) > 0;
    }

    private boolean checkExist1(Long id, Long sysAreaParentId, String sysAreaName) {
        return areaDAO.getNameAndParent(id, sysAreaParentId, sysAreaName) > 0;
    }

    private String getLevel(Long sysAreaParentId) {
        if (sysAreaParentId == null) {
            return null;
        }
        AreaDO areaDO = areaDAO.getLevelByParentId(sysAreaParentId);
        if (areaDO == null) {
            return null;
        } else {
            return areaDO.getSysAreaLevel();
        }
    }

    public JSONResult editArea(AreaAddReq areaAddReq, RedisUser redisUser) {
        AreaDO before = areaMapper.areaDo(areaAddReq.getSysAreaUuid());
        if (before == null) {
            return JSONResult.errorMap("待更新的区域不存在");
        }
        if (checkExist(before.getId(), areaAddReq.getSysAreaParentId(), areaAddReq.getSysAreaName())) {
            return JSONResult.errorMap("同一层级下存在相同名称的区域");
        }
        AreaDO after = new AreaDO();
        BeanUtils.copyProperties(areaAddReq, after);
        after.setId(before.getId());
        //after.setSysAreaUuid(before.getSysAreaUuid());
        after.setSysAreaLevel(LevelUtil.calculateLevel(getLevel(areaAddReq.getSysAreaParentId()), areaAddReq.getSysAreaParentId()));
        after.setCreateBy(redisUser.getSysUserName());
        after.setUpdateBy(redisUser.getSysUserName());
        after.setCreateTime(DateUtils.getCurrentDateTime());
        after.setUpdateTime(DateUtils.getCurrentDateTime());
        //是否需要更新子级字典
        updateWithChild(before, after);

        return JSONResult.ok("更新区域成功");
    }

    private void updateWithChild(AreaDO before, AreaDO after) {
        String newLevelPrefix = after.getSysAreaLevel();
        String oldLevelPrefix = before.getSysAreaLevel();
        if (!after.getSysAreaLevel().equals(before.getSysAreaLevel())) {
            List<AreaDO> areaDOList = areaDAO.getChildDictionaryListByLevel(before.getSysAreaLevel() + ".");
            if (CollectionUtils.isNotEmpty(areaDOList)) {
                for (AreaDO areaDO : areaDOList) {
                    String level = areaDO.getSysAreaLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        areaDO.setSysAreaLevel(level);
                    }
                }
                areaDAO.updatebatchUpdateLevel(areaDOList);
            }
        }
        areaDAO.updateArea(after);
    }

    public JSONResult areaTreeList() {


        //递归树最终结果集
        //将deptList拷贝到dtoList
        List<AreaTreeVo> areaTreeVos = new ArrayList<>(0);
        //得到所有字典
        List<AreaTreeResult> dicVos = areaMapper.AreaTree();
        if (dicVos != null && dicVos.size() != 0) {
            dicVos.forEach(dicEntity -> {
                //去除被删除的字典
                if (dicEntity.getDelFlag().equals("0")) {
                    AreaTreeVo treeRes = new AreaTreeVo();
                    BeanUtils.copyProperties(dicEntity, treeRes);
                    areaTreeVos.add(treeRes);
                }
            });
        }

        return JSONResult.ok(dicMenuToTree(areaTreeVos));

    }

    public List<AreaTreeVo> dicMenuToTree(List<AreaTreeVo> resList) {
        //集合为空直接返回
        if (org.springframework.util.CollectionUtils.isEmpty(resList)) {
            return Lists.newArrayList();
        }
        //可以放相同的key，比普通的map高级
        //把当前的tree以level为key 相同level的字典作为value，放到map里
        // level->[dictionary1,dictionary2,....]
        Multimap<String, AreaTreeVo> levelDeptMap = ArrayListMultimap.create();
        //用来保存第一层级的树,同时也是最终的字典树
        List<AreaTreeVo> rootList = Lists.newArrayList();
        for (AreaTreeVo treeRes : resList) {
            levelDeptMap.put(treeRes.getSysAreaLevel(), treeRes);
            if (LevelUtil.ROOT.equals(treeRes.getSysAreaLevel())) {
                rootList.add(treeRes);
            }
        }
        //按照 serial_number 从小到大 对字典树进行排序
        Collections.sort(rootList, new Comparator<AreaTreeVo>() {
            @Override
            public int compare(AreaTreeVo o1, AreaTreeVo o2) {
                return o1.getSysAreaSeq() - o2.getSysAreaSeq();
            }
        });
        //递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    private void transformDeptTree(List<AreaTreeVo> dicLevelList, String level, Multimap<String, AreaTreeVo> levelDeptMap) {
        int size = dicLevelList.size();
        for (int i = 0; i < size; i++) {
            //遍历该层的每个元素
            AreaTreeVo dicMenuDto = dicLevelList.get(i);
            //处理当前层级的数据
            String nexeLevel = LevelUtil.calculateLevelString(level, dicMenuDto.getId());
            //处理下一层
            List<AreaTreeVo> tempDeptList = (List<AreaTreeVo>) levelDeptMap.get(nexeLevel);
            if (!org.springframework.util.CollectionUtils.isEmpty(tempDeptList)) {
                //排序
                Collections.sort(tempDeptList, dicSerialNumberComparator);
                //设置下一层字典
                dicMenuDto.setChildren(tempDeptList);
                //进入到下一层处理
                transformDeptTree(tempDeptList, nexeLevel, levelDeptMap);
            }
        }
    }

    public Comparator<AreaTreeVo> dicSerialNumberComparator = new Comparator<AreaTreeVo>() {
        @Override
        public int compare(AreaTreeVo o1, AreaTreeVo o2) {
            return o1.getSysAreaSeq() - o2.getSysAreaSeq();
        }
    };

    public JSONResult getAreaInfoByUuid(String sysAreaUuid) {
        AreaDO areaDO = areaMapper.areaDo(sysAreaUuid);
        if (null == areaDO) {
            return JSONResult.errorMap("查询区域信息异常");
        }
        AreaVo areaVo = new AreaVo();
        BeanUtils.copyProperties(areaDO, areaVo);
        return JSONResult.ok(areaVo);
    }

    public JSONResult delArea(AreaDReq areaDReq, RedisUser redisUser) {
        AreaDO areaDO = areaMapper.areaDo(areaDReq.getSysAreaUuid());

        if (null == areaDO) {
            return JSONResult.errorMap("待删除的区域不存在，您无法删除");
        }
        if (checkExist1(areaDO.getId(), areaDO.getId(), areaDO.getSysAreaName())) {
            return JSONResult.errorMap("该区域下还有子级区域，您无法删除");
        }

        AreaDO areaDO1 = new AreaDO();
        areaDO1.setId(areaDO.getId());
        areaDO1.setSysAreaUuid(areaDReq.getSysAreaUuid());
        areaDO1.setDelFlag("1");
        areaDO1.setUpdateTime(DateUtils.getCurrentDateTime());
        areaDO1.setUpdateBy(redisUser.getSysUserName());
        areaDAO.updateArea(areaDO1);
        return JSONResult.ok("删除区域成功");
    }

    public JSONResult del(AreaDReq areaDReq, RedisUser redisUser) {
        AreaDO areaDO = areaMapper.areaDo(areaDReq.getSysAreaUuid());

        if (null == areaDO) {
            return JSONResult.errorMap("待删除的区域不存在，您无法删除");
        }
        if (checkExist1(null, areaDO.getId(), null)) {
            return JSONResult.errorMap("该区域下还有子级区域，您无法删除");
        }

        areaDAO.deleteByUuid(areaDO.getSysAreaUuid());
        return JSONResult.ok("删除区域成功");
    }

}




