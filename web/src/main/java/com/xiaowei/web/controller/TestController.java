package com.xiaowei.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.RedisKeyEnum;
import com.xiaowei.common.error.BaseReq;
import com.xiaowei.common.req.SysUser;
import com.xiaowei.common.req.TestUserReq;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dataobject.TestDbDO;
import com.xiaowei.service.test.TestAddService;
import com.xiaowei.service.test.TestService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by MOMO on 2018/12/5.
 */
@RestController
@Slf4j
@RequestMapping("/platform")
public class TestController {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TestService testService;
    @Autowired
    private TestAddService testAddService;



    @PostMapping("/testRedisListKey")
    public void testRedisListKey() {
        List<String> list= Lists.newArrayList();
        list.add(RedisKeyEnum.REDIS_ACL_ACL_INFO.getKey()+"14");
        list.add(RedisKeyEnum.REDIS_ACL_ACL_INFO.getKey()+"9");
        redisUtil.batchListByKeys(list);
    }
    @PostMapping("/testRedis")
    public void testRedis() {
        Map<String, Object> map = Maps.newHashMap();
        map.put("a", "冯绍峰");
        map.put("b", "冯绍峰bbb");
        redisUtil.hmset("hash", map);
        redisUtil.hset("hash", "c", "cc草草草");
        log.info(redisUtil.hget("hash", "c") + "");
        Object o = redisUtil.hmget("hash");
        HashMap<String, Object> map1 = (HashMap<String, Object>) o;
        log.error(map1.toString()+"");
        log.error(map1+"");
        redisUtil.hdel("hash", "c", "b");
    }

    @PostMapping("testDbDAO")
    public JSONResult testDbDAO(@Validated(SysUser.Add.class) @RequestBody SysUser sysUser,
                                @RequestHeader(value = "testHeader", required = false) String testHeader) {
        PageInfo<TestDbDO> pageInfo = testService.testDbDAO();
        return JSONResult.ok(pageInfo);
    }

    @RequestMapping("insert")
    public JSONResult insert() {
        testService.insert();
        return JSONResult.ok(1);
    }
    @PostMapping("add")
    public JSONResult add(@Validated(TestUserReq.Add.class) @RequestBody TestUserReq testUserReq,
                             @RequestHeader(value = "testHeader", required = false) String testHeader) {
        JSONResult jsonResult=testAddService.add(testUserReq);
        if(jsonResult.isOK()){
            return JSONResult.build(200,"添加成功",jsonResult.getData());
        }else{
            return JSONResult.errorException(500,"","添加失败");
        }

    }

}
