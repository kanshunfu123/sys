package com.xiaowei.mana.mapper.mapper;

import com.xiaowei.mana.mapper.dataobject.DictionaryDataDO;
import com.xiaowei.mana.mapper.resultmap.DictionaryTreeResult;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface DictionaryMapper {
    /**
     * 查看字典树
     * @return
     */
    public List<DictionaryTreeResult> DictionaryTree();

    /**
     * 根据uuid查询字典树
     * @param sysDictionaryUuid
     * @return
     */
    public DictionaryDataDO dictionaryDataDO(@Param("sysDictionaryUuid") String sysDictionaryUuid);
}

