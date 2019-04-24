package com.xiaowei.mana.mapper.mapper.device;

import com.xiaowei.mana.mapper.dataobject.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MOMO on 2019/1/14.
 */
public interface RWDeviceV2Mapper {
    /**
     * 批量获取设备
     * 0
     *
     * @param list
     * @return
     */
    public List<DeviceRoleDeviceRea> RWbatchCWsByDeviceId(List<String> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 1
     *
     * @param list
     * @return
     */
    public List<DeviceRoleDeviceRea> MHbatchCWsByDeviceId(List<String> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 2
     *
     * @param list
     * @return
     */
    public List<DeviceRoleDeviceRea> CWbatchCWsByDeviceId(List<String> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);

    /**
     * 3
     *
     * @param list
     * @return
     */
    public List<DeviceRoleDeviceRea> ECbatchCWsByDeviceId(List<String> list, @Param("provinceId") Long provinceId, @Param("cityId") Long cityId);
}
