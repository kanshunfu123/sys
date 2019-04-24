package com.xiaowei.service.areatodevice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.github.pagehelper.PageHelper;
import com.google.common.collect.Lists;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.AreaToDeviceReq;
import com.xiaowei.common.req.AreaToDeviceReq_v1;
import com.xiaowei.common.res.areatodevice.AreatodeviceRes;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.RoleDeviceMapper;
import com.xiaowei.mana.mapper.mapper.areatodevice.AreatodeviceMapper;
import com.xiaowei.mana.mapper.mapper.device.RWDeviceMapper;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MOMO on 2019/1/18.
 */
@Service
public class AreaToDevice_v1 {

    @Autowired
    private AreatodeviceMapper areatodeviceMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoleDeviceMapper roleDeviceMapper;
    @Autowired
    private RWDeviceMapper rwDeviceMapper;

    /**
     * 地表水
     *
     * @param areaToDeviceReq
     */
    public List<AreatodeviceRes> rw(AreaToDeviceReq_v1 areaToDeviceReq) {
        AreaToDeviceVO_v1 areaToDeviceVO_v1 = AreaToDeviceReq_v1.areaToDeviceVO_v1(areaToDeviceReq);
        List<XwEquipmentRwDO> xwEquipmentRwDOS = Lists.newArrayList();
        if (areaToDeviceReq.getGroupId() == 1) {

            xwEquipmentRwDOS.addAll(rwDeviceMapper.getRWAllBySelect_v1_admin(areaToDeviceVO_v1));
        } else {
            xwEquipmentRwDOS.addAll(rwDeviceMapper.getRWAllBySelect_v1(areaToDeviceVO_v1));
        }
        List<AreatodeviceRes> areatodeviceRes = Lists.newArrayList();
        if (CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
            return Lists.newArrayList();
        }
        xwEquipmentRwDOS.forEach(xwEquipmentRwDO -> {
            AreatodeviceRes areatodevic = AreatodeviceRes.builder()
                    .deviceCode(xwEquipmentRwDO.getDeviceCode()).deviceName(xwEquipmentRwDO.getDeviceName())
                    .deviceNo(xwEquipmentRwDO.getDeviceNo()).build();
            areatodeviceRes.add(areatodevic);
        });
        return areatodeviceRes;
    }

    /**
     * 井盖
     *
     * @param areaToDeviceReq
     * @return
     */
    public List<AreatodeviceRes> mh(AreaToDeviceReq_v1 areaToDeviceReq) {
        AreaToDeviceVO_v1 areaToDeviceVO_v1 = AreaToDeviceReq_v1.areaToDeviceVO_v1(areaToDeviceReq);
        List<XwEquipmentMhDO> xwEquipmentRwDOS = Lists.newArrayList();
        if (areaToDeviceReq.getGroupId() == 1) {
            xwEquipmentRwDOS.addAll(rwDeviceMapper.getMHAllBySelect_v1_admin(areaToDeviceVO_v1));
        } else {
            xwEquipmentRwDOS.addAll(rwDeviceMapper.getMHAllBySelect_v1(areaToDeviceVO_v1));
        }
        List<AreatodeviceRes> areatodeviceRes = Lists.newArrayList();
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

    /**
     * 生活用水
     *
     * @param areaToDeviceReq
     * @return
     */
    public List<AreatodeviceRes> cw(AreaToDeviceReq_v1 areaToDeviceReq) {
        AreaToDeviceVO_v1 areaToDeviceVO_v1 = AreaToDeviceReq_v1.areaToDeviceVO_v1(areaToDeviceReq);
        List<XwEquipmentCwDO> xwEquipmentRwDOS = Lists.newArrayList();
        if (areaToDeviceReq.getGroupId() == 1) {
            xwEquipmentRwDOS.addAll(rwDeviceMapper.getCWAllBySelect_v1_admin(areaToDeviceVO_v1));
        }else{
            xwEquipmentRwDOS.addAll(rwDeviceMapper.getCWAllBySelect_v1(areaToDeviceVO_v1));
        }
        List<AreatodeviceRes> areatodeviceRes = Lists.newArrayList();
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

    /**
     * 电梯
     *
     * @param areaToDeviceReq
     * @return
     */
    public List<AreatodeviceRes> ec(AreaToDeviceReq_v1 areaToDeviceReq) {
        AreaToDeviceVO_v1 areaToDeviceVO_v1 = AreaToDeviceReq_v1.areaToDeviceVO_v1(areaToDeviceReq);
        List<XwEquipmentEcDO> xwEquipmentRwDOS =Lists.newArrayList();
        if (areaToDeviceReq.getGroupId() == 1) {
            xwEquipmentRwDOS.addAll( rwDeviceMapper.getECAllBySelect_v1_admin(areaToDeviceVO_v1));
        }else {
            xwEquipmentRwDOS.addAll( rwDeviceMapper.getECAllBySelect_v1(areaToDeviceVO_v1));
        }

        List<AreatodeviceRes> areatodeviceRes = Lists.newArrayList();
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
}
