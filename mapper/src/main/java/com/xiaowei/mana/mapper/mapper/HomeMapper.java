package com.xiaowei.mana.mapper.mapper;


import com.xiaowei.mana.mapper.dataobject.XwEquipmentCwDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentEcDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentMhDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentRwDO;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_EQUIPMENT_CW.
 * 饮用水设备表
 */
public interface HomeMapper {
    /**
     * 小为账号统计电梯设备数量
     */
    Integer mainEcTotal();

    /**
     * 小为账号统计井盖设备数量
     */
    Integer mainMhTotal();

    /**
     * 小为账号统计地表水设备数量
     */
    Integer mainRwTotal();

    /**
     * 小为账号统计饮用水设备数量
     */
    Integer mainCwTotal();

    /**
     * 小为账号电梯运行总时长
     */
    List<Date> ecTotalTime();

    /**
     * 小为账号井盖运行总时长
     */
    List<Date> mhTotalTime();

    /**
     * 小为账号地表水运行总时长
     */
    List<Date> rwTotalTime();

    /**
     * 小为账号河道水运行总时长
     */
    List<Date> cwTotalTime();

    /**
     * 电梯最新接入设备
     */
    List<XwEquipmentEcDO> latestEc(@Param("count") Integer count);

    /**
     * 井盖最新接入设备
     */
    List<XwEquipmentMhDO> latestMh(@Param("count") Integer count);

    /**
     * 地表水最新接入设备
     */
    List<XwEquipmentRwDO> latestRw(@Param("count") Integer count);

    /**
     * 饮用水最新接入设备
     */
    List<XwEquipmentCwDO> latestCw(@Param("count") Integer count);


    /**
     * 电梯编号集合
     */
    List<String> deviceNoListEc();

    /**
     * 井盖编号集合
     */
    List<String> deviceNoListMh();

    /**
     * 地表水编号集合
     */
    List<String> deviceNoListRw();

    /**
     * 饮用水编号集合
     */
    List<String> deviceNoListCw();

    /**
     * 电梯总量
     */
    Integer ecSize();

    /**
     * 井盖总量
     */
    Integer mhSize();

    /**
     * 地表水总量
     */
    Integer rwSize();

    /**
     * 饮用水总量
     */
    Integer cwSize();

    /**
     * 电梯设备总量
     */
    Integer ecAccessTotal(@Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("provinceId")Long provinceId,@Param("cityId")Long cityId);
    /**
     * 井盖设备总量
     */
    Integer mhAccessTotal(@Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("provinceId")Long provinceId,@Param("cityId")Long cityId);
    /**
     * 地表水设备总量
     */
    Integer rwAccessTotal(@Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("provinceId")Long provinceId,@Param("cityId")Long cityId);
    /**
     * 饮用水设备总量
     */
    Integer cwAccessTotal(@Param("beginTime") String beginTime, @Param("endTime") String endTime,@Param("provinceId")Long provinceId,@Param("cityId")Long cityId);
}
