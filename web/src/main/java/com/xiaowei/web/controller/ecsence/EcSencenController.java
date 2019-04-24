package com.xiaowei.web.controller.ecsence;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.ecsence.EcSenceAddReq;
import com.xiaowei.common.req.ecsence.EcSenceEditReq;
import com.xiaowei.common.req.ecsence.EcSenceUuidReq;
import com.xiaowei.service.ecsence.EcSenceService;
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
public class EcSencenController extends BaseController {
    @Autowired
    private EcSenceService ecSenceService;
    @PostMapping("/ecsence/add/v1")
    public JSONResult add(@Validated(EcSenceAddReq.Add.class) @RequestBody EcSenceAddReq ecSenceAddReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return ecSenceService.add(ecSenceAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增,编辑电梯场景失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增,编辑电梯场景失败");
        }
    }
    @PostMapping("/ecsence/selEc/v1")
    public JSONResult selEc(
            @RequestHeader(value = "userHeader", required = false) String userInfo) {
        JSONResult jsonResult = ecSenceService.selEcSen();
        return jsonResult;

    }
}
