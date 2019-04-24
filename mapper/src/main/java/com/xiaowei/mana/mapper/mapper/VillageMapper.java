package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.VillageDO;
import com.xiaowei.mana.mapper.dataobject.XwVillageDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface VillageMapper {

    XwVillageDO selectByPrimaryKey(Long id);

    /**
     * 根据区域id查询 小区列表
     *
     * @param areaId
     * @return
     */
    public List<VillageDO> getAllByAreaId(@Param("areaId") Long areaId);

    int insertSelective(VillageDO record);

    int updateByPrimaryKey(VillageDO record);

    VillageDO getVillageByUUID(@Param("uuid") String uuid);

    List<VillageDO> pageListVi(@Param("villageName") String villageName);

}
