package com.xiaowei.service.ecsence;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.ecsence.EcSenceAddReq;
import com.xiaowei.common.req.ecsence.EcSenceEditReq;
import com.xiaowei.common.req.ecsence.EcSenceUuidReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.XwEcSenceDAO;
import com.xiaowei.mana.mapper.dataobject.XwEcSenceDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentEcDO;
import com.xiaowei.mana.mapper.dataobject.XwMhSenceDO;
import com.xiaowei.mana.mapper.mapper.EcMapper;
import com.xiaowei.mana.mapper.mapper.EcSenceMapper;
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
public class EcSenceServiceImpl implements EcSenceService {
    @Autowired
    private XwEcSenceDAO xwEcSenceDAO;
    @Autowired
    private EcSenceMapper ecSenceMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private EcMapper ecMapper;

    @Override
    public JSONResult add(EcSenceAddReq ecSenceAddReq, RedisUser redisUser) {
        XwEcSenceDO xwEcSenceDO = new XwEcSenceDO();
        BeanUtils.copyProperties(ecSenceAddReq, xwEcSenceDO);
        XwEquipmentEcDO xwEquipmentEcDO=ecMapper.deNo(ecSenceAddReq.getDeviceNo());
        if (null == xwEquipmentEcDO) {
            return JSONResult.errorMap("待查询的设备不存在");
        }
        int count = ecSenceMapper.deSenNo(ecSenceAddReq.getDeviceNo());

        if (count <= 0) {

            if (StringUtil.isNotEmpty(ecSenceAddReq.getSenceInstallTime())){
                xwEcSenceDO.setSenceInstallTime(DateUtil.getStrToDate2(ecSenceAddReq.getSenceInstallTime()));
            }else {
                xwEcSenceDO.setSenceInstallTime(null);
            }

            if (StringUtil.isNotEmpty(ecSenceAddReq.getProductTime())){
                xwEcSenceDO.setProductTime(DateUtil.getStrToDate2(ecSenceAddReq.getProductTime()));
            }else {
                xwEcSenceDO.setProductTime(null);
            }

            xwEcSenceDO.setSenceInstallTime(DateUtil.getStrToDate(ecSenceAddReq.getSenceInstallTime()));
            xwEcSenceDO.setProductTime(DateUtil.getStrToDate(ecSenceAddReq.getProductTime()));
            xwEcSenceDO.setSenceUuid(StrUtil.genUUID());
            xwEcSenceDO.setCreateBy(redisUser.getSysUserName());
            xwEcSenceDO.setUpdateBy(redisUser.getSysUserName());
            xwEcSenceDO.setDelFlag("0");
            xwEcSenceDO.setCreateTime(DateUtils.getCurrentDateTime());
            xwEcSenceDO.setUpdateTime(DateUtils.getCurrentDateTime());
            xwEcSenceDO.setProvinceId(xwEquipmentEcDO.getProvinceId());
            xwEcSenceDO.setCityId(xwEquipmentEcDO.getCityId());
            xwEcSenceDO.setAreaId(xwEquipmentEcDO.getAreaId());
            xwEcSenceDAO.insertEcSence(xwEcSenceDO);
            if (xwEcSenceDO != null) {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_ES_INFO.getKey() + xwEcSenceDO.getDeviceNo(), JSONObject.toJSONString(xwEcSenceDO));
            }

            return JSONResult.ok("新增电梯场景成功", xwEcSenceDO.getSenceUuid());
        } else {
            XwEcSenceDO ecSenceDO = ecSenceMapper.uuid(ecSenceAddReq.getDeviceNo());
            if (!ecSenceDO.getDeviceNo().equals(ecSenceAddReq.getDeviceNo())) {
                return JSONResult.errorMap("设备号不能编辑");
            }
            XwEcSenceDO after = new XwEcSenceDO();
            BeanUtils.copyProperties(ecSenceAddReq, after);
            after.setProvinceId(xwEquipmentEcDO.getProvinceId());
            after.setCityId(xwEquipmentEcDO.getCityId());
            after.setAreaId(xwEquipmentEcDO.getAreaId());
            if (StringUtil.isNotEmpty(ecSenceAddReq.getSenceInstallTime())){
                after.setSenceInstallTime(DateUtil.getStrToDate2(ecSenceAddReq.getSenceInstallTime()));
            }else {
                after.setSenceInstallTime(null);
            }

            if (StringUtil.isNotEmpty(ecSenceAddReq.getProductTime())){
                after.setProductTime(DateUtil.getStrToDate2(ecSenceAddReq.getProductTime()));
            }else {
                after.setProductTime(null);
            }

            after.setDelFlag("0");
            after.setUpdateBy(redisUser.getSysUserName());
            after.setUpdateTime(DateUtils.getCurrentDateTime());
            xwEcSenceDAO.updateEcSence(after);
            if (after != null) {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_ES_INFO.getKey() + after.getDeviceNo(), JSONObject.toJSONString(after));
            }
            return JSONResult.ok("更新电梯场景成功");
        }
    }

    @Override
    public JSONResult selEcSen() {
        List<XwEcSenceDO> equipmentEcDOS = ecSenceMapper.selEcSen();
        if (!CollectionUtils.isEmpty(equipmentEcDOS)) {
            equipmentEcDOS.forEach(equipmentEcDO -> {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_ES_INFO.getKey()+equipmentEcDO.getDeviceNo(), JSONObject.toJSONString(equipmentEcDO) );
            });
        }

        return JSONResult.ok("新增成功");
    }
}
