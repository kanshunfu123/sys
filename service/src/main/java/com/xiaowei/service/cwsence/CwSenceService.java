package com.xiaowei.service.cwsence;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.cwsence.CwSenceAddReq;
import com.xiaowei.common.req.cwsence.CwSenceEditReq;
import com.xiaowei.common.req.cwsence.CwSenceUuidReq;
import com.xiaowei.common.res.redis.RedisUser;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface CwSenceService {
    /**
     * 新增饮用水场景信息
     *
     * @param cwSenceAddReq
     * @return
     */
    public JSONResult add(CwSenceAddReq cwSenceAddReq, RedisUser redisUser);

  /*  *//**
     * 根据uuid查询饮用水场景信息
     *
     * @param cwSenceUuidReq
     * @return
     *//*
    public JSONResult uuidCw(CwSenceUuidReq cwSenceUuidReq, RedisUser redisUser);

    *//**
     * 编辑饮用水场景信息
     *
     * @param cwSenceEditReq
     * @return
     *//*
    public JSONResult editCwSence(CwSenceEditReq cwSenceEditReq, RedisUser redisUser);*/
    /**
     * redis
     * @return
     */
    public JSONResult selCwSen();
}
