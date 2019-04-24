package com.xiaowei.service.deviceroleById;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.DeviceUserRoleReq;
import com.xiaowei.mana.mapper.dataobject.DeviceRoleDeviceRea;
import com.xiaowei.common.res.DeviceRoleRea;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dataobject.RoleDeviceDO;
import com.xiaowei.mana.mapper.mapper.RoleDeviceMapper;
import com.xiaowei.mana.mapper.mapper.RoleUserMapper;
import com.xiaowei.mana.mapper.mapper.device.RWDeviceMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MOMO on 2019/1/14.
 */
@Service
@Slf4j
public class DeviceRoleByUserIdService {
    @Autowired
    private RoleUserMapper roleUserMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RoleDeviceMapper roleDeviceMapper;
    @Autowired
    private RWDeviceMapper rwDeviceMapper;//0


    //0 地表水 1井盖 2生活用水 3电梯'
    private final String[] deviceType = {"0", "1", "2", "3"};

    /**
     * 根据用户id----->角色----->设备列表
     * @param deviceUserRoleReq
     * @return
     */
    public DeviceRoleRea getDeviceByUserId(DeviceUserRoleReq deviceUserRoleReq) {
        //用户与角色关系
        Object o = redisUtil.get(RedisKeyEnum.REDIS_USER_ROLE_INFO.getKey() + deviceUserRoleReq.getUserId());
        if (o != null) {
            List<Long> redisRole = JSON.parseObject(o + "", new TypeReference<List<Long>>() {
            });
            if (CollectionUtils.isEmpty(redisRole)) {
                return new DeviceRoleRea();
            }
            //角色和设备
            List<RoleDeviceDO> roleDeviceDOS = roleDeviceMapper.getDevicesInfoByRoleId(redisRole);
            if (CollectionUtils.isEmpty(roleDeviceDOS)) {
                return new DeviceRoleRea();
            }
            return deviceRoleReas(roleDeviceDOS);

        } else {
            List<Long> roles = roleUserMapper.getRolesByUserId(deviceUserRoleReq.getUserId());
            if (CollectionUtils.isEmpty(roles)) {
                return new DeviceRoleRea();
            }
            //角色和设备
            List<RoleDeviceDO> roleDeviceDOS = roleDeviceMapper.getDevicesInfoByRoleId(roles);
            if (CollectionUtils.isEmpty(roleDeviceDOS)) {
                return new DeviceRoleRea();
            }
            return new DeviceRoleRea();
        }
    }

    private DeviceRoleRea deviceRoleReas(List<RoleDeviceDO> roleDeviceDOS) {
        DeviceRoleRea deviceRoleRea = new DeviceRoleRea();
        //key--> 设备类型   v-->设备id
        //0 地表水 1井盖 2生活用水 3电梯'
        Multimap<String, String> multimap = ArrayListMultimap.create();
        roleDeviceDOS.forEach(roleDeviceDO -> {
            multimap.put(roleDeviceDO.getDeviceType(), roleDeviceDO.getDeviceNo());
        });
        List<String> rw = (List<String>) multimap.get("0");
        if (!CollectionUtils.isEmpty(rw)) {
            deviceRoleRea.setRw(deviceInfo("0", rw));
        }
        List<String> mh = (List<String>) multimap.get("1");
        if (!CollectionUtils.isEmpty(rw)) {
            deviceRoleRea.setMh(deviceInfo("1", mh));
        }
        List<String> cw = (List<String>) multimap.get("2");
        if (!CollectionUtils.isEmpty(cw)) {
            deviceRoleRea.setCw(deviceInfo("2", cw));
        }
        List<String> ec = (List<String>) multimap.get("3");
        if (!CollectionUtils.isEmpty(ec)) {
            deviceRoleRea.setEc(deviceInfo("3", ec));
        }
        return deviceRoleRea;

    }

    private List<DeviceRoleDeviceRea> deviceInfo(String deviceT, List<String> deviceIds) {
        switch (deviceT) {
            case "0":
                return CollectionUtils.isEmpty(deviceIds) ? Lists.newArrayList() : rwDeviceMapper.RWbatchCWsByDeviceId(deviceIds);
            case "1":
                return CollectionUtils.isEmpty(deviceIds) ? Lists.newArrayList() : rwDeviceMapper.MHbatchCWsByDeviceId(deviceIds);
            case "2":
                return CollectionUtils.isEmpty(deviceIds) ? Lists.newArrayList() : rwDeviceMapper.CWbatchCWsByDeviceId(deviceIds);
            case "3":
                return CollectionUtils.isEmpty(deviceIds) ? Lists.newArrayList() : rwDeviceMapper.ECbatchCWsByDeviceId(deviceIds);
            default:
                return Lists.newArrayList();
        }
    }
}
