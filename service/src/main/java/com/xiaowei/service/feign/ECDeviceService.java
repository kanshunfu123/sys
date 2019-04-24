package com.xiaowei.service.feign;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.feign.FeignECbyDeviceNoReq;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dao.XwEquipmentCwDAO;
import com.xiaowei.mana.mapper.dao.XwEquipmentMhDAO;
import com.xiaowei.mana.mapper.dao.XwEquipmentRwDAO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentCwDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentEcDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentMhDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentRwDO;
import com.xiaowei.mana.mapper.mapper.device.ECDeviceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by MOMO on 2019/1/26.
 */
@Service
public class ECDeviceService {
    @Autowired
    private ECDeviceMapper ecDeviceMapper;
    @Autowired
    private XwEquipmentRwDAO xwEquipmentRwDAO;
    @Autowired
    private XwEquipmentMhDAO xwEquipmentMhDAO;
    @Autowired
    private XwEquipmentCwDAO xwEquipmentCwDAO;
    @Autowired
    private RedisUtil redisUtil;

    public JSONResult feignECbyDeviceNo(FeignECbyDeviceNoReq feignECbyDeviceNoReq) {
        Object o = redisUtil.get("ec:" + feignECbyDeviceNoReq.getDeviceNo());
        if (null != o) {
            return JSONResult.ok(o);
        }
        XwEquipmentEcDO xwEquipmentEcDO = ecDeviceMapper.feignECbyDeviceNo(feignECbyDeviceNoReq.getDeviceNo());
        if (null == xwEquipmentEcDO) {
            return JSONResult.ok(new XwEquipmentEcDO());
        }
        return JSONResult.ok(xwEquipmentEcDO);

    }

    public JSONResult feignRWbyDeviceNo(FeignECbyDeviceNoReq feignECbyDeviceNoReq) {
        Object o = redisUtil.get("rw:" + feignECbyDeviceNoReq.getDeviceNo());
        if (null != o) {
            return JSONResult.ok(o);
        }
        XwEquipmentRwDO xwEquipmentRwDO = xwEquipmentRwDAO.feignRWbyDeviceNo(feignECbyDeviceNoReq.getDeviceNo());
        if (null==xwEquipmentRwDO){
            return JSONResult.ok(new XwEquipmentRwDO());
        }
        return JSONResult.ok(xwEquipmentRwDO);

    }

    public JSONResult feignMHbyDeviceNo(FeignECbyDeviceNoReq feignECbyDeviceNoReq) {
        Object o = redisUtil.get("mh:" + feignECbyDeviceNoReq.getDeviceNo());
        if (null != o) {
            return JSONResult.ok(o);
        }
        XwEquipmentMhDO xwEquipmentMhDO= xwEquipmentMhDAO.feignMHbyDeviceNo(feignECbyDeviceNoReq.getDeviceNo());
        if (null==xwEquipmentMhDO){
            return JSONResult.ok(new XwEquipmentMhDO());
        }
        return JSONResult.ok(xwEquipmentMhDO);

    }

    public JSONResult feignCWbyDeviceNo(FeignECbyDeviceNoReq feignECbyDeviceNoReq) {
        Object o = redisUtil.get("cw:" + feignECbyDeviceNoReq.getDeviceNo());
        if (null != o) {
            return JSONResult.ok(o);
        }
        XwEquipmentCwDO xwEquipmentCwDO= xwEquipmentCwDAO.feignCWbyDeviceNo(feignECbyDeviceNoReq.getDeviceNo());
        if (null==xwEquipmentCwDO){
            return JSONResult.ok(new XwEquipmentCwDO());
        }
        return JSONResult.ok(xwEquipmentCwDO);

    }
}
