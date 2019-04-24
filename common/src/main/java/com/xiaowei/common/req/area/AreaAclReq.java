package com.xiaowei.common.req.area;

import com.xiaowei.common.error.BaseReq;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by MOMO on 2019/2/14.
 */
@Data
public class AreaAclReq extends BaseReq{

    //层级 0省 1市 2区 3街道  4 小区
    @NotNull(message = "区域层级必填 level",groups = {Query.class})
    private Integer level;
    //首级为 0
    @NotNull(message = "父级id 必填 parentId",groups = {Query.class})
    private Long parentId;
    //0 地表水 1井盖 2生活用水 3电梯
//    @NotBlank(message = "场景类型必填",groups = {Query.class})
    private String deviceType;
    //sysAreaLevel
    private String areaLevel;
}
