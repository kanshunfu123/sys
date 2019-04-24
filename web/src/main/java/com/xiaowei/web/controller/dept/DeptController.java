package com.xiaowei.web.controller.dept;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.dept.DeptAddReq;
import com.xiaowei.service.dept.DeptService;
import com.xiaowei.service.dept.DeptTreeService;
import com.xiaowei.web.controller.BaseController;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;

/**
 * Created by MOMO on 2019/1/15.
 */
@RestController
@RequestMapping("/platform/dept")
@Slf4j
public class DeptController extends BaseController {
    @Autowired
    private DeptTreeService deptTreeService;
    @Autowired
    private DeptService deptService;

    /**
     * 部门树
     *
     * @param userInfo
     * @return
     */
    @PostMapping("/deptTree/v1")
    public JSONResult deptTree(@RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return deptTreeService.deptTree(redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，部门树生成失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("部门树生成失败");
        }
    }

    @PostMapping("/addDept/v1")
    public JSONResult addDept(@Validated(DeptAddReq.Add.class) @RequestBody DeptAddReq deptAddReq, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return deptService.addDept(deptAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，新增部门失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("新增部门失败");
        }
    }

    @PostMapping("/modifyDept/v1")
    public JSONResult modifyDept(@Validated(DeptAddReq.Modify.class) @RequestBody DeptAddReq deptAddReq, @RequestHeader(value = "userHeader", required = false) String userInfo) {
        try {
            return deptService.modifyDept(deptAddReq, redisUser(userInfo));
        } catch (UnsupportedEncodingException e) {
            log.error("token解析错误:{}，编辑部门失败:{}", userInfo, e.getMessage());
            return JSONResult.errorMap("编辑部门失败");
        }
    }
}
