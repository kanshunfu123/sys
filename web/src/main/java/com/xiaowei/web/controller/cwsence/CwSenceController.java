package com.xiaowei.web.controller.cwsence;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.BaseReq;
import com.xiaowei.common.req.cwsence.CwSenceAddReq;
import com.xiaowei.common.req.cwsence.CwSenceEditReq;
import com.xiaowei.common.req.cwsence.CwSenceUuidReq;
import com.xiaowei.service.cwsence.CwSenceService;
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
public class CwSenceController extends BaseController {
    @Autowired
    private CwSenceService cwSenceService;
    @PostMapping("/cwsence/add/v1")
    public JSONResult add(@Validated(CwSenceAddReq.Add.class) @RequestBody CwSenceAddReq cwSenceAddReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return cwSenceService.add(cwSenceAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增,编辑饮用水场景失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增,编辑饮用水场景失败");
        }
    }
    @PostMapping("/cwsence/selCwSen/v1")
    public JSONResult selEc(
            @RequestHeader(value = "userHeader", required = false) String userInfo) {
        JSONResult jsonResult = cwSenceService.selCwSen();
        return jsonResult;

    }
}
