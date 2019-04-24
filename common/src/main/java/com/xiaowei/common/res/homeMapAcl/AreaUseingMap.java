package com.xiaowei.common.res.homeMapAcl;

import lombok.Data;
import lombok.ToString;

/**
 * Created by MOMO on 2019/2/20.
 */
@Data
@ToString
public class AreaUseingMap {
    //区域名称
    private String areaName;
    //设备数量
    private Integer deviceNum=0;
}
