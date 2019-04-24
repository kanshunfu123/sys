package com.xiaowei.service.rwsence;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.rwsence.RwSenceAddReq;
import com.xiaowei.common.req.rwsence.RwSenceEditReq;
import com.xiaowei.common.req.rwsence.RwSenceUuidReq;
import com.xiaowei.common.res.redis.RedisUser;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface RwSenceService {
    /**
     * 新增地表水场景信息
     * @param rwSenceAddReq
     * @return
     */
    public JSONResult add(RwSenceAddReq rwSenceAddReq, RedisUser redisUser);
    /**
     * 根据设备编号查询地表水场景信息
     * @param rwSenceUuidReq
     * @return
     */
    public JSONResult uuidRw(RwSenceUuidReq rwSenceUuidReq, RedisUser redisUser);
    /**
     * 编辑电地表水场景信息
     * @param rwSenceEditReq
     * @return
     */
    public JSONResult edit(RwSenceEditReq rwSenceEditReq, RedisUser redisUser);
    /**
     * redis
     * @return
     */
    public JSONResult selRwSen();
}
