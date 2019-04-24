package com.xiaowei.web.controller.role;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.area.AreaAclReq_v2;
import com.xiaowei.service.core.AreaRoleSingleService;
import com.xiaowei.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by MOMO on 2019/2/22.
 * 当个场景地图汇总
 */
@RestController
@RequestMapping(value = "/platform/areaRoleSingle", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class AreaRoleSingleController extends BaseController{

    @Autowired
    private AreaRoleSingleService areaRoleSingleService;

    @PostMapping("/showMapInfo/v3")
    public JSONResult showMapInfo(
        @Validated(AreaAclReq_v2.Submit.class) @RequestBody AreaAclReq_v2 areaAclReq_v2,
        @RequestHeader(value = "userHeader", required = false) String userInfo) {
            try {
                return JSONResult.ok(areaRoleSingleService.showMapInfo(areaAclReq_v2, this.redisUser(userInfo)));
            } catch (UnsupportedEncodingException e) {
                log.error("场景鼠标移上去信息汇总失败{}", e.getMessage());
                return JSONResult.errorMap("场景鼠标移上去信息汇总失败");
            }
    }

}
