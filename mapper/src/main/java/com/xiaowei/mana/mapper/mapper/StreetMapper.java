package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.StreetDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;


public interface StreetMapper {

    /**
     * 根据区域id查询 街道列表
     * @param areaId
     * @return
     */
    public List<StreetDO> getAllByAreaId(@Param("areaId")Long areaId);
    int insertSelective(StreetDO record);

    StreetDO getStreetByUUID(@Param("uuid")String uuid);
    int updateByPrimaryKeySelective(StreetDO record);

}
