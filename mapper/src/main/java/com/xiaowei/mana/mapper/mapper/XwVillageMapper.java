package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.XwVillageDO;
import org.apache.ibatis.annotations.Param;


public interface XwVillageMapper {

    XwVillageDO selectByPrimaryKey(Long id);
    int insertSelective(XwVillageDO record);
    int updateByPrimaryKey(XwVillageDO record);
    XwVillageDO getVillageByUUID(@Param("uuid")String uuid);
}
