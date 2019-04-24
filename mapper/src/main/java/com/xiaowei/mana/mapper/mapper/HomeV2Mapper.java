package com.xiaowei.mana.mapper.mapper;

import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * created by 韩金群 2019/2/20
 */
public interface HomeV2Mapper {
    /**
     * 小为账号统计电梯设备数量
     */
    Integer mainEcTotal(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 小为账号统计井盖设备数量
     */
    Integer mainMhTotal(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 小为账号统计地表水设备数量
     */
    Integer mainRwTotal(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 小为账号统计饮用水设备数量
     */
    Integer mainCwTotal(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 小为账号电梯运行总时长
     */
    List<Date> ecTotalTime(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 小为账号井盖运行总时长
     */
    List<Date> mhTotalTime(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 小为账号地表水运行总时长
     */
    List<Date> rwTotalTime(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 小为账号河道水运行总时长
     */
    List<Date> cwTotalTime(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 电梯总量
     */
    Integer ecSize(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 井盖总量
     */
    Integer mhSize(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 地表水总量
     */
    Integer rwSize(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 饮用水总量
     */
    Integer cwSize(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
    /**
     * 小为账号电梯设备列表
     */
    List<String> ecTotalDevice(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 小为账号井盖设备列表
     */
    List<String> mhTotalDevice(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 小为账号地表水设备列表
     */
    List<String> rwTotalDevice(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 小为账号河道水设备列表
     */
    List<String> cwTotalDevice(@Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
}
