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
public class VillageInfoReq extends BaseReq {
    /**
     * id 主键.
     */
    @NotNull(message = "小区id必填",groups = {Query.class})
    private Long id;
    /**
     * areaId 区id.
     */
    private Long areaId;
    /**
     * cityId 市id.
     */
    private Long cityId;
    /**
     * provinceId 省id.
     */
    private Long provinceId;
    /**
     * area 区.
     */
    private String area;
    /**
     * city 市.
     */
    private String city;
    /**
     * street 街道（设备表用此字段）.
     */
    private String street;
    /**
     * delFlag 0 正常 1删除.
     */
    private String delFlag;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * villageName 小区名称.
     */
    private String villageName;
    /**
     * villageUuid 小区uuid.
     */
    private String villageUuid;
    /**
     * provinceName 省.
     */
    private String provinceName;
}
