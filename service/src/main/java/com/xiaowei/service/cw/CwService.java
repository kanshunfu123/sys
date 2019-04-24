package com.xiaowei.service.cw;

import com.github.pagehelper.PageInfo;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.cw.*;
import com.xiaowei.common.req.rw.RwPageList;
import com.xiaowei.common.res.redis.RedisUser;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface CwService {
    /**
     * 新增饮用水设备
     * @param cwAddReq
     * @return
     */
    public JSONResult interCw(CwAddReq cwAddReq,RedisUser redisUser);

    /**
     * 根据uuid查询饮用水设备
     * @param cwUuidReq
     * @return
     */
    public JSONResult uuidCw(CwUuidReq cwUuidReq,RedisUser redisUser);
    /**
     * 编辑饮用水设备
     * @param cwEditReq
     * @return
     */
    public JSONResult editCw(CwEditReq cwEditReq,RedisUser redisUser);
    /**
     * redis
     * @return
     */
    public JSONResult selCw();
    public JSONResult editPush(CwPushReq cwPushReq, RedisUser redisUser);



}
