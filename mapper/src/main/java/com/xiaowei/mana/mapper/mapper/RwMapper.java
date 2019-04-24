package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.XwCwSenceDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentRwDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_EQUIPMENT_CW.
 * 饮用水设备表
 */
public interface RwMapper {
    /**
     * 编号是否存在
     */
    public int deviceCode(@Param("deviceNo") String deviceNo, @Param("deviceCode") String deviceCode, @Param("area") String area);

    /**
     * 设备编号是否存在
     */
    public int deviceNo(@Param("deviceNo") String deviceNo);

    /**
     * 根据uuid查询电梯设备
     *
     * @param equipmentUuid
     * @return
     */
    public XwEquipmentRwDO uuid(@Param("equipmentUuid") String equipmentUuid);

    /**
     * 井盖设备分页
     *
     * @return
     */
    List<XwEquipmentRwDO> pageList(List<String> list, @Param("deviceNo") String deviceNo1, @Param("startTime") String startTime, @Param("endTime") String endTime, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId, @Param("areaId") Long areaId);

    List<XwEquipmentRwDO> selRw();
    /**
     * 根据deviceNo查询电梯设备
     *
     * @param deviceNo
     * @return
     */
    public XwEquipmentRwDO deNo(@Param("deviceNo") String deviceNo);

}
