package com.xiaowei.service.ec;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.DeviceUserRoleReq;
import com.xiaowei.common.req.cw.CwPushReq;
import com.xiaowei.common.req.ec.*;
import com.xiaowei.common.req.rw.RwPageList;
import com.xiaowei.common.res.DeviceRoleRea;
import com.xiaowei.common.res.ec.EcEquipmentRes;
import com.xiaowei.common.res.ec.EcInfoRes;
import com.xiaowei.common.res.ec.EcPageListRes;
import com.xiaowei.common.res.ec.EcSenceRes;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.XwEquipmentEcDAO;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.*;
import com.xiaowei.service.deviceroleById.DeviceRoleByUserIdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by kanshunfu on 2019/01/07.
 */
@Service
@Slf4j
public class EcServiceImpl implements EcService {
    @Autowired
    private XwEquipmentEcDAO xwEquipmentEcDAO;
    @Autowired
    private EcMapper ecMapper;
    @Autowired
    private RwMapper rwMapper;
    @Autowired
    private MhMapper mhMapper;
     @Autowired
    private CwMapper cwMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private EcSenceMapper ecSenceMapper;
    @Autowired
    private DeviceRoleByUserIdService deviceRoleByUserIdService;
    @Autowired
    private SafeMapper safeMapper;

    @Override
    public JSONResult interEc(EcAddReq ecAddReq, RedisUser redisUser) {
        XwEquipmentEcDO xwEquipmentEcDO = new XwEquipmentEcDO();
        int countCode = ecMapper.deviceCode(null, ecAddReq.getDeviceCode(), ecAddReq.getVillageName());
        if (countCode > 0) {
            return JSONResult.errorMap("设备别名已存在");
        }
        int countNo = ecMapper.deviceNo(ecAddReq.getDeviceNo());
        if (countNo > 0) {
            return JSONResult.errorMap("设备号已存在");
        }
        if (StringUtil.isNotEmpty(ecAddReq.getProductTime())) {
            xwEquipmentEcDO.setProductTime(DateUtil.getStrToDate(ecAddReq.getProductTime()));
        } else {
            xwEquipmentEcDO.setProductTime(null);
        }
        if (StringUtil.isNotEmpty(ecAddReq.getSetupTime())) {
            xwEquipmentEcDO.setSetupTime(DateUtil.getStrToDate(ecAddReq.getSetupTime()));
        } else {
            xwEquipmentEcDO.setSetupTime(null);
        }
        if (StringUtil.isNotEmpty(ecAddReq.getSafeTime())) {
            xwEquipmentEcDO.setSafeTime(DateUtil.getStrToDate(ecAddReq.getSafeTime()));
        } else {
            xwEquipmentEcDO.setSafeTime(null);
        }
        BeanUtils.copyProperties(ecAddReq, xwEquipmentEcDO);
        xwEquipmentEcDO.setEquipmentUuid(StrUtil.genUUID());
        xwEquipmentEcDO.setCreateBy(redisUser.getSysUserName());
        xwEquipmentEcDO.setUpdateBy(redisUser.getSysUserName());
        xwEquipmentEcDO.setDelFlag("0");
        xwEquipmentEcDO.setDeviceType("电梯");
        xwEquipmentEcDO.setCreateTime(DateUtils.getCurrentDateTime());
        xwEquipmentEcDO.setUpdateTime(DateUtils.getCurrentDateTime());
        xwEquipmentEcDAO.insertEc(xwEquipmentEcDO);
        if (xwEquipmentEcDO != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_FOOR_INFO.getKey() + xwEquipmentEcDO.getDeviceNo(), JSONObject.toJSONString(xwEquipmentEcDO));
        }

        return JSONResult.ok("新增电梯设备成功", xwEquipmentEcDO.getEquipmentUuid());
    }

    @Override
    public JSONResult uuidEc(EcUuidReq ecUuidReq, RedisUser redisUser) {
        EcInfoRes result = new EcInfoRes();
        EcEquipmentRes equipment = new EcEquipmentRes();
        EcSenceRes sence = new EcSenceRes();
        XwEquipmentEcDO xwEquipmentEcDO = ecMapper.uuid(ecUuidReq.getEquipmentUuid());
        if (null == xwEquipmentEcDO) {
            return JSONResult.errorMap("待查询的设备不存在");
        }
        //设备信息 0
        String deviceNo = xwEquipmentEcDO.getDeviceNo();
        BeanUtils.copyProperties(xwEquipmentEcDO, equipment);
        if (xwEquipmentEcDO.getSetupTime() != null) {
            equipment.setSetupTime(DateUtil.dateToString2(xwEquipmentEcDO.getSetupTime()));
        } else {
            equipment.setSetupTime("");
        }
        if (xwEquipmentEcDO.getProductTime() != null) {
            equipment.setProductTime(DateUtil.dateToString2(xwEquipmentEcDO.getProductTime()));
        } else {
            equipment.setProductTime("");
        }
        if (xwEquipmentEcDO.getSafeTime() != null) {
            equipment.setSafeTime(DateUtil.dateToString2(xwEquipmentEcDO.getSafeTime()));
        } else {
            equipment.setSafeTime("");
        }
        //场景信息
        XwEcSenceDO xwEcSenceDO = ecSenceMapper.ecSenceByNo(deviceNo);
        if (xwEcSenceDO != null) {
            BeanUtils.copyProperties(xwEcSenceDO, sence);
            if (xwEcSenceDO.getSenceInstallTime() != null) {
                sence.setSenceInstallTime(DateUtil.dateToString2(xwEcSenceDO.getSenceInstallTime()));
            } else {
                sence.setSenceInstallTime("");
            }
            if (xwEcSenceDO.getProductTime() != null) {
                sence.setProductTime(DateUtil.dateToString2(xwEcSenceDO.getProductTime()));
            } else {
                sence.setProductTime("");
            }
            if (StringUtils.isNotBlank(xwEquipmentEcDO.getAddress())) {
                sence.setLocation(xwEquipmentEcDO.getProvince() + xwEquipmentEcDO.getCity() + xwEquipmentEcDO.getArea() + xwEquipmentEcDO.getAddress());
            } else {
                sence.setLocation(xwEquipmentEcDO.getProvince() + xwEquipmentEcDO.getCity() + xwEquipmentEcDO.getArea());
            }
        }
        result.setEcEquipmentRes(equipment);
        result.setEcSenceRes(sence);
        return JSONResult.ok(result);
    }

    @Override
    public JSONResult editEc(EcEditReq ecEditReq, RedisUser redisUser) {
        XwEquipmentEcDO equipmentEcDO = ecMapper.uuid(ecEditReq.getEquipmentUuid());
        if (null == equipmentEcDO) {
            return JSONResult.errorMap("待编辑的设备不存在");
        }
        if (!equipmentEcDO.getDeviceNo().equals(ecEditReq.getDeviceNo())) {
            return JSONResult.errorMap("设备号不能编辑");
        }
        int countCode = ecMapper.deviceCode(ecEditReq.getDeviceNo(), ecEditReq.getDeviceCode(), ecEditReq.getVillageName());
        if (countCode > 0) {
            return JSONResult.errorMap("设备别名已存在");
        }
        XwEquipmentEcDO after = new XwEquipmentEcDO();
        BeanUtils.copyProperties(ecEditReq, after);
        if (StringUtil.isNotEmpty(ecEditReq.getProductTime())) {
            after.setProductTime(DateUtil.getStrToDate(ecEditReq.getProductTime()));
        } else {
            after.setProductTime(null);
        }
        if (StringUtil.isNotEmpty(ecEditReq.getSetupTime())) {
            after.setSetupTime(DateUtil.getStrToDate(ecEditReq.getSetupTime()));
        } else {
            after.setSetupTime(null);
        }
        if (StringUtil.isNotEmpty(ecEditReq.getSafeTime())) {
            after.setSafeTime(DateUtil.getStrToDate(ecEditReq.getSafeTime()));
            if(ecEditReq.getSafeTime().equals(equipmentEcDO.getSafeTime())==false){
                XwSafeRecordDO xwSafeRecordDO=new XwSafeRecordDO();
                xwSafeRecordDO.setDeviceNo(after.getDeviceNo());
                xwSafeRecordDO.setDeviceType("3");
                xwSafeRecordDO.setSafeRecordUuid(StrUtil.genUUID());
                xwSafeRecordDO.setDelFlag("0");
                xwSafeRecordDO.setSafeTime(DateUtil.getStrToDate(ecEditReq.getSafeTime()));
                xwSafeRecordDO.setSafeCompany(ecEditReq.getSafeCompany());
                xwSafeRecordDO.setSafePhone(ecEditReq.getSafePhone());
                xwSafeRecordDO.setSafeCompany(ecEditReq.getSafeCompany());
                xwSafeRecordDO.setCreateTime(DateUtils.getCurrentDateTime());
                xwSafeRecordDO.setCreateBy(redisUser.getSysUserName());
                xwSafeRecordDO.setSafeMan(ecEditReq.getSafeMan());
                xwSafeRecordDO.setProvinceId(ecEditReq.getProvinceId());
                xwSafeRecordDO.setProvinceName(ecEditReq.getProvince());
                xwSafeRecordDO.setCityId(ecEditReq.getCityId());
                xwSafeRecordDO.setCityName(ecEditReq.getCity());
                xwSafeRecordDO.setAreaId(ecEditReq.getAreaId());
                xwSafeRecordDO.setAreaName(ecEditReq.getArea());
                String safeTime=ecEditReq.getSafeTime();
                int year=Integer.valueOf(safeTime.substring(0,4));
                String month=safeTime.substring(5,7);
                if("0".equals(month.substring(0,1)))
                {
                    month=month.substring(1,2);
                }
                xwSafeRecordDO.setSafeYear(year);
                xwSafeRecordDO.setSafeMonth(Integer.valueOf(month));

                int i= safeMapper.addSafe(xwSafeRecordDO);
                if(i>0){
                    JSONResult.ok("新增维保时间成功");
                }else {
                    JSONResult.ok("新增维保时间失败");
                }
            }
        } else {
            after.setSafeTime(null);
        }

        after.setDelFlag("0");
        after.setUpdateBy(redisUser.getSysUserName());
        after.setUpdateTime(DateUtils.getCurrentDateTime());
        xwEquipmentEcDAO.updateEc(after);
        if (after != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_FOOR_INFO.getKey() + after.getDeviceNo(), JSONObject.toJSONString(after));
        }
        return JSONResult.ok("更新电梯设备成功");
    }

    @Override
    public JSONResult selEc() {
        List<XwEquipmentEcDO> equipmentEcDOS = ecMapper.selEc();
        if (!CollectionUtils.isEmpty(equipmentEcDOS)) {
            equipmentEcDOS.forEach(equipmentEcDO -> {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_FOOR_INFO.getKey() + equipmentEcDO.getDeviceNo(), JSONObject.toJSONString(equipmentEcDO));
            });
        }

        return JSONResult.ok("新增成功");
    }

    @Override
    public JSONResult editPush(CwPushReq cwPushReq, RedisUser redisUser) {
        XwEquipmentEcDO ecDO = ecMapper.uuid(cwPushReq.getEquipmentUuid());
        if (null == ecDO) {
            return JSONResult.errorMap("待编辑的设备不存在");
        }
        XwEquipmentEcDO after = new XwEquipmentEcDO();
        BeanUtils.copyProperties(cwPushReq, after);
        after.setDeviceNo(ecDO.getDeviceNo());
        if (cwPushReq.getFlag() == false) {
            after.setFaultPush(0);
        } else if (cwPushReq.getFlag() == true) {
            after.setFaultPush(1);
        }
        xwEquipmentEcDAO.updatePush(after);
        XwEquipmentEcDO after1=ecMapper.uuid(after.getEquipmentUuid());


        if (after1 != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_FOOR_INFO.getKey() + after1.getDeviceNo(), JSONObject.toJSONString(after1));
        }
        return JSONResult.ok("更新电梯推送状态成功");

    }





}
