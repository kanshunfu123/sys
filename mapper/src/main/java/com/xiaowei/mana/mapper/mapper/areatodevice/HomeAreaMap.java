package com.xiaowei.mana.mapper.mapper.areatodevice;

import com.xiaowei.mana.mapper.dataobject.AreaDO;
import com.xiaowei.mana.mapper.dataobject.AreaDO_v3;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by MOMO on 2019/3/6.
 */
public interface HomeAreaMap {

    //############################     admin 首页地图        ##################################

    public List<AreaDO> adminHomeMap(@Param("parentId") Long parentId);

    //############################     common 首页地图        ##################################

    /**
     * //0 地表水 1井盖 2生活用水 3电梯
     *
     * @param userId
     * @param areaLevel 层级 0省 1市 2区 3街道  4 小区)
     * @return
     */
    public List<AreaDO> commonHomeMap(@Param("userId") Long userId, @Param("level") Integer areaLevel, @Param("deviceType") String deviceType,@Param("areaId") Long areaId);

    //############################     admin  单个场景 省市区 地图        ##################################
    public List<AreaDO_v3> adminECHomeMap(@Param("parentId") Long parentId,@Param("level")String level);

    public List<AreaDO_v3> adminRWHomeMap(@Param("parentId") Long parentId,@Param("level")String level);

    public List<AreaDO_v3> adminMHHomeMap(@Param("parentId") Long parentId,@Param("level")String level);

    public List<AreaDO_v3> adminCWHomeMap(@Param("parentId") Long parentId,@Param("level")String level);
    //############################     admin  单个场景 小区 地图        ##################################
    public List<AreaDO_v3> adminECHomeMap_village(@Param("parentId") Long parentId,@Param("areaLevel")String areaLevel);

    public List<AreaDO_v3> adminRWHomeMap_village(@Param("parentId") Long parentId);

    public List<AreaDO_v3> adminMHHomeMap_village(@Param("parentId") Long parentId);

    public List<AreaDO_v3> adminCWHomeMap_village(@Param("parentId") Long parentId,@Param("areaLevel")String areaLevel);

    //############################     common  单个场景 省市区 地图        ##################################
    public List<AreaDO_v3> commonECHomeMap(@Param("userId") Long userId,@Param("deviceType") String deviceType,@Param("level")String level,@Param("areaId")Long areaId);
    public List<AreaDO_v3> commonECHomeMap_village(@Param("userId") Long userId,@Param("deviceType") String deviceType,@Param("level")String level,@Param("areaLevel")String areaLevel);
}
