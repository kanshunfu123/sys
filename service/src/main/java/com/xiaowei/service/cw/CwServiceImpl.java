package com.xiaowei.service.cw;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.cw.*;
import com.xiaowei.common.res.cw.CwEquipmentRes;
import com.xiaowei.common.res.cw.CwInfoRes;
import com.xiaowei.common.res.cw.CwSenceRes;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.XwEquipmentCwDAO;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.*;
import com.xiaowei.service.deviceroleById.DeviceRoleByUserIdService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import java.util.List;

/**
 * Created by kanshunfu on 2019/01/07.
 */
@Service
@Slf4j
public class CwServiceImpl implements CwService {
    @Autowired
    private XwEquipmentCwDAO xwEquipmentCwDAO;
    @Autowired
    private XwVillageMapper xwVillageMapper;
    @Autowired
    private CwMapper cwMapper;
    @Autowired
    private CwSenceMapper cwSenceMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private SafeMapper safeMapper;


    @Autowired
    private DeviceRoleByUserIdService deviceRoleByUserIdService;

    @Override
    public JSONResult interCw(CwAddReq cwAddReq, RedisUser redisUser) {
        XwEquipmentCwDO xwEquipmentCwDO = new XwEquipmentCwDO();
        BeanUtils.copyProperties(cwAddReq, xwEquipmentCwDO);
        int countCode = cwMapper.deviceCode(null, cwAddReq.getDeviceCode(), cwAddReq.getVillageName());
        if (countCode > 0) {
            return JSONResult.errorMap("设备别名已存在");
        }
        int countNo = cwMapper.deviceNo(cwAddReq.getDeviceNo());
        if (countNo > 0) {
            return JSONResult.errorMap("设备号已存在");
        }
        if (StringUtil.isNotEmpty(cwAddReq.getProductTime())) {
            xwEquipmentCwDO.setProductTime(DateUtil.getStrToDate(cwAddReq.getProductTime()));
        } else {
            xwEquipmentCwDO.setProductTime(null);
        }
        if (StringUtil.isNotEmpty(cwAddReq.getSetupTime())) {
            xwEquipmentCwDO.setSetupTime(DateUtil.getStrToDate(cwAddReq.getSetupTime()));
        } else {
            xwEquipmentCwDO.setSetupTime(null);
        }
        if (StringUtil.isNotEmpty(cwAddReq.getSafeTime())) {
            xwEquipmentCwDO.setSafeTime(DateUtil.getStrToDate(cwAddReq.getSafeTime()));
        } else {
            xwEquipmentCwDO.setSafeTime(null);
        }
        xwEquipmentCwDO.setEquipmentUuid(StrUtil.genUUID());
        xwEquipmentCwDO.setCreateBy(redisUser.getSysUserName());
        xwEquipmentCwDO.setUpdateBy(redisUser.getSysUserName());
        xwEquipmentCwDO.setDelFlag("0");
        xwEquipmentCwDO.setDeviceType("饮用水");
        xwEquipmentCwDO.setCreateTime(DateUtils.getCurrentDateTime());
        xwEquipmentCwDO.setUpdateTime(DateUtils.getCurrentDateTime());
        xwEquipmentCwDAO.saveCw(xwEquipmentCwDO);
        if (xwEquipmentCwDO != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + xwEquipmentCwDO.getDeviceNo(), JSONObject.toJSONString(xwEquipmentCwDO));
        }

        return JSONResult.ok("新增饮用水设备成功", xwEquipmentCwDO.getEquipmentUuid());

    }

    @Override
    public JSONResult uuidCw(CwUuidReq cwUuidReq, RedisUser redisUser) {
        CwInfoRes result = new CwInfoRes();
        CwEquipmentRes equipment = new CwEquipmentRes();
        CwSenceRes sence = new CwSenceRes();
        XwEquipmentCwDO xwEquipmentCwDO = cwMapper.uuid(cwUuidReq.getEquipmentUuid());
        if (null == xwEquipmentCwDO) {
            return JSONResult.errorMap("待查询的设备不存在");
        }
        //设备信息
        String deviceNo = xwEquipmentCwDO.getDeviceNo();
        BeanUtils.copyProperties(xwEquipmentCwDO, equipment);
        if (xwEquipmentCwDO.getSetupTime() != null) {
            equipment.setSetupTime(DateUtil.dateToString2(xwEquipmentCwDO.getSetupTime()));
        } else {
            equipment.setSetupTime("");
        }
        if (xwEquipmentCwDO.getProductTime() != null) {
            equipment.setProductTime(DateUtil.dateToString2(xwEquipmentCwDO.getProductTime()));
        } else {
            equipment.setProductTime("");
        }
        if (xwEquipmentCwDO.getSafeTime() != null) {
            equipment.setSafeTime(DateUtil.dateToString2(xwEquipmentCwDO.getSafeTime()));
        } else {
            equipment.setSafeTime("");
        }
        //场景信息
        XwCwSenceDO xwCwSenceDO = cwSenceMapper.cwSenceByNo(deviceNo);
        if (xwCwSenceDO != null) {
            BeanUtils.copyProperties(xwCwSenceDO, sence);
            if (xwCwSenceDO.getLastCleaningTime() != null) {
                sence.setLastCleaningTime(DateUtil.dateToString2(xwCwSenceDO.getLastCleaningTime()));
            } else {
                sence.setLastCleaningTime("");
            }
            if (xwCwSenceDO.getSenceInstallTime() != null) {
                sence.setSenceInstallTime(DateUtil.dateToString2(xwCwSenceDO.getSenceInstallTime()));
            } else {
                sence.setSenceInstallTime("");
            }
            if (StringUtils.isNotBlank(xwEquipmentCwDO.getAddress())) {
                sence.setLocation(xwEquipmentCwDO.getProvince() + xwEquipmentCwDO.getCity() + xwEquipmentCwDO.getArea() + xwEquipmentCwDO.getAddress());
            } else {
                sence.setLocation(xwEquipmentCwDO.getProvince() + xwEquipmentCwDO.getCity() + xwEquipmentCwDO.getArea());
            }
        }
        result.setEquipment(equipment);
        result.setSence(sence);
        return JSONResult.ok(result);
    }

    @Override
    public JSONResult editCw(CwEditReq cwEditReq, RedisUser redisUser) {
        XwEquipmentCwDO cwDO = cwMapper.uuid(cwEditReq.getEquipmentUuid());
        if (null == cwDO) {
            return JSONResult.errorMap("待编辑的设备不存在");
        }
        if (!cwDO.getDeviceNo().equals(cwEditReq.getDeviceNo())) {
            return JSONResult.errorMap("设备号不能编辑");
        }
        int countCode = cwMapper.deviceCode(cwEditReq.getDeviceNo(), cwEditReq.getDeviceCode(), cwEditReq.getVillageName());
        if (countCode > 0) {
            return JSONResult.errorMap("设备别名已存在");
        }
        XwEquipmentCwDO after = new XwEquipmentCwDO();
        BeanUtils.copyProperties(cwEditReq, after);
        if (StringUtil.isNotEmpty(cwEditReq.getProductTime())) {
            after.setProductTime(DateUtil.getStrToDate(cwEditReq.getProductTime()));
        } else {
            after.setProductTime(null);
        }
        if (StringUtil.isNotEmpty(cwEditReq.getSetupTime())) {
            after.setSetupTime(DateUtil.getStrToDate(cwEditReq.getSetupTime()));
        } else {
            after.setSetupTime(null);
        }
        if (StringUtil.isNotEmpty(cwEditReq.getSafeTime())) {
            after.setSafeTime(DateUtil.getStrToDate(cwEditReq.getSafeTime()));
            if(cwEditReq.getSafeTime().equals(cwDO.getSafeTime())==false){
                XwSafeRecordDO xwSafeRecordDO=new XwSafeRecordDO();
                xwSafeRecordDO.setDeviceNo(after.getDeviceNo());
                xwSafeRecordDO.setDeviceType("2");
                xwSafeRecordDO.setSafeRecordUuid(StrUtil.genUUID());
                xwSafeRecordDO.setDelFlag("0");
                xwSafeRecordDO.setSafeTime(DateUtil.getStrToDate(cwEditReq.getSafeTime()));
                xwSafeRecordDO.setSafeCompany(cwEditReq.getSafeCompany());
                xwSafeRecordDO.setSafePhone(cwEditReq.getSafePhone());
                xwSafeRecordDO.setSafeCompany(cwEditReq.getSafeCompany());
                xwSafeRecordDO.setCreateTime(DateUtils.getCurrentDateTime());
                xwSafeRecordDO.setCreateBy(redisUser.getSysUserName());
                xwSafeRecordDO.setSafeMan(cwEditReq.getSafeMan());
                xwSafeRecordDO.setProvinceId(cwEditReq.getProvinceId());
                xwSafeRecordDO.setProvinceName(cwEditReq.getProvince());
                xwSafeRecordDO.setCityId(cwEditReq.getCityId());
                xwSafeRecordDO.setCityName(cwEditReq.getCity());
                xwSafeRecordDO.setAreaId(cwEditReq.getAreaId());
                xwSafeRecordDO.setAreaName(cwEditReq.getArea());
                String safeTime=cwEditReq.getSafeTime();
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
        xwEquipmentCwDAO.updateCw(after);
        if (after != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + after.getDeviceNo(), JSONObject.toJSONString(after));
        }
        return JSONResult.ok("更新饮用水设备成功");

    }

    @Override
    public JSONResult selCw() {
        List<XwEquipmentCwDO> equipmentEcDOS = cwMapper.selCw();
        if (!CollectionUtils.isEmpty(equipmentEcDOS)) {
            equipmentEcDOS.forEach(equipmentEcDO -> {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + equipmentEcDO.getDeviceNo(), JSONObject.toJSONString(equipmentEcDO));
            });
        }

        return JSONResult.ok("新增成功");
    }

    @Override
    public JSONResult editPush(CwPushReq cwPushReq, RedisUser redisUser) {
        XwEquipmentCwDO cwDO = cwMapper.uuid(cwPushReq.getEquipmentUuid());
        if (null == cwDO) {
            return JSONResult.errorMap("待编辑的设备不存在");
        }
        XwEquipmentCwDO after = new XwEquipmentCwDO();
        BeanUtils.copyProperties(cwPushReq, after);
        after.setDeviceNo(cwDO.getDeviceNo());
        if (cwPushReq.getFlag() == false) {
            after.setFaultPush(0);
        } else if (cwPushReq.getFlag() == true) {
            after.setFaultPush(1);
        }
        xwEquipmentCwDAO.updatePush(after);
        XwEquipmentCwDO after1=cwMapper.uuid(after.getEquipmentUuid());
        if (after1 != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_CW_INFO.getKey() + after1.getDeviceNo(), JSONObject.toJSONString(after1));
        }
        return JSONResult.ok("更新饮用水推送状态成功");

    }


}
