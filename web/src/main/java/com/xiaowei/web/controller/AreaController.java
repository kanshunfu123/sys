package com.xiaowei.web.controller;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.BaseReq;
import com.xiaowei.common.req.AreaReq;
import com.xiaowei.common.req.DictionaryDelReq;
import com.xiaowei.common.req.area.AreaAddReq;
import com.xiaowei.common.req.area.AreaDReq;
import com.xiaowei.common.req.area.AreaDelReq;
import com.xiaowei.common.req.dept.DeptAddReq;
import com.xiaowei.service.area.AreaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by MOMO on 2019/1/9.
 */
@RestController
@RequestMapping("/platform/area")
@Slf4j
public class AreaController extends BaseController {
    @Autowired
    private AreaService areaService;

    /**
     * 根据id查询区域
     *
     * @param areaReq
     * @return
     */
    @PostMapping("/getAreaById/v1")
    public JSONResult getAreaById(@Validated(AreaReq.Query.class) @RequestBody AreaReq areaReq) {
        return areaService.getAreaById(areaReq);
    }

    /**
     * 下拉框级联操作
     *
     * @param areaReq
     * @return
     */
    @PostMapping("/getAreaByParentId/v1")
    public JSONResult getAreaByparentId(@Validated(AreaReq.Add.class) @RequestBody AreaReq areaReq) {
        return areaService.getAreaByParentId(areaReq);
    }

    /**
     * 下拉框级联操作
     * 街道
     *
     * @param areaReq
     * @return
     */
    @PostMapping("/getStreetByParentId/v1")
    public JSONResult getAllByAreaId(@Validated(AreaReq.Add.class) @RequestBody AreaReq areaReq) {
        return areaService.getAllByAreaId(areaReq);
    }

    /**
     * 下拉框级联操作
     * 小区
     *
     * @param areaReq
     * @return
     */
    @PostMapping("/getAllVillByAreaId/v1")
    public JSONResult getAllVillByAreaId(@Validated(AreaReq.Add.class) @RequestBody AreaReq areaReq) {
        return areaService.getAllVilByAreaId(areaReq);
    }

    /**
     * 新增区域
     *
     * @param areaAddReq
     * @param userInfo
     * @return
     */
    @PostMapping("/add/v1")
    public JSONResult addArea(@Validated(AreaAddReq.Add.class) @RequestBody AreaAddReq areaAddReq, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return areaService.addArea(areaAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增区域失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增区域失败");
        }
    }

    @PostMapping("/edit/v1")
    public JSONResult editArea(@Validated(AreaAddReq.Add.class) @RequestBody AreaAddReq areaAddReq, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return areaService.editArea(areaAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑区域失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑区域失败");
        }
    }

    @PostMapping("/treeList/v1")
    public JSONResult treeList() {
        JSONResult jsonResult = areaService.areaTreeList();
        return jsonResult;
    }

    @PostMapping("/uuid/v1")
    public JSONResult infoByUuId(@Validated(AreaDelReq.Add.class) @RequestBody AreaDelReq areaDelReq,
                                 @RequestHeader(value = "testHeader", required = false) String testHeader) {
        JSONResult jsonResult = areaService.getAreaInfoByUuid(areaDelReq.getSysAreaUuid());
        return jsonResult;
    }

    @PostMapping("/deluuid/v1")
    public JSONResult deluuid(@Validated(AreaDReq.Add.class) @RequestBody AreaDReq areaDReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return areaService.delArea(areaDReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，删除区域失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("删除区域失败");
        }
    }
    @PostMapping("/del/v1")
    public JSONResult del(@Validated(AreaDReq.Add.class) @RequestBody AreaDReq areaDReq,
                          @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return areaService.del(areaDReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，删除区域失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("删除区域失败");
        }
    }
}
