package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotNull;

/**
 * Created by MOMO on 2019/1/9.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaReq extends BaseReq{
    @NotNull(message = "地区id必填",groups = {Query.class})
    private Long id;
    @NotNull(message = "地区父 parentId必填",groups = {Add.class})
    private Long parentId=0L;

}
