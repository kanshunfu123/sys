package com.xiaowei.service.ec;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.cw.CwPushReq;
import com.xiaowei.common.req.ec.EcAddReq;
import com.xiaowei.common.req.ec.EcEditReq;
import com.xiaowei.common.req.ec.EcUuidReq;
import com.xiaowei.common.res.redis.RedisUser;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface EcService {
    /**
     * 新增电梯设备
     * @param ecAddReq
     * @return
     */
    public JSONResult interEc(EcAddReq ecAddReq,RedisUser redisUser);
    /**
     * 根据uuid查询电梯设备
     * @param ecUuidReq
     * @return
     */
    public JSONResult uuidEc(EcUuidReq ecUuidReq,RedisUser redisUser);
    /**
     * 编辑电梯设备
     * @param cwEditReq
     * @return
     */
    public JSONResult editEc(EcEditReq cwEditReq,RedisUser redisUser);

    /**
     * redis
     * @return
     */
    public JSONResult selEc();

    /**
     * 电梯故障推送
     * @param cwPushReq
     * @param redisUser
     * @return
     */
    public JSONResult editPush(CwPushReq cwPushReq, RedisUser redisUser);

}
