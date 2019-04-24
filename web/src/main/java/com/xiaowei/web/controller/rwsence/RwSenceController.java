package com.xiaowei.web.controller.rwsence;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.rwsence.RwSenceAddReq;
import com.xiaowei.common.req.rwsence.RwSenceEditReq;
import com.xiaowei.common.req.rwsence.RwSenceUuidReq;
import com.xiaowei.service.rwsence.RwSenceService;
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
public class RwSenceController extends BaseController {
    @Autowired
    private RwSenceService rwSenceService;
    @PostMapping("/rwsence/add/v1")
    public JSONResult add(@Validated(RwSenceAddReq.Add.class) @RequestBody RwSenceAddReq rwSenceAddReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return rwSenceService.add(rwSenceAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增,编辑地表水场景失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增,编辑地表水场景失败");
        }
    }
    @PostMapping("/rwsence/uuid/v1")
    public JSONResult uuid(@Validated(RwSenceUuidReq.Add.class) @RequestBody RwSenceUuidReq rwSenceUuidReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return rwSenceService.uuidRw(rwSenceUuidReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增地表水设备失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增地表水设备失败");
        }
    }
    @PostMapping("/rwsence/edit/v1")
    public JSONResult edit(@Validated(RwSenceEditReq.Add.class) @RequestBody RwSenceEditReq rwSenceEditReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return rwSenceService.edit(rwSenceEditReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增地表水设备失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增地表水设备失败");
        }
    }
    @PostMapping("/rwsence/selRwSen/v1")
    public JSONResult selEc(
            @RequestHeader(value = "userHeader", required = false) String userInfo) {
        JSONResult jsonResult = rwSenceService.selRwSen();
        return jsonResult;

    }
}
