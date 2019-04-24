package com.xiaowei.common.res.homeMapAcl;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by MOMO on 2019/2/19.
 */
@Data
@ToString
public class HomeMapAclRes {
    //区域id
    private Long areaId;
    //区域名称
    private String areaName;
    //经度
    private BigDecimal log;
    //纬度
    private BigDecimal lat;
    private List<DeviceList> deviceInfos;

}
