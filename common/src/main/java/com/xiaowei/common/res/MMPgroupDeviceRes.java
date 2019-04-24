package com.xiaowei.common.res;

import lombok.*;

import java.util.Date;

/**
 * Created by MOMO on 2019/1/25.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MMPgroupDeviceRes {
    private String deviceNo;
    private String deviceCode;
    private String deviceName;
    private String address;

    /**
     * productTime 生产时间.
     */
    private Date productTime;
}
