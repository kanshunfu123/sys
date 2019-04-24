package com.xiaowei.mana.mapper.mapper.areatodevice;

import com.xiaowei.mana.mapper.dataobject.XwEquipmentCwDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentEcDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentRwDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MOMO on 2019/1/18.
 */
public interface AreatodeviceMapper {
    /**
     * 根据下拉的id 查询设备
     * 地表水
     *
     * @return
     */
    public List<XwEquipmentRwDO> getRWAllBySelect(@Param("areaId") Long areaId);

    /**
     * 根据下拉的id 查询设备
     * 井盖
     *
     * @param areaId
     * @return
     */
    public List<XwEquipmentRwDO> getMHAllBySelect(@Param("areaId") Long areaId);
    /**
     * 根据下拉的id 查询设备
     * 井盖
     *
     * @param areaId
     * @return
     */
    public List<XwEquipmentCwDO> getCWAllBySelect(@Param("areaId") Long areaId);
    /**
     * 根据下拉的id 查询设备
     * 电梯
     *
     * @param areaId
     * @return
     */
    public List<XwEquipmentEcDO> getECAllBySelect(@Param("areaId") Long areaId);

    /**
     * 根据设备号列表查询设备
     * 地表水
     *
     * @param deiviceNos
     * @return
     */
    public List<XwEquipmentRwDO> getRWDeivcesByDeviceNos(List<String> deiviceNos);

    /**
     * 根据设备号列表查询设备
     * 井盖
     *
     * @param deiviceNos
     * @return
     */
    public List<XwEquipmentRwDO> getMHDeivcesByDeviceNos(List<String> deiviceNos);
    /**
     * 根据设备号列表查询设备
     * 生活用水
     *
     * @param deiviceNos
     * @return
     */
    public List<XwEquipmentCwDO> getCWDeivcesByDeviceNos(List<String> deiviceNos);
    /**
     * 根据设备号列表查询设备
     * 电梯
     *
     * @param deiviceNos
     * @return
     */
    public List<XwEquipmentEcDO> getECDeivcesByDeviceNos(List<String> deiviceNos);
}
