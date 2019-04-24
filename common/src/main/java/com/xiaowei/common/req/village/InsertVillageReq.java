package com.xiaowei.common.req.village;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * Created by MOMO on 2019/1/19.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertVillageReq extends BaseReq {

    /**
     * areaId 区id.
     */
    @NotNull(message = "区id 必填 areaId",groups = {Add.class,Modify.class})
    @Min(value = 1,message ="区id 最小值为1",groups = {Add.class})
    private Long areaId;
    /**
     * cityId 市id.
     */
    @NotNull(message = "市id 必填 cityId",groups = {Add.class,Modify.class})
    @Min(value = 1,message ="市id 最小值为1",groups = {Add.class,Modify.class})
    private Long cityId;
    /**
     * provinceId 省id.
     */
    @NotNull(message = "省id 必填 provinceId",groups = {Add.class,Modify.class})
    @Min(value = 1,message ="省id 最小值为1",groups = {Add.class,Modify.class})
    private Long provinceId;
    /**
     * area 区.
     */
    @NotBlank(message = "区名称 必填 area",groups = {Add.class,Modify.class})
    private String area;
    /**
     * city 市.
     */
    @NotBlank(message = "市名称 必填 city",groups = {Add.class,Modify.class})
    private String city;
    /**
     * street 街道（设备表用此字段）.
     */
    @NotBlank(message = "街道名称 必填 street",groups = {Add.class,Modify.class})
    private String street;
    /**
     * streetId 街道id.
     */
    @NotNull(message = "街道id必填 streetId",groups = {Add.class,Modify.class})
    @Min(value = 1,message ="街道id 最小值为1",groups = {Add.class,Modify.class})
    private Long streetId;
    /**
     * villageName 小区名称.
     */
    @NotBlank(message = "小区名称名称 必填 villageName",groups = {Add.class,Modify.class})
    private String villageName;
    /**
     * villageUuid 小区uuid.
     */
    @NotBlank(message = "小区uuid 必填 villageUuid",groups = {Modify.class,Query.class})
    private String villageUuid;
    /**
     * provinceName 省.
     */
    @NotBlank(message = "省名称 必填 provinceName",groups = {Add.class,Modify.class})
    private String provinceName;
    /**
     * remark 备注.
     */
    private String remark;
    /**
     * typeName 类型名称.
     */
    private String typeName;
    /**
     * villageType 小区类型(0 低层住宅 1高层住宅）.
     */
    private Integer villageType;
    /**
     * address 详细地址.
     */
    private String address;
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
