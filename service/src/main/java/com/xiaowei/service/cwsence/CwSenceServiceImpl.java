package com.xiaowei.service.cwsence;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.util.StringUtil;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.req.cwsence.CwSenceAddReq;
import com.xiaowei.common.req.cwsence.CwSenceEditReq;
import com.xiaowei.common.req.cwsence.CwSenceUuidReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.XwCwSenceDAO;
import com.xiaowei.mana.mapper.dao.XwEquipmentCwDAO;
import com.xiaowei.mana.mapper.dataobject.XwCwSenceDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentCwDO;
import com.xiaowei.mana.mapper.dataobject.XwMhSenceDO;
import com.xiaowei.mana.mapper.mapper.CwMapper;
import com.xiaowei.mana.mapper.mapper.CwSenceMapper;
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
public class CwSenceServiceImpl implements CwSenceService {
    @Autowired
    private XwCwSenceDAO xwCwSenceDAO;
    @Autowired
    private CwSenceMapper cwSenceMapper;
    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private CwMapper cwMapper;

    @Override
    public JSONResult add(CwSenceAddReq cwSenceAddReq, RedisUser redisUser) {
        XwCwSenceDO xwCwSenceDO=new XwCwSenceDO();
        BeanUtils.copyProperties(cwSenceAddReq, xwCwSenceDO);
        XwEquipmentCwDO xwEquipmentCwDO=cwMapper.deNo(cwSenceAddReq.getDeviceNo());
        if (null == xwEquipmentCwDO) {
            return JSONResult.errorMap("待查询的设备不存在");
        }
        int count=cwSenceMapper.deSenNo(cwSenceAddReq.getDeviceNo());
        if(count<=0){
            if (StringUtil.isNotEmpty(cwSenceAddReq.getSenceInstallTime())){
                xwCwSenceDO.setSenceInstallTime(DateUtil.getStrToDate2(cwSenceAddReq.getSenceInstallTime()));
            }else {
                xwCwSenceDO.setSenceInstallTime(null);
            }

            if (StringUtil.isNotEmpty(cwSenceAddReq.getLastCleaningTime())){
                xwCwSenceDO.setLastCleaningTime(DateUtil.getStrToDate2(cwSenceAddReq.getLastCleaningTime()));
            }else {
                xwCwSenceDO.setLastCleaningTime(null);
            }
            xwCwSenceDO.setSenceUuid(StrUtil.genUUID());
            xwCwSenceDO.setCreateBy(redisUser.getSysUserName());
            xwCwSenceDO.setUpdateBy(redisUser.getSysUserName());
            xwCwSenceDO.setDelFlag("0");
            xwCwSenceDO.setCreateTime(DateUtils.getCurrentDateTime());
            xwCwSenceDO.setUpdateTime(DateUtils.getCurrentDateTime());
            xwCwSenceDO.setProvinceId(xwEquipmentCwDO.getProvinceId());
            xwCwSenceDO.setCityId(xwEquipmentCwDO.getCityId());
            xwCwSenceDO.setAreaId(xwEquipmentCwDO.getAreaId());
            xwCwSenceDAO.saveCwSence(xwCwSenceDO);
            if(xwCwSenceDO!=null){
                redisUtil.set(RedisKeyEnum.REDIS_KEY_CS_INFO.getKey()+xwCwSenceDO.getDeviceNo(), JSONObject.toJSONString(xwCwSenceDO) );
            }
            return JSONResult.ok("新增饮用水场景成功", cwSenceAddReq.getSenceUuid());
        } else{
            XwCwSenceDO cwSenceDO = cwSenceMapper.uuid(cwSenceAddReq.getDeviceNo());
            if (!cwSenceDO.getDeviceNo().equals(cwSenceAddReq.getDeviceNo())) {
                return JSONResult.errorMap("设备号不能编辑");
            }

            XwCwSenceDO after = new XwCwSenceDO();
            BeanUtils.copyProperties(cwSenceAddReq, after);
            if (StringUtil.isNotEmpty(cwSenceAddReq.getSenceInstallTime())){
                after.setSenceInstallTime(DateUtil.getStrToDate2(cwSenceAddReq.getSenceInstallTime()));
            }else {
                after.setSenceInstallTime(null);
            }

            if (StringUtil.isNotEmpty(cwSenceAddReq.getLastCleaningTime())){
                after.setLastCleaningTime(DateUtil.getStrToDate2(cwSenceAddReq.getLastCleaningTime()));
            }else {
                after.setLastCleaningTime(null);
            }

            after.setDelFlag("0");
            after.setUpdateBy(redisUser.getSysUserName());
            after.setUpdateTime(DateUtils.getCurrentDateTime());
            after.setProvinceId(xwEquipmentCwDO.getProvinceId());
            after.setCityId(xwEquipmentCwDO.getCityId());
            after.setAreaId(xwEquipmentCwDO.getAreaId());
            xwCwSenceDAO.updateCwSence(after);
            if(after!=null){
                redisUtil.set(RedisKeyEnum.REDIS_KEY_CS_INFO.getKey()+after.getDeviceNo(), JSONObject.toJSONString(after));

            }

            return JSONResult.ok("更新饮用水场景成功");
        }
    }

    @Override
    public JSONResult selCwSen() {
        List<XwCwSenceDO> equipmentEcDOS = cwSenceMapper.selCwSen();
        if (!CollectionUtils.isEmpty(equipmentEcDOS)) {
            equipmentEcDOS.forEach(equipmentEcDO -> {
                redisUtil.set(RedisKeyEnum.REDIS_KEY_CS_INFO.getKey()+equipmentEcDO.getDeviceNo(), JSONObject.toJSONString(equipmentEcDO) );
            });
        }

        return JSONResult.ok("新增成功");
    }


}
