package com.xiaowei.service.mh;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.DeviceUserRoleReq;
import com.xiaowei.common.req.cw.CwPushReq;
import com.xiaowei.common.req.mh.MhAddReq;
import com.xiaowei.common.req.mh.MhEditReq;
import com.xiaowei.common.req.mh.MhPageList;
import com.xiaowei.common.req.mh.MhUuidReq;
import com.xiaowei.common.req.rw.RwPageList;
import com.xiaowei.common.res.DeviceRoleRea;
import com.xiaowei.common.res.mh.MhEquipmentRes;
import com.xiaowei.common.res.mh.MhInfoRes;
import com.xiaowei.common.res.mh.MhPageListRes;
import com.xiaowei.common.res.mh.MhSenceRes;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.XwEquipmentMhDAO;
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
public class MhServiceImpl implements MhService {
    @Autowired
    private XwEquipmentMhDAO xwEquipmentMhDAO;
    @Autowired
    private MhMapper mhMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MhSenceMapper mhSenceMapper;
    @Autowired
    private SafeMapper safeMapper;

    @Override
    public JSONResult interMh(MhAddReq mhAddReq,RedisUser redisUser) {
        XwEquipmentMhDO mhDO = new XwEquipmentMhDO();
        int countCode = mhMapper.deviceCode(null,mhAddReq.getDeviceCode(),mhAddReq.getStreet());
        if (countCode > 0) {
            return JSONResult.errorMap("设备别名已存在");
        }
        int countNo = mhMapper.deviceNo(mhAddReq.getDeviceNo());
        if (countNo > 0) {
            return JSONResult.errorMap("设备号已存在");
        }
        if(StringUtil.isNotEmpty(mhAddReq.getProductTime())){
            mhDO.setProductTime(DateUtil.getStrToDate(mhAddReq.getProductTime()));
        }else {
            mhDO.setProductTime(null);
        }
        if(StringUtil.isNotEmpty(mhAddReq.getSetupTime())){
            mhDO.setSetupTime(DateUtil.getStrToDate(mhAddReq.getSetupTime()));
        }else {
            mhDO.setSetupTime(null);
        }
        if(StringUtil.isNotEmpty(mhAddReq.getSafeTime())){
            mhDO.setSafeTime(DateUtil.getStrToDate(mhAddReq.getSafeTime()));
        }else {
            mhDO.setSafeTime(null);
        }
        BeanUtils.copyProperties(mhAddReq, mhDO);
        mhDO.setEquipmentUuid(StrUtil.genUUID());
        mhDO.setCreateBy(redisUser.getSysUserName());
        mhDO.setUpdateBy(redisUser.getSysUserName());
        mhDO.setDelFlag("0");
        mhDO.setDeviceType("井盖");
        mhDO.setCreateTime(DateUtils.getCurrentDateTime());
        mhDO.setUpdateTime(DateUtils.getCurrentDateTime());
        xwEquipmentMhDAO.insertMh(mhDO);
        if (mhDO != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + mhDO.getDeviceNo(), JSONObject.toJSONString(mhDO));
        }

        return JSONResult.ok("新增井盖设备成功", mhDO.getEquipmentUuid());
    }

    @Override
    public JSONResult uuidMh(MhUuidReq mhUuidReq,RedisUser redisUser) {
        MhInfoRes result = new MhInfoRes();
        MhEquipmentRes equipment = new MhEquipmentRes();
        MhSenceRes sence = new MhSenceRes();
        XwEquipmentMhDO mhDO = mhMapper.uuid(mhUuidReq.getEquipmentUuid());
        if (null == mhDO) {
            return JSONResult.errorMap("待查询的设备不存在");
        }
        //设备信息
        String deviceNo = mhDO.getDeviceNo();
        BeanUtils.copyProperties(mhDO, equipment);
        if (mhDO.getSetupTime() != null) {
            equipment.setSetupTime(DateUtil.dateToString2(mhDO.getSetupTime()));
        } else {
            equipment.setSetupTime("");
        }
        if (mhDO.getProductTime() != null) {
            equipment.setProductTime(DateUtil.dateToString2(mhDO.getProductTime()));
        } else {
            equipment.setProductTime("");
        }
        if(mhDO.getSafeTime()!=null){
            equipment.setSafeTime(DateUtil.dateToString2(mhDO.getSafeTime()));
        }else {
            equipment.setSafeTime("");
        }
        //场景信息
        XwMhSenceDO mhSenceDO = mhSenceMapper.mhSenceByNo(deviceNo);
        if (mhSenceDO != null) {
            BeanUtils.copyProperties(mhSenceDO, sence);
            if (mhSenceDO.getSenceInstallTime() != null) {
                sence.setSenceInstallTime(DateUtil.dateToString2(mhSenceDO.getSenceInstallTime()));
            } else {
                sence.setSenceInstallTime("");
            }
            if (mhSenceDO.getMhEndTime() != null) {
                sence.setMhEndTime(DateUtil.dateToString2(mhSenceDO.getMhEndTime()));
            } else {
                sence.setMhEndTime("");
            }
            if (StringUtils.isNotBlank(mhDO.getAddress())) {
                sence.setLocation(mhDO.getProvince() + mhDO.getCity() + mhDO.getArea() + mhDO.getAddress());
            } else {
                sence.setLocation(mhDO.getProvince() + mhDO.getCity() + mhDO.getArea());
            }
        }
        result.setMhEquipmentRes(equipment);
        result.setMhSenceRes(sence);
        return JSONResult.ok(result);
    }

    @Override
    public JSONResult editMh(MhEditReq mhEditReq,RedisUser redisUser) {
        XwEquipmentMhDO equipmentMhDO = mhMapper.uuid(mhEditReq.getEquipmentUuid());
        if (null == equipmentMhDO) {
            return JSONResult.errorMap("待编辑的设备不存在");
        }
        if (!equipmentMhDO.getDeviceNo().equals(mhEditReq.getDeviceNo())) {
            return JSONResult.errorMap("设备号不能编辑");
        }
        int countCode = mhMapper.deviceCode(mhEditReq.getDeviceNo(),mhEditReq.getDeviceCode(),mhEditReq.getStreet());
        if (countCode > 0) {
            return JSONResult.errorMap("设备别名已存在");
        }

        XwEquipmentMhDO after = new XwEquipmentMhDO();
        BeanUtils.copyProperties(mhEditReq, after);
        if(StringUtil.isNotEmpty(mhEditReq.getProductTime())){
            after.setProductTime(DateUtil.getStrToDate(mhEditReq.getProductTime()));
        }else {
            after.setProductTime(null);
        }
        if(StringUtil.isNotEmpty(mhEditReq.getSetupTime())){
            after.setSetupTime(DateUtil.getStrToDate(mhEditReq.getSetupTime()));
        }else {
            after.setSetupTime(null);
        }
        if(StringUtil.isNotEmpty(mhEditReq.getSafeTime())){
            after.setSafeTime(DateUtil.getStrToDate(mhEditReq.getSafeTime()));
            if(mhEditReq.getSafeTime().equals(equipmentMhDO.getSafeTime())==false){
                XwSafeRecordDO xwSafeRecordDO=new XwSafeRecordDO();
                xwSafeRecordDO.setDeviceNo(after.getDeviceNo());
                xwSafeRecordDO.setDeviceType("1");
                xwSafeRecordDO.setSafeRecordUuid(StrUtil.genUUID());
                xwSafeRecordDO.setDelFlag("0");
                xwSafeRecordDO.setSafeTime(DateUtil.getStrToDate(mhEditReq.getSafeTime()));
                xwSafeRecordDO.setSafeCompany(mhEditReq.getSafeCompany());
                xwSafeRecordDO.setSafePhone(mhEditReq.getSafePhone());
                xwSafeRecordDO.setSafeCompany(mhEditReq.getSafeCompany());
                xwSafeRecordDO.setCreateTime(DateUtils.getCurrentDateTime());
                xwSafeRecordDO.setCreateBy(redisUser.getSysUserName());
                xwSafeRecordDO.setSafeMan(mhEditReq.getSafeMan());
                xwSafeRecordDO.setProvinceId(mhEditReq.getProvinceId());
                xwSafeRecordDO.setProvinceName(mhEditReq.getProvince());
                xwSafeRecordDO.setCityId(mhEditReq.getCityId());
                xwSafeRecordDO.setCityName(mhEditReq.getCity());
                xwSafeRecordDO.setAreaId(mhEditReq.getAreaId());
                xwSafeRecordDO.setAreaName(mhEditReq.getArea());
                String safeTime=mhEditReq.getSafeTime();
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
        }else {
            after.setSafeTime(null);
        }
        after.setDelFlag("0");
        after.setUpdateBy(redisUser.getSysUserName());
        after.setUpdateTime(DateUtils.getCurrentDateTime());
        xwEquipmentMhDAO.updateMh(after);
        if (after != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + after.getDeviceNo(), JSONObject.toJSONString(after));
        }
        return JSONResult.ok("更新井盖设备成功");
    }

    @Override
    public JSONResult selMh() {
        List<XwEquipmentMhDO> equipmentEcDOS = mhMapper.selMh();
        if (!CollectionUtils.isEmpty(equipmentEcDOS)) {
            equipmentEcDOS.forEach(equipmentEcDO -> {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey()+equipmentEcDO.getDeviceNo(), JSONObject.toJSONString(equipmentEcDO) );
            });
        }

        return JSONResult.ok("新增成功");
    }

    @Override
    public JSONResult editPush(CwPushReq cwPushReq, RedisUser redisUser) {
        XwEquipmentMhDO mhDO = mhMapper.uuid(cwPushReq.getEquipmentUuid());
        if (null == mhDO) {
            return JSONResult.errorMap("待编辑的设备不存在");
        }
        XwEquipmentMhDO after = new XwEquipmentMhDO();
        BeanUtils.copyProperties(cwPushReq, after);
        after.setDeviceNo(mhDO.getDeviceNo());
        if (cwPushReq.getFlag() == false) {
            after.setFaultPush(0);
        } else if (cwPushReq.getFlag() == true) {
            after.setFaultPush(1);
        }
        xwEquipmentMhDAO.updatePush(after);
        XwEquipmentMhDO after1=mhMapper.uuid(after.getEquipmentUuid());
        if (after1 != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_MH_INFO.getKey() + after1.getDeviceNo(), JSONObject.toJSONString(after1));
        }
        return JSONResult.ok("更新井盖推送状态成功");

    }

}
