package com.xiaowei.mana.mapper.mapper.groupDevice;

import com.xiaowei.mana.mapper.dataobject.XwEquipmentCwDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentEcDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentMhDO;
import com.xiaowei.mana.mapper.dataobject.XwEquipmentRwDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MOMO on 2019/1/25.
 */
public interface MMPgroupDevice {
    //#####################     已设备授权-- 不包含已经授权的设备列表     ##############################

    /**
     * 电梯
     *
     * @param groupId
     * @param lists
     * @return
     */
    public List<XwEquipmentEcDO> ecAclList(@Param("deviceNo") String deviceNo, @Param("lists") List<String> lists);

    /**
     * 井盖
     *
     * @param groupId
     * @param lists
     * @return
     */
    public List<XwEquipmentMhDO> mhAclList(@Param("deviceNo") String deviceNo, @Param("lists") List<String> lists);

    /**
     * 地表水
     *
     * @param groupId
     * @param lists
     * @return
     */
    public List<XwEquipmentRwDO> rwAclList(@Param("deviceNo") String deviceNo, @Param("lists") List<String> lists);
    /**
     * 地表水
     *
     * @param groupId
     * @param lists
     * @return
     */
    public List<XwEquipmentCwDO> cwAclList(@Param("deviceNo") String deviceNo, @Param("lists") List<String> lists);

    //#####################     设备授权-- 不包含已经授权的设备列表     ##############################

    /**
     * 生活用水
     *
     * @return
     */
    public List<XwEquipmentCwDO> cwList(@Param("deviceNo") String deviceNo);

    /**
     * 地表水
     *
     * @return
     */
    public List<XwEquipmentRwDO> rwList(@Param("deviceNo") String deviceNo);

    /**
     * 井盖
     *
     * @return
     */
    public List<XwEquipmentMhDO> mhList(@Param("deviceNo") String deviceNo);

    /**
     * 电梯
     *
     * @return
     */
    public List<XwEquipmentEcDO> ecList(@Param("deviceNo") String deviceNo);

}
