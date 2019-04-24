package com.xiaowei.mana.mapper.mapper.device;

import com.xiaowei.mana.mapper.dataobject.DeviceRoleDeviceRea;
import com.xiaowei.mana.mapper.dataobject.XwCwSenceDO;

import java.util.List;

/**
 * Created by MOMO on 2019/1/14.
 */
public interface CWDeviceMapper {
    /**
     * 批量获取设备
     * @param longs
     * @return
     */
    public List<DeviceRoleDeviceRea> batchCWsByDeviceId(List<Long> longs);
}
