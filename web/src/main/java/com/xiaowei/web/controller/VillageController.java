package com.xiaowei.web.controller;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.InsertUserGroupReq;
import com.xiaowei.common.req.VillageInfoReq;
import com.xiaowei.common.req.village.InsertStreetReq;
import com.xiaowei.common.req.village.InsertVillageReq;
import com.xiaowei.common.req.village.VaPageListReq;
import com.xiaowei.service.village.VillageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by MOMO on 2019/1/9.
 */
@RestController
@RequestMapping("/platform/village")
@Slf4j
public class VillageController extends BaseController {
    @Autowired
    private VillageService villageService;

    /**
     * 根据主键查询小区
     *
     * @param villageInfoReq
     * @return
     */
    @PostMapping("/selectByPrimaryKey/v1")
    public JSONResult selectByPrimaryKey(@Validated(VillageInfoReq.Query.class) @RequestBody VillageInfoReq villageInfoReq) {
        return villageService.selectByPrimaryKey(villageInfoReq);
    }

    @PostMapping("/add/v1")
    public JSONResult add(@Validated(InsertVillageReq.Add.class) @RequestBody InsertVillageReq insertVillageReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return villageService.insert(insertVillageReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("新增小区失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增小区失败");
        }
    }

    @PostMapping("/modify/v1")
    public JSONResult modify(@Validated(InsertVillageReq.Modify.class) @RequestBody InsertVillageReq insertVillageReq,
                             @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return villageService.modify(insertVillageReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("新增小区失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增小区失败");
        }
    }

    @PostMapping("/addStreet/v1")
    public JSONResult addStreet(@Validated(InsertStreetReq.Add.class) @RequestBody InsertStreetReq insertVillageReq,
                                @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return villageService.insertStreet(insertVillageReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("新增小区失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增小区失败");
        }
    }

    @PostMapping("/modifyStreet/v1")
    public JSONResult modifyStreet(@Validated(InsertStreetReq.Modify.class) @RequestBody InsertStreetReq insertVillageReq,
                                   @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return villageService.modifyStreet(insertVillageReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("新增小区失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增小区失败");
        }
    }

    @PostMapping("/uuidStreet/v1")
    public JSONResult uuidStreet(@Validated(InsertStreetReq.Query.class) @RequestBody InsertStreetReq insertVillageReq) {
        return villageService.uuidStreet(insertVillageReq);
    }
    @PostMapping("/uuidVillage/v1")
    public JSONResult uuidStreet(@Validated(InsertVillageReq.Query.class) @RequestBody InsertVillageReq insertVillageReq) {
        return villageService.uuidVillage(insertVillageReq);
    }
    @PostMapping("/villagePage/v1")
    public JSONResult pageList(@Validated(VaPageListReq.Add.class) @RequestBody VaPageListReq vaPageListReq,
                                @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return villageService.pageList(vaPageListReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("分页小区失败{}====：{}", userInfo, e.getMessage());
            return JSONResult.errorMap("分页小区失败");
        }
    }
}
