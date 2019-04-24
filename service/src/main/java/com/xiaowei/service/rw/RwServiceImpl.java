package com.xiaowei.service.rw;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;
import com.google.common.collect.Lists;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.DeviceUserRoleReq;
import com.xiaowei.common.req.cw.CwPushReq;
import com.xiaowei.common.req.rw.RwAddReq;
import com.xiaowei.common.req.rw.RwEditReq;
import com.xiaowei.common.req.rw.RwPageList;
import com.xiaowei.common.req.rw.RwUuidReq;
import com.xiaowei.common.res.DeviceRoleRea;
import com.xiaowei.common.res.cw.CwPageListRes;
import com.xiaowei.common.res.ec.EcPageListRes;
import com.xiaowei.common.res.mh.MhPageListRes;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.res.rw.RwEquipmentRes;
import com.xiaowei.common.res.rw.RwInfoRes;
import com.xiaowei.common.res.rw.RwPageListRes;
import com.xiaowei.common.res.rw.RwSenceRes;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.XwEquipmentRwDAO;
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
import java.util.stream.Collectors;

/**
 * Created by kanshunfu on 2019/01/07.
 */
@Service
@Slf4j
public class RwServiceImpl implements RwService {
    @Autowired
    private XwEquipmentRwDAO xwEquipmentRwDAO;
    @Autowired
    private RwMapper rwMapper;
    @Autowired
    private MhMapper mhMapper;
    @Autowired
    private CwMapper cwMapper;
    @Autowired
    private EcMapper ecMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RwSenceMapper rwSenceMapper;
    @Autowired
    private DeviceRoleByUserIdService deviceRoleByUserIdService;
    @Autowired
    private SafeMapper safeMapper;


    @Override
    public JSONResult interRw(RwAddReq rwAddReq, RedisUser redisUser) {

        XwEquipmentRwDO rwDO = new XwEquipmentRwDO();
        int countCode = rwMapper.deviceCode(null, rwAddReq.getDeviceCode(), rwAddReq.getArea());
        if (countCode > 0) {
            return JSONResult.errorMap("设备别名已存在");
        }
        int countNo = rwMapper.deviceNo(rwAddReq.getDeviceNo());
        if (countNo > 0) {
            return JSONResult.errorMap("设备号已存在");
        }
        if (StringUtil.isNotEmpty(rwAddReq.getProductTime())) {
            rwDO.setProductTime(DateUtil.getStrToDate(rwAddReq.getProductTime()));
        } else {
            rwDO.setProductTime(null);
        }
        if (StringUtil.isNotEmpty(rwAddReq.getSetupTime())) {
            rwDO.setSetupTime(DateUtil.getStrToDate(rwAddReq.getSetupTime()));
        } else {
            rwDO.setSetupTime(null);
        }
        if (StringUtil.isNotEmpty(rwAddReq.getSafeTime())) {
            rwDO.setSafeTime(DateUtil.getStrToDate(rwAddReq.getSafeTime()));
        } else {
            rwDO.setSafeTime(null);
        }
        BeanUtils.copyProperties(rwAddReq, rwDO);
        rwDO.setEquipmentUuid(StrUtil.genUUID());
        rwDO.setCreateBy(redisUser.getSysUserName());
        rwDO.setUpdateBy(redisUser.getSysUserName());
        rwDO.setDelFlag("0");
        rwDO.setDeviceType("地表水");
        rwDO.setCreateTime(DateUtils.getCurrentDateTime());
        rwDO.setUpdateTime(DateUtils.getCurrentDateTime());
        xwEquipmentRwDAO.insertRw(rwDO);
        if (rwDO != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + rwDO.getDeviceNo(), JSONObject.toJSONString(rwDO));
        }

        return JSONResult.ok("新增地表水设备成功", rwDO.getEquipmentUuid());
    }

    @Override
    public JSONResult uuidRw(RwUuidReq rwUuidReq, RedisUser redisUser) {
        RwInfoRes result = new RwInfoRes();
        RwEquipmentRes equipment = new RwEquipmentRes();
        RwSenceRes sence = new RwSenceRes();
        XwEquipmentRwDO rwDO = rwMapper.uuid(rwUuidReq.getEquipmentUuid());
        if (null == rwDO) {
            return JSONResult.errorMap("待查询的设备不存在");
        }
        //设备信息
        String deviceNo = rwDO.getDeviceNo();
        BeanUtils.copyProperties(rwDO, equipment);
        if (rwDO.getSetupTime() != null) {
            equipment.setSetupTime(DateUtil.dateToString2(rwDO.getSetupTime()));
        } else {
            equipment.setSetupTime("");
        }
        if (rwDO.getProductTime() != null) {
            equipment.setProductTime(DateUtil.dateToString2(rwDO.getProductTime()));
        } else {
            equipment.setProductTime("");
        }
        if (rwDO.getSafeTime() != null) {
            equipment.setSafeTime(DateUtil.dateToString2(rwDO.getSafeTime()));
        } else {
            equipment.setSafeTime("");
        }
        //场景信息
        XwRwSenceDO rwSenceDO = rwSenceMapper.rwSenceByNo(deviceNo);
        if (rwSenceDO != null) {
            BeanUtils.copyProperties(rwSenceDO, sence);
            if (rwSenceDO.getSenceInstallTime() != null) {
                sence.setSenceInstallTime(DateUtil.dateToString2(rwSenceDO.getSenceInstallTime()));
            } else {
                sence.setSenceInstallTime("");
            }

            if (StringUtils.isNotBlank(rwDO.getAddress())) {
                sence.setLocation(rwDO.getProvince() + rwDO.getCity() + rwDO.getArea() + rwDO.getAddress());
            } else {
                sence.setLocation(rwDO.getProvince() + rwDO.getCity() + rwDO.getArea());
            }
        }
        result.setRwEquipmentRes(equipment);
        result.setRwSenceRes(sence);
        return JSONResult.ok(result);
    }

    @Override
    public JSONResult editRw(RwEditReq rwEditReq, RedisUser redisUser) {
        XwEquipmentRwDO equipmentRwDO = rwMapper.uuid(rwEditReq.getEquipmentUuid());
        if (null == equipmentRwDO) {
            return JSONResult.errorMap("待编辑的设备不存在");
        }
        if (!equipmentRwDO.getDeviceNo().equals(rwEditReq.getDeviceNo())) {
            return JSONResult.errorMap("设备号不能编辑");
        }
        int countCode = rwMapper.deviceCode(rwEditReq.getDeviceNo(), rwEditReq.getDeviceCode(), rwEditReq.getArea());
        if (countCode > 0) {
            return JSONResult.errorMap("设备别名已存在");
        }
        XwEquipmentRwDO after = new XwEquipmentRwDO();
        BeanUtils.copyProperties(rwEditReq, after);
        if (StringUtil.isNotEmpty(rwEditReq.getProductTime())) {
            after.setProductTime(DateUtil.getStrToDate(rwEditReq.getProductTime()));
        } else {
            after.setProductTime(null);
        }
        if (StringUtil.isNotEmpty(rwEditReq.getSetupTime())) {
            after.setSetupTime(DateUtil.getStrToDate(rwEditReq.getSetupTime()));
        } else {
            after.setSetupTime(null);
        }
        if (StringUtil.isNotEmpty(rwEditReq.getSafeTime())) {
            after.setSafeTime(DateUtil.getStrToDate(rwEditReq.getSafeTime()));
            if(rwEditReq.getSafeTime().equals(equipmentRwDO.getSafeTime())==false){
                XwSafeRecordDO xwSafeRecordDO=new XwSafeRecordDO();
                xwSafeRecordDO.setDeviceNo(after.getDeviceNo());
                xwSafeRecordDO.setDeviceType("0");
                xwSafeRecordDO.setSafeRecordUuid(StrUtil.genUUID());
                xwSafeRecordDO.setDelFlag("0");
                xwSafeRecordDO.setSafeTime(DateUtil.getStrToDate(rwEditReq.getSafeTime()));
                xwSafeRecordDO.setSafeCompany(rwEditReq.getSafeCompany());
                xwSafeRecordDO.setSafePhone(rwEditReq.getSafePhone());
                xwSafeRecordDO.setSafeCompany(rwEditReq.getSafeCompany());
                xwSafeRecordDO.setCreateTime(DateUtils.getCurrentDateTime());
                xwSafeRecordDO.setCreateBy(redisUser.getSysUserName());
                xwSafeRecordDO.setSafeMan(rwEditReq.getSafeMan());
                xwSafeRecordDO.setProvinceId(rwEditReq.getProvinceId());
                xwSafeRecordDO.setProvinceName(rwEditReq.getProvince());
                xwSafeRecordDO.setCityId(rwEditReq.getCityId());
                xwSafeRecordDO.setCityName(rwEditReq.getCity());
                xwSafeRecordDO.setAreaId(rwEditReq.getAreaId());
                xwSafeRecordDO.setAreaName(rwEditReq.getArea());
                String safeTime=rwEditReq.getSafeTime();
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

        xwEquipmentRwDAO.updateRw(after);
        if (after != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + after.getDeviceNo(), JSONObject.toJSONString(after));
        }
        return JSONResult.ok("更新地表水设备成功");
    }

    @Override
    public PageInfo pageList(RwPageList rwPageList, RedisUser redisUser) {
        DeviceUserRoleReq req = new DeviceUserRoleReq();
        req.setUserId(redisUser.getId());
        //小为科技
        if (redisUser.getGroupId() == 1) {
            String deviceNo = rwPageList.getDeviceNo();
            String startTime = rwPageList.getStartTime();
            String endTime = rwPageList.getEndTime();
            Long provinceId = rwPageList.getProvinceId();
            Long cityId = rwPageList.getCityId();
            Long areaId = rwPageList.getAreaId();
            return deviceList(rwPageList.getDeviceType(), rwPageList, null, deviceNo, startTime, endTime, provinceId, cityId, areaId);
        } else {
            //第三方
            //0 地表水 1井盖 2生活用水 3电梯'
            DeviceRoleRea deviceRoleRea = deviceRoleByUserIdService.getDeviceByUserId(req);
            if (StringUtils.isNotEmpty(rwPageList.getDeviceType())) {
                if ("0".equals(rwPageList.getDeviceType())) {
                    List<DeviceRoleDeviceRea> rwList = deviceRoleRea.getRw();
                    if (!CollectionUtils.isEmpty(rwList)) {
                        List<String> rwDevices = null;
                        String deviceNo = rwPageList.getDeviceNo();
                        String startTime = rwPageList.getStartTime();
                        String endTime = rwPageList.getEndTime();
                        Long provinceId = rwPageList.getProvinceId();
                        Long cityId = rwPageList.getCityId();
                        Long areaId = rwPageList.getAreaId();
                        rwDevices = rwList.stream().map(rw -> rw.getDeviceNo()).collect(Collectors.toList());

                        return deviceList(rwPageList.getDeviceType(), rwPageList, rwDevices, deviceNo, startTime, endTime, provinceId, cityId, areaId);
                    } else {
                        return deviceList("-1", rwPageList, null, null, null, null, null, null, null);
                    }

                } else if ("1".equals(rwPageList.getDeviceType())) {
                    List<DeviceRoleDeviceRea> mhList = deviceRoleRea.getMh();
                    if (!CollectionUtils.isEmpty(mhList)) {
                        List<String> rwDevices = null;
                        String deviceNo = rwPageList.getDeviceNo();
                        String startTime = rwPageList.getStartTime();
                        String endTime = rwPageList.getEndTime();
                        Long provinceId = rwPageList.getProvinceId();
                        Long cityId = rwPageList.getCityId();
                        Long areaId = rwPageList.getAreaId();
                        rwDevices = mhList.stream().map(mh -> mh.getDeviceNo()).collect(Collectors.toList());

                        return deviceList(rwPageList.getDeviceType(), rwPageList, rwDevices, deviceNo, startTime, endTime, provinceId, cityId, areaId);
                    } else {
                        return deviceList("-1", rwPageList, null, null, null, null, null, null, null);
                    }
                } else if ("2".equals(rwPageList.getDeviceType())) {
                    List<DeviceRoleDeviceRea> cwList = deviceRoleRea.getCw();
                    if (!CollectionUtils.isEmpty(cwList)) {
                        List<String> rwDevices = null;
                        String deviceNo = rwPageList.getDeviceNo();
                        String startTime = rwPageList.getStartTime();
                        String endTime = rwPageList.getEndTime();
                        Long provinceId = rwPageList.getProvinceId();
                        Long cityId = rwPageList.getCityId();
                        Long areaId = rwPageList.getAreaId();
                        rwDevices = cwList.stream().map(cw -> cw.getDeviceNo()).collect(Collectors.toList());
                        return deviceList(rwPageList.getDeviceType(), rwPageList, rwDevices, deviceNo, startTime, endTime, provinceId, cityId, areaId);
                    } else {
                        return deviceList("-1", rwPageList, null, null, null, null, null, null, null);
                    }

                } else if ("3".equals(rwPageList.getDeviceType())) {
                    List<DeviceRoleDeviceRea> ecList = deviceRoleRea.getEc();
                    if (!CollectionUtils.isEmpty(ecList)) {
                        List<String> rwDevices = null;
                        String deviceNo = rwPageList.getDeviceNo();
                        String startTime = rwPageList.getStartTime();
                        String endTime = rwPageList.getEndTime();
                        Long provinceId = rwPageList.getProvinceId();
                        Long cityId = rwPageList.getCityId();
                        Long areaId = rwPageList.getAreaId();
                        rwDevices = ecList.stream().map(ec -> ec.getDeviceNo()).collect(Collectors.toList());
                        return deviceList(rwPageList.getDeviceType(), rwPageList, rwDevices, deviceNo, startTime, endTime, provinceId, cityId, areaId);
                    } else {
                        return deviceList("-1", rwPageList, null, null, null, null, null, null, null);
                    }
                }
            }
        }
        return deviceList("-1", rwPageList, null, null, null, null, null, null, null);
    }

    @Override
    public JSONResult selRw() {
        List<XwEquipmentRwDO> equipmentEcDOS = rwMapper.selRw();
        if (!CollectionUtils.isEmpty(equipmentEcDOS)) {
            equipmentEcDOS.forEach(equipmentEcDO -> {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + equipmentEcDO.getDeviceNo(), JSONObject.toJSONString(equipmentEcDO));
            });
        }

        return JSONResult.ok("新增成功");
    }

    @Override
    public JSONResult editPush(CwPushReq cwPushReq, RedisUser redisUser) {
        XwEquipmentRwDO cwDO = rwMapper.uuid(cwPushReq.getEquipmentUuid());
        if (null == cwDO) {
            return JSONResult.errorMap("待编辑的设备不存在");
        }
        XwEquipmentRwDO after = new XwEquipmentRwDO();
        BeanUtils.copyProperties(cwPushReq, after);
        after.setDeviceNo(cwDO.getDeviceNo());
        if (cwPushReq.getFlag() == false) {
            after.setFaultPush(0);
        } else if (cwPushReq.getFlag() == true) {
            after.setFaultPush(1);
        }
        xwEquipmentRwDAO.updatePush(after);
        XwEquipmentRwDO after1=rwMapper.uuid(cwPushReq.getEquipmentUuid());

        if (after1 != null) {
            redisUtil.set(RedisKeyEnum.REDIS_KEY_RW_INFO.getKey() + after1.getDeviceNo(), JSONObject.toJSONString(after1));
        }
        return JSONResult.ok("更新地表水推送状态成功");

    }

    //deviceType   0 地表水 1井盖 2生活用水 3电梯'
    public PageInfo deviceList(String deviceType, RwPageList rwPageList, List<String> deviceNo, String deviceNo1, String startTime, String endTime, Long provinceId, Long cityId, Long areaId) {
        switch (deviceType) {
            case "0":
                PageHelper.startPage(rwPageList.getCurrPageNo(), rwPageList.getLimit());
                List<XwEquipmentRwDO> list = rwMapper.pageList(deviceNo, deviceNo1, startTime, endTime, provinceId, cityId, areaId);
                PageInfo<XwEquipmentRwDO> pageInfoRW = new PageInfo<>(list);
                List<XwEquipmentRwDO> xwEquipmentRwDOS = pageInfoRW.getList();
                List<RwPageListRes> rwPageListRes = Lists.newArrayList();
                if (!CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
                    xwEquipmentRwDOS.forEach(xwEquipmentRwDO -> {
                        RwPageListRes rwPageListRes1 = new RwPageListRes();
                        rwPageListRes1.setSetupTime(DateUtil.dateToString2(xwEquipmentRwDO.getSetupTime()));
                        if (StringUtils.isNotBlank(xwEquipmentRwDO.getAddress())) {
                            rwPageListRes1.setLocaltion(xwEquipmentRwDO.getProvince() + xwEquipmentRwDO.getCity() + xwEquipmentRwDO.getArea() + xwEquipmentRwDO.getAddress());
                        } else {
                            rwPageListRes1.setLocaltion(xwEquipmentRwDO.getProvince() + xwEquipmentRwDO.getCity() + xwEquipmentRwDO.getArea());
                        }
                        BeanUtils.copyProperties(xwEquipmentRwDO, rwPageListRes1);
                        rwPageListRes.add(rwPageListRes1);
                    });
                }
                PageInfo<RwPageListRes> rwPageListResPageInfo = new PageInfo<>(rwPageListRes);
                rwPageListResPageInfo.setTotal(pageInfoRW.getTotal());
                rwPageListResPageInfo.setPageNum(rwPageList.getCurrPageNo());
                return rwPageListResPageInfo;
            case "1":
                PageHelper.startPage(rwPageList.getCurrPageNo(), rwPageList.getLimit());
                List<XwEquipmentMhDO> list1 = mhMapper.pageList(deviceNo, deviceNo1, startTime, endTime, provinceId, cityId, areaId);
                PageInfo<XwEquipmentMhDO> pageInfoMh = new PageInfo<>(list1);
                List<XwEquipmentMhDO> xwEquipmentMhDOS = pageInfoMh.getList();
                List<MhPageListRes> mhPageListRes = Lists.newArrayList();
                if (!CollectionUtils.isEmpty(xwEquipmentMhDOS)) {
                    xwEquipmentMhDOS.forEach(xwEquipmentMhDO -> {
                        MhPageListRes mhPageListRes1 = new MhPageListRes();
                        mhPageListRes1.setSetupTime(DateUtil.dateToString2(xwEquipmentMhDO.getSetupTime()));
                        if (StringUtils.isNotBlank(xwEquipmentMhDO.getAddress())) {
                            mhPageListRes1.setLocaltion(xwEquipmentMhDO.getProvince() + xwEquipmentMhDO.getCity() + xwEquipmentMhDO.getArea() + xwEquipmentMhDO.getStreet() + xwEquipmentMhDO.getAddress());
                        } else {
                            mhPageListRes1.setLocaltion(xwEquipmentMhDO.getProvince() + xwEquipmentMhDO.getCity() + xwEquipmentMhDO.getArea() + xwEquipmentMhDO.getStreet());
                        }
                        BeanUtils.copyProperties(xwEquipmentMhDO, mhPageListRes1);
                        mhPageListRes.add(mhPageListRes1);
                    });
                }
                PageInfo<MhPageListRes> mhPageListResPageInfo = new PageInfo<>(mhPageListRes);
                mhPageListResPageInfo.setTotal(pageInfoMh.getTotal());
                mhPageListResPageInfo.setPageNum(rwPageList.getCurrPageNo());
                return mhPageListResPageInfo;
            case "2":
                PageHelper.startPage(rwPageList.getCurrPageNo(), rwPageList.getLimit());
                List<XwEquipmentCwDO> list2 = cwMapper.pageList(deviceNo, deviceNo1, startTime, endTime, provinceId, cityId, areaId);
                PageInfo<XwEquipmentCwDO> pageInfoCW = new PageInfo<>(list2);
                List<XwEquipmentCwDO> xwEquipmentCwDOS = pageInfoCW.getList();
                List<CwPageListRes> cwPageListRes = Lists.newArrayList();
                if (!CollectionUtils.isEmpty(xwEquipmentCwDOS)) {
                    xwEquipmentCwDOS.forEach(xwEquipmentCwDO -> {
                        CwPageListRes cwPageListRes1 = new CwPageListRes();
                        cwPageListRes1.setSetupTime(DateUtil.dateToString2(xwEquipmentCwDO.getSetupTime()));
                        if (StringUtils.isNotBlank(xwEquipmentCwDO.getAddress())) {
                            cwPageListRes1.setLocaltion(xwEquipmentCwDO.getProvince() + xwEquipmentCwDO.getCity() + xwEquipmentCwDO.getArea() + xwEquipmentCwDO.getStreet() + xwEquipmentCwDO.getVillageName() + xwEquipmentCwDO.getAddress());
                        } else {
                            cwPageListRes1.setLocaltion(xwEquipmentCwDO.getProvince() + xwEquipmentCwDO.getCity() + xwEquipmentCwDO.getArea() + xwEquipmentCwDO.getStreet() + xwEquipmentCwDO.getVillageName());
                        }
                        BeanUtils.copyProperties(xwEquipmentCwDO, cwPageListRes1);
                        cwPageListRes.add(cwPageListRes1);
                    });
                }
                PageInfo<CwPageListRes> cwPageListResPageInfo = new PageInfo<>(cwPageListRes);
                cwPageListResPageInfo.setTotal(pageInfoCW.getTotal());
                cwPageListResPageInfo.setPageNum(rwPageList.getCurrPageNo());
                return cwPageListResPageInfo;
            case "3":
                PageHelper.startPage(rwPageList.getCurrPageNo(), rwPageList.getLimit());
                List<XwEquipmentEcDO> list3 = ecMapper.pageList(deviceNo, deviceNo1, startTime, endTime, provinceId, cityId, areaId);
                PageInfo<XwEquipmentEcDO> pageInfoEC = new PageInfo<>(list3);
                List<XwEquipmentEcDO> xwEquipmentEcDOS = pageInfoEC.getList();
                List<EcPageListRes> ecPageListRes = Lists.newArrayList();
                if (!CollectionUtils.isEmpty(xwEquipmentEcDOS)) {
                    xwEquipmentEcDOS.forEach(xwEquipmentEcDO -> {
                        EcPageListRes ecPageListRes1 = new EcPageListRes();
                        ecPageListRes1.setSetupTime(DateUtil.dateToString2(xwEquipmentEcDO.getSetupTime()));
                        if (StringUtils.isNotBlank(xwEquipmentEcDO.getAddress())) {
                            ecPageListRes1.setLocaltion(xwEquipmentEcDO.getProvince() + xwEquipmentEcDO.getCity() + xwEquipmentEcDO.getArea() + xwEquipmentEcDO.getStreet() + xwEquipmentEcDO.getVillageName() + xwEquipmentEcDO.getAddress());
                        } else {
                            ecPageListRes1.setLocaltion(xwEquipmentEcDO.getProvince() + xwEquipmentEcDO.getCity() + xwEquipmentEcDO.getArea() + xwEquipmentEcDO.getStreet() + xwEquipmentEcDO.getVillageName());
                        }
                        BeanUtils.copyProperties(xwEquipmentEcDO, ecPageListRes1);
                        ecPageListRes.add(ecPageListRes1);
                    });
                }
                PageInfo<EcPageListRes> ecPageListResPageInfo = new PageInfo<>(ecPageListRes);
                ecPageListResPageInfo.setTotal(pageInfoEC.getTotal());
                ecPageListResPageInfo.setPageNum(rwPageList.getCurrPageNo());
                return ecPageListResPageInfo;
            default:
                return new PageInfo();
        }
    }


}
