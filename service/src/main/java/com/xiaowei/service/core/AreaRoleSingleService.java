package com.xiaowei.service.core;

import com.google.common.collect.Lists;
import com.xiaowei.common.req.area.AreaAclReq_v2;
import com.xiaowei.common.res.homeMapAcl.DeviceList;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.mana.mapper.dataobject.AreaAclMapDO_v2;
import com.xiaowei.mana.mapper.mapper.areatodevice.AreaAclMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MOMO on 2019/2/22.
 */
@Service
@Slf4j
public class AreaRoleSingleService {
    @Autowired
    private AreaAclMapper areaAclMapper;

    public DeviceList showMapInfo(AreaAclReq_v2 areaAclReq_v2, RedisUser redisUser) {
        //0 地表水 1井盖 2生活用水 3电梯
        DeviceList deviceLists = third(areaAclReq_v2, redisUser);

        return deviceLists;
    }

    private DeviceList third(AreaAclReq_v2 areaAclReq_v2, RedisUser redisUser) {
        DeviceList deviceLisaaat = new DeviceList();
        if (redisUser.getGroupId() != 1) {
            DeviceList deviceList = deviceType_common(areaAclReq_v2.getDeviceType(), areaAclReq_v2, redisUser);
            DeviceList breakdown = common_breakdown_showMapInfo(areaAclReq_v2.getDeviceType(), areaAclReq_v2, redisUser);
            DeviceList offLine = common_offLine_showMapInfo(areaAclReq_v2.getDeviceType(), areaAclReq_v2, redisUser);
            if (null != deviceList) {
                deviceLisaaat.setDeviceName(deviceList.getDeviceName());
                deviceLisaaat.setDeviceNum(deviceList.getDeviceNum());
            }
            if (null != breakdown) {
                deviceLisaaat.setBreakdown(breakdown.getBreakdown());
            }
            if (null != offLine) {
                deviceLisaaat.setOffLine(offLine.getOffLine());
            }
//            Long nomal=deviceLisaaat.getDeviceNum()-(deviceLisaaat.getBreakdown()+deviceLisaaat.getOffLine());
            if (deviceLisaaat.getBreakdown()!=null && !deviceLisaaat.getBreakdown().equals(0L)){
                Long nomal=deviceLisaaat.getDeviceNum()-deviceLisaaat.getBreakdown();
                deviceLisaaat.setNormal(nomal);
            }else {
                Long nomal=deviceLisaaat.getDeviceNum()-deviceLisaaat.getOffLine();
                deviceLisaaat.setNormal(nomal);
            }


        } else {
            List<String> list = Lists.newArrayList();
            if (areaAclReq_v2.getLevel() <= 2) {//0省 1市 2区 3街道  4 小区
                //0 地表水 1井盖 2生活用水 3电梯
                list.add(areaAclReq_v2.getDeviceType());

            } else if (areaAclReq_v2.getLevel().equals(3)) {
                list.add(areaAclReq_v2.getDeviceType());
            } else if (areaAclReq_v2.getLevel().equals(4)) {
                list.add(areaAclReq_v2.getDeviceType());
            }
            for (String string : list) {
                DeviceList deviceList = deviceType_admin(string, areaAclReq_v2, redisUser);
                DeviceList breakdown = admin_breakdown_showMapInfo(string, areaAclReq_v2, redisUser);
                DeviceList offLine = admin_offLine_showMapInfo(string, areaAclReq_v2, redisUser);
                if (null != deviceList) {
                    deviceLisaaat.setDeviceName(deviceList.getDeviceName());
                    deviceLisaaat.setDeviceNum(deviceList.getDeviceNum());
                }
                if (null != breakdown) {
                    deviceLisaaat.setBreakdown(breakdown.getBreakdown());
                }
                if (null != offLine) {
                    deviceLisaaat.setOffLine(offLine.getOffLine());
                }
//                Long nomal=deviceLisaaat.getDeviceNum()-(deviceLisaaat.getBreakdown()+deviceLisaaat.getOffLine());
//                Long nomal=deviceLisaaat.getDeviceNum()-deviceLisaaat.getBreakdown();
                if (deviceLisaaat.getBreakdown()!=null && !deviceLisaaat.getBreakdown().equals(0L)){
                    Long nomal=deviceLisaaat.getDeviceNum()-deviceLisaaat.getBreakdown();
                    deviceLisaaat.setNormal(nomal);
                }else {
                    Long nomal=deviceLisaaat.getDeviceNum()-deviceLisaaat.getOffLine();
                    deviceLisaaat.setNormal(nomal);
                }
//                deviceLisaaat.setNormal(nomal);
            }
        }
        return deviceLisaaat;
    }

    private DeviceList admin_offLine_showMapInfo(String deviceType, AreaAclReq_v2 areaAclReq_v2, RedisUser redisUser) {
        DeviceList deviceList = new DeviceList();
        //0地表水 1井盖 2生活用水 3电梯
        switch (deviceType) {
            case "0":
                Long rw = areaAclMapper.admin_offLine_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 0, areaAclReq_v2.getLevel(), -1, DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setOffLine(rw);
                return deviceList;
            case "1":
                Long mh = areaAclMapper.admin_offLine_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 1, areaAclReq_v2.getLevel(), 1, DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setOffLine(mh);
                return deviceList;
            case "2":
                Long cw = areaAclMapper.admin_offLine_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 2, areaAclReq_v2.getLevel(), 2, DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setOffLine(cw);
                return deviceList;
            case "3":
                Long ec = areaAclMapper.admin_offLine_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 3, areaAclReq_v2.getLevel(), 3, DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setOffLine(ec);
                return deviceList;
            default:
                return deviceList;
        }
    }
    private DeviceList admin_breakdown_showMapInfo(String deviceType, AreaAclReq_v2 areaAclReq_v2, RedisUser redisUser) {
        DeviceList deviceList = new DeviceList();
        //0 地表水 1井盖 2生活用水 3电梯
        switch (deviceType) {
            case "0":
                Long rw = areaAclMapper.admin_breakdown_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 0, areaAclReq_v2.getLevel(), -1, DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setBreakdown(rw);
                return deviceList;
            case "1":
                Long mh = areaAclMapper.admin_breakdown_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 1, areaAclReq_v2.getLevel(), 1, DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setBreakdown(mh);
                return deviceList;
            case "2":
                Long cw = areaAclMapper.admin_breakdown_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 2, areaAclReq_v2.getLevel(), 2, DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setBreakdown(cw);
                return deviceList;
            case "3":
                Long ec = areaAclMapper.admin_breakdown_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 3, areaAclReq_v2.getLevel(), 3, DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setBreakdown(ec);
                return deviceList;
            default:
                return deviceList;
        }
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

    private DeviceList common_offLine_showMapInfo(String deviceType, AreaAclReq_v2 areaAclReq_v2, RedisUser redisUser) {
        DeviceList deviceList = new DeviceList();
        //0 地表水 1井盖 2生活用水 3电梯
        switch (deviceType) {
            case "0":
                Long rw = areaAclMapper.common_offLine_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 0, redisUser.getId(), DateUtil.getYesterday(), DateUtil.getTomorrow());

                deviceList.setOffLine(rw);
                return deviceList;
            case "1":
                Long mh = areaAclMapper.common_offLine_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 1, redisUser.getId(), DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setOffLine(mh);
                return deviceList;
            case "2":
                Long cw = areaAclMapper.common_offLine_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 2, redisUser.getId(), DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setOffLine(cw);
                return deviceList;
            case "3":
                Long ec = areaAclMapper.common_offLine_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 3, redisUser.getId(), DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setOffLine(ec);
                return deviceList;
            default:
                return deviceList;
        }
    }
    private DeviceList common_breakdown_showMapInfo(String deviceType, AreaAclReq_v2 areaAclReq_v2, RedisUser redisUser) {
        DeviceList deviceList = new DeviceList();
        //0 地表水 1井盖 2生活用水 3电梯
        switch (deviceType) {
            case "0":
                Long rw = areaAclMapper.common_breakdown_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 0, redisUser.getId(), DateUtil.getYesterday(), DateUtil.getTomorrow());

                deviceList.setBreakdown(rw);
                return deviceList;
            case "1":
                Long mh = areaAclMapper.common_breakdown_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 1, redisUser.getId(), DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setBreakdown(mh);
                return deviceList;
            case "2":
                Long cw = areaAclMapper.common_breakdown_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 2, redisUser.getId(), DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setBreakdown(cw);
                return deviceList;
            case "3":
                Long ec = areaAclMapper.common_breakdown_showMapInfo(areaAclReq_v2.getAreaId(), areaAclReq_v2.getLevel(), 3, redisUser.getId(), DateUtil.getYesterday(), DateUtil.getTomorrow());
                deviceList.setBreakdown(ec);
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
}
