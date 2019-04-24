package com.xiaowei.service.MMPgroupDevice;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.MMPgroupDeviceReq;
import com.xiaowei.common.res.MMPgroupDeviceRes;
import com.xiaowei.mana.mapper.dataobject.*;
import com.xiaowei.mana.mapper.mapper.UserGroupDeviceMapper;
import com.xiaowei.mana.mapper.mapper.UserGroupMapper;
import com.xiaowei.mana.mapper.mapper.groupDevice.MMPgroupDevice;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by MOMO on 2019/1/25.
 */
@Service
public class MMPgroupDeviceService {

    @Autowired
    private MMPgroupDevice mmPgroupDevice;
    @Autowired
    private UserGroupDeviceMapper userGroupDeviceMapper;
    @Autowired
    private UserGroupMapper userGroupMapper;

    public JSONResult aclmmp(MMPgroupDeviceReq mmPgroupDeviceReq) {
        List<UserGroupDO> userGroupDOS = userGroupMapper.getUserGroupBygId(mmPgroupDeviceReq.getGroupId(), "0");
        String s = userGroupDOS.get(0).getSysScene();
        List<String> list = userGroupDeviceMapper.getDeviceIdByGroupIdANDType(mmPgroupDeviceReq.getGroupId(), mmPgroupDeviceReq.getDeviceType());
        if (CollectionUtils.isEmpty(list)) {
            return JSONResult.ok(new PageInfo<>());
        }
        //设备场景 0 地表水 1井盖 2生活用水 3电梯
        switch (mmPgroupDeviceReq.getDeviceType()) {
            case "3":
                PageHelper.startPage(mmPgroupDeviceReq.getPageNum(), mmPgroupDeviceReq.getPageSize());
                List<XwEquipmentEcDO> xwEquipmentEcDOS = mmPgroupDevice.ecAclList(mmPgroupDeviceReq.getDeviceNo(), list);
                PageInfo<XwEquipmentEcDO> pageInfo = new PageInfo<>(xwEquipmentEcDOS);
                List<XwEquipmentEcDO> ecDOS = pageInfo.getList();
                if (CollectionUtils.isEmpty(ecDOS)) {
                    return JSONResult.ok(pageInfo);
                }
                ecDOS.forEach(xwEquipmentEcDO -> {
                    if (StringUtils.isBlank(xwEquipmentEcDO.getAddress())){
                        xwEquipmentEcDO.setAddress("");
                    }
                    if (StringUtils.isBlank(xwEquipmentEcDO.getVillageName())){
                        xwEquipmentEcDO.setVillageName("");
                    }
                    xwEquipmentEcDO.setAddress(xwEquipmentEcDO.getProvince()+xwEquipmentEcDO.getCity()+xwEquipmentEcDO.getArea()+xwEquipmentEcDO.getAddress()+xwEquipmentEcDO.getVillageName());
                });
                PageInfo<XwEquipmentEcDO> pageInfoTwo = new PageInfo<>();
                pageInfoTwo.setTotal(pageInfo.getTotal());
                pageInfoTwo.setPageNum(mmPgroupDeviceReq.getPageNum());
                pageInfoTwo.setPageSize(mmPgroupDeviceReq.getPageSize());
                pageInfoTwo.setList(ecDOS);
                return JSONResult.ok(pageInfoTwo);
            case "1":
                PageHelper.startPage(mmPgroupDeviceReq.getPageNum(), mmPgroupDeviceReq.getPageSize());
                List<XwEquipmentMhDO> xwEquipmentMhDOS = mmPgroupDevice.mhAclList(mmPgroupDeviceReq.getDeviceNo(), list);
                PageInfo<XwEquipmentMhDO> pageInfoaa = new PageInfo<>(xwEquipmentMhDOS);
                List<XwEquipmentMhDO> ecDOSaa = pageInfoaa.getList();
                if (CollectionUtils.isEmpty(ecDOSaa)) {
                    return JSONResult.ok(pageInfoaa);
                }
                ecDOSaa.forEach(xwEquipmentEcDO -> {
                    if (StringUtils.isBlank(xwEquipmentEcDO.getAddress())){
                        xwEquipmentEcDO.setAddress("");
                    }
                    if (StringUtils.isBlank(xwEquipmentEcDO.getVillageName())){
                        xwEquipmentEcDO.setVillageName("");
                    }
                    xwEquipmentEcDO.setAddress(xwEquipmentEcDO.getProvince()+xwEquipmentEcDO.getCity()+xwEquipmentEcDO.getArea()+xwEquipmentEcDO.getAddress()+xwEquipmentEcDO.getVillageName());
                });
                PageInfo<XwEquipmentMhDO> pageInfoTwoaa = new PageInfo<>();
                pageInfoTwoaa.setTotal(pageInfoaa.getTotal());
                pageInfoTwoaa.setPageNum(mmPgroupDeviceReq.getPageNum());
                pageInfoTwoaa.setPageSize(mmPgroupDeviceReq.getPageSize());
                pageInfoTwoaa.setList(ecDOSaa);
                return JSONResult.ok(pageInfoTwoaa);
            case "0":
                PageHelper.startPage(mmPgroupDeviceReq.getPageNum(), mmPgroupDeviceReq.getPageSize());
                List<XwEquipmentRwDO> xwEquipmentrwDOS = mmPgroupDevice.rwAclList(mmPgroupDeviceReq.getDeviceNo(), list);
                PageInfo<XwEquipmentRwDO> pageInforw = new PageInfo<>(xwEquipmentrwDOS);

                List<XwEquipmentRwDO> ecDOSttt = pageInforw.getList();
                if (CollectionUtils.isEmpty(ecDOSttt)) {
                    return JSONResult.ok(pageInforw);
                }
                ecDOSttt.forEach(xwEquipmentEcDO -> {
                    if (StringUtils.isBlank(xwEquipmentEcDO.getAddress())){
                        xwEquipmentEcDO.setAddress("");
                    }

                    xwEquipmentEcDO.setAddress(xwEquipmentEcDO.getProvince()+xwEquipmentEcDO.getCity()+xwEquipmentEcDO.getArea()+xwEquipmentEcDO.getAddress());
                });
                PageInfo<XwEquipmentRwDO> pageInfoTwoff= new PageInfo<>();
                pageInfoTwoff.setTotal(pageInforw.getTotal());
                pageInfoTwoff.setPageNum(mmPgroupDeviceReq.getPageNum());
                pageInfoTwoff.setPageSize(mmPgroupDeviceReq.getPageSize());
                pageInfoTwoff.setList(ecDOSttt);
                return JSONResult.ok(pageInfoTwoff);
            case "2":
                PageHelper.startPage(mmPgroupDeviceReq.getPageNum(), mmPgroupDeviceReq.getPageSize());
                List<XwEquipmentCwDO> xwEquipmencrwDOS = mmPgroupDevice.cwAclList(mmPgroupDeviceReq.getDeviceNo(), list);
                PageInfo<XwEquipmentCwDO> pageInforcw = new PageInfo<>(xwEquipmencrwDOS);


                List<XwEquipmentCwDO> ecDOScw = pageInforcw.getList();
                if (CollectionUtils.isEmpty(ecDOScw)) {
                    return JSONResult.ok(ecDOScw);
                }
                ecDOScw.forEach(xwEquipmentEcDO -> {
                    if (StringUtils.isBlank(xwEquipmentEcDO.getAddress())){
                        xwEquipmentEcDO.setAddress("");
                    }
                    if (StringUtils.isBlank(xwEquipmentEcDO.getVillageName())){
                        xwEquipmentEcDO.setVillageName("");
                    }
                    xwEquipmentEcDO.setAddress(xwEquipmentEcDO.getProvince()+xwEquipmentEcDO.getCity()+xwEquipmentEcDO.getArea()+xwEquipmentEcDO.getAddress()+xwEquipmentEcDO.getVillageName());
                });
                PageInfo<XwEquipmentCwDO> pageInfoTwocw = new PageInfo<>();
                pageInfoTwocw.setTotal(pageInforcw.getTotal());
                pageInfoTwocw.setPageNum(mmPgroupDeviceReq.getPageNum());
                pageInfoTwocw.setPageSize(mmPgroupDeviceReq.getPageSize());
                pageInfoTwocw.setList(ecDOScw);
                return JSONResult.ok(pageInfoTwocw);
            default:
                return JSONResult.ok(new PageInfo<>());
        }


    }

    /**
     * 待授权设备列表  不包含已经授权的设备列表
     *
     * @param mmPgroupDeviceReq
     * @return
     */
    public JSONResult mmp(MMPgroupDeviceReq mmPgroupDeviceReq) {
        PageInfo pageInfo = switchType(mmPgroupDeviceReq);
        return JSONResult.ok(pageInfo);
    }

    //设备场景 0 地表水 1井盖 2生活用水 3电梯
    private PageInfo<MMPgroupDeviceRes> switchType(MMPgroupDeviceReq mmPgroupDeviceReq) {
        List<String> list = userGroupDeviceMapper.getDeviceIdByGroupIdANDType(mmPgroupDeviceReq.getGroupId(), mmPgroupDeviceReq.getDeviceType());
        int offsite = (mmPgroupDeviceReq.getPageNum() - 1) * mmPgroupDeviceReq.getPageSize();
        int size = mmPgroupDeviceReq.getPageSize();
        List<MMPgroupDeviceRes> mmPgroupDeviceRes = Lists.newArrayList();
        PageInfo<MMPgroupDeviceRes> pageInfo = new PageInfo<>();
        switch (mmPgroupDeviceReq.getDeviceType()) {


            case "0":
                List<XwEquipmentRwDO> xwEquipmentRwDOS = mmPgroupDevice.rwList(mmPgroupDeviceReq.getDeviceNo());
                int maxSize = xwEquipmentRwDOS.size();
                if (CollectionUtils.isEmpty(xwEquipmentRwDOS)) {
                    pageInfo.setTotal(0);
                    return pageInfo;
                }
                if (CollectionUtils.isEmpty(list)) {
                    int mmpSize = maxSize - offsite;
                    if (mmpSize < size) {
                        size = offsite + mmpSize;
                    } else {
                        size = offsite + size;
                    }
                    if (offsite > maxSize || size > maxSize) {
                        pageInfo.setTotal(0);
                        return pageInfo;
                    }
                    List<XwEquipmentRwDO> rwDOS = xwEquipmentRwDOS.subList(offsite, size);
                    rwDOS.forEach(xwEquipmentRwDO -> {
                        if (StringUtils.isBlank(xwEquipmentRwDO.getAddress())){
                            xwEquipmentRwDO.setAddress("");
                        }
                        MMPgroupDeviceRes mmPgroupDeviceRes1 = new MMPgroupDeviceRes();
                        mmPgroupDeviceRes1.setDeviceCode(xwEquipmentRwDO.getDeviceCode());
                        mmPgroupDeviceRes1.setDeviceName(xwEquipmentRwDO.getDeviceName());
                        mmPgroupDeviceRes1.setDeviceNo(xwEquipmentRwDO.getDeviceNo());
                        mmPgroupDeviceRes1.setProductTime(xwEquipmentRwDO.getProductTime());
                        mmPgroupDeviceRes1.setAddress(xwEquipmentRwDO.getProvince()+xwEquipmentRwDO.getCity()+xwEquipmentRwDO.getArea()+xwEquipmentRwDO.getAddress());
                        mmPgroupDeviceRes.add(mmPgroupDeviceRes1);
                    });
                    pageInfo.setTotal(xwEquipmentRwDOS.size());
                    pageInfo.setList(mmPgroupDeviceRes);
                    return pageInfo;
                } else {
                    //把所有设备放进map里
                    Map<String, String> map = Maps.newHashMap();
                    list.forEach(xwEquipmentRwDO -> {
                        map.put(xwEquipmentRwDO, xwEquipmentRwDO);
                    });
                    //最终组拥有的设备总列表
                    List<XwEquipmentRwDO> valueList = Lists.newArrayList();
                    xwEquipmentRwDOS.forEach(s -> {
                        Object o = map.get(s.getDeviceNo());
                        if (null == o) {
                            valueList.add(s);
                        }
                    });
                    int maxSizeaaa = valueList.size();
                    int mmpSizeaaa = maxSizeaaa - offsite;
                    if (mmpSizeaaa < size) {
                        size = offsite + mmpSizeaaa;
                    } else {
                        size = offsite + size;
                    }
                    if (offsite > maxSizeaaa || size > maxSizeaaa) {
                        pageInfo.setTotal(0);
                        return pageInfo;
                    }
                    List<XwEquipmentRwDO> rwDOS = valueList.subList(offsite, size);
                    rwDOS.forEach(xwEquipmentRwDO -> {
                        if (StringUtils.isBlank(xwEquipmentRwDO.getAddress())){
                            xwEquipmentRwDO.setAddress("");
                        }
                        MMPgroupDeviceRes mmPgroupDeviceRes1 = new MMPgroupDeviceRes();
                        mmPgroupDeviceRes1.setDeviceCode(xwEquipmentRwDO.getDeviceCode());
                        mmPgroupDeviceRes1.setDeviceName(xwEquipmentRwDO.getDeviceName());
                        mmPgroupDeviceRes1.setDeviceNo(xwEquipmentRwDO.getDeviceNo());
                        mmPgroupDeviceRes1.setProductTime(xwEquipmentRwDO.getProductTime());
                        mmPgroupDeviceRes1.setAddress(xwEquipmentRwDO.getProvince()+xwEquipmentRwDO.getCity()+xwEquipmentRwDO.getArea()+xwEquipmentRwDO.getAddress());

                        mmPgroupDeviceRes.add(mmPgroupDeviceRes1);
                    });
                    pageInfo.setList(mmPgroupDeviceRes);
                    pageInfo.setTotal(maxSizeaaa);
                    return pageInfo;
                }
            case "1":
                List<XwEquipmentMhDO> xwEquipmentRwDOS1 = mmPgroupDevice.mhList(mmPgroupDeviceReq.getDeviceNo());
                int maxSize1 = xwEquipmentRwDOS1.size();
                if (CollectionUtils.isEmpty(xwEquipmentRwDOS1)) {
                    pageInfo.setTotal(0);
                    return pageInfo;
                }
                if (CollectionUtils.isEmpty(list)) {
                    int mmpSize = maxSize1 - offsite;
                    if (mmpSize < size) {
                        size = offsite + mmpSize;
                    } else {
                        size = offsite + size;
                    }
                    if (offsite > maxSize1 || size > maxSize1) {
                        pageInfo.setTotal(0);
                        return pageInfo;
                    }
                    List<XwEquipmentMhDO> rwDOS = xwEquipmentRwDOS1.subList(offsite, size);
                    rwDOS.forEach(xwEquipmentRwDO -> {
                        if (StringUtils.isBlank(xwEquipmentRwDO.getAddress())){
                            xwEquipmentRwDO.setAddress("");
                        }
                        if (StringUtils.isBlank(xwEquipmentRwDO.getVillageName())){
                            xwEquipmentRwDO.setVillageName("");
                        }
                        MMPgroupDeviceRes mmPgroupDeviceRes1 = new MMPgroupDeviceRes();
                        mmPgroupDeviceRes1.setDeviceCode(xwEquipmentRwDO.getDeviceCode());
                        mmPgroupDeviceRes1.setDeviceName(xwEquipmentRwDO.getDeviceName());
                        mmPgroupDeviceRes1.setDeviceNo(xwEquipmentRwDO.getDeviceNo());
                        mmPgroupDeviceRes1.setProductTime(xwEquipmentRwDO.getProductTime());
                        mmPgroupDeviceRes1.setAddress(xwEquipmentRwDO.getProvince()+xwEquipmentRwDO.getCity()+xwEquipmentRwDO.getArea()+xwEquipmentRwDO.getAddress()+xwEquipmentRwDO.getVillageName());

                        mmPgroupDeviceRes.add(mmPgroupDeviceRes1);
                    });
                    pageInfo.setTotal(xwEquipmentRwDOS1.size());
                    pageInfo.setList(mmPgroupDeviceRes);
                    return pageInfo;
                } else {
                    //把所有设备放进map里
                    Map<String, String> map = Maps.newHashMap();
                    list.forEach(xwEquipmentRwDO -> {
                        map.put(xwEquipmentRwDO, xwEquipmentRwDO);
                    });
                    //最终组拥有的设备总列表
                    List<XwEquipmentMhDO> valueList = Lists.newArrayList();
                    xwEquipmentRwDOS1.forEach(s -> {
                        Object o = map.get(s.getDeviceNo());
                        if (null == o) {
                            valueList.add(s);
                        }
                    });
                    int maxSizeaaa = valueList.size();
                    int mmpSizeaaa = maxSizeaaa - offsite;
                    if (mmpSizeaaa < size) {
                        size = offsite + mmpSizeaaa;
                    } else {
                        size = offsite + size;
                    }
                    if (offsite > maxSizeaaa || size > maxSizeaaa) {
                        pageInfo.setTotal(0);
                        return pageInfo;
                    }
                    List<XwEquipmentMhDO> rwDOS = valueList.subList(offsite, size);
                    rwDOS.forEach(xwEquipmentRwDO -> {
                        if (StringUtils.isBlank(xwEquipmentRwDO.getAddress())){
                            xwEquipmentRwDO.setAddress("");
                        }
                        if (StringUtils.isBlank(xwEquipmentRwDO.getVillageName())){
                            xwEquipmentRwDO.setVillageName("");
                        }
                        MMPgroupDeviceRes mmPgroupDeviceRes1 = new MMPgroupDeviceRes();
                        mmPgroupDeviceRes1.setDeviceCode(xwEquipmentRwDO.getDeviceCode());
                        mmPgroupDeviceRes1.setDeviceName(xwEquipmentRwDO.getDeviceName());
                        mmPgroupDeviceRes1.setDeviceNo(xwEquipmentRwDO.getDeviceNo());
                        mmPgroupDeviceRes1.setProductTime(xwEquipmentRwDO.getProductTime());
                        mmPgroupDeviceRes1.setAddress(xwEquipmentRwDO.getProvince()+xwEquipmentRwDO.getCity()+xwEquipmentRwDO.getArea()+xwEquipmentRwDO.getAddress()+xwEquipmentRwDO.getVillageName());

                        mmPgroupDeviceRes.add(mmPgroupDeviceRes1);
                    });
                    pageInfo.setList(mmPgroupDeviceRes);
                    pageInfo.setTotal(maxSizeaaa);
                    return pageInfo;
                }
            case "2":
                List<XwEquipmentCwDO> xwEquipmentRwDOS2 = mmPgroupDevice.cwList(mmPgroupDeviceReq.getDeviceNo());
                int maxSize2 = xwEquipmentRwDOS2.size();
                if (CollectionUtils.isEmpty(xwEquipmentRwDOS2)) {
                    pageInfo.setTotal(0);
                    return pageInfo;
                }
                if (CollectionUtils.isEmpty(list)) {
                    int mmpSize2 = maxSize2 - offsite;
                    if (mmpSize2 < size) {
                        size = offsite + mmpSize2;
                    } else {
                        size = offsite + size;
                    }
                    if (offsite > maxSize2 || size > maxSize2) {
                        pageInfo.setTotal(0);
                        return pageInfo;
                    }
                    List<XwEquipmentCwDO> rwDOS = xwEquipmentRwDOS2.subList(offsite, size);
                    rwDOS.forEach(xwEquipmentRwDO -> {
                        if (StringUtils.isBlank(xwEquipmentRwDO.getAddress())){
                            xwEquipmentRwDO.setAddress("");
                        }
                        if (StringUtils.isBlank(xwEquipmentRwDO.getVillageName())){
                            xwEquipmentRwDO.setVillageName("");
                        }
                        MMPgroupDeviceRes mmPgroupDeviceRes1 = new MMPgroupDeviceRes();
                        mmPgroupDeviceRes1.setDeviceCode(xwEquipmentRwDO.getDeviceCode());
                        mmPgroupDeviceRes1.setDeviceName(xwEquipmentRwDO.getDeviceName());
                        mmPgroupDeviceRes1.setDeviceNo(xwEquipmentRwDO.getDeviceNo());
                        mmPgroupDeviceRes1.setProductTime(xwEquipmentRwDO.getProductTime());
                        mmPgroupDeviceRes1.setAddress(xwEquipmentRwDO.getProvince()+xwEquipmentRwDO.getCity()+xwEquipmentRwDO.getArea()+xwEquipmentRwDO.getAddress()+xwEquipmentRwDO.getVillageName());

                        mmPgroupDeviceRes.add(mmPgroupDeviceRes1);
                    });
                    pageInfo.setTotal(xwEquipmentRwDOS2.size());
                    pageInfo.setList(mmPgroupDeviceRes);
                    return pageInfo;
                } else {
                    //把所有设备放进map里
                    Map<String, String> map = Maps.newHashMap();
                    list.forEach(xwEquipmentRwDO -> {
                        map.put(xwEquipmentRwDO, xwEquipmentRwDO);
                    });
                    //最终组拥有的设备总列表
                    List<XwEquipmentCwDO> valueList = Lists.newArrayList();
                    xwEquipmentRwDOS2.forEach(s -> {
                        Object o = map.get(s.getDeviceNo());
                        if (null == o) {
                            valueList.add(s);
                        }
                    });
                    int maxSizeaaa = valueList.size();
                    int mmpSizeaaa = maxSizeaaa - offsite;
                    if (mmpSizeaaa < size) {
                        size = offsite + mmpSizeaaa;
                    } else {
                        size = offsite + size;
                    }
                    if (offsite > maxSizeaaa || size > maxSizeaaa) {
                        pageInfo.setTotal(0);
                        return pageInfo;
                    }
                    List<XwEquipmentCwDO> rwDOS = valueList.subList(offsite, size);
                    rwDOS.forEach(xwEquipmentRwDO -> {
                        if (StringUtils.isBlank(xwEquipmentRwDO.getAddress())){
                            xwEquipmentRwDO.setAddress("");
                        }
                        if (StringUtils.isBlank(xwEquipmentRwDO.getVillageName())){
                            xwEquipmentRwDO.setVillageName("");
                        }
                        MMPgroupDeviceRes mmPgroupDeviceRes1 = new MMPgroupDeviceRes();
                        mmPgroupDeviceRes1.setDeviceCode(xwEquipmentRwDO.getDeviceCode());
                        mmPgroupDeviceRes1.setDeviceName(xwEquipmentRwDO.getDeviceName());
                        mmPgroupDeviceRes1.setDeviceNo(xwEquipmentRwDO.getDeviceNo());
                        mmPgroupDeviceRes1.setProductTime(xwEquipmentRwDO.getProductTime());
                        mmPgroupDeviceRes1.setAddress(xwEquipmentRwDO.getProvince()+xwEquipmentRwDO.getCity()+xwEquipmentRwDO.getArea()+xwEquipmentRwDO.getAddress()+xwEquipmentRwDO.getVillageName());

                        mmPgroupDeviceRes.add(mmPgroupDeviceRes1);
                    });
                    pageInfo.setList(mmPgroupDeviceRes);
                    pageInfo.setTotal(maxSizeaaa);
                    return pageInfo;
                }
            case "3":
                List<XwEquipmentEcDO> xwEquipmentRwDOS3 = mmPgroupDevice.ecList(mmPgroupDeviceReq.getDeviceNo());
                int maxSize3 = xwEquipmentRwDOS3.size();
                if (CollectionUtils.isEmpty(xwEquipmentRwDOS3)) {
                    pageInfo.setTotal(0);
                    return pageInfo;
                }
                if (CollectionUtils.isEmpty(list)) {
                    int mmpSize3 = maxSize3 - offsite;
                    if (mmpSize3 < size) {
                        size = offsite + mmpSize3;
                    } else {
                        size = offsite + size;
                    }
                    if (offsite > maxSize3 || size > maxSize3) {
                        pageInfo.setTotal(0);
                        return pageInfo;
                    }
                    List<XwEquipmentEcDO> rwDOS = xwEquipmentRwDOS3.subList(offsite, size);
                    rwDOS.forEach(xwEquipmentRwDO -> {
                        if (StringUtils.isBlank(xwEquipmentRwDO.getAddress())){
                            xwEquipmentRwDO.setAddress("");
                        }
                        if (StringUtils.isBlank(xwEquipmentRwDO.getVillageName())){
                            xwEquipmentRwDO.setVillageName("");
                        }
                        MMPgroupDeviceRes mmPgroupDeviceRes1 = new MMPgroupDeviceRes();
                        mmPgroupDeviceRes1.setDeviceCode(xwEquipmentRwDO.getDeviceCode());
                        mmPgroupDeviceRes1.setDeviceName(xwEquipmentRwDO.getDeviceName());
                        mmPgroupDeviceRes1.setDeviceNo(xwEquipmentRwDO.getDeviceNo());
                        mmPgroupDeviceRes1.setProductTime(xwEquipmentRwDO.getProductTime());
                        mmPgroupDeviceRes1.setAddress(xwEquipmentRwDO.getProvince()+xwEquipmentRwDO.getCity()+xwEquipmentRwDO.getArea()+xwEquipmentRwDO.getAddress()+xwEquipmentRwDO.getVillageName());

                        mmPgroupDeviceRes.add(mmPgroupDeviceRes1);
                    });
                    pageInfo.setTotal(xwEquipmentRwDOS3.size());
                    pageInfo.setList(mmPgroupDeviceRes);
                    return pageInfo;
                } else {
                    //把所有设备放进map里
                    Map<String, String> map = Maps.newHashMap();
                    list.forEach(xwEquipmentRwDO -> {
                        map.put(xwEquipmentRwDO, xwEquipmentRwDO);
                    });
                    //最终组拥有的设备总列表
                    List<XwEquipmentEcDO> valueList = Lists.newArrayList();
                    xwEquipmentRwDOS3.forEach(s -> {
                        Object o = map.get(s.getDeviceNo());
                        if (null == o) {
                            valueList.add(s);
                        }
                    });
                    int maxSizeaaa = valueList.size();
                    int mmpSizeaaa = maxSizeaaa - offsite;
                    if (mmpSizeaaa < size) {
                        size = offsite + mmpSizeaaa;
                    } else {
                        size = offsite + size;
                    }
                    if (offsite > maxSizeaaa || size > maxSizeaaa) {
                        pageInfo.setTotal(0);
                        return pageInfo;
                    }
                    List<XwEquipmentEcDO> rwDOS = valueList.subList(offsite, size);
                    rwDOS.forEach(xwEquipmentRwDO -> {
                        if (StringUtils.isBlank(xwEquipmentRwDO.getAddress())){
                            xwEquipmentRwDO.setAddress("");
                        }
                        if (StringUtils.isBlank(xwEquipmentRwDO.getVillageName())){
                            xwEquipmentRwDO.setVillageName("");
                        }
                        MMPgroupDeviceRes mmPgroupDeviceRes1 = new MMPgroupDeviceRes();
                        mmPgroupDeviceRes1.setDeviceCode(xwEquipmentRwDO.getDeviceCode());
                        mmPgroupDeviceRes1.setDeviceName(xwEquipmentRwDO.getDeviceName());
                        mmPgroupDeviceRes1.setDeviceNo(xwEquipmentRwDO.getDeviceNo());
                        mmPgroupDeviceRes1.setProductTime(xwEquipmentRwDO.getProductTime());
                        mmPgroupDeviceRes1.setAddress(xwEquipmentRwDO.getProvince()+xwEquipmentRwDO.getCity()+xwEquipmentRwDO.getArea()+xwEquipmentRwDO.getAddress()+xwEquipmentRwDO.getVillageName());

                        mmPgroupDeviceRes.add(mmPgroupDeviceRes1);
                    });
                    pageInfo.setList(mmPgroupDeviceRes);
                    pageInfo.setTotal(maxSizeaaa);
                    return pageInfo;
                }
            default:
                return new PageInfo<>();
        }
    }

}
