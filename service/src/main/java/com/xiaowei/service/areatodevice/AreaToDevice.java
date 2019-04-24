package com.xiaowei.service.areatodevice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.Lists;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.AreaToDeviceReq;
import com.xiaowei.common.res.areatodevice.AreatodeviceRes;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentCwDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentEcDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentRwDO;
import com.xiaowei.mana.mapper.mapper.RoleDeviceMapper;
import com.xiaowei.mana.mapper.mapper.areatodevice.AreatodeviceMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MOMO on 2019/1/18.
 */
@Service
public class AreaToDevice {

    @Autowired
    private AreatodeviceMapper areatodeviceMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoleDeviceMapper roleDeviceMapper;

    /**
     * 地表水
     *
     * @param areaToDeviceReq
     */
    public List<AreatodeviceRes> rw(AreaToDeviceReq areaToDeviceReq) {
        List<AreatodeviceRes> areatodeviceRes = Lists.newArrayList();
        //小为公司
        if (areaToDeviceReq.getGroupId() == 1) {
            List<XwEquipmentRwDO> xwEquipmentRwDOS = areatodeviceMapper.getRWAllBySelect(areaToDeviceReq.getSelectId());
            if (CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
                return Lists.newArrayList();
            }
            xwEquipmentRwDOS.forEach(xwEquipmentRwDO -> {
                AreatodeviceRes areatodeviceRes1 = AreatodeviceRes.builder().deviceNo(xwEquipmentRwDO.getDeviceNo())
                        .deviceName(xwEquipmentRwDO.getDeviceName()).deviceCode(xwEquipmentRwDO.getDeviceCode()).build();
                areatodeviceRes.add(areatodeviceRes1);
            });
            return areatodeviceRes;
        }
        //redis-->根据用户id得到角色列表
        Object o = redisUtil.get(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + areaToDeviceReq.getUserId());
        if (null == o) {
            return Lists.newArrayList();
        }
        List<Long> userRoleIdList = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
        });
        //0 地表水 1井盖 2生活用水 3电梯'
        List<String> deiviceNos = roleDeviceMapper.getDevicesByRoleIdAndDeviceType(userRoleIdList, "0");
        if (CollectionUtils.isEmpty(deiviceNos)) {
            return Lists.newArrayList();
        }
        List<XwEquipmentRwDO> xwEquipmentRwDOS = areatodeviceMapper.getRWDeivcesByDeviceNos(deiviceNos);
        if (!CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
            xwEquipmentRwDOS.forEach(xwEquipmentRwDO -> {
                AreatodeviceRes areatodeviceRes1 = AreatodeviceRes.builder().deviceNo(xwEquipmentRwDO.getDeviceNo())
                        .deviceName(xwEquipmentRwDO.getDeviceName()).deviceCode(xwEquipmentRwDO.getDeviceCode()).build();
                areatodeviceRes.add(areatodeviceRes1);
            });
            return areatodeviceRes;
        }
        return Lists.newArrayList();
    }

    /**
     * 井盖
     *
     * @param areaToDeviceReq
     * @return
     */
    public List<AreatodeviceRes> mh(AreaToDeviceReq areaToDeviceReq) {
        List<AreatodeviceRes> areatodeviceRes = Lists.newArrayList();
        //小为公司
        if (areaToDeviceReq.getGroupId() == 1) {
            List<XwEquipmentRwDO> xwEquipmentRwDOS = areatodeviceMapper.getMHAllBySelect(areaToDeviceReq.getSelectId());
            if (CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
                return Lists.newArrayList();
            }
            xwEquipmentRwDOS.forEach(xwEquipmentRwDO -> {
                AreatodeviceRes areatodeviceRes1 = AreatodeviceRes.builder().deviceNo(xwEquipmentRwDO.getDeviceNo())
                        .deviceName(xwEquipmentRwDO.getDeviceName()).deviceCode(xwEquipmentRwDO.getDeviceCode()).build();
                areatodeviceRes.add(areatodeviceRes1);
            });
            return areatodeviceRes;
        }
        //redis-->根据用户id得到角色列表
        Object o = redisUtil.get(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + areaToDeviceReq.getUserId());
        if (null == o) {
            return Lists.newArrayList();
        }
        List<Long> userRoleIdList = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
        });
        //0 地表水 1井盖 2生活用水 3电梯'
        List<String> deiviceNos = roleDeviceMapper.getDevicesByRoleIdAndDeviceType(userRoleIdList, "1");
        if (CollectionUtils.isEmpty(deiviceNos)) {
            return Lists.newArrayList();
        }
        List<XwEquipmentRwDO> xwEquipmentRwDOS = areatodeviceMapper.getMHDeivcesByDeviceNos(deiviceNos);
        if (!CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
            xwEquipmentRwDOS.forEach(xwEquipmentRwDO -> {
                AreatodeviceRes areatodeviceRes1 = AreatodeviceRes.builder().deviceNo(xwEquipmentRwDO.getDeviceNo())
                        .deviceName(xwEquipmentRwDO.getDeviceName()).deviceCode(xwEquipmentRwDO.getDeviceCode()).build();
                areatodeviceRes.add(areatodeviceRes1);
            });
            return areatodeviceRes;
        }
        return Lists.newArrayList();
    }

    /**
     * 生活用水
     *
     * @param areaToDeviceReq
     * @return
     */
    public List<AreatodeviceRes> cw(AreaToDeviceReq areaToDeviceReq) {
        List<AreatodeviceRes> areatodeviceRes = Lists.newArrayList();
        //小为公司
        if (areaToDeviceReq.getGroupId() == 1) {
            List<XwEquipmentCwDO> xwEquipmentRwDOS = areatodeviceMapper.getCWAllBySelect(areaToDeviceReq.getSelectId());
            if (CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
                return Lists.newArrayList();
            }
            xwEquipmentRwDOS.forEach(xwEquipmentRwDO -> {
                AreatodeviceRes areatodeviceRes1 = AreatodeviceRes.builder().deviceNo(xwEquipmentRwDO.getDeviceNo())
                        .deviceName(xwEquipmentRwDO.getDeviceName()).deviceCode(xwEquipmentRwDO.getDeviceCode()).floor(xwEquipmentRwDO.getFloor()).build();
                areatodeviceRes.add(areatodeviceRes1);
            });
            return areatodeviceRes;
        }
        //redis-->根据用户id得到角色列表
        Object o = redisUtil.get(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + areaToDeviceReq.getUserId());
        if (null == o) {
            return Lists.newArrayList();
        }
        List<Long> userRoleIdList = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
        });
        //0 地表水 1井盖 2生活用水 3电梯'
        List<String> deiviceNos = roleDeviceMapper.getDevicesByRoleIdAndDeviceType(userRoleIdList, "2");
        if (CollectionUtils.isEmpty(deiviceNos)) {
            return Lists.newArrayList();
        }
        List<XwEquipmentCwDO> xwEquipmentRwDOS = areatodeviceMapper.getCWDeivcesByDeviceNos(deiviceNos);
        if (!CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
            xwEquipmentRwDOS.forEach(xwEquipmentRwDO -> {
                AreatodeviceRes areatodeviceRes1 = AreatodeviceRes.builder().deviceNo(xwEquipmentRwDO.getDeviceNo())
                        .deviceName(xwEquipmentRwDO.getDeviceName()).deviceCode(xwEquipmentRwDO.getDeviceCode()).build();
                areatodeviceRes.add(areatodeviceRes1);
            });
            return areatodeviceRes;
        }
        return Lists.newArrayList();
    }

    /**
     * 电梯
     *
     * @param areaToDeviceReq
     * @return
     */
    public List<AreatodeviceRes> ec(AreaToDeviceReq areaToDeviceReq) {
        List<AreatodeviceRes> areatodeviceRes = Lists.newArrayList();
        //小为公司
        if (areaToDeviceReq.getGroupId() == 1) {
            List<XwEquipmentEcDO> xwEquipmentRwDOS = areatodeviceMapper.getECAllBySelect(areaToDeviceReq.getSelectId());
            if (CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
                return Lists.newArrayList();
            }
            xwEquipmentRwDOS.forEach(xwEquipmentRwDO -> {
                AreatodeviceRes areatodeviceRes1 = AreatodeviceRes.builder().deviceNo(xwEquipmentRwDO.getDeviceNo())
                        .deviceName(xwEquipmentRwDO.getDeviceName()).deviceCode(xwEquipmentRwDO.getDeviceCode())
                        .floorDownNum(xwEquipmentRwDO.getFloorDownnum()).floorNum(xwEquipmentRwDO.getFloorNum()).build();
                areatodeviceRes.add(areatodeviceRes1);
            });
            return areatodeviceRes;
        }
        //redis-->根据用户id得到角色列表
        Object o = redisUtil.get(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + areaToDeviceReq.getUserId());
        if (null == o) {
            return Lists.newArrayList();
        }
        List<Long> userRoleIdList = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
        });
        //0 地表水 1井盖 2生活用水 3电梯'
        List<String> deiviceNos = roleDeviceMapper.getDevicesByRoleIdAndDeviceType(userRoleIdList, "3");
        if (CollectionUtils.isEmpty(deiviceNos)) {
            return Lists.newArrayList();
        }
        List<XwEquipmentEcDO> xwEquipmentRwDOS = areatodeviceMapper.getECDeivcesByDeviceNos(deiviceNos);
        if (!CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
            xwEquipmentRwDOS.forEach(xwEquipmentRwDO -> {
                AreatodeviceRes areatodeviceRes1 = AreatodeviceRes.builder().deviceNo(xwEquipmentRwDO.getDeviceNo())
                        .deviceName(xwEquipmentRwDO.getDeviceName()).deviceCode(xwEquipmentRwDO.getDeviceCode())
                        .floorDownNum(xwEquipmentRwDO.getFloorDownnum()).floorNum(xwEquipmentRwDO.getFloorNum()).build();
                areatodeviceRes.add(areatodeviceRes1);
            });
            return areatodeviceRes;
        }
        return Lists.newArrayList();
    }
}
