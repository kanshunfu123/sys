package com.xiaowei.common.res.homeMapAcl;

import lombok.Data;
import lombok.ToString;

/**
 * Created by MOMO on 2019/2/19.
 */
@Data
@ToString
public class DeviceList {
    //设备名称
    private String deviceName;
    //设备数量
    private Integer deviceNum=0;

    //正常
    private Long normal=0L;
    //故障
    private Long breakdown=0L;
    //离线
    private Long offLine=0L;


}
