package com.xiaowei.web.controller.mhsence;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.ecsence.EcSenceEditReq;
import com.xiaowei.common.req.ecsence.EcSenceUuidReq;
import com.xiaowei.common.req.mhsence.MhSenceAddReq;
import com.xiaowei.common.req.mhsence.MhSenceEditReq;
import com.xiaowei.common.req.mhsence.MhSenceUuidReq;
import com.xiaowei.service.mhsence.MhSenceService;
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
public class MhSenceController extends BaseController {
    @Autowired
    private MhSenceService mhSenceService;
    @PostMapping("/mhsence/add/v1")
    public JSONResult add(@Validated(MhSenceAddReq.Add.class) @RequestBody MhSenceAddReq mhSenceAddReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return mhSenceService.add(mhSenceAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增,编辑井盖场景失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增,编辑井盖场景失败");
        }
    }
    @PostMapping("/mhsence/selMhSen/v1")
    public JSONResult selEc(
            @RequestHeader(value = "userHeader", required = false) String userInfo) {
        JSONResult jsonResult = mhSenceService.selMhSen();
        return jsonResult;

    }
}
