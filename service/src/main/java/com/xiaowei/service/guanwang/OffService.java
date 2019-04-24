package com.xiaowei.service.guanwang;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.guanwang.OffReq;
import com.xiaowei.common.res.redis.RedisUser;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface OffService {
    /**
     * 新增
     * @param offReq
     * @return
     */
    public JSONResult interOff(OffReq offReq);
}
