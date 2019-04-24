package com.xiaowei.web.controller;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.service.core.InitializeAclToRedisService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MOMO on 2019/1/14.
 * 初始化，acl权限到redis缓存
 */
@RestController
@Slf4j
public class InitializeAclRedisController {

    @Autowired
    private InitializeAclToRedisService initializeAclToRedisService;

    @PostMapping("/initializeAclRedis")
    @Async("threadPoolTaskExecutor")
    public JSONResult initializeAclRedis() {
        return initializeAclToRedisService.initializeAclRedis();
    }

}
