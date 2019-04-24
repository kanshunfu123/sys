package com.xiaowei.web.controller.role;

import com.xiaowei.common.AddRoleAreaReq;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.area.AreaAclReq;
import com.xiaowei.common.req.area.AreaAclReq_v2;
import com.xiaowei.common.req.area.MapZoom;
import com.xiaowei.service.core.AreaRoleService;
import com.xiaowei.service.core.AreaRoleService_v3;
import com.xiaowei.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by MOMO on 2019/2/14.
 */
@RestController
@RequestMapping(value = "/platform/rolearea", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
@Slf4j
public class AreaRoleController extends BaseController {

    @Autowired
    private AreaRoleService areaRoleService;
    @Autowired
    private AreaRoleService_v3 areaRoleService_v3;

    /**
     * 某个角色所拥有的区域权限树 回显
     *
     * @param addRoleAreaReq
     * @param userInfo
     * @return
     */
    @RequestMapping("/areaAclTree/v1")
    public JSONResult areaAclTree(@Validated(AddRoleAreaReq.Query.class) @RequestBody AddRoleAreaReq addRoleAreaReq,
                                  @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return JSONResult.ok(areaRoleService.areaAclTree(addRoleAreaReq, redisUser(userInfo)));
        } catch (UnsupportedEncodingException e) {
            log.error("某个角色所拥有的区域权限树 回显失败{}", e.getMessage());
            return JSONResult.errorMap("某个角色所拥有的区域权限树 回显失败");
        }
    }

    @RequestMapping("/scale/v1")
    public JSONResult scale(@Validated(MapZoom.Query.class) @RequestBody MapZoom addRoleAreaReq,
                            @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return JSONResult.ok(areaRoleService.mapZoom(addRoleAreaReq, redisUser(userInfo)));
        } catch (UnsupportedEncodingException e) {
            log.error("获取地图层级坐标失败{}", e.getMessage());
            return JSONResult.errorMap("获取地图层级坐标失败");
        }
    }

    @RequestMapping("/mapAreaAcl/v1")
    public JSONResult addRoleArea(@Validated(AreaAclReq.Query.class) @RequestBody AreaAclReq addRoleAreaReq,
                                  @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return areaRoleService.areaAcl(addRoleAreaReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("获取地图层级坐标失败{}", e.getMessage());
            return JSONResult.errorMap("获取地图层级坐标失败");
        }
    }

    /**
     * 地区使用分布图
     * @param addRoleAreaReq
     * @param userInfo
     * @return
     */
   /* @RequestMapping("/areaUseingMap/v2")
    public JSONResult areaUseingMap(@Validated(AreaAclReq_v2.Status.class) @RequestBody AreaAclReq_v2 addRoleAreaReq,
                                  @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return JSONResult.ok(areaRoleService.areaUseingMap(addRoleAreaReq, redisUser(userInfo)));
        } catch (UnsupportedEncodingException e) {
            log.error("获取地图层级坐标失败{}", e.getMessage());
            return JSONResult.errorMap("获取地图层级坐标失败");
        }
    }*/

    /**
     * 地图权限
     *
     * @param addRoleAreaReq
     * @param userInfo
     * @return
     */
    @RequestMapping("/mapAreaAcl/v2")
    public JSONResult addRoleArea_v2(@Validated(AreaAclReq_v2.Query.class) @RequestBody AreaAclReq_v2 addRoleAreaReq,
                                     @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return JSONResult.ok(areaRoleService.areaAcl_v2(addRoleAreaReq, redisUser(userInfo)));
        } catch (UnsupportedEncodingException e) {
            log.error("获取地图层级坐标失败{}", e.getMessage());
            return JSONResult.errorMap("获取地图层级坐标失败");
        }
    }
    /**
     * 地图权限 采用ali地图
     *
     * @param addRoleAreaReq
     * @param userInfo
     * @return
     */
    @RequestMapping("/mapAreaAcl/v3")
    public JSONResult addRoleArea_v3(@Validated(AreaAclReq_v2.Permission.class) @RequestBody AreaAclReq_v2 addRoleAreaReq,
                                     @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return JSONResult.ok(areaRoleService_v3.areaAcl_v3(addRoleAreaReq, redisUser(userInfo)));
        } catch (UnsupportedEncodingException e) {
            log.error("获取地图层级坐标失败{}", e.getMessage());
            return JSONResult.errorMap("获取地图层级坐标失败");
        }
    }


    /**
     * 区域和角色
     *
     * @param addRoleAreaReq
     * @param userInfo
     * @return
     */
    @RequestMapping("/addRoleArea/v1")
    public JSONResult addRoleArea(@Validated(AddRoleAreaReq.Add.class) @RequestBody AddRoleAreaReq addRoleAreaReq,
                                  @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return areaRoleService.addRoleArea(addRoleAreaReq, this.redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("区域和角色失败{}", e.getMessage());
            return JSONResult.errorMap("区域和角色失败");
        }
    }

    @PostMapping("/showMapInfo/v3")
    public JSONResult showMapInfo(@Validated(AreaAclReq_v2.Detail.class) @RequestBody AreaAclReq_v2 areaAclReq_v2,
                                  @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return JSONResult.ok(areaRoleService.showMapInfo(areaAclReq_v2, this.redisUser(userInfo)));
        } catch (UnsupportedEncodingException e) {
            log.error("区域和角色失败{}", e.getMessage());
            return JSONResult.errorMap("区域和角色失败");
        }
    }

    /**
     * 地区使用分布图
     *
     * @param addRoleAreaReq
     * @param userInfo
     * @return
     */
    @RequestMapping("/areaUseingMap/v2")
    public JSONResult areaUseingMap(@Validated(AreaAclReq_v2.Detail.class) @RequestBody AreaAclReq_v2 addRoleAreaReq,
                                    @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return JSONResult.ok(areaRoleService.areaUseingMap(addRoleAreaReq, redisUser(userInfo)));
//            return JSONResult.ok(areaRoleService.areaUseingMap(addRoleAreaReq, redisUser(userInfo)));
        } catch (UnsupportedEncodingException e) {
            log.error("获取地图层级坐标失败{}", e.getMessage());
            return JSONResult.errorMap("获取地图层级坐标失败");
        }
    }

}
