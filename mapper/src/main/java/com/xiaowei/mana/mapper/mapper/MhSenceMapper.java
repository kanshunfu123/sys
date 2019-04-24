package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.XwEcSenceDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentEcDO;
import com.xiaowei.mana.mapper.dataobject.XwMhSenceDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 由于需要对分页支持,请直接使用对应的DAO类
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 * The Table XW_EQUIPMENT_CW.
 * 饮用水设备表
 */
public interface MhSenceMapper {

    /**
     * 设备编号是否存在
     */
    public int deviceNo(@Param("deviceNo") String deviceNo);
    /**
     * 设备编号是否存在
     */
    public int deNo(@Param("deviceNo") String deviceNo);
    /**
     * 根据设备号查询电梯场景信息
     * @param deviceNo
     * @return
     */
    public XwMhSenceDO uuid(@Param("deviceNo") String deviceNo);
    /**
     * 根据设备号查询场景信息
     * @param deviceNo
     * @return
     */
    XwMhSenceDO mhSenceByNo(@Param("deviceNo") String deviceNo);

    /**
     * 井盖场景设备编号是否存在
     */
    public int deSenNo(@Param("deviceNo") String deviceNo);
    List<XwMhSenceDO> selMhSen();



}
