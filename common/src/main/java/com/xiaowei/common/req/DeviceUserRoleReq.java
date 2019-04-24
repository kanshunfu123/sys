package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by MOMO on 2019/1/14.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeviceUserRoleReq extends BaseReq{
    @NotNull(message = "用户id 必填 userId",groups = {Query.class})
    private Long userId;
}
