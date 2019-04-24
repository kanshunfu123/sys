package com.xiaowei.common.req.area;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by tonghao 2019/1/19.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AreaAddReq extends BaseReq {
    @NotBlank(message = "部门uuid必填 ", groups = {Modify.class})
    private String sysAreaUuid;
    /**
     * sysAreaParentId 上级区域id.
     */
    @NotNull(message = "上级区域id 必填",groups = {Add.class, Modify.class})
    @Min(value = 0,message = "最小值为0",groups = {Add.class, Modify.class})
    private Long sysAreaParentId = 0L;

    /**
     * sysAreaName 区域名称.
     */
    @NotBlank(message = "区域名称 必填 sysAreaName", groups = {Add.class, Modify.class})
    private String sysAreaName;

    /**
     * sysAreaRemark 备注.
     */
    private String sysAreaRemark;
    /**
     * sysAreaSeq 区域在当前层级下的顺序，由小到大.
     */
    @NotNull(message = "区域在当前层级下的顺序 必填 sysAreaSeq", groups = {Add.class, Modify.class})
    private Integer sysAreaSeq;
    /**
     * 经度
     */
    @NotNull(message = "经度 必填 longitude",groups = {Add.class,Modify.class})
    private BigDecimal longitude;
    /**
     * 纬度
     */
    @NotNull(message = "纬度 必填 longitude",groups = {Add.class,Modify.class})
    private BigDecimal latitude;
}
