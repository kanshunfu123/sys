package com.xiaowei.service.ecsence;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.ecsence.EcSenceAddReq;
import com.xiaowei.common.req.ecsence.EcSenceEditReq;
import com.xiaowei.common.req.ecsence.EcSenceUuidReq;
import com.xiaowei.common.res.redis.RedisUser;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface EcSenceService {
    /**
     * 新增电梯场景信息
     * @param ecSenceAddReq
     * @return
     */
    public JSONResult add(EcSenceAddReq ecSenceAddReq, RedisUser redisUser);

    /**
     * 编辑电梯场景信息
     * @param ecSenceEditReq
     * @return
     *//*
    public JSONResult edit(EcSenceEditReq ecSenceEditReq);
    *//**
     * 根据设备编号查询饮用水场景信息
     * @param
     * @return
     *//*
    public JSONResult uuidEc(EcSenceUuidReq ecSenceUuidReq);*/
    public JSONResult selEcSen();

}
