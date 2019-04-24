package com.xiaowei.service.mhsence;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.ecsence.EcSenceUuidReq;
import com.xiaowei.common.req.mhsence.MhSenceAddReq;
import com.xiaowei.common.req.mhsence.MhSenceEditReq;
import com.xiaowei.common.req.mhsence.MhSenceUuidReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.XwMhSenceDAO;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.MhMapper;
import com.xiaowei.mana.mapper.mapper.MhSenceMapper;
import lombok.extern.slf4j.Slf4j;
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
public class MhSenceServiceImpl implements MhSenceService {
    @Autowired
    private XwMhSenceDAO xwMhSenceDAO;
    @Autowired
    private MhSenceMapper mhSenceMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MhMapper mhMapper;
    @Override
    public JSONResult add(MhSenceAddReq mhSenceAddReq, RedisUser redisUser) {
        XwMhSenceDO xwMhSenceDO=new XwMhSenceDO();
        BeanUtils.copyProperties(mhSenceAddReq, xwMhSenceDO);
        XwEquipmentMhDO xwEquipmentMhDO=mhMapper.deNo(mhSenceAddReq.getDeviceNo());
        if (null == xwEquipmentMhDO) {
            return JSONResult.errorMap("待查询的设备不存在");
        }
        int count=mhSenceMapper.deSenNo(mhSenceAddReq.getDeviceNo());
        if(count<=0){
            if (StringUtil.isNotEmpty(mhSenceAddReq.getSenceInstallTime())){
                xwMhSenceDO.setSenceInstallTime(DateUtil.getStrToDate2(mhSenceAddReq.getSenceInstallTime()));
            }else {
                xwMhSenceDO.setSenceInstallTime(null);
            }

            if (StringUtil.isNotEmpty(mhSenceAddReq.getMhEndTime())){
                xwMhSenceDO.setMhEndTime(DateUtil.getStrToDate2(mhSenceAddReq.getMhEndTime()));
            }else {
                xwMhSenceDO.setMhEndTime(null);
            }

            xwMhSenceDO.setSenceUuid(StrUtil.genUUID());
            xwMhSenceDO.setCreateBy(redisUser.getSysUserName());
            xwMhSenceDO.setUpdateBy(redisUser.getSysUserName());
            xwMhSenceDO.setDelFlag("0");
            xwMhSenceDO.setCreateTime(DateUtils.getCurrentDateTime());
            xwMhSenceDO.setUpdateTime(DateUtils.getCurrentDateTime());
            xwMhSenceDO.setProvinceId(xwEquipmentMhDO.getProvinceId());
            xwMhSenceDO.setCityId(xwEquipmentMhDO.getCityId());
            xwMhSenceDO.setAreaId(xwEquipmentMhDO.getAreaId());

            xwMhSenceDAO.insertMhSence(xwMhSenceDO);
            if(xwMhSenceDO!=null){
                redisUtil.set(RedisKeyEnum.REDIS_KEY_MS_INFO.getKey()+xwMhSenceDO.getDeviceNo(), JSONObject.toJSONString(xwMhSenceDO) );
            }

            return JSONResult.ok("新增井盖场景信息成功", xwMhSenceDO.getSenceUuid());
        } else{
            XwMhSenceDO mhSenceDO = mhSenceMapper.uuid(mhSenceAddReq.getDeviceNo());
            if (!mhSenceDO.getDeviceNo().equals(mhSenceAddReq.getDeviceNo())) {
                return JSONResult.errorMap("设备号不能编辑");
            }
            XwMhSenceDO after = new XwMhSenceDO();
            BeanUtils.copyProperties(mhSenceAddReq, after);
            if (StringUtil.isNotEmpty(mhSenceAddReq.getSenceInstallTime())){
                after.setSenceInstallTime(DateUtil.getStrToDate2(mhSenceAddReq.getSenceInstallTime()));
            }else {
                after.setSenceInstallTime(null);
            }

            if (StringUtil.isNotEmpty(mhSenceAddReq.getMhEndTime())){
                after.setMhEndTime(DateUtil.getStrToDate2(mhSenceAddReq.getMhEndTime()));
            }else {
                after.setMhEndTime(null);
            }

            after.setDelFlag("0");
            after.setUpdateBy(redisUser.getSysUserName());
            after.setUpdateTime(DateUtils.getCurrentDateTime());
            after.setProvinceId(xwEquipmentMhDO.getProvinceId());
            after.setCityId(xwEquipmentMhDO.getCityId());
            after.setAreaId(xwEquipmentMhDO.getAreaId());
            xwMhSenceDAO.updateMhSence(after);
            if(after!=null){
                redisUtil.set(RedisKeyEnum.REDIS_KEY_MS_INFO.getKey()+after.getDeviceNo(), JSONObject.toJSONString(after) );
            }
            return JSONResult.ok("更新井盖场景信息成功");
        }
    }

    @Override
    public JSONResult selMhSen() {
        List<XwMhSenceDO> equipmentEcDOS = mhSenceMapper.selMhSen();
        if (!CollectionUtils.isEmpty(equipmentEcDOS)) {
            equipmentEcDOS.forEach(equipmentEcDO -> {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_MS_INFO.getKey()+equipmentEcDO.getDeviceNo(), JSONObject.toJSONString(equipmentEcDO) );
            });
        }

        return JSONResult.ok("新增成功");
    }


}
