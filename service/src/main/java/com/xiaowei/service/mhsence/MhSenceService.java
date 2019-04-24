package com.xiaowei.service.mhsence;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.ecsence.EcSenceEditReq;
import com.xiaowei.common.req.ecsence.EcSenceUuidReq;
import com.xiaowei.common.req.mhsence.MhSenceAddReq;
import com.xiaowei.common.req.mhsence.MhSenceEditReq;
import com.xiaowei.common.req.mhsence.MhSenceUuidReq;
import com.xiaowei.common.res.redis.RedisUser;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface MhSenceService {
    /**
     * 新增井盖场景信息
     * @param mhSenceAddReq
     * @return
     */
    public JSONResult add(MhSenceAddReq mhSenceAddReq, RedisUser redisUser);
  /*  *//**
     * 根据设备编号查询井盖场景信息
     * @param mhSenceUuidReq
     * @return
     *//*
    public JSONResult uuidMh(MhSenceUuidReq mhSenceUuidReq);
    *//**
     * 编辑井盖场景信息
     * @param mhSenceEditReq
     * @return
     *//*
    public JSONResult edit(MhSenceEditReq mhSenceEditReq);*/
    /**
     * redis
     * @return
     */
    public JSONResult selMhSen();
}
