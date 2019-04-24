package com.xiaowei.common.req.area;

import com.xiaowei.common.error.BaseReq;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by MOMO on 2019/2/14.
 * 地图缩放
 */
@Data
@ToString
public class MapZoom extends BaseReq{
    //经度
//    @NotBlank(message = "经度左边的必填 lngLeft",groups = {Query.class})
    private BigDecimal lngLeft;
//    @NotBlank(message = "经度右边的必填 lngRight",groups = {Query.class})
    private BigDecimal lngRight;
    //纬度
//    @NotBlank(message = "纬度左边的必填 latLeft",groups = {Query.class})
    private BigDecimal latLeft;
//    @NotBlank(message = "纬度右边的必填 latRight",groups = {Query.class})
    private BigDecimal latRight;

    //层级 0省 1市 2区 3街道  4 小区
    @NotNull(message = "区域层级必填 level",groups = {Query.class})
    private Integer level;
    //首级为 0
//    @NotNull(message = "父级id 必填 parentId",groups = {Query.class})
//    private Long parentId;
    //0 地表水 1井盖 2生活用水 3电梯
//    @NotBlank(message = "场景类型必填",groups = {Query.class})
    private String deviceType;
}
