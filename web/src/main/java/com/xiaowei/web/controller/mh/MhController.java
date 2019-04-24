package com.xiaowei.web.controller.mh;

import com.github.pagehelper.PageInfo;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.cw.CwPushReq;
import com.xiaowei.common.req.mh.MhAddReq;
import com.xiaowei.common.req.mh.MhEditReq;
import com.xiaowei.common.req.mh.MhPageList;
import com.xiaowei.common.req.mh.MhUuidReq;
import com.xiaowei.common.req.rw.RwPageList;
import com.xiaowei.service.mh.MhService;
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
public class MhController extends BaseController {
    @Autowired
    private MhService mhService;
    @Autowired
    private RwService rwService;
    @PostMapping("/mh/add/v1")
    public JSONResult add(@Validated(MhAddReq.Add.class) @RequestBody MhAddReq mhAddReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return mhService.interMh(mhAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增井盖设备失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增井盖设备失败");
        }
    }
    @PostMapping("/mh/uuid/v1")
    public JSONResult uuid(@Validated(MhUuidReq.Add.class) @RequestBody MhUuidReq mhUuidReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return mhService.uuidMh(mhUuidReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，查询井盖设备失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("查询井盖设备失败");
        }
    }
    @PostMapping("/mh/edit/v1")
    public JSONResult edit(@Validated(MhEditReq.Add.class) @RequestBody MhEditReq mhEditReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return mhService.editMh(mhEditReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑井盖设备失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑井盖设备失败");
        }
    }
    @PostMapping("/mh/pageList/v1")
    public JSONResult parentId(@Validated(RwPageList.Add.class) @RequestBody RwPageList rwPageList,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            PageInfo pageInfo = rwService.pageList(rwPageList, this.redisUser(userInfo));
            return JSONResult.ok(pageInfo);
        } catch (UnsupportedEncodingException e) {
            log.error("井盖分页失败:{}，井盖分页失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("井盖分页失败");
        }

    }
    @PostMapping("/mh/selMh/v1")
    public JSONResult selEc(
            @RequestHeader(value = "userHeader", required = false) String userInfo) {
        JSONResult jsonResult = mhService.selMh();
        return jsonResult;

    }
    @PostMapping("/mh/push/v1")
    public JSONResult editPush(@Validated(CwPushReq.Add.class) @RequestBody CwPushReq cwPushReq,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return mhService.editPush(cwPushReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑饮用水推送失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑饮用水推送失败");
        }

    }
}
