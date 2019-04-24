package com.xiaowei.mana.mapper.dataobject;

import lombok.*;

/**
 * Created by MOMO on 2019/1/14.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceRoleDeviceRea {
    //设备编号
    private String deviceNo;
    private Long id;
    private String setupTime;
}
