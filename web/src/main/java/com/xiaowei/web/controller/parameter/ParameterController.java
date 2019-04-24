package com.xiaowei.web.controller.parameter;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.*;
import com.xiaowei.service.parameter.ParameterService;
import com.xiaowei.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by MOMO on 2018/12/5.
 */
@RestController
@Slf4j
@RequestMapping("/platform")
public class ParameterController extends BaseController {
    @Autowired
    private ParameterService parameterService;
    @PostMapping("/paramter/add/v1")
    public JSONResult add(@Validated(ParameterAddReq.Add.class) @RequestBody ParameterAddReq parameterAddReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return parameterService.interParameter(parameterAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增参数失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增参数失败");
        }
    }
    @PostMapping("/dictionaryId/v1")
    public JSONResult parentId(@Validated(ParameterPageList.Add.class)@RequestBody ParameterPageList parameterPageList,
                               @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return parameterService.pageList(parameterPageList, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，分页参数失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("分页参数失败");
        }
    }
    @PostMapping("/uuid/v1")
    public JSONResult uuid(@Validated(ParameterByUuidReq.Add.class)@RequestBody ParameterByUuidReq parameterByUuidReq,
                               @RequestHeader(value = "testHeader", required = false) String testHeader) {
        JSONResult jsonResult = parameterService.getInfoByUuid(parameterByUuidReq);
        return jsonResult;
    }
    @PostMapping("/parameter/edit/v1")
    public JSONResult edit(@Validated(ParameterAddReq.Add.class)@RequestBody ParameterAddReq parameterAddReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return parameterService.updateParameter(parameterAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑参数失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑参数失败");
        }
    }
    @PostMapping("/parameter/del/v1")
    public JSONResult del(@Validated(ParameterReq.Add.class)@RequestBody ParameterReq parameterReq,
                           @RequestHeader(value = "testHeader", required = false) String testHeader) {
        JSONResult jsonResult = parameterService.delParameter(parameterReq);
        return jsonResult;
    }
}
