package com.xiaowei.service.core;

import com.google.common.collect.Lists;
import com.xiaowei.common.req.area.AreaAclReq_v2;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.areatodevice.HomeAreaMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/2/14.
 */
@Service
@Slf4j
public class AreaRoleService_v3 {

    @Autowired
    private HomeAreaMap homeAreaMap;


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


    public List<AreaDO> areaAcl_v3(AreaAclReq_v2 areaAclReq, RedisUser redisUser) {
        if (areaAclReq.getParentId() == 6) {
            areaAclReq.setParentId(7L);
        }
        //首页地图
        if (StringUtils.isBlank(areaAclReq.getType())) {
            if (redisUser.getGroupId().equals(1L)) {
                List<AreaDO> areaDOS = homeAreaMap.adminHomeMap(areaAclReq.getParentId());
                return cnm(areaDOS, areaAclReq.getLevel());
            } else {
                List<AreaDO> areaDOS = homeAreaMap.commonHomeMap(redisUser.getId(), areaAclReq.getLevel(), areaAclReq.getType(),areaAclReq.getParentId());
                return cnm(areaDOS, areaAclReq.getLevel());
            }
        } else {//单个场景 ec mh cw rw
            if (redisUser.getGroupId().equals(1L)) {
                List<AreaDO_v3> all = Lists.newArrayList();
                if ("ec".equals(areaAclReq.getType())) {//电梯
                    if (areaAclReq.getLevel() <= 2) {
                        List<AreaDO_v3> ec = homeAreaMap.adminECHomeMap(areaAclReq.getParentId(), String.valueOf(areaAclReq.getLevel()));
                        if (!CollectionUtils.isEmpty(ec)) {
                            all.addAll(ec);
                        }
                    } else {
                        List<AreaDO_v3> ec = homeAreaMap.adminECHomeMap_village(areaAclReq.getParentId(),areaAclReq.getAreaLevel()+"."+areaAclReq.getParentId());
                        if (!CollectionUtils.isEmpty(ec)) {
                            all.addAll(ec);
                        }
                    }

                } else if ("rw".equals(areaAclReq.getType())) {
                    List<AreaDO_v3> rw = homeAreaMap.adminRWHomeMap(areaAclReq.getParentId(), String.valueOf(areaAclReq.getLevel()));
                    if (!CollectionUtils.isEmpty(rw)) {
                        all.addAll(rw);
                    }
                } else if ("mh".equals(areaAclReq.getType())) {
                    List<AreaDO_v3> mh = homeAreaMap.adminMHHomeMap(areaAclReq.getParentId(), String.valueOf(areaAclReq.getLevel()));
                    if (!CollectionUtils.isEmpty(mh)) {
                        all.addAll(mh);
                    }
                } else if ("cw".equals(areaAclReq.getType())) {//生活用水
                    if (areaAclReq.getLevel() <= 2) {
                        List<AreaDO_v3> cw = homeAreaMap.adminCWHomeMap(areaAclReq.getParentId(), String.valueOf(areaAclReq.getLevel()));
                        if (!CollectionUtils.isEmpty(cw)) {
                            all.addAll(cw);
                        }
                    } else {
                        List<AreaDO_v3> cw = homeAreaMap.adminCWHomeMap_village(areaAclReq.getParentId(),areaAclReq.getAreaLevel()+"."+areaAclReq.getParentId());
                        if (!CollectionUtils.isEmpty(cw)) {
                            all.addAll(cw);
                        }
                    }

                }
                if (CollectionUtils.isEmpty(all)) {
                    return Lists.newArrayList();
                }
                List<AreaDO_v3> allFinal = all.stream().distinct().collect(Collectors.toList());
                List<AreaDO> areaDOS = Lists.newArrayList();
                allFinal.forEach(areaDO_v3 -> {
                    AreaDO areaDO = new AreaDO();
                    BeanUtils.copyProperties(areaDO_v3, areaDO);
                    areaDOS.add(areaDO);
                });
                if (areaAclReq.getLevel() > 2) {
                    return areaDOS;
                }
                return cnm(areaDOS, areaAclReq.getLevel());
            } else {
                //0 地表水 1井盖 2生活用水 3电梯
                String deviceType = "";
                if ("ec".equals(areaAclReq.getType())) {
                    deviceType = "3";
                } else if ("mh".equals(areaAclReq.getType())) {
                    deviceType = "1";
                } else if ("cw".equals(areaAclReq.getType())) {
                    deviceType = "2";
                } else if ("rw".equals(areaAclReq.getType())) {
                    deviceType = "0";
                }
                if("ec".equals(areaAclReq.getType()) ||"rw".equals(areaAclReq.getType()) ){
                    //type ec mh cw rw
                    //level  0省 1市 2区 3街道  4 小区
                    if (areaAclReq.getLevel() <= 2) {
                        List<AreaDO_v3> areaDO_v3s = homeAreaMap.commonECHomeMap(redisUser.getId(), deviceType, String.valueOf(areaAclReq.getLevel()),areaAclReq.getParentId());
                        List<AreaDO_v3> allFinal = areaDO_v3s.stream().distinct().collect(Collectors.toList());
                        List<AreaDO> areaDOS = Lists.newArrayList();
                        allFinal.forEach(areaDO_v3 -> {
                            AreaDO areaDO = new AreaDO();
                            BeanUtils.copyProperties(areaDO_v3, areaDO);
                            areaDOS.add(areaDO);
                        });
                        return cnm(areaDOS, areaAclReq.getLevel());
                    } else {
                        List<AreaDO_v3> areaDOS = homeAreaMap.commonECHomeMap_village(redisUser.getId(), deviceType, String.valueOf(areaAclReq.getLevel()),areaAclReq.getAreaLevel()+"."+areaAclReq.getParentId());
                        List<AreaDO_v3> allFinal = areaDOS.stream().distinct().collect(Collectors.toList());
                        List<AreaDO> areaDOSaa = Lists.newArrayList();
                        allFinal.forEach(areaDO_v3 -> {
                            AreaDO areaDO = new AreaDO();
                            BeanUtils.copyProperties(areaDO_v3, areaDO);
                            areaDOSaa.add(areaDO);
                        });
                        return cnm(areaDOSaa, areaAclReq.getLevel());
                    }
                }
                //type ec mh cw rw
                //level  0省 1市 2区 3街道  4 小区
                if (areaAclReq.getLevel() <= 3) {
                    List<AreaDO_v3> areaDO_v3s = homeAreaMap.commonECHomeMap(redisUser.getId(), deviceType, String.valueOf(areaAclReq.getLevel()),areaAclReq.getParentId());
                    List<AreaDO_v3> allFinal = areaDO_v3s.stream().distinct().collect(Collectors.toList());
                    List<AreaDO> areaDOS = Lists.newArrayList();
                    allFinal.forEach(areaDO_v3 -> {
                        AreaDO areaDO = new AreaDO();
                        BeanUtils.copyProperties(areaDO_v3, areaDO);
                        areaDOS.add(areaDO);
                    });
                    return cnm(areaDOS, areaAclReq.getLevel());
                } else {
                    List<AreaDO_v3> areaDOS = homeAreaMap.commonECHomeMap_village(redisUser.getId(), deviceType, String.valueOf(areaAclReq.getLevel()),areaAclReq.getAreaLevel()+"."+areaAclReq.getParentId());
                    List<AreaDO_v3> allFinal = areaDOS.stream().distinct().collect(Collectors.toList());
                    List<AreaDO> areaDOSaa = Lists.newArrayList();
                    allFinal.forEach(areaDO_v3 -> {
                        AreaDO areaDO = new AreaDO();
                        BeanUtils.copyProperties(areaDO_v3, areaDO);
                        areaDOSaa.add(areaDO);
                    });
                    return cnm(areaDOSaa, areaAclReq.getLevel());
                }


            }
        }
    }

    private List<AreaDO> cnm(List<AreaDO> areaDOS, Integer level) {
        List<AreaDO> mmp = Lists.newArrayList();
        areaDOS.forEach(areaDO -> {
            if (areaDO.getId().equals(6L)) {
                areaDO.setId(7L);
                areaDO.setSysAreaLevel("0.6");
                areaDO.setSysAreaName("上海市");
                areaDO.setLatitude(new BigDecimal("31.2361760000"));
                areaDO.setLongitude(new BigDecimal("121.4799650000"));
                areaDO.setSysWeathCode("101020100");
                areaDO.setSysAreaLevel("0.6");
            }
            areaDO.setCreateBy(String.valueOf(level));

            mmp.add(areaDO);
        });
        return mmp;
    }

}
