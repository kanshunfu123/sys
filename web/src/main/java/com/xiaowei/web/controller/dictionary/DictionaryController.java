package com.xiaowei.web.controller.dictionary;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.DictionaryAddReq;
import com.xiaowei.common.req.DictionaryDelReq;
import com.xiaowei.common.req.DictionaryParentReq;
import com.xiaowei.common.req.DictionaryReq;
import com.xiaowei.service.dictionary.DictionaryService;
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
public class DictionaryController extends BaseController {
    @Autowired
    private DictionaryService dictionaryService;

    @PostMapping("/treeList/v1")
    public JSONResult treeList() {
        JSONResult jsonResult = dictionaryService.dictionaryTreeList();
        return jsonResult;
    }

    @PostMapping("/add/v1")
    public JSONResult add(@Validated(DictionaryAddReq.Add.class) @RequestBody DictionaryAddReq dictionaryAddReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return dictionaryService.interDictionary(dictionaryAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增字典失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增字典失败");
        }
    }

    @PostMapping("/infoByUuId/v1")
    public JSONResult infoByUuId(@Validated(DictionaryDelReq.Add.class) @RequestBody DictionaryDelReq dictionaryDelReq,
                                 @RequestHeader(value = "testHeader", required = false) String testHeader) {
        JSONResult jsonResult = dictionaryService.getDictionaryInfoByUuid(dictionaryDelReq.getSysDictionaryUuid());
        return jsonResult;
    }

    @PostMapping("/edit/v1")
    public JSONResult edit(@Validated(DictionaryReq.Add.class) @RequestBody DictionaryReq dictionaryReq,
                           @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return dictionaryService.updateDictionary(dictionaryReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑字典失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑字典失败");
        }
    }

    @PostMapping("/del/v1")
    public JSONResult del(@Validated(DictionaryReq.Add.class) @RequestBody DictionaryReq dictionaryReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        JSONResult jsonResult = dictionaryService.delDictionary(dictionaryReq);
        return jsonResult;
    }

    @PostMapping("/parentId/v1")
    public JSONResult parentId(@Validated(DictionaryParentReq.Add.class)@RequestBody DictionaryParentReq dictionaryParentReq,
                               @RequestHeader(value = "testHeader", required = false) String testHeader) {
        JSONResult jsonResult = dictionaryService.getDictionaryInfoByParentId(dictionaryParentReq.getParentId());
        return jsonResult;
    }


}
