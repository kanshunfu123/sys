package com.xiaowei.service.test;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.error.ErrorEnum;
import com.xiaowei.common.req.TestUserReq;
import com.xiaowei.mana.mapper.dao.TestDbDAO;
import com.xiaowei.mana.mapper.dataobject.TestDbDO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TestAddService implements TestAdd {
    @Autowired
    private  TestDbDAO testDbDAO;
    @Override
    public JSONResult add(TestUserReq testUserReq) {
        TestDbDO testDbDO=new TestDbDO();
        BeanUtils.copyProperties(testUserReq,testDbDO);
        System.out.println(testDbDO.getName());
        if(StringUtils.isEmpty(testDbDO.getName())){
            return JSONResult.errorException(500,"","名称不能为空");
        }
        int count=testDbDAO.getName(testDbDO);
        if(count>0){
            return JSONResult.errorException(ErrorEnum.ERROR_ADD_NAME_FAIL.getErrorCode(),"",ErrorEnum.ERROR_ADD_NAME_FAIL.getErrorMessage());
        }

        testDbDAO.add(testDbDO);
        return JSONResult.ok("添加成功",200);
    }
}
