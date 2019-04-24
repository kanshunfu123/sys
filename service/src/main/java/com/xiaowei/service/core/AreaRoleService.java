package com.xiaowei.service.core;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.*;
import com.xiaowei.common.AddRoleAreaReq;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.area.AreaAclReq;
import com.xiaowei.common.req.area.AreaAclReq_v2;
import com.xiaowei.common.req.area.MapZoom;
import com.xiaowei.common.res.homeMapAcl.AreaUseingMap;
import com.xiaowei.common.res.homeMapAcl.AreaUseingMap_two;
import com.xiaowei.common.res.homeMapAcl.DeviceList;
import com.xiaowei.common.res.homeMapAcl.HomeMapAclRes;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.LevelUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.vo.AreaTreeVo_acl;
import com.xiaowei.mana.mapper.dao.AreaDAO;
import com.xiaowei.mana.mapper.dao.RoleAreaDAO;
import com.xiaowei.mana.mapper.dao.VillageDAO;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.AreaMapper;
import com.xiaowei.mana.mapper.mapper.RoleMapper;
import com.xiaowei.mana.mapper.mapper.RoleUserMapper;
import com.xiaowei.mana.mapper.mapper.areatodevice.AreaAclMapper;
import com.xiaowei.mana.mapper.resultmap.AreaTreeResult;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.PipedReader;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/2/14.
 */
@Service
@Slf4j
public class AreaRoleService {
    @Autowired
    private RoleAreaDAO roleAreaDAO;
    @Autowired
    private RoleMapper roleMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private AreaAclMapper areaAclMapper;
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private AreaMapper areaMapper;
    @Autowired
    private AreaDAO areaDAO;
    @Autowired
    private VillageDAO villageDAO;
//    private final String[] stringsaa = {"0", "1", "2", "3"};

    public List<DeviceList> showMapInfo(AreaAclReq_v2 areaAclReq_v2, RedisUser redisUser) {
        //0 地表水 1井盖 2生活用水 3电梯
        return third(areaAclReq_v2, redisUser);

    }

    private List<DeviceList> third(AreaAclReq_v2 areaAclReq_v2, RedisUser redisUser) {
        List<DeviceList> deviceLists = Lists.newArrayList();

        if (redisUser.getGroupId() != 1) {
            List<String> list = Lists.newArrayList();
            //0 地表水 1井盖 2生活用水 3电梯
            String s = areaAclMapper.secons(redisUser.getId());
            if (StringUtils.isNotBlank(s)) {
                String[] strings = s.split(",");
                for (String string : strings) {
                    if (areaAclReq_v2.getLevel() <= 2) {//0省 1市 2区 3街道  4 小区
                        if ("0".equals(string) || "1".equals(string) || "2".equals(string) || "3".equals(string)) {//0 地表水 1井盖 2生活用水 3电梯
                            list.add(string);
                        }
                    } else if (areaAclReq_v2.getLevel() == 3) {//0省 1市 2区 3街道  4 小区
                        if ("1".equals(string) || "2".equals(string) || "3".equals(string)) {//0 地表水 1井盖 2生活用水 3电梯
                            list.add(string);
                        }
                    } else if (areaAclReq_v2.getLevel() == 4) {//0省 1市 2区 3街道  4 小区
                        if ("2".equals(string) || "3".equals(string)) {//0 地表水 1井盖 2生活用水 3电梯
                            list.add(string);
                        }
                    }
                }
                list.forEach(s1 -> {
                    DeviceList deviceList = deviceType_common(s1, areaAclReq_v2, redisUser);
                    deviceLists.add(deviceList);
                });

            } else {
                return deviceLists;
            }
        } else {

            List<String> list = Lists.newArrayList();
            if (areaAclReq_v2.getLevel() <= 2) {//0省 1市 2区 3街道  4 小区
                list.add("0");//0 地表水 1井盖 2生活用水 3电梯
                list.add("1");
                list.add("2");
                list.add("3");
            } else if (areaAclReq_v2.getLevel().equals(3)) {
                list.add("1");
                list.add("2");
                list.add("3");
            } else if (areaAclReq_v2.getLevel().equals(4)) {
                list.add("2");
                list.add("3");
            }
            for (String string : list) {
                DeviceList deviceList = deviceType_admin(string, areaAclReq_v2, redisUser);
                deviceLists.add(deviceList);
            }
        }


        return deviceLists;
    }

    private DeviceList deviceType_admin(String deviceType, AreaAclReq_v2 areaAclReq_v2, RedisUser redisUser) {
        DeviceList deviceList = new DeviceList();
        //0 地表水 1井盖 2生活用水 3电梯
        switch (deviceType) {
            case "0":
                AreaAclMapDO_v2 rw = areaAclMapper.admin_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 0, areaAclReq_v2.getLevel(), -1);
                deviceList.setDeviceNum(rw.getDeviceNum());
                deviceList.setDeviceName("地表水");
                return deviceList;
            case "1":
                AreaAclMapDO_v2 mh = areaAclMapper.admin_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 1, areaAclReq_v2.getLevel(), 1);
                deviceList.setDeviceNum(mh.getDeviceNum());
                deviceList.setDeviceName("井盖");
                return deviceList;
            case "2":
                AreaAclMapDO_v2 cw = areaAclMapper.admin_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 2, areaAclReq_v2.getLevel(), 2);
                deviceList.setDeviceNum(cw.getDeviceNum());
                deviceList.setDeviceName("生活用水");
                return deviceList;
            case "3":
                AreaAclMapDO_v2 ec = areaAclMapper.admin_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 3, areaAclReq_v2.getLevel(), 3);
                deviceList.setDeviceNum(ec.getDeviceNum());
                deviceList.setDeviceName("电梯");
                return deviceList;
            default:
                return deviceList;
        }
    }

    private DeviceList deviceType_common(String deviceType, AreaAclReq_v2 areaAclReq_v2, RedisUser redisUser) {
        DeviceList deviceList = new DeviceList();
        //0 地表水 1井盖 2生活用水 3电梯
        switch (deviceType) {
            case "0":
                AreaAclMapDO_v2 rw = areaAclMapper.common_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 0, redisUser.getId());

                deviceList.setDeviceNum(rw.getDeviceNum());
                deviceList.setDeviceName("地表水");
                return deviceList;
            case "1":
                AreaAclMapDO_v2 mh = areaAclMapper.common_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 1, redisUser.getId());
                deviceList.setDeviceNum(mh.getDeviceNum());
                deviceList.setDeviceName("井盖");
                return deviceList;
            case "2":
                AreaAclMapDO_v2 cw = areaAclMapper.common_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 2, redisUser.getId());
                deviceList.setDeviceNum(cw.getDeviceNum());
                deviceList.setDeviceName("生活用水");
                return deviceList;
            case "3":
                AreaAclMapDO_v2 ec = areaAclMapper.common_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 3, redisUser.getId());
                deviceList.setDeviceNum(ec.getDeviceNum());
                deviceList.setDeviceName("电梯");
                return deviceList;
            default:
                return deviceList;
        }
    }

    public JSONResult areaAclTree(AddRoleAreaReq addRoleAreaReq, RedisUser redisUser) {
        RoleDO roleDO = roleMapper.getRoleByUUID(addRoleAreaReq.getRoleUuid());
        if (null == roleDO) {
            return JSONResult.errorMap("待查询的角色不存在");
        }
        List<VillageDO> villageDOSAll = villageDAO.getAll();
        List<VillageDO> villageDOS = villageDAO.getAllTwo(roleDO.getId(), addRoleAreaReq.getDeviceType());
        Multimap<Long, VillageDO> longVillageDOMultimap = ArrayListMultimap.create();
        if (!CollectionUtils.isEmpty(villageDOS)) {
            villageDOS.forEach(villageDO -> {
                longVillageDOMultimap.put(villageDO.getStreetId(), villageDO);
            });
        }
        List<Long> userRoleIdList = null;
        //redis-->根据用户id得到角色列表
        Object o = redisUtil.get(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + redisUser.getId());
        if (null != o) {
            userRoleIdList = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
            });
        } else {
            //根据用户id得到角色列表
            userRoleIdList = roleUserMapper.getRolesByUserId(redisUser.getId());
        }
        //当前角色所拥有的区域权限，按场景来
        List<Long> longs = areaIds(Arrays.asList(roleDO.getId()), addRoleAreaReq.getDeviceType());
        //当前登录用户所拥有的角色所拥有的区域权限，按场景来
        List<Long> longsTwo = areaIds(userRoleIdList, addRoleAreaReq.getDeviceType());
        Set<Long> userAclIdSet = longs.stream().map(sysAcl -> sysAcl).collect(Collectors.toSet());
        Set<Long> roleAclIdSet = longsTwo.stream().map(sysAcl -> sysAcl).collect(Collectors.toSet());
        //递归树最终结果集
        //将deptList拷贝到dtoList
        List<AreaTreeVo_acl> areaTreeVos = new ArrayList<>(0);
        //得到所有字典
        List<AreaTreeResult> dicVos = areaMapper.AreaTree();
        if (dicVos != null && dicVos.size() != 0) {
            dicVos.forEach(dicEntity -> {
                //去除被删除的字典
                if (dicEntity.getDelFlag().equals("0")) {
                    AreaTreeVo_acl treeRes = new AreaTreeVo_acl();
                    BeanUtils.copyProperties(dicEntity, treeRes);
                    treeRes.setId(dicEntity.getSysAreaUuid());
                    treeRes.setPryId(dicEntity.getId());
                    if (!CollectionUtils.isEmpty(userAclIdSet)) {
                        if (userAclIdSet.contains(dicEntity.getId())) {
                            treeRes.setChecked(true);
                            treeRes.setHasAcl(true);
                        }

                    }
                    if (!CollectionUtils.isEmpty(roleAclIdSet)) {
                        if (roleAclIdSet.contains(dicEntity.getId())) {
                            treeRes.setHasAcl(true);
                        }
                    }
                    if (redisUser.getGroupId() == 1) {
                        treeRes.setHasAcl(true);
                    }

                    if (countStr(dicEntity.getSysAreaLevel(), ".") == 3) {
                        List<VillageDO> villageDOS1 = (List<VillageDO>) longVillageDOMultimap.get(dicEntity.getId());
//                        if (!CollectionUtils.isEmpty(villageDOS1)) {
                        List<AreaTreeVo_acl> children = Lists.newArrayList();
                        villageDOSAll.forEach(villageDO -> {
                            log.info(villageDO.getStreetId() + "==" + dicEntity.getId());
                            if (!CollectionUtils.isEmpty(villageDOS1) && villageDO.getStreetId().equals(dicEntity.getId())) {
                                AreaTreeVo_acl areaTreeVo_acl = new AreaTreeVo_acl();
                                areaTreeVo_acl.setChecked(true);
                                areaTreeVo_acl.setHasAcl(true);
                                areaTreeVo_acl.setTrueId(villageDO.getId());
                                areaTreeVo_acl.setId(villageDO.getVillageUuid());
                                areaTreeVo_acl.setSysAreaName(villageDO.getVillageName());
                                areaTreeVo_acl.setPryId(villageDO.getStreetId());
                                children.add(areaTreeVo_acl);
                            } else if (villageDO.getStreetId().equals(dicEntity.getId())) {
                                AreaTreeVo_acl areaTreeVo_acl = new AreaTreeVo_acl();
                                areaTreeVo_acl.setChecked(false);
                                areaTreeVo_acl.setHasAcl(false);
                                areaTreeVo_acl.setTrueId(villageDO.getId());
                                areaTreeVo_acl.setId(villageDO.getVillageUuid());
                                areaTreeVo_acl.setSysAreaName(villageDO.getVillageName());
                                areaTreeVo_acl.setPryId(villageDO.getStreetId());
                                children.add(areaTreeVo_acl);
                            }
                        });
                        treeRes.setChildren(children);
//                        }
                    }
                    areaTreeVos.add(treeRes);
                }
            });
        }

        return JSONResult.ok(dicMenuToTree(areaTreeVos));
    }

    private List<Long> areaIds(List<Long> roleId, String deviceType) {
        return areaAclMapper.getRoleAclArea(roleId, deviceType);
    }

    public List<AreaTreeVo_acl> dicMenuToTree(List<AreaTreeVo_acl> resList) {
        //集合为空直接返回
        if (org.springframework.util.CollectionUtils.isEmpty(resList)) {
            return Lists.newArrayList();
        }
        //可以放相同的key，比普通的map高级
        //把当前的tree以level为key 相同level的字典作为value，放到map里
        // level->[dictionary1,dictionary2,....]
        Multimap<String, AreaTreeVo_acl> levelDeptMap = ArrayListMultimap.create();
        //用来保存第一层级的树,同时也是最终的字典树
        List<AreaTreeVo_acl> rootList = Lists.newArrayList();
        for (AreaTreeVo_acl treeRes : resList) {
            levelDeptMap.put(treeRes.getSysAreaLevel(), treeRes);
            if (LevelUtil.ROOT.equals(treeRes.getSysAreaLevel())) {
                rootList.add(treeRes);
            }
        }
        //按照 serial_number 从小到大 对字典树进行排序
        Collections.sort(rootList, new Comparator<AreaTreeVo_acl>() {
            @Override
            public int compare(AreaTreeVo_acl o1, AreaTreeVo_acl o2) {
                return o1.getSysAreaSeq() - o2.getSysAreaSeq();
            }
        });
        //递归生成树
        transformDeptTree(rootList, LevelUtil.ROOT, levelDeptMap);
        return rootList;
    }

    private void transformDeptTree(List<AreaTreeVo_acl> dicLevelList, String level, Multimap<String, AreaTreeVo_acl> levelDeptMap) {
        int size = dicLevelList.size();
        for (int i = 0; i < size; i++) {
            //遍历该层的每个元素
            AreaTreeVo_acl dicMenuDto = dicLevelList.get(i);
            //处理当前层级的数据
            String nexeLevel = LevelUtil.calculateLevelString(level, dicMenuDto.getPryId());
            //处理下一层
            List<AreaTreeVo_acl> tempDeptList = (List<AreaTreeVo_acl>) levelDeptMap.get(nexeLevel);
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

    /**
     * @param str     原字符串
     * @param sToFind 需要查找的字符串
     * @return 返回在原字符串中sToFind出现的次数
     */
    private int countStr(String str, String sToFind) {
        int num = 0;
        while (str.contains(sToFind)) {
            str = str.substring(str.indexOf(sToFind) + sToFind.length());
            num++;
        }
        return num;
    }

    public Comparator<AreaTreeVo_acl> dicSerialNumberComparator = new Comparator<AreaTreeVo_acl>() {
        @Override
        public int compare(AreaTreeVo_acl o1, AreaTreeVo_acl o2) {
            return o1.getSysAreaSeq() - o2.getSysAreaSeq();
        }
    };


    public List<AreaDOEquals> mapZoom(MapZoom mapZoom, RedisUser redisUser) {
        AreaAclReq areaAclReq = new AreaAclReq();
        BeanUtils.copyProperties(mapZoom, areaAclReq);
        MapZoomVO mapZoomVO = new MapZoomVO();
        BeanUtils.copyProperties(mapZoom, mapZoomVO);
        List<AreaDOEquals> areaDOS = areaAclTotal(areaAclReq, redisUser, mapZoomVO);
        if (CollectionUtils.isEmpty(areaDOS)) {
            return Lists.newArrayList();
        }
        return areaDOS;
    }

    /*public List<HomeMapAclRes> areaAcl_v3(AreaAclReq_v2 areaAclReq, RedisUser redisUser) {
        MapZoom mapZoom = new MapZoom();
        BeanUtils.copyProperties(areaAclReq, mapZoom);
        List<AreaDO> areaDOS = mapZoom(mapZoom, redisUser);
        if (CollectionUtils.isEmpty(areaDOS)) {
            return Lists.newArrayList();

        }
        Set<Long> villageId= Sets.newHashSet();
        List<AreaAclMapDO_v2> allList = Lists.newArrayList();
        areaDOS.forEach(areaDO -> {
            if (countStr(areaDO.getSysAreaLevel(), ".") == 3) {
                villageId.add(areaDO.getId());
            }
            List<AreaAclMapDO_v2> areaAclMapDO_v2s = areaAcl_v2(areaAclReq, redisUser);
            allList.addAll(areaAclMapDO_v2s);
        });
        if (!areaAclReq.getLevel().equals(4)){

        }



    }*/

    /**
     * 地区使用分布图
     *
     * @param areaAclReq
     * @param redisUser
     * @return
     */
    public List<AreaUseingMap> areaUseingMap(AreaAclReq_v2 areaAclReq, RedisUser redisUser) {

        if (redisUser.getGroupId() != 1) {
            return com_areaUseingMap(areaAclReq, redisUser);
        } else {
            return admin_areaUsing(areaAclReq, redisUser);
        }

    }

    public List<AreaUseingMap> admin_areaUsing(AreaAclReq_v2 areaAclReq, RedisUser redisUser) {
        List<AreaAclMapDO_v2> all = Lists.newArrayList();
        String[] strings = "0,1,2,3".split(",");
        for (String string : strings) {
            if (areaAclReq.getParentId().equals(0L)) {
                areaAclReq.setLevel(-1);
            }
            List<AreaAclMapDO_v2> areaAclMapDO_v2s = areaAclMapper.admin_areaUsing(string, redisUser.getId(), String.valueOf(areaAclReq.getLevel()), areaAclReq.getParentId());
            all.addAll(areaAclMapDO_v2s);
        }
        Multimap<Long, AreaAclMapDO_v2> multimap = ArrayListMultimap.create();
        //全国
        Set<AreaUseingMap_two> set = Sets.newHashSet();
        //层级 0省 1市 2区 3街道  4 小区
        if (areaAclReq.getParentId().equals(0L)) {
            all.forEach(v2 -> {
                AreaUseingMap_two areaAclMapDO_v2 = new AreaUseingMap_two();
//                areaAclMapDO_v2.setArea(v2.getArea());
//                areaAclMapDO_v2.setAreaId(v2.getAreaId());
//                areaAclMapDO_v2.setCity(v2.getCity());
//                areaAclMapDO_v2.setCityId(v2.getCityId());
                areaAclMapDO_v2.setAreaName(v2.getProvince());
                areaAclMapDO_v2.setAreaId(v2.getProvinceId());
                multimap.put(v2.getProvinceId(), v2);
                set.add(areaAclMapDO_v2);
            });
        } else {
            if (areaAclReq.getLevel().equals(1)) {//当前省下的所有地区
                all.forEach(v2 -> {
                    AreaUseingMap_two areaAclMapDO_v2 = new AreaUseingMap_two();
                    areaAclMapDO_v2.setAreaName(v2.getCity());
                    areaAclMapDO_v2.setAreaId(v2.getCityId());
                    multimap.put(v2.getCityId(), v2);
                    set.add(areaAclMapDO_v2);
                });
            } else if (areaAclReq.getLevel().equals(2)) {//当前市下的所有地区
                all.forEach(v2 -> {
                    AreaUseingMap_two areaAclMapDO_v2 = new AreaUseingMap_two();
                    areaAclMapDO_v2.setAreaName(v2.getArea());
                    areaAclMapDO_v2.setAreaId(v2.getAreaId());
                    multimap.put(v2.getAreaId(), v2);
                    set.add(areaAclMapDO_v2);
                });
            }
        }
        List<AreaUseingMap> areaUseingMaps = Lists.newArrayList();
        set.forEach(v2 -> {
            AreaUseingMap areaUseingMap = new AreaUseingMap();
            areaUseingMap.setAreaName(v2.getAreaName());
            List<AreaAclMapDO_v2> areaAclMapDO_v2s = (List<AreaAclMapDO_v2>) multimap.get(v2.getAreaId());
            if (CollectionUtils.isEmpty(areaAclMapDO_v2s)) {
                areaUseingMap.setDeviceNum(0);
            } else {
                areaUseingMap.setDeviceNum(areaAclMapDO_v2s.size());

            }
            areaUseingMaps.add(areaUseingMap);
        });
        return areaUseingMaps;
    }

    private List<AreaUseingMap> com_areaUseingMap(AreaAclReq_v2 areaAclReq, RedisUser redisUser) {
        List<AreaAclMapDO_v2> all = Lists.newArrayList();
        //0 地表水 1井盖 2生活用水 3电梯
        String s = areaAclMapper.secons(redisUser.getId());
        if (StringUtils.isNotBlank(s)) {
            String[] strings = s.split(",");
            for (String string : strings) {
                if (areaAclReq.getParentId().equals(0L)) {
                    areaAclReq.setLevel(-1);
                }
                List<AreaAclMapDO_v2> areaAclMapDO_v2s = areaAclMapper.com_areaUsing_v2(string, redisUser.getId(), String.valueOf(areaAclReq.getLevel()), areaAclReq.getParentId());
                all.addAll(areaAclMapDO_v2s);
            }
        }
        Multimap<Long, AreaAclMapDO_v2> multimap = ArrayListMultimap.create();
        //全国
        Set<AreaUseingMap_two> set = Sets.newHashSet();
        //层级 0省 1市 2区 3街道  4 小区
        if (areaAclReq.getParentId().equals(0L)) {
            all.forEach(v2 -> {
                AreaUseingMap_two areaAclMapDO_v2 = new AreaUseingMap_two();
//                areaAclMapDO_v2.setArea(v2.getArea());
//                areaAclMapDO_v2.setAreaId(v2.getAreaId());
//                areaAclMapDO_v2.setCity(v2.getCity());
//                areaAclMapDO_v2.setCityId(v2.getCityId());
                areaAclMapDO_v2.setAreaName(v2.getProvince());
                areaAclMapDO_v2.setAreaId(v2.getProvinceId());
                multimap.put(v2.getProvinceId(), v2);
                set.add(areaAclMapDO_v2);
            });
        } else {
            if (areaAclReq.getLevel().equals(1)) {//当前省下的所有地区
                all.forEach(v2 -> {
                    AreaUseingMap_two areaAclMapDO_v2 = new AreaUseingMap_two();
                    areaAclMapDO_v2.setAreaName(v2.getCity());
                    areaAclMapDO_v2.setAreaId(v2.getCityId());
                    multimap.put(v2.getCityId(), v2);
                    set.add(areaAclMapDO_v2);
                });
            } else if (areaAclReq.getLevel().equals(2)) {//当前市下的所有地区
                all.forEach(v2 -> {
                    AreaUseingMap_two areaAclMapDO_v2 = new AreaUseingMap_two();
                    areaAclMapDO_v2.setAreaName(v2.getArea());
                    areaAclMapDO_v2.setAreaId(v2.getAreaId());
                    multimap.put(v2.getAreaId(), v2);
                    set.add(areaAclMapDO_v2);
                });
            }
        }
        List<AreaUseingMap> areaUseingMaps = Lists.newArrayList();
        set.forEach(v2 -> {
            AreaUseingMap areaUseingMap = new AreaUseingMap();
            areaUseingMap.setAreaName(v2.getAreaName());
            List<AreaAclMapDO_v2> areaAclMapDO_v2s = (List<AreaAclMapDO_v2>) multimap.get(v2.getAreaId());
            if (CollectionUtils.isEmpty(areaAclMapDO_v2s)) {
                areaUseingMap.setDeviceNum(0);
            } else {
                areaUseingMap.setDeviceNum(areaAclMapDO_v2s.size());

            }
            areaUseingMaps.add(areaUseingMap);
        });
        return areaUseingMaps;
    }

    private List<AreaUseingMap> areaUseingMaps(List<AreaAclMapDO_v2> areaAclMapDO_v2s, AreaAclReq_v2 areaAclReq) {
        List<AreaUseingMap> areaUseingMaps = Lists.newArrayList();
        if (CollectionUtils.isEmpty(areaAclMapDO_v2s)) {
            return Lists.newArrayList();
        }
        List<AreaAclMapDO_v2> newListaaa = areaAclMapDO_v2s.stream().distinct().collect(Collectors.toList());
        if (countStr(areaAclReq.getAreaLevel(), ".") == 0) {
            return sheng(areaAclMapDO_v2s, areaAclReq);
        } else if (countStr(areaAclReq.getAreaLevel(), ".") == 1) {
            return shi(areaAclMapDO_v2s, areaAclReq);
        }

        return null;
    }

    //前台给0.1 市
    private List<AreaUseingMap> shi(List<AreaAclMapDO_v2> newListaaa, AreaAclReq_v2 areaAclReq) {
        //0 得到市
        Set<AreaAclMapDO_v2> first = Sets.newHashSet();

        newListaaa.forEach(v2 -> {
            if (v2.getAreaLevel().equals(areaAclReq.getAreaLevel())) {
                v2.setDeviceNum(0);
                first.add(v2);
            } else {
                v2.setDeviceNum(0);
            }
        });
        //区
        Set<AreaAclMapDO_v2> two = Sets.newHashSet();

        first.forEach(v2 -> {
            newListaaa.forEach(v21 -> {
                if (v21.getAreaLevel().equals(v2.getAreaLevel() + "." + v2.getAreaId())) {
                    v2.setDeviceNum(0);
                    v21.setDeviceNum(0);
                    two.add(v21);
                }
            });
        });

        two.forEach(v2 -> {
            newListaaa.forEach(v21 -> {
                if (v21.getAreaLevel().contains(v2.getAreaId() + "." + v2.getAreaId() + ".")) {
                    v2.setDeviceNum(v2.getDeviceNum() + 1);
                }
            });
        });
        List<AreaUseingMap> list = Lists.newArrayList();

        return list;
    }

    //前台给0
    private List<AreaUseingMap> sheng(List<AreaAclMapDO_v2> newListaaa, AreaAclReq_v2 areaAclReq) {
        //0 得到全国
        Set<AreaAclMapDO_v2> sheng = Sets.newHashSet();
        //市
        Set<AreaAclMapDO_v2> shi = Sets.newHashSet();
        //区
        Set<AreaAclMapDO_v2> qu = Sets.newHashSet();

        newListaaa.forEach(v2 -> {
            if (v2.getAreaLevel().equals(areaAclReq.getAreaLevel())) {
                v2.setDeviceNum(0);
                sheng.add(v2);
            } else {
                v2.setDeviceNum(0);
            }
        });
        sheng.forEach(v2 -> {
            newListaaa.forEach(v21 -> {
                if (v21.getAreaLevel().equals(v2.getAreaLevel() + "." + v2.getAreaId())) {
                    v2.setDeviceNum(0);
                    v21.setDeviceNum(0);
                    shi.add(v21);
                }
            });
        });
        shi.forEach(v2 -> {
            newListaaa.forEach(v21 -> {
                if (v21.getAreaLevel().contains(v2.getAreaLevel() + "." + v2.getAreaId())) {
                    v2.setDeviceNum(0);
                    qu.add(v21);
                }
            });
        });
        qu.forEach(v2 -> {
            newListaaa.forEach(v21 -> {
                if (v21.getAreaLevel().contains(v2.getAreaId() + "." + v2.getAreaId() + ".")) {
                    v2.setDeviceNum(v2.getDeviceNum() + 1);
                }
            });
        });


        List<AreaUseingMap> list = Lists.newArrayList();

        shi.forEach(v2 -> {
            qu.forEach(v21 -> {
                if (v21.getAreaLevel().contains(v2.getAreaLevel() + "." + v2.getAreaId())) {
                    AreaUseingMap areaUseingMap = new AreaUseingMap();
                    areaUseingMap.setAreaName(v21.getAreaName());
                    areaUseingMap.setDeviceNum(v21.getDeviceNum());
                    list.add(areaUseingMap);
                }
            });
        });


        return list;
    }

    /**
     * 地区使用分布图
     *
     * @param areaAclReq
     * @param redisUser
     * @return
     */
    /*public List<AreaUseingMap> areaUseingMap(AreaAclReq_v2 areaAclReq, RedisUser redisUser) {
        List<HomeMapAclRes> list =list(areaAcl_v2(areaAclReq, redisUser)) ;
        if (CollectionUtils.isEmpty(list)) {
            return Lists.newArrayList();
        }
        List<AreaUseingMap> areaUseingMaps = Lists.newArrayList();
        list.forEach(homeMapAclRes -> {
            AreaUseingMap areaUseingMap = new AreaUseingMap();
            areaUseingMap.setAreaName(homeMapAclRes.getAreaName());
            List<DeviceList> deviceInfos = homeMapAclRes.getDeviceInfos();
            if (CollectionUtils.isEmpty(deviceInfos)) {
                areaUseingMap.setDeviceNum(0);
            }
            Integer count = 0;
            for (DeviceList deviceInfo : deviceInfos) {
                count += deviceInfo.getDeviceNum();
            }
            areaUseingMap.setDeviceNum(count);
            areaUseingMaps.add(areaUseingMap);
        });
        return areaUseingMaps;
    }*/
    public List<AreaDO> areaAcl_v3(AreaAclReq_v2 areaAclReq, RedisUser redisUser) {
        if (redisUser.getGroupId() == 1) {
            List<AreaDO> areaDOS = areaDAO.adminAreaAL(areaAclReq.getParentId());
            return areaDOS;
        }
        List<AreaDO> areaDOS = areaDAO.commonAreaAL(areaAclReq.getParentId());
        return areaDOS;
    }

    /**
     * 地图每一个层级区域权限
     *
     * @param areaAclReq
     * @param redisUser
     * @return
     */
    public List<AreaAclMapDO_v2> areaAcl_v2(AreaAclReq_v2 areaAclReq, RedisUser redisUser) {
        MapZoomVO mapZoomVO = new MapZoomVO();
        BeanUtils.copyProperties(areaAclReq, mapZoomVO);
        List<AreaAclMapDO_v2> areaList = Lists.newArrayList();
        final List<AreaAclMapDO_v2> areaList_two = Lists.newArrayList();
        if (redisUser.getGroupId() != 1) {
            if (areaAclReq.getLevel() != 4) {
                areaList = areaAclMapper.v3_commonListMapAcl(areaAclReq.getLevel(), areaAclReq.getParentId(), redisUser.getId(), mapZoomVO);
            } else {
                areaList = areaAclMapper.v3_commonListMapAcl_two(areaAclReq.getLevel(), areaAclReq.getParentId(), redisUser.getId(), mapZoomVO);
            }
        } else {
            if (areaAclReq.getLevel() != 4) {
                //0 地表水 1井盖 2生活用水 3电梯
                List<AreaAclMapDO_v2> rw = areaAclMapper.v3_adminListMapAcl(null, "0", areaAclReq.getLevel(), areaAclReq.getParentId(), null, mapZoomVO);
                List<AreaAclMapDO_v2> mh = areaAclMapper.v3_adminListMapAcl(null, "1", areaAclReq.getLevel(), areaAclReq.getParentId(), null, mapZoomVO);
                List<AreaAclMapDO_v2> cw = areaAclMapper.v3_adminListMapAcl(null, "2", areaAclReq.getLevel(), areaAclReq.getParentId(), areaAclReq.getParentId(), mapZoomVO);
                List<AreaAclMapDO_v2> ec = areaAclMapper.v3_adminListMapAcl(null, "3", areaAclReq.getLevel(), areaAclReq.getParentId(), areaAclReq.getParentId(), mapZoomVO);
                if (!CollectionUtils.isEmpty(rw)) {
                    rw.forEach(areaAclMapDO_v2 -> {
                        areaAclMapDO_v2.setDeviceType("0");
                        areaList_two.add(areaAclMapDO_v2);
                    });
                }
                if (!CollectionUtils.isEmpty(mh)) {
                    mh.forEach(areaAclMapDO_v2 -> {
                        areaAclMapDO_v2.setDeviceType("1");
                        areaList_two.add(areaAclMapDO_v2);
                    });
                }
                if (!CollectionUtils.isEmpty(cw)) {
                    cw.forEach(areaAclMapDO_v2 -> {
                        areaAclMapDO_v2.setDeviceType("2");
                        areaList_two.add(areaAclMapDO_v2);
                    });
                }
                if (!CollectionUtils.isEmpty(ec)) {
                    ec.forEach(areaAclMapDO_v2 -> {
                        areaAclMapDO_v2.setDeviceType("3");
                        areaList_two.add(areaAclMapDO_v2);
                    });
                }
                if (CollectionUtils.isEmpty(areaList_two)) {
                    return Lists.newArrayList();
                }
                areaList.addAll(areaList_two);
                return areaList;
            } else {
                List<AreaAclMapDO_v2> cw = areaAclMapper.v3_adminListMapAcl(1, "2", areaAclReq.getLevel(), areaAclReq.getParentId(), areaAclReq.getParentId(), mapZoomVO);
                List<AreaAclMapDO_v2> ec = areaAclMapper.v3_adminListMapAcl(1, "3", areaAclReq.getLevel(), areaAclReq.getParentId(), areaAclReq.getParentId(), mapZoomVO);
                if (!CollectionUtils.isEmpty(cw)) {
                    cw.forEach(areaAclMapDO_v2 -> {
                        areaAclMapDO_v2.setDeviceType("2");
                        areaList_two.add(areaAclMapDO_v2);
                    });
                }
                if (!CollectionUtils.isEmpty(ec)) {
                    ec.forEach(areaAclMapDO_v2 -> {
                        areaAclMapDO_v2.setDeviceType("3");
                        areaList_two.add(areaAclMapDO_v2);
                    });
                }
                if (CollectionUtils.isEmpty(areaList_two)) {
                    return Lists.newArrayList();
                }
                areaList.addAll(areaList_two);
                return areaList;
            }
        }
        if (CollectionUtils.isEmpty(areaList)) {
            return Lists.newArrayList();
        }
        return areaList;
    }

    private List<HomeMapAclRes> list(List<AreaAclMapDO_v2> areaList) {
        List<HomeMapAclRes> list = Lists.newArrayList();
        //区域id
        Set<Long> set = Sets.newHashSet();
        //key 区域id v 查询出对象
        Multimap<Long, AreaAclMapDO_v2> multimap = ArrayListMultimap.create();
        areaList.forEach(areaAclMapDO_v2 -> {
            set.add(areaAclMapDO_v2.getAreaId());
            multimap.put(areaAclMapDO_v2.getAreaId(), areaAclMapDO_v2);
        });
        set.forEach(aLong -> {
            HomeMapAclRes homeMapAclRes = new HomeMapAclRes();
            List<AreaAclMapDO_v2> areaAclMapDO_v2s = (List<AreaAclMapDO_v2>) multimap.get(aLong);
            if (!CollectionUtils.isEmpty(areaAclMapDO_v2s)) {
                AreaAclMapDO_v2 areaAclMapDO_v2 = areaAclMapDO_v2s.get(0);
                BeanUtils.copyProperties(areaAclMapDO_v2, homeMapAclRes);
                //设备类型  0 地表水 1井盖 2生活用水 3电梯
                Multimap<String, Long> multimap1 = ArrayListMultimap.create();
                Set<String> str = Sets.newHashSet();
                areaAclMapDO_v2s.forEach(areaAclMa -> {
                    str.add(areaAclMa.getDeviceType());
                    multimap1.put(areaAclMa.getDeviceType(), areaAclMa.getAreaId());
                });
                List<DeviceList> deviceInfos = Lists.newArrayList();
                str.forEach(s -> {
                    List<Long> longs = (List<Long>) multimap1.get(s);
                    DeviceList deviceList = deviceInfos(s, longs);
                    deviceInfos.add(deviceList);
                });
                homeMapAclRes.setDeviceInfos(deviceInfos);
            }
            list.add(homeMapAclRes);
        });
        return list;
    }

    /**
     * @param deviceType 设备类型 0 地表水 1井盖 2生活用水 3电梯
     * @param longs      设备数量
     * @return
     */
    private DeviceList deviceInfos(String deviceType, List<Long> longs) {
        DeviceList deviceList = new DeviceList();
        switch (deviceType) {
            case "0":
                deviceList.setDeviceName("地表水");
                deviceList.setDeviceNum(CollectionUtils.isEmpty(longs) ? 0 : longs.size());
                return deviceList;
            case "1":
                deviceList.setDeviceName("井盖");
                deviceList.setDeviceNum(CollectionUtils.isEmpty(longs) ? 0 : longs.size());
                return deviceList;
            case "2":
                deviceList.setDeviceName("生活用水");
                deviceList.setDeviceNum(CollectionUtils.isEmpty(longs) ? 0 : longs.size());
                return deviceList;
            case "3":
                deviceList.setDeviceName("电梯");
                deviceList.setDeviceNum(CollectionUtils.isEmpty(longs) ? 0 : longs.size());
                return deviceList;
            default:
                return deviceList;
        }
    }

    /**
     * 地图每一个层级区域权限
     *
     * @param areaAclReq
     * @param redisUser
     * @return
     */
    public JSONResult areaAcl(AreaAclReq areaAclReq, RedisUser redisUser) {
        return JSONResult.ok(areaAclTotal(areaAclReq, redisUser, null));
    }

    private List<AreaDOEquals> areaAclTotal(AreaAclReq areaAclReq, RedisUser redisUser, MapZoomVO mapZoomVO) {
        if (redisUser.getGroupId() == 1) {
            if (StringUtils.isBlank(areaAclReq.getDeviceType())) {
                List<String> list = Lists.newArrayList();
                if (areaAclReq.getLevel() <= 2) {//0省 1市 2区 3街道  4 小区
                    list.add("0");//0 地表水 1井盖 2生活用水 3电梯
                    list.add("1");
                    list.add("2");
                    list.add("3");
                } else if (areaAclReq.getLevel().equals(3)) {
                    list.add("1");
                    list.add("2");
                    list.add("3");
                } else if (areaAclReq.getLevel().equals(4)) {
                    list.add("2");
                    list.add("3");
                }
                //0 地表水 1井盖 2生活用水 3电梯
                List<AreaDO> areaDOS = Lists.newArrayList();
                list.forEach(s -> {
                    List<AreaDO> rw = areaAclMapper.getVillageByAcl_admin(areaAclReq.getLevel(), areaAclReq.getLevel(), areaAclReq.getLevel(), null, mapZoomVO, s);
                    if (!CollectionUtils.isEmpty(rw)) {
                        areaDOS.addAll(rw);
                    }
                });
                List<AreaDOEquals> areaDOEquals = Lists.newArrayList();
                areaDOS.forEach(areaDO -> {
                    AreaDOEquals areaDOEquals1 = new AreaDOEquals();
                    BeanUtils.copyProperties(areaDO, areaDOEquals1);
                    areaDOEquals.add(areaDOEquals1);
                });
                //查询小区表
                List<AreaDOEquals> newList = areaDOEquals.stream().distinct().collect(Collectors.toList());
                return newList;
            }
            //查询小区表
            List<AreaDO> villageDOS = areaAclMapper.getVillageByAcl_admin(areaAclReq.getLevel(), areaAclReq.getLevel(), areaAclReq.getLevel(), null, mapZoomVO, areaAclReq.getDeviceType());
            List<AreaDOEquals> areaDOEquals = Lists.newArrayList();
            villageDOS.forEach(areaDO -> {
                AreaDOEquals areaDOEquals1 = new AreaDOEquals();
                BeanUtils.copyProperties(areaDO, areaDOEquals1);
                areaDOEquals.add(areaDOEquals1);
            });
            //查询小区表
            List<AreaDOEquals> newList = areaDOEquals.stream().distinct().collect(Collectors.toList());
            return newList;
        }
        List<Long> userRoleIdList = null;
        //redis-->根据用户id得到角色列表
        Object o = redisUtil.get(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + redisUser.getId());
        if (null != o) {
            userRoleIdList = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
            });
        } else {
            //根据用户id得到角色列表
            userRoleIdList = roleUserMapper.getRolesByUserId(redisUser.getId());
        }

        //查询小区表
        if (areaAclReq.getLevel().equals(4)) {
            List<AreaDO> villageDOS = areaAclMapper.getVillageByAcl(areaAclReq.getLevel(), userRoleIdList, mapZoomVO, areaAclReq.getDeviceType());
            List<AreaDOEquals> areaDOEquals = Lists.newArrayList();
            villageDOS.forEach(areaDO -> {
                AreaDOEquals areaDOEquals1 = new AreaDOEquals();
                BeanUtils.copyProperties(areaDO, areaDOEquals1);
                areaDOEquals.add(areaDOEquals1);
            });
            //查询小区表
            List<AreaDOEquals> newList = areaDOEquals.stream().distinct().collect(Collectors.toList());
            return newList;
        } else {//查询  省市区街道
            if (CollectionUtils.isEmpty(userRoleIdList)) {
                return Lists.newArrayList();
            }
            List<AreaDO> areaDOS = areaAclMapper.getAreaByAcl(areaAclReq.getLevel(), userRoleIdList, mapZoomVO, areaAclReq.getDeviceType());
            List<AreaDOEquals> areaDOEquals = Lists.newArrayList();
            areaDOS.forEach(areaDO -> {
                AreaDOEquals areaDOEquals1 = new AreaDOEquals();
                BeanUtils.copyProperties(areaDO, areaDOEquals1);
                areaDOEquals.add(areaDOEquals1);
            });
            //查询小区表
            List<AreaDOEquals> newList = areaDOEquals.stream().distinct().collect(Collectors.toList());
            return newList;
        }
    }

    /**
     * 添加 区域和角色
     *
     * @param addRoleAreaReq
     * @param redisUser
     * @return
     */
    public JSONResult addRoleArea(AddRoleAreaReq addRoleAreaReq, RedisUser redisUser) {
        RoleDO roleDO = roleMapper.getRoleByUUID(addRoleAreaReq.getRoleUuid());
        if (null == roleDO) {
            return JSONResult.errorMap("待查询的角色不存在");
        }
        //根据角色id 得到区域点id列表
        List<Long> originAclIdList = roleAreaDAO.selectByRoleId(roleDO.getId());
        //得到前端给的区域点id
        List<RoleAreaDO> aclIdList = addRoleAreaReq.getAreaIds();
        if (!CollectionUtils.isEmpty(aclIdList)) {
            if (originAclIdList.size() == aclIdList.size()) {
                List<Long> longs = Lists.newArrayList();
                aclIdList.forEach(aclDO -> {
                    longs.add(aclDO.getId());
                });
                Set<Long> originAclIdSet = Sets.newHashSet(originAclIdList);
                Set<Long> aclIdSet = Sets.newHashSet(longs);
                originAclIdSet.removeAll(aclIdSet);
                if (CollectionUtils.isEmpty(originAclIdSet)) {
                    return JSONResult.ok("把区域赋予给角色成功");
                }
            }
        }
        updateRoleAcls(roleDO.getId(), aclIdList, roleDO.getGroupId(), redisUser.getSysUserName(), addRoleAreaReq.getDeviceType());
        return JSONResult.ok("把区域赋予给角色成功");

    }

    @Transactional
    public void updateRoleAcls(Long roleId, List<RoleAreaDO> aclIdList, Long groupId, String userName, String deviceType) {

        roleAreaDAO.deleteByParams(roleId, deviceType);
        if (CollectionUtils.isEmpty(aclIdList)) {
            return;
        }
        List<RoleAreaDO> roleAreaDOS = Lists.newArrayList();
        aclIdList.forEach(aLong -> {
            RoleAreaDO roleAreaDO = new RoleAreaDO();
            roleAreaDO.setAreaId(aLong.getAreaId());
            roleAreaDO.setAreaLevel(aLong.getAreaLevel());
            roleAreaDO.setGroupId(groupId);
            roleAreaDO.setRoleId(roleId);
            roleAreaDO.setDeviceType(deviceType);
            roleAreaDOS.add(roleAreaDO);
        });
        roleAreaDAO.insertBatch(roleAreaDOS);
    }
}
