package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.ParameterConfigurationDO;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ParameterMapper {
    /**
     * 参数分页
     * @param dictionaryId
     * @return
     */
    List<ParameterConfigurationDO> pageList(@Param("dictionaryId") Long dictionaryId);
}
