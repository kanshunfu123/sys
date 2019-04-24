package com.xiaowei.service.dictionary;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.DictionaryAddReq;
import com.xiaowei.common.req.DictionaryReq;
import com.xiaowei.common.res.redis.RedisUser;

import java.util.List;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface DictionaryService {
    /**
     * 字典树形图
     */
    public JSONResult dictionaryTreeList();
    /**
     * 新增数据字典
     */
    public JSONResult interDictionary(DictionaryAddReq dictionaryAddReq, RedisUser redisUser);
    /**
     * 根据uuid查看字典架构
     *
     * @param sysDictionaryUuid
     * @return
     */
    public JSONResult getDictionaryInfoByUuid(String sysDictionaryUuid);
    /**
     * 编辑字典
     */
    public JSONResult updateDictionary(DictionaryReq dictionaryReq, RedisUser redisUser);
    /**
     * 删除字典
     *
     * @param dictionaryReq
     * @return
     */
    public JSONResult delDictionary(DictionaryReq dictionaryReq);
    /**
     * 根据parentId查询字典
     *
     * @param parentId
     * @return
     */
    public JSONResult getDictionaryInfoByParentId(Long parentId);
}
