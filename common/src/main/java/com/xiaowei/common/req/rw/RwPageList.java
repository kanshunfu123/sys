package com.xiaowei.common.req.rw;

import com.xiaowei.common.error.BaseReq;
import com.xiaowei.common.res.redis.RedisUser;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * Created by kanshunfu on 2019/01/14.
 */
public class RwPageList extends BaseReq implements Serializable {
    @Min(value = 1, message = "每页显示记录数不能为空，且只能为整数", groups = {Query.class})
    private Integer limit = 10;//每页显示记录数
    @Min(value = 1, message = "页码不能为空，且只能为整数", groups = {Query.class})
    private Integer currPageNo = 1;//当前页 页码
    //0 地表水 1井盖 2生活用水 3电梯'
    @NotBlank( message = "场景类型不能为空", groups = {Query.class})
    private String deviceType;
    private String deviceNo;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 省id
     */
    private Long provinceId;

    /**
     * 市id
     * @return
     */
    private Long cityId;

    /**
     * 区id
     * @return
     */
    private Long areaId;

    public Long getProvinceId() {
        return provinceId;
    }

    public void setProvinceId(Long provinceId) {
        this.provinceId = provinceId;
    }

    public Long getCityId() {
        return cityId;
    }

    public void setCityId(Long cityId) {
        this.cityId = cityId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDeviceNo() {
        return deviceNo;
    }

    public void setDeviceNo(String deviceNo) {
        this.deviceNo = deviceNo;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getLimit() {
        return limit;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public Integer getCurrPageNo() {
        return currPageNo;
    }

    public void setCurrPageNo(Integer currPageNo) {
        this.currPageNo = currPageNo;
    }
}
