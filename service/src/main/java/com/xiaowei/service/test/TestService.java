package com.xiaowei.service.test;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xiaowei.common.util.RedisUtil;
import com.xiaowei.mana.mapper.dao.TestDbDAO;
import com.xiaowei.mana.mapper.dataobject.TestDbDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by MOMO on 2019/1/2.
 */
@Service
@Slf4j
public class TestService {

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private TestDbDAO testDbDAO;


    public PageInfo<TestDbDO> testDbDAO() {
        PageHelper.startPage(1, 10);
        List<TestDbDO> testDbDOS = testDbDAO.testPage();
        PageInfo<TestDbDO> pageInfo = new PageInfo<>(testDbDOS);
        return pageInfo;
    }
    public void insert() {
        TestDbDO entity=new TestDbDO();
//        System.out.println(1/0);
        entity.setName("麻烦你发");
        testDbDAO.insert(entity);
    }
}
