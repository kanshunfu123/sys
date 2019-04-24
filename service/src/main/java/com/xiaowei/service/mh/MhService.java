package com.xiaowei.service.mh;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.cw.CwPushReq;
import com.xiaowei.common.req.mh.MhAddReq;
import com.xiaowei.common.req.mh.MhEditReq;
import com.xiaowei.common.req.mh.MhUuidReq;
import com.xiaowei.common.res.redis.RedisUser;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface MhService {
    /**
     * 新增井盖设备
     * @param mhAddReq
     * @return
     */
    public JSONResult interMh(MhAddReq mhAddReq, RedisUser redisUser);
    /**
     * 根据uuid查询井盖设备
     * @param mhUuidReq
     * @return
     */
    public JSONResult uuidMh(MhUuidReq mhUuidReq,RedisUser redisUser);
    /**
     * 编辑井盖设备
     * @param mhEditReq
     * @return
     */
    public JSONResult editMh(MhEditReq mhEditReq,RedisUser redisUser);
    /**
     * redis
     * @return
     */
    public JSONResult selMh();
    public JSONResult editPush(CwPushReq cwPushReq, RedisUser redisUser);

}
