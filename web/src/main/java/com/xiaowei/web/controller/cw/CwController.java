package com.xiaowei.web.controller.cw;

import com.github.pagehelper.PageInfo;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.BaseReq;
import com.xiaowei.common.req.cw.*;
import com.xiaowei.common.req.rw.RwPageList;
import com.xiaowei.service.cw.CwService;
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
public class CwController extends BaseController {
    @Autowired
    private CwService cwService;
    @Autowired
    private RwService rwService;
    @PostMapping("/cw/add/v1")
    public JSONResult add(@Validated(CwAddReq.Add.class) @RequestBody CwAddReq cwAddReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return cwService.interCw(cwAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增饮用水失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增饮用水失败");
        }
    }
    @PostMapping("/cw/uuid/v1")
    public JSONResult uuid(@Validated(CwUuidReq.Add.class) @RequestBody CwUuidReq cwUuidReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return cwService.uuidCw(cwUuidReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询饮用水失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询饮用水失败");
        }
    }
    @PostMapping("/cw/edit/v1")
    public JSONResult edit(@Validated(CwEditReq.Add.class) @RequestBody CwEditReq cwEditReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return cwService.editCw(cwEditReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑饮用水失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑饮用水失败");
        }

    }
    @PostMapping("/cw/pageList/v1")
    public JSONResult parentId(@Validated(RwPageList.Add.class) @RequestBody RwPageList rwPageList,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            PageInfo pageInfo = rwService.pageList(rwPageList, this.redisUser(userInfo));
            return JSONResult.ok(pageInfo);
        } catch (UnsupportedEncodingException e) {
            log.error("饮用水分页失败:{}，饮用水分页失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("饮用水分页失败");
        }

    }
    @PostMapping("/cw/selCw/v1")
    public JSONResult selEc(
            @RequestHeader(value = "userHeader", required = false) String userInfo) {
        JSONResult jsonResult = cwService.selCw();
        return jsonResult;

    }
    @PostMapping("/cw/push/v1")
    public JSONResult editPush(@Validated(CwPushReq.Add.class) @RequestBody CwPushReq cwPushReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return cwService.editPush(cwPushReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑饮用水推送失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑饮用水推送失败");
        }

    }
}
