package com.xiaowei.common.feign;

import com.xiaowei.common.error.BaseReq;
import lombok.Data;

import javax.validation.constraints.NotBlank;

/**
 * Created by MOMO on 2019/1/26.
 */
@Data
public class FeignECbyDeviceNoReq extends BaseReq{
    @NotBlank(message = "电梯设备号必填  deviceNo",groups = {Query.class})
    private String deviceNo;
}
