package com.xiaowei.mana.mapper.mapper.device;

import com.xiaowei.mana.mapper.dataobject.DeviceRoleDeviceRea;
import com.xiaowei.mana.mapper.dataobject.XwCwSenceDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentEcDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MOMO on 2019/1/14.
 */
public interface ECDeviceMapper {
    /**
     * 批量获取设备
     * @param longs
     * @return
     */
//    public List<DeviceRoleDeviceRea> batchCWsByDeviceId(List<Long> longs);

    /**
     * 根据设备号查询  电梯  设备信息
     * @param deviceNo
     * @return
     */
    public XwEquipmentEcDO  feignECbyDeviceNo(@Param("deviceNo")String deviceNo);
}
