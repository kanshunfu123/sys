package com.xiaowei.mana.mapper.mapper.device;

import com.xiaowei.mana.mapper.dataobject.*;

import java.util.List;

/**
 * Created by MOMO on 2019/1/14.
 */
public interface RWDeviceMapper {
    /**
     * 批量获取设备
     * 0
     *
     * @param longs
     * @return
     */
    public List<DeviceRoleDeviceRea> RWbatchCWsByDeviceId(List<String> longs);

    /**
     * 1
     *
     * @param longs
     * @return
     */
    public List<DeviceRoleDeviceRea> MHbatchCWsByDeviceId(List<String> longs);

    /**
     * 2
     *
     * @param longs
     * @return
     */
    public List<DeviceRoleDeviceRea> CWbatchCWsByDeviceId(List<String> longs);

    /**
     * 3
     *
     * @param longs
     * @return
     */
    public List<DeviceRoleDeviceRea> ECbatchCWsByDeviceId(List<String> longs);

    /**
     * 根据区域   查询 地表水设备
     *
     * @return
     */
    public List<XwEquipmentRwDO> getRWAllBySelect_v1(AreaToDeviceVO_v1 areaToDeviceVO_v1);
    /**
     * 根据区域   查询 地表水设备
     *
     * @return
     */
    public List<XwEquipmentRwDO> getRWAllBySelect_v1_admin(AreaToDeviceVO_v1 areaToDeviceVO_v1);

    /**
     * 根据区域   查询   井盖  设备
     *
     * @return
     */
    public List<XwEquipmentMhDO> getMHAllBySelect_v1(AreaToDeviceVO_v1 areaToDeviceVO_v1);
    /**
     * 根据区域   查询   井盖  设备
     *
     * @return
     */
    public List<XwEquipmentMhDO> getMHAllBySelect_v1_admin(AreaToDeviceVO_v1 areaToDeviceVO_v1);

    /**
     * 根据区域   查询   生活用水  设备
     *
     * @return
     */
    public List<XwEquipmentCwDO> getCWAllBySelect_v1(AreaToDeviceVO_v1 areaToDeviceVO_v1);
    /**
     * 根据区域   查询   生活用水  设备
     *
     * @return
     */
    public List<XwEquipmentCwDO> getCWAllBySelect_v1_admin(AreaToDeviceVO_v1 areaToDeviceVO_v1);

    /**
     * 根据区域   查询  电梯  设备
     *
     * @return
     */
    public List<XwEquipmentEcDO> getECAllBySelect_v1(AreaToDeviceVO_v1 areaToDeviceVO_v1);
    /**
     * 根据区域   查询  电梯  设备
     *
     * @return
     */
    public List<XwEquipmentEcDO> getECAllBySelect_v1_admin(AreaToDeviceVO_v1 areaToDeviceVO_v1);
}
