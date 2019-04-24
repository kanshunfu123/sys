package com.xiaowei.common.req.village;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by MOMO on 2019/1/19.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertStreetReq extends BaseReq {

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

    @NotBlank(message = "街道名称必填 name",groups = {Add.class,Modify.class})
    private String name;
    /**
     * villageUuid 小区uuid.
     */
    @NotBlank(message = "街道uuid 必填 streetUuid",groups = {Modify.class,Query.class})
    private String streetUuid;
    /**
     * provinceName 省.
     */
    @NotBlank(message = "省名称 必填 provinceName",groups = {Add.class,Modify.class})
    private String provinceName;
}
