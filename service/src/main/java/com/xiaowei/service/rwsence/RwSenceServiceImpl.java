package com.xiaowei.service.rwsence;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.rwsence.RwSenceAddReq;
import com.xiaowei.common.req.rwsence.RwSenceEditReq;
import com.xiaowei.common.req.rwsence.RwSenceUuidReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.XwRwSenceDAO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentRwDO;
import com.xiaowei.mana.mapper.dataobject.XwMhSenceDO;
import com.xiaowei.mana.mapper.dataobject.XwRwSenceDO;
import com.xiaowei.mana.mapper.mapper.RwMapper;
import com.xiaowei.mana.mapper.mapper.RwSenceMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 * Created by kanshunfu on 2019/01/07.
 */
@Service
@Slf4j
public class RwSenceServiceImpl implements RwSenceService {
    @Autowired
    private XwRwSenceDAO xwRwSenceDAO;
    @Autowired
    private RwSenceMapper rwSenceMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private RwMapper rwMapper;

    @Override
    public JSONResult add(RwSenceAddReq rwSenceAddReq, RedisUser redisUser) {
        XwRwSenceDO xwRwSenceDO = new XwRwSenceDO();
        BeanUtils.copyProperties(rwSenceAddReq, xwRwSenceDO);
        XwEquipmentRwDO xwEquipmentRwDO = rwMapper.deNo(rwSenceAddReq.getDeviceNo());
        if (null == xwEquipmentRwDO) {
            return JSONResult.errorMap("待查询的设备不存在");
        }
        int count = rwSenceMapper.deSenNo(rwSenceAddReq.getDeviceNo());
        if (count <= 0) {
            if (StringUtil.isNotEmpty(rwSenceAddReq.getSenceInstallTime())) {
                xwRwSenceDO.setSenceInstallTime(DateUtil.getStrToDate2(rwSenceAddReq.getSenceInstallTime()));
            } else {
                xwRwSenceDO.setSenceInstallTime(null);
            }

            xwRwSenceDO.setSenceUuid(StrUtil.genUUID());
            xwRwSenceDO.setCreateBy(redisUser.getSysUserName());
            xwRwSenceDO.setUpdateBy(redisUser.getSysUserName());
            xwRwSenceDO.setDelFlag("0");
            xwRwSenceDO.setCreateTime(DateUtils.getCurrentDateTime());
            xwRwSenceDO.setUpdateTime(DateUtils.getCurrentDateTime());
            xwRwSenceDO.setProvinceId(xwEquipmentRwDO.getProvinceId());
            xwRwSenceDO.setCityId(xwEquipmentRwDO.getCityId());
            xwRwSenceDO.setAreaId(xwEquipmentRwDO.getAreaId());
            xwRwSenceDAO.insertRwSence(xwRwSenceDO);
            if (xwRwSenceDO != null) {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_RS_INFO.getKey() + xwRwSenceDO.getDeviceNo(), JSONObject.toJSONString(xwRwSenceDO));
            }
            return JSONResult.ok("新增地表水场景成功", xwRwSenceDO.getSenceUuid());
        } else {
            XwRwSenceDO rwSenceDO = rwSenceMapper.uuid(rwSenceAddReq.getDeviceNo());
            if (!rwSenceDO.getDeviceNo().equals(rwSenceAddReq.getDeviceNo())) {
                return JSONResult.errorMap("设备号不能编辑");
            }
            XwRwSenceDO after = new XwRwSenceDO();
            BeanUtils.copyProperties(rwSenceAddReq, after);
            if (StringUtil.isNotEmpty(rwSenceAddReq.getSenceInstallTime())) {
                after.setSenceInstallTime(DateUtil.getStrToDate2(rwSenceAddReq.getSenceInstallTime()));
            } else {
                after.setSenceInstallTime(null);
            }


            after.setDelFlag("0");
            after.setUpdateBy(redisUser.getSysUserName());
            after.setUpdateTime(DateUtils.getCurrentDateTime());
            after.setProvinceId(xwEquipmentRwDO.getProvinceId());
            after.setCityId(xwEquipmentRwDO.getCityId());
            after.setAreaId(xwEquipmentRwDO.getAreaId());
            xwRwSenceDAO.updateRwSence(after);
            if (after != null) {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_RS_INFO.getKey() + after.getDeviceNo(), JSONObject.toJSONString(after));
            }
            return JSONResult.ok("更新地表水场景成功");
        }
    }

    @Override
    public JSONResult uuidRw(RwSenceUuidReq rwSenceUuidReq, RedisUser redisUser) {
        XwRwSenceDO rwSenceDO = rwSenceMapper.uuid(rwSenceUuidReq.getDeviceNo());
        if (null == rwSenceDO) {
            return JSONResult.errorMap("待查询的地表水场景信息不存在");

        }

        return JSONResult.ok(rwSenceDO);
    }

    @Override
    public JSONResult edit(RwSenceEditReq rwSenceEditReq, RedisUser redisUser) {
        XwRwSenceDO rwSenceDO = rwSenceMapper.uuid(rwSenceEditReq.getDeviceNo());
        if (null == rwSenceDO) {
            return JSONResult.errorMap("待编辑的设备不存在");
        }
        if (!rwSenceDO.getDeviceNo().equals(rwSenceEditReq.getDeviceNo())) {
            return JSONResult.errorMap("设备号不能编辑");
        }
        XwRwSenceDO after = new XwRwSenceDO();
        BeanUtils.copyProperties(rwSenceEditReq, after);
        after.setDelFlag("0");
        after.setUpdateBy(redisUser.getSysUserName());
        after.setUpdateTime(DateUtils.getCurrentDateTime());
        xwRwSenceDAO.updateRwSence(after);
        return JSONResult.ok("更新地表水场景成功");
    }

    @Override
    public JSONResult selRwSen() {
        List<XwRwSenceDO> equipmentEcDOS = rwSenceMapper.selRwSen();
        if (!CollectionUtils.isEmpty(equipmentEcDOS)) {
            equipmentEcDOS.forEach(equipmentEcDO -> {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_RS_INFO.getKey() + equipmentEcDO.getDeviceNo(), JSONObject.toJSONString(equipmentEcDO));
            });
        }

        return JSONResult.ok("新增成功");
    }
}
