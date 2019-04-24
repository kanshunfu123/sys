package com.xiaowei.mana.mapper.dataobject;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.math.BigDecimal;

/**
 * Created by MOMO on 2019/2/19.
 */
@Data
public class AreaAclMapDO_v2 {
    //设备数量
    private Integer deviceNum;
    //区域id
    private Long areaId;
    //区域名称
    private String areaName;
    //经度
    private BigDecimal log;
    //纬度
    private BigDecimal lat;
    //设备类型  0 地表水 1井盖 2生活用水 3电梯
    private String deviceType;
    //区域层级
    private String areaLevel;

    private String province;
    private Long provinceId;
    private String city;
    private Long cityId;
    private String area;
}
