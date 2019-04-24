package com.xiaowei.service.rw;

import com.github.pagehelper.PageInfo;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.cw.CwPushReq;
import com.xiaowei.common.req.mh.MhPageList;
import com.xiaowei.common.req.rw.RwAddReq;
import com.xiaowei.common.req.rw.RwEditReq;
import com.xiaowei.common.req.rw.RwPageList;
import com.xiaowei.common.req.rw.RwUuidReq;
import com.xiaowei.common.res.redis.RedisUser;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public interface RwService {
    /**
     * 新增地表水设备
     * @param rwAddReq
     * @return
     */
    public JSONResult interRw(RwAddReq rwAddReq,RedisUser redisUser);
    /**
     * 根据uuid查询地表水设备
     * @param rwUuidReq
     * @return
     */
    public JSONResult uuidRw(RwUuidReq rwUuidReq,RedisUser redisUser);
    /**
     * 编辑地表水设备
     * @param rwEditReq
     * @return
     */
    public JSONResult editRw(RwEditReq rwEditReq,RedisUser redisUser);
    /**
     * 地表水设备分页
     * @param rwPageList
     * @return
     */
    public PageInfo pageList(RwPageList rwPageList, RedisUser redisUser );
    /**
     * redis
     * @return
     */
    public JSONResult selRw();
    public JSONResult editPush(CwPushReq cwPushReq, RedisUser redisUser);

}
