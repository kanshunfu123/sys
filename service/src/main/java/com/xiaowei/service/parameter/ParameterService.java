package com.xiaowei.service.parameter;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.ParameterAddReq;
import com.xiaowei.common.req.ParameterByUuidReq;
import com.xiaowei.common.req.ParameterPageList;
import com.xiaowei.common.req.ParameterReq;
import com.xiaowei.common.res.redis.RedisUser;


/**
 * Created by kanshunfu on 2019/01/08.
 */
public interface ParameterService {
    /**
     * 新增参数
     *
     * @param parameterAddReq
     * @return
     */
    public JSONResult interParameter(ParameterAddReq parameterAddReq, RedisUser redisUser);

    /**
     * 根据dictionaryId分页查看参数列表详情
     *
     * @param parameterPageList
     * @return
     */
    public JSONResult pageList(ParameterPageList parameterPageList, RedisUser redisUser);

    /**
     * 编辑参数
     *
     * @param parameterAddReq
     * @return
     */
    public JSONResult updateParameter(ParameterAddReq parameterAddReq, RedisUser redisUser);

    /**
     * 根据uuid查看参数列表详情
     *
     * @param parameterByUuidReq
     * @return
     */
    public JSONResult getInfoByUuid(ParameterByUuidReq parameterByUuidReq);

    /**
     * 逻辑删除参数
     *
     * @param parameterReq
     * @return
     */
    public JSONResult delParameter(ParameterReq parameterReq);



}
