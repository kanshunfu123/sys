package com.xiaowei.common.req;

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
public class GroupDeviceReq {
    /**
     * sysDeviceId 设备id.
     */
    private String sysDeviceNo;

    /**
     * deviceType 设备场景 0 地表水 1井盖 2生活用水 3电梯'.
     */
    private String deviceType;
}
