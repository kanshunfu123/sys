package com.xiaowei.common.res.areatodevice;

import lombok.*;

/**
 * Created by MOMO on 2019/1/18.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreatodeviceRes {
    //设备号
    private String deviceNo;
    //设备名称
    private String deviceName;
    //设备code
    private String deviceCode;

    private Integer floorNum;
    private Integer floorDownNum;
    private Integer floor;

}
