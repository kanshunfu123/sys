package com.xiaowei.web.controller.rw;

import com.github.pagehelper.PageInfo;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.cw.CwPushReq;
import com.xiaowei.common.req.rw.RwAddReq;
import com.xiaowei.common.req.rw.RwEditReq;
import com.xiaowei.common.req.rw.RwPageList;
import com.xiaowei.common.req.rw.RwUuidReq;
import com.xiaowei.common.res.redis.RedisUser;
import com.xiaowei.service.rw.RwService;
import com.xiaowei.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by MOMO on 2019/1/10.
 */
@RestController
@Slf4j
@RequestMapping("/platform")
public class RwController extends BaseController {
    @Autowired
    private RwService rwService;

    @PostMapping("/rw/add/v1")
    public JSONResult add(@Validated(RwAddReq.Add.class) @RequestBody RwAddReq rwAddReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return rwService.interRw(rwAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增地表水设备失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增地表水设备失败");
        }
    }

    @PostMapping("/rw/uuid/v1")
    public JSONResult uuid(@Validated(RwUuidReq.Add.class) @RequestBody RwUuidReq rwUuidReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return rwService.uuidRw(rwUuidReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询地表水设备失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询地表水设备失败");
        }
    }

    @PostMapping("/rw/edit/v1")
    public JSONResult edit(@Validated(RwEditReq.Add.class) @RequestBody RwEditReq rwEditReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return rwService.editRw(rwEditReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}， 编辑地表水设备失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑地表水设备失败");
        }
    }

    @PostMapping("/rmce/pageList/v1")
    public JSONResult parentId(@Validated(RwPageList.Add.class) @RequestBody RwPageList rwPageList,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            PageInfo pageInfo = rwService.pageList(rwPageList, this.redisUser(userInfo));
            return JSONResult.ok(pageInfo);
        } catch (UnsupportedEncodingException e) {
            log.error("地表水分页失败:{}，地表水分页失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("地表水分页失败");
        }

    }
    @PostMapping("/rw/selRw/v1")
    public JSONResult selEc(
            @RequestHeader(value = "userHeader", required = false) String userInfo) {
        JSONResult jsonResult = rwService.selRw();
        return jsonResult;

    }
    @PostMapping("/rw/push/v1")
    public JSONResult editPush(@Validated(CwPushReq.Add.class) @RequestBody CwPushReq cwPushReq,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return rwService.editPush(cwPushReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑饮用水推送失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑饮用水推送失败");
        }

    }
}
