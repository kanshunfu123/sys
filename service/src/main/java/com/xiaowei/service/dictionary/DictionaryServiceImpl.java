package com.xiaowei.service.dictionary;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.DictionaryAddReq;
import com.xiaowei.common.req.DictionaryReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.LevelUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.common.vo.DictionaryTreeVo;
import com.xiaowei.common.vo.DictionaryVO;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.DictionaryDataDAO;
import com.xiaowei.mana.mapper.dao.ParameterConfigurationDAO;
import com.xiaowei.mana.mapper.dataobject.DictionaryDataDO;
import com.xiaowei.mana.mapper.dataobject.ParameterConfigurationDO;
import com.xiaowei.mana.mapper.mapper.DictionaryMapper;
import com.xiaowei.mana.mapper.resultmap.DictionaryTreeResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

import com.google.common.collect.Multimap;

/**
 * Created by kanshunfu on 2019/01/07.
 */
@Service
@Slf4j
public class DictionaryServiceImpl implements DictionaryService {
    @Autowired
    private DictionaryMapper dictionaryMapper;
    @Autowired
    private DictionaryDataDAO dictionaryDataDAO;
    @Autowired
    private ParameterConfigurationDAO parameterConfigurationDAO;

    @Override
    public JSONResult dictionaryTreeList() {


        //递归树最终结果集
        //将deptList拷贝到dtoList
        List<DictionaryTreeVo> dictionaryTreeVo = new ArrayList<>(0);
        //得到所有字典
        List<DictionaryTreeResult> dicVos = dictionaryMapper.DictionaryTree();
        if (dicVos != null && dicVos.size() != 0) {
            dicVos.forEach(dicEntity -> {
                //去除被删除的字典
                if (dicEntity.getDelFlag().equals("0")) {
                    DictionaryTreeVo treeRes = new DictionaryTreeVo();
                    BeanUtils.copyProperties(dicEntity, treeRes);
                    dictionaryTreeVo.add(treeRes);
                }
            });
        }

        return JSONResult.ok(dicMenuToTree(dictionaryTreeVo));

    }

    @Override
    public JSONResult interDictionary(DictionaryAddReq dictionaryAddReq, RedisUser redisUser) {
        DictionaryDataDO dictionaryDataDO = new DictionaryDataDO();

        BeanUtils.copyProperties(dictionaryAddReq, dictionaryDataDO);
        int count = dictionaryDataDAO.getDictionaryCodeName(dictionaryDataDO);
        if (count > 0) {
            return JSONResult.errorMap("字典代码名称重复");
        }
        int count2=dictionaryDataDAO.getDictionaryCodeValue(dictionaryDataDO);
        if (count2 > 0) {
            return JSONResult.errorMap("字典代码值重复");
        }

        if (dictionaryDataDO.getParentId() == null) {
            dictionaryDataDO.setParentId(0L);
        } else {
            DictionaryDataDO dictionaryDataDO1 = new DictionaryDataDO();
            dictionaryDataDO1.setId(dictionaryDataDO.getParentId());
            int count1 = dictionaryDataDAO.getDictionaryId(dictionaryDataDO1);
            if (count1 < 0) {
                return JSONResult.errorMap("上级字典id不存在");
            }
        }

        if (checkExist(dictionaryAddReq.getCodeName(), dictionaryAddReq.getParentId())) {
         return   JSONResult.errorMap("同一层级下存在相同名称的字典");
        }
        //根据uuid计算  level层级
        String level = getLevel(dictionaryDataDO.getParentId());
        String newLevel = LevelUtil.calculateLevelString(level, dictionaryDataDO.getParentId());
        dictionaryDataDO.setCodeLevel(newLevel);
        dictionaryDataDO.setDelFlag("0");
        dictionaryDataDO.setCreateTime(DateUtils.getCurrentDateTime());
        dictionaryDataDO.setCreateBy(redisUser.getSysUserName());
        dictionaryDataDO.setUpdateTime(DateUtils.getCurrentDateTime());
        dictionaryDataDO.setUpdateBy(redisUser.getSysUserName());
        dictionaryDataDO.setSysDictionaryUuid(StrUtil.genUUID());
        dictionaryDataDAO.saveDictionary(dictionaryDataDO);
        return JSONResult.ok("添加成功", dictionaryDataDO.getSysDictionaryUuid());
    }

    @Override
    public JSONResult getDictionaryInfoByUuid(String sysDictionaryUuid) {
        DictionaryDataDO getDictionaryInfoByUuid = dictionaryMapper.dictionaryDataDO(sysDictionaryUuid);
        if (null == getDictionaryInfoByUuid) {
            return JSONResult.errorMap("查询字典架构信息异常");
        }
        DictionaryVO dictionaryVO = new DictionaryVO();
        BeanUtils.copyProperties(getDictionaryInfoByUuid, dictionaryVO);
        return JSONResult.ok(dictionaryVO);
    }

    @Override
    public JSONResult updateDictionary(DictionaryReq dictionaryReq, RedisUser redisUser) {
        DictionaryDataDO before = dictionaryMapper.dictionaryDataDO(dictionaryReq.getSysDictionaryUuid());
        if (before == null) {
            return JSONResult.errorMap("待更新的字典不存在");
        }
        if (!before.getCodeValue().equals(dictionaryReq.getCodeValue())) {
            return JSONResult.errorMap("字典代码值不能修改");
        }

        DictionaryDataDO after = new DictionaryDataDO();
        BeanUtils.copyProperties(dictionaryReq, after);
        String level = null;
        DictionaryDataDO dictionaryDataDO = dictionaryDataDAO.getLevelByParentId(dictionaryReq.getParentId());
        Long fatherId = 0L;
        if (dictionaryDataDO != null) {
            level = dictionaryDataDO.getCodeLevel();
            fatherId = dictionaryDataDO.getId();
        }
        String newLevel = LevelUtil.calculateLevelString(level, fatherId);
        after.setCodeLevel(newLevel);
        //是否需要更新子级字典
        updateWithChild(before, after, fatherId);

        return JSONResult.ok("更新字典成功");
    }

    @Override
    public JSONResult delDictionary(DictionaryReq dictionaryReq) {
        DictionaryDataDO dictionaryDataDO = dictionaryMapper.dictionaryDataDO(dictionaryReq.getSysDictionaryUuid());

        if (null == dictionaryDataDO) {
            return JSONResult.errorMap("待删除的字典不存在，您无法删除");
        } else {
            Long filedValue = dictionaryDataDO.getCodeValue();
            ParameterConfigurationDO parameterConfigurationDO = new ParameterConfigurationDO();
            parameterConfigurationDO.setDictionaryId(filedValue);
            int count2 = parameterConfigurationDAO.getCountFieldValue(parameterConfigurationDO);
            if (count2 > 0) {
                return   JSONResult.errorMap("删除字典时，关联参数表有数据时，不能删除");
            }
            if (checkExist(null, dictionaryDataDO.getId())) {
                return JSONResult.errorMap("该字典下还有子级字典，您无法删除");
            }
            DictionaryDataDO sysDictionaryDataDO1 = new DictionaryDataDO();
            sysDictionaryDataDO1.setSysDictionaryUuid(dictionaryReq.getSysDictionaryUuid());
            sysDictionaryDataDO1.setDelFlag("1");
            sysDictionaryDataDO1.setUpdateTime(DateUtil.getDateTime());
            sysDictionaryDataDO1.setUpdateBy("sys");
            dictionaryDataDAO.updateDictionary(sysDictionaryDataDO1);
            return JSONResult.ok("删除字典成功");
        }
    }


    @Override
    public JSONResult getDictionaryInfoByParentId(Long parentId) {
        List<DictionaryVO> dictionaryVOS = new ArrayList<DictionaryVO>();
        List<DictionaryDataDO> dictionaryDataDOS = dictionaryDataDAO.getDictionaryInfoByParentId(parentId);
        if (!CollectionUtils.isEmpty(dictionaryDataDOS)) {
            for (int i = 0; i < dictionaryDataDOS.size(); i++) {
                DictionaryVO dictionaryVO = new DictionaryVO();
                dictionaryVO.setCodeName(dictionaryDataDOS.get(i).getCodeName());
                dictionaryVOS.add(dictionaryVO);
            }
            return JSONResult.ok(dictionaryVOS);
        }
        return JSONResult.errorMap("待查询的父级id不存在");
    }

    private void updateWithChild(DictionaryDataDO before, DictionaryDataDO after, Long fatherId) {
        //更新过后的字典level
        String newLevelPrefix = after.getCodeLevel();
        //更新之前的字典level
        String oldLevelPrefix = before.getCodeLevel();
        String newLevel = LevelUtil.calculateLevelString(oldLevelPrefix, before.getId());
        if (!newLevelPrefix.equals(oldLevelPrefix)) {
            List<DictionaryDataDO> dicList = dictionaryDataDAO.getChildDictionaryListByLevel(newLevel);
            if (!CollectionUtils.isEmpty(dicList)) {
                for (DictionaryDataDO dict : dicList) {
                    String level = dict.getCodeLevel();
                    if (level.indexOf(oldLevelPrefix) == 0) {
                        level = newLevelPrefix + level.substring(oldLevelPrefix.length());
                        dict.setCodeLevel(level);
                        dict.setParentId(fatherId);
                    }
                }
                dictionaryDataDAO.updatebatchUpdateLevel(dicList);
            }
        }
        dictionaryDataDAO.updateDictionary(after);
    }

    private String getLevel(Long parentId) {
        if (parentId == null) {
            return null;
        }
        DictionaryDataDO dictionaryDataDO = dictionaryDataDAO.getLevelByParentId(parentId);
        if (dictionaryDataDO == null) {
            return null;
        } else {
            return dictionaryDataDO.getCodeLevel();
        }
    }


    private boolean checkExist(String codeName, long parentId) {
        return dictionaryDataDAO.getBycodeNameAndByparentId(parentId, codeName) > 0;
    }

    public List<DictionaryTreeVo> dicMenuToTree(List<DictionaryTreeVo> resList) {
        //集合为空直接返回
        if (CollectionUtils.isEmpty(resList)) {
            return Lists.newArrayList();
        }
        //可以放相同的key，比普通的map高级
        //把当前的tree以level为key 相同level的字典作为value，放到map里
        // level->[dictionary1,dictionary2,....]
        Multimap<String, DictionaryTreeVo> levelDeptMap = ArrayListMultimap.create();
        //用来保存第一层级的树,同时也是最终的字典树
        List<DictionaryTreeVo> rootList = Lists.newArrayList();
        for (DictionaryTreeVo treeRes : resList) {
            levelDeptMap.put(treeRes.getCodeLevel(), treeRes);
            if (LevelUtil.ROOT.equals(treeRes.getCodeLevel())) {
                rootList.add(treeRes);
            }
        }
        //按照 serial_number 从小到大 对字典树进行排序
        Collections.sort(rootList, new Comparator<DictionaryTreeVo>() {
            @Override
            public int compare(DictionaryTreeVo o1, DictionaryTreeVo o2) {
                return o1.getSysDictionarySeq() - o2.getSysDictionarySeq();
            }
        });
        //递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    private void transformDeptTree(List<DictionaryTreeVo> dicLevelList, String level, Multimap<String, DictionaryTreeVo> levelDeptMap) {
        int size = dicLevelList.size();
        for (int i = 0; i < size; i++) {
            //遍历该层的每个元素
            DictionaryTreeVo dicMenuDto = dicLevelList.get(i);
            //处理当前层级的数据
            String nexeLevel = LevelUtil.calculateLevelString(level, dicMenuDto.getId());
            //处理下一层
            List<DictionaryTreeVo> tempDeptList = (List<DictionaryTreeVo>) levelDeptMap.get(nexeLevel);
            if (!CollectionUtils.isEmpty(tempDeptList)) {
                //排序
                Collections.sort(tempDeptList, dicSerialNumberComparator);
                //设置下一层字典
                dicMenuDto.setChildren(tempDeptList);
                //进入到下一层处理
                transformDeptTree(tempDeptList, nexeLevel, levelDeptMap);
            }
        }
    }

    public Comparator<DictionaryTreeVo> dicSerialNumberComparator = new Comparator<DictionaryTreeVo>() {
        @Override
        public int compare(DictionaryTreeVo o1, DictionaryTreeVo o2) {
            return o1.getSysDictionarySeq() - o2.getSysDictionarySeq();
        }
    };
}
