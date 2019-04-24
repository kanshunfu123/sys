package com.xiaowei.web.controller.ec;

import com.github.pagehelper.PageInfo;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.BaseReq;
import com.xiaowei.common.req.cw.CwPushReq;
import com.xiaowei.common.req.ec.EcAddReq;
import com.xiaowei.common.req.ec.EcEditReq;
import com.xiaowei.common.req.ec.EcPageList;
import com.xiaowei.common.req.ec.EcUuidReq;
import com.xiaowei.common.req.rw.RwPageList;
import com.xiaowei.service.ec.EcService;
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
public class EcController extends BaseController {
    @Autowired
    private EcService ecService;
    @Autowired
    private RwService rwService;

    @PostMapping("/ec/add/v1")
    public JSONResult add(@Validated(EcAddReq.Add.class) @RequestBody EcAddReq ecAddReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return ecService.interEc(ecAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增电梯失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增电梯失败");
        }
    }

    @PostMapping("/ec/uuid/v1")
    public JSONResult uuid(@Validated(EcUuidReq.Add.class) @RequestBody EcUuidReq ecUuidReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return ecService.uuidEc(ecUuidReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询电梯失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询电梯失败");
        }
    }

    @PostMapping("/ec/edit/v1")
    public JSONResult edit(@Validated(EcEditReq.Add.class) @RequestBody EcEditReq ecEditReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return ecService.editEc(ecEditReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑电梯设备失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑电梯设备失败");
        }
    }

    @PostMapping("/ec/pageList/v1")
    public JSONResult parentId(@Validated(RwPageList.Add.class) @RequestBody RwPageList rwPageList,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            PageInfo pageInfo = rwService.pageList(rwPageList, this.redisUser(userInfo));
            return JSONResult.ok(pageInfo);
        } catch (UnsupportedEncodingException e) {
            log.error("电梯分页失败:{}，电梯分页失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("电梯分页失败");
        }

    }

    @PostMapping("/ec/selEc/v1")
    public JSONResult selEc(
            @RequestHeader(value = "userHeader", required = false) String userInfo) {
        JSONResult jsonResult = ecService.selEc();
        return jsonResult;

    }
    @PostMapping("/ec/push/v1")
    public JSONResult editPush(@Validated(CwPushReq.Add.class) @RequestBody CwPushReq cwPushReq,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return ecService.editPush(cwPushReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑饮用水推送失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑饮用水推送失败");
        }

    }
}
