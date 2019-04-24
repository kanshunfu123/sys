package com.xiaowei.service.home;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.home.AreaV2Req;
import com.xiaowei.common.req.home.HomeOneReq;
import com.xiaowei.common.res.home.HomeTotalRes;
import com.xiaowei.common.res.home.TotalSizeRes;
import com.xiaowei.mana.mapper.mapper.HomeV2Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * created by 韩金群 2019/2/20
 */
@Service
public class HomeV2Service {
    @Autowired
    private HomeV2Mapper homeV2Mapper;

    /**
     * 首页今日统计
     */
    public JSONResult homeTotalV2(AreaV2Req req) {
        Long provinceId = null;
        Long cityId = null;
        if (req.getLevel() == 1) {
            provinceId = req.getParentId();
        }
        if (req.getLevel() == 2) {
            cityId = req.getParentId();
        }
        Integer count = 0;
        Long runTime = 0L;
        HomeTotalRes result = new HomeTotalRes();
        count += homeV2Mapper.mainEcTotal(provinceId, cityId);
        count += homeV2Mapper.mainMhTotal(provinceId, cityId);
        count += homeV2Mapper.mainRwTotal(provinceId, cityId);
        count += homeV2Mapper.mainCwTotal(provinceId, cityId);
        Date now = new Date();
        List<Date> dateEcList = homeV2Mapper.ecTotalTime(provinceId, cityId);
        for (int i = 0; i < dateEcList.size(); i++) {
            runTime += now.getTime() - dateEcList.get(i).getTime();
        }
        List<Date> dateMhList = homeV2Mapper.mhTotalTime(provinceId, cityId);
        for (int i = 0; i < dateMhList.size(); i++) {
            runTime += now.getTime() - dateMhList.get(i).getTime();
        }
        List<Date> dateRwList = homeV2Mapper.rwTotalTime(provinceId, cityId);
        for (int i = 0; i < dateRwList.size(); i++) {
            runTime += now.getTime() - dateRwList.get(i).getTime();
        }
        List<Date> dateCwList = homeV2Mapper.cwTotalTime(provinceId, cityId);
        for (int i = 0; i < dateCwList.size(); i++) {
            runTime += now.getTime() - dateCwList.get(i).getTime();
        }
        result.setCount(count);
        result.setRunTime(runTime);
        return JSONResult.ok(result);
    }

    /**
     * 首页单个场景总台数和总运行时长
     */
    public JSONResult homeDeviceOneV2(HomeOneReq req) {
        String type = req.getType();
        Long provinceId = null;
        Long cityId = null;
        if (req.getLevel() == 1) {
            provinceId = req.getParentId();
        }
        if (req.getLevel() == 2) {
            cityId = req.getParentId();
        }
        List<String> result = new ArrayList<>();
        Date now = new Date();
        if ("ec".equals(type)) {
            result = homeV2Mapper.ecTotalDevice(provinceId, cityId);
        }
        if ("mh".equals(type)) {
            result = homeV2Mapper.mhTotalDevice(provinceId, cityId);
        }
        if ("rw".equals(type)) {
            result  = homeV2Mapper.rwTotalDevice(provinceId, cityId);
        }
        if ("cw".equals(type)) {
            result = homeV2Mapper.cwTotalDevice(provinceId, cityId);
        }
        return JSONResult.ok(result);
    }
    /**
     * 统计单个场景设备列表
     */
    public JSONResult homeTotalOneV2(HomeOneReq req) {
        String type = req.getType();
        Long provinceId = null;
        Long cityId = null;
        if (req.getLevel() == 1) {
            provinceId = req.getParentId();
        }
        if (req.getLevel() == 2) {
            cityId = req.getParentId();
        }
        Integer count = 0;
        Long runTime = 0L;
        HomeTotalRes result = new HomeTotalRes();
        Date now = new Date();
        if ("ec".equals(type)) {
            count += homeV2Mapper.mainEcTotal(provinceId, cityId);
            List<Date> dateEcList = homeV2Mapper.ecTotalTime(provinceId, cityId);
            for (int i = 0; i < dateEcList.size(); i++) {
                runTime += now.getTime() - dateEcList.get(i).getTime();
            }
        }
        if ("mh".equals(type)) {
            count += homeV2Mapper.mainMhTotal(provinceId, cityId);
            List<Date> dateMhList = homeV2Mapper.mhTotalTime(provinceId, cityId);
            for (int i = 0; i < dateMhList.size(); i++) {
                runTime += now.getTime() - dateMhList.get(i).getTime();
            }
        }
        if ("rw".equals(type)) {
            count += homeV2Mapper.mainRwTotal(provinceId, cityId);
            List<Date> dateRwList = homeV2Mapper.rwTotalTime(provinceId, cityId);
            for (int i = 0; i < dateRwList.size(); i++) {
                runTime += now.getTime() - dateRwList.get(i).getTime();
            }
        }
        if ("cw".equals(type)) {
            count += homeV2Mapper.mainCwTotal(provinceId, cityId);
            List<Date> dateCwList = homeV2Mapper.cwTotalTime(provinceId, cityId);
            for (int i = 0; i < dateCwList.size(); i++) {
                runTime += now.getTime() - dateCwList.get(i).getTime();
            }
        }
        result.setCount(count);
        result.setRunTime(runTime);
        return JSONResult.ok(result);
    }

    /**
     * 总共设备
     */
    public JSONResult totalSizeV2(AreaV2Req req) {
        Long provinceId = null;
        Long cityId = null;
        if (req.getLevel() == 1) {
            provinceId = req.getParentId();
        }
        if (req.getLevel() == 2) {
            cityId = req.getParentId();
        }
        TotalSizeRes res = new TotalSizeRes();
        res.setEc(homeV2Mapper.ecSize(provinceId, cityId));
        res.setMh(homeV2Mapper.mhSize(provinceId, cityId));
        res.setRw(homeV2Mapper.rwSize(provinceId, cityId));
        res.setCw(homeV2Mapper.cwSize(provinceId, cityId));
        return JSONResult.ok(res);
    }
}
