package com.xiaowei.common;

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
public class DeviceUserRoleV2Req extends BaseReq {
    @NotNull(message = "用户id 必填 userId", groups = {Query.class})
    private Long userId;
    @NotNull(message = "level不能为空", groups = {Query.class})
    private Integer level;
    @NotNull(message = "parentId不能为空", groups = {Query.class})
    private Long parentId;
}
