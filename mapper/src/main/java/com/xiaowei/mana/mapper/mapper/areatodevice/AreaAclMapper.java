package com.xiaowei.mana.mapper.mapper.areatodevice;

import com.xiaowei.mana.mapper.dataobject.*;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Created by MOMO on 2019/2/14.
 */
public interface AreaAclMapper {

    /**
     * 地图 每一层级 区域
     *
     * @param level   层级 0省 1市 2区 3街道  4 小区)
     * @param roleIds 角色 id 列表
     * @return
     */
    public List<AreaDO> getAreaByAcl(@Param("lvl") Integer level, @Param("list") List<Long> roleIds, @Param("mapZoomVO") MapZoomVO mapZoomVO, @Param("deviceType") String deviceType);

    /**
     * 地图 小区
     *
     * @param level   层级 0省 1市 2区 3街道  4 小区)
     * @param roleIds 角色 id 列表
     * @return
     */
    public List<AreaDO> getVillageByAcl(@Param("lvl") Integer level, @Param("list") List<Long> roleIds, @Param("mapZoomVO") MapZoomVO mapZoomVO, @Param("deviceType") String deviceType);

    public List<AreaDO> getVillageByAcl_admin(@Param("cc") Integer cc, @Param("bb") Integer bb, @Param("aaaa") Integer level, @Param("list") List<Long> roleIds, @Param("mapZoomVO") MapZoomVO mapZoomVO, @Param("deviceType") String deviceType);

    /**
     * 角色所拥有的区域权限，按场景来
     *
     * @param roleId
     * @param deviceType
     * @return
     */
    public List<Long> getRoleAclArea(@Param("roleId") List<Long> roleId, @Param("deviceType") String deviceType);

    /**
     * 第三方用户
     * 首页地图权限第二版
     *
     * @param lev
     * @param parentId
     * @param userId
     * @return
     */
    public List<AreaAclMapDO_v2> v2_commonListMapAcl(@Param("lev") Integer lev, @Param("parentId") Long parentId, @Param("userId") Long userId);

    /**
     * 第三方用户
     * 首页地图权限第二版
     *
     * @param lev
     * @param parentId
     * @param userId
     * @return
     */
    public List<AreaAclMapDO_v2> v2_commonListMapAcl_two(@Param("lev") Integer lev, @Param("parentId") Long parentId, @Param("userId") Long userId);


    /**
     * @param village    为null 查询区域表 不为null 查询小区表
     * @param deviceType 0 地表水 1井盖 2生活用水 3电梯
     * @param lev        0省 1市 2区 3街道  4 小区
     * @param parentId   父级id
     * @param streetId   街道id
     * @return
     */

    public List<AreaAclMapDO_v2> v2_adminListMapAcl(@Param("village") Integer village, @Param("deviceType") String deviceType, @Param("lev") Integer lev, @Param("parentId") Long parentId, @Param("streetId") Long streetId);


    /**
     * 第三方用户
     * 首页地图权限第二版
     *
     * @param lev
     * @param parentId
     * @param userId
     * @return
     */
    public List<AreaAclMapDO_v2> v3_commonListMapAcl(@Param("lev") Integer lev, @Param("parentId") Long parentId, @Param("userId") Long userId, @Param("mapZoomVO") MapZoomVO mapZoomVO);

    /**
     * 第三方用户
     * 首页地图权限第二版
     *
     * @param lev
     * @param parentId
     * @param userId
     * @return
     */
    public List<AreaAclMapDO_v2> v3_commonListMapAcl_two(@Param("lev") Integer lev, @Param("parentId") Long parentId, @Param("userId") Long userId, @Param("mapZoomVO") MapZoomVO mapZoomVO);


    /**
     * @param village    为null 查询区域表 不为null 查询小区表
     * @param deviceType 0 地表水 1井盖 2生活用水 3电梯
     * @param lev        0省 1市 2区 3街道  4 小区
     * @param parentId   父级id
     * @param streetId   街道id
     * @return
     */

    public List<AreaAclMapDO_v2> v3_adminListMapAcl(@Param("village") Integer village, @Param("deviceType") String deviceType, @Param("lev") Integer lev, @Param("parentId") Long parentId, @Param("streetId") Long streetId, @Param("mapZoomVO") MapZoomVO mapZoomVO);

    public List<AreaAclMapDO_v2> v3_mmp_ec(@Param("sets") Set<Long> sets);

    public List<AreaAclMapDO_v2> v3_mmp_cw(@Param("sets") Set<Long> sets, @Param("lev") Integer lev);

    //#############################     V3  大公鸡    #####################################################

    /**
     * @param areaId     前端传区域id
     * @param lev        层级
     * @param deviceType 0 地表水 1井盖 2生活用水 3电梯
     * @return
     */
    public AreaAclMapDO_v2 common_showMapInfo(@Param("areaId") Long areaId, @Param("lev") Integer lev, @Param("deviceType") Integer deviceType, @Param("userId") Long userId);

    public Long common_breakdown_showMapInfo(@Param("areaId") Long areaId, @Param("lev") Integer lev, @Param("deviceType") Integer deviceType, @Param("userId") Long userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
    public Long common_offLine_showMapInfo(@Param("areaId") Long areaId, @Param("lev") Integer lev, @Param("deviceType") Integer deviceType, @Param("userId") Long userId, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * @param areaId     前端传区域id
     * @param lev        层级
     * @param deviceType 0 地表水 1井盖 2生活用水 3电梯
     * @return
     */
    public AreaAclMapDO_v2 admin_showMapInfo(@Param("areaId") Long areaId, @Param("lev") Integer lev, @Param("deviceType") Integer deviceType, @Param("mmp") Integer mmp, @Param("three") Integer three);

    public Long admin_breakdown_showMapInfo(@Param("areaId") Long areaId, @Param("lev") Integer lev, @Param("deviceType") Integer deviceType, @Param("mmp") Integer mmp, @Param("three") Integer three, @Param("beginTime") String beginTime, @Param("endTime") String endTime);
    public Long admin_offLine_showMapInfo(@Param("areaId") Long areaId, @Param("lev") Integer lev, @Param("deviceType") Integer deviceType, @Param("mmp") Integer mmp, @Param("three") Integer three, @Param("beginTime") String beginTime, @Param("endTime") String endTime);

    /**
     * 查看当前登录用户的场景列表
     *
     * @param userId
     */
    public String secons(@Param("userId") Long userId);
    //#############################     V3  区域分布图    #####################################################

    /**
     * @param lev    区域层级
     * @param userId 用户id
     * @return
     */
    public List<AreaAclMapDO_v2> com_areaUsing(@Param("lev") String lev, @Param("userId") Long userId);
    public List<AreaAclMapDO_v2> com_areaUsing_v2(@Param("deviceType") String deviceType, @Param("userId") Long userId,@Param("level")String level,@Param("parentId")Long parentId);

    /**
     * @param lev        区域层级 0.1
     * @param deviceType 0 地表水 1井盖 2生活用水 3电梯
     * @param levels     0省 1市 2区 3街道  4 小区
     * @return
     */

    public List<AreaAclMapDO_v2> admin_areaUsing(@Param("deviceType") String deviceType, @Param("userId") Long userId,@Param("level")String level,@Param("parentId")Long parentId);


}
