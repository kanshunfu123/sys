package com.xiaowei.web.controller.acl;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.service.core.ShowCoreService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MOMO on 2019/1/17.
 */
@RestController
@RequestMapping("/platform/acl")
@Slf4j
public class ShowAclTreeController {
    @Autowired
    private ShowCoreService showCoreService;

    @PostMapping("/showAclTree/v1")
    public JSONResult aa() {
       return JSONResult.ok(showCoreService.allTypeAclTree());
    }
}
