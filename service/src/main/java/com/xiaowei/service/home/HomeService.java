package com.xiaowei.service.home;

import com.xiaowei.common.common.DateUtils;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.DeviceUserRoleReq;
import com.xiaowei.common.req.home.AccessDeviceReq;
import com.xiaowei.common.req.home.AccessReq;
import com.xiaowei.common.req.home.AreaV2Req;
import com.xiaowei.common.res.home.AccessDeviceRes;
import com.xiaowei.common.res.home.HomeTotalRes;
import com.xiaowei.common.res.home.LatestDeviceRes;
import com.xiaowei.common.res.home.TotalSizeRes;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentCwDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentEcDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentMhDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentRwDO;
import com.xiaowei.mana.mapper.mapper.HomeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by 韩金群
 * CreateTime 2019/1/16
 */
@Service
public class HomeService {
    @Autowired
    private HomeMapper homeMapper;
    private static final int EC_LATEST_COUNT = 20;
    private static final int MH_LATEST_COUNT = 20;
    private static final int RW_LATEST_COUNT = 20;
    private static final int CW_LATEST_COUNT = 20;

    /**
     * 首页总台数和总运行时长
     */
    public JSONResult homeTotal() {
        Integer count = 0;
        Long runTime = 0L;
        HomeTotalRes result = new HomeTotalRes();
        count += homeMapper.mainEcTotal();
        count += homeMapper.mainMhTotal();
        count += homeMapper.mainRwTotal();
        count += homeMapper.mainCwTotal();
        Date now = new Date();
        List<Date> dateEcList = homeMapper.ecTotalTime();
        for (int i = 0; i < dateEcList.size(); i++) {
            runTime += now.getTime() - dateEcList.get(i).getTime();
        }
        List<Date> dateMhList = homeMapper.mhTotalTime();
        for (int i = 0; i < dateMhList.size(); i++) {
            runTime += now.getTime() - dateMhList.get(i).getTime();
        }
        List<Date> dateRwList = homeMapper.rwTotalTime();
        for (int i = 0; i < dateRwList.size(); i++) {
            runTime += now.getTime() - dateRwList.get(i).getTime();
        }
        List<Date> dateCwList = homeMapper.cwTotalTime();
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
    public JSONResult homeTotalOne(String type) {
        Integer count = 0;
        Long runTime = 0L;
        HomeTotalRes result = new HomeTotalRes();
        Date now = new Date();
        if ("ec".equals(type)) {
            count += homeMapper.mainEcTotal();
            List<Date> dateEcList = homeMapper.ecTotalTime();
            for (int i = 0; i < dateEcList.size(); i++) {
                runTime += now.getTime() - dateEcList.get(i).getTime();
            }
        }
        if ("mh".equals(type)) {
            count += homeMapper.mainMhTotal();
            List<Date> dateMhList = homeMapper.mhTotalTime();
            for (int i = 0; i < dateMhList.size(); i++) {
                runTime += now.getTime() - dateMhList.get(i).getTime();
            }
        }
        if ("rw".equals(type)) {
            count += homeMapper.mainRwTotal();
            List<Date> dateRwList = homeMapper.rwTotalTime();
            for (int i = 0; i < dateRwList.size(); i++) {
                runTime += now.getTime() - dateRwList.get(i).getTime();
            }
        }
        if ("cw".equals(type)) {
            count += homeMapper.mainCwTotal();
            List<Date> dateCwList = homeMapper.cwTotalTime();
            for (int i = 0; i < dateCwList.size(); i++) {
                runTime += now.getTime() - dateCwList.get(i).getTime();
            }
        }
        result.setCount(count);
        result.setRunTime(runTime);
        return JSONResult.ok(result);
    }

    /**
     * 最新接入设备
     */
    public JSONResult latestDevice() {
        List<LatestDeviceRes> result = new ArrayList<>();
        //电梯
        List<XwEquipmentEcDO> ecDOS = homeMapper.latestEc(EC_LATEST_COUNT);
        for (int i = 0; i < ecDOS.size(); i++) {
            LatestDeviceRes res = new LatestDeviceRes();
            res.setDeviceNo(ecDOS.get(i).getDeviceNo());
            res.setSetupTime(DateUtils.dateToString(ecDOS.get(i).getSetupTime()));
            res.setType("电梯");
            res.setAddress(ecDOS.get(i).getVillageName() + ecDOS.get(i).getDeviceCode());
            result.add(res);
        }
        //井盖
        List<XwEquipmentMhDO> mhDOS = homeMapper.latestMh(MH_LATEST_COUNT);
        for (int i = 0; i < mhDOS.size(); i++) {
            LatestDeviceRes res = new LatestDeviceRes();
            res.setDeviceNo(mhDOS.get(i).getDeviceNo());
            res.setSetupTime(DateUtils.dateToString(mhDOS.get(i).getSetupTime()));
            res.setType("井盖");
            res.setAddress(mhDOS.get(i).getAddress() + mhDOS.get(i).getDeviceCode());
            result.add(res);
        }
        //地表水
        List<XwEquipmentRwDO> rwDOS = homeMapper.latestRw(RW_LATEST_COUNT);
        for (int i = 0; i < rwDOS.size(); i++) {
            LatestDeviceRes res = new LatestDeviceRes();
            res.setDeviceNo(rwDOS.get(i).getDeviceNo());
            res.setSetupTime(DateUtils.dateToString(rwDOS.get(i).getSetupTime()));
            res.setType("地表水");
            res.setAddress(rwDOS.get(i).getDeviceName() + rwDOS.get(i).getDeviceCode());
            result.add(res);
        }
        //饮用水
        List<XwEquipmentCwDO> cwDOS = homeMapper.latestCw(CW_LATEST_COUNT);
        for (int i = 0; i < cwDOS.size(); i++) {
            LatestDeviceRes res = new LatestDeviceRes();
            res.setDeviceNo(cwDOS.get(i).getDeviceNo());
            res.setSetupTime(DateUtils.dateToString(cwDOS.get(i).getSetupTime()));
            res.setType("饮用水");
            res.setAddress(cwDOS.get(i).getVillageName() + cwDOS.get(i).getDeviceCode());
            result.add(res);
        }
        return JSONResult.ok(result);
    }

    /**
     * 电梯设备编号集合
     */
    public JSONResult deviceNoListEc() {
        List<String> list = homeMapper.deviceNoListEc();
        return JSONResult.ok(list);
    }

    /**
     * 井盖设备编号集合
     */
    public JSONResult deviceNoListMh() {
        List<String> list = homeMapper.deviceNoListMh();
        return JSONResult.ok(list);
    }

    /**
     * 地表水设备编号集合
     */
    public JSONResult deviceNoListRw() {
        List<String> list = homeMapper.deviceNoListRw();
        return JSONResult.ok(list);
    }

    /**
     * 饮用水设备编号集合
     */
    public JSONResult deviceNoListCw() {
        List<String> list = homeMapper.deviceNoListCw();
        return JSONResult.ok(list);
    }

    /**
     * 总共设备
     */
    public JSONResult totalSize() {
        TotalSizeRes res = new TotalSizeRes();
        res.setEc(homeMapper.ecSize());
        res.setMh(homeMapper.mhSize());
        res.setRw(homeMapper.rwSize());
        res.setCw(homeMapper.cwSize());
        return JSONResult.ok(res);
    }

    /**
     * 本年接入设备趋势图
     */
    public JSONResult accessDevice(AccessReq accessReq) {
        List<AccessDeviceReq> list = accessReq.getList();
        AreaV2Req req = accessReq.getAreaV2Req();
        Long provinceId = null;
        Long cityId = null;
        if (req.getLevel() == 1) {
            provinceId = req.getParentId();
        }
        if (req.getLevel() == 2) {
            cityId = req.getParentId();
        }
        List<AccessDeviceRes> result = new ArrayList<>();
        for (int i = 0; i < list.size(); i++) {
            int ec = homeMapper.ecAccessTotal(list.get(i).getBeginTime(), list.get(i).getEndTime(), provinceId, cityId);
            int mh = homeMapper.mhAccessTotal(list.get(i).getBeginTime(), list.get(i).getEndTime(), provinceId, cityId);
            int rw = homeMapper.rwAccessTotal(list.get(i).getBeginTime(), list.get(i).getEndTime(), provinceId, cityId);
            int cw = homeMapper.cwAccessTotal(list.get(i).getBeginTime(), list.get(i).getEndTime(), provinceId, cityId);
            int total = ec + mh + rw + cw;
            AccessDeviceRes res = new AccessDeviceRes();
            res.setNumber(total);
            res.setMonth(i + 1 + "月");
            result.add(res);
        }
        return JSONResult.ok(result);
    }
}
