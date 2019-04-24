package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by MOMO on 2019/1/25.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MMPgroupDeviceReq extends BaseReq {
    //设备场景 0 地表水 1井盖 2生活用水 3电梯
    @NotBlank(message = "设备场景必填", groups = {Query.class})
    private String deviceType;
    @NotNull(message = "组织id必填", groups = {Query.class, select.class})
    private Long groupId;
    //设备号
    private String deviceNo;
    //页码
    private Integer pageNum = 1;
    //页大小
    private Integer pageSize = 20;

}
