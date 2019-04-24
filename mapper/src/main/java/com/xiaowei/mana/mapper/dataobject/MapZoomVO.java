package com.xiaowei.mana.mapper.dataobject;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;


/**
 * Created by MOMO on 2019/2/14.
 * 地图缩放
 */
@Data
@ToString
public class MapZoomVO {
    //经度
    private BigDecimal lngLeft;
    private BigDecimal lngRight;
    //纬度
    private BigDecimal latLeft;
    private BigDecimal latRight;

    //层级 0省 1市 2区 3街道  4 小区
    private Integer level;
    //首级为 0
    private Long parentId;


}
