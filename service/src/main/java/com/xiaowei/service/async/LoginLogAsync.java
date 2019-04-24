package com.xiaowei.service.async;

import com.alibaba.fastjson.JSONObject;
import com.xiaowei.common.common.UserAgentGetter;
import com.xiaowei.common.util.DateUtil;
import com.xiaowei.mana.mapper.dataobject.LoginLogDO;
import com.xiaowei.mana.mapper.mapper.LoginLogDOMapper;
import lombok.extern.log4j.Log4j;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by MOMO on 2019/1/21.
 */
@Service
@Slf4j
public class LoginLogAsync {
    @Autowired
    private LoginLogDOMapper loginLogDOMapper;

    @Async("threadPoolTaskExecutor")
    public void loginLog(LoginLogDO entity, HttpServletRequest request) {
        UserAgentGetter userAgentGetter=new UserAgentGetter(request);
        entity.setCreateTime(DateUtil.getDateTime());
        entity.setUserIp(userAgentGetter.getIpAddr());
        entity.setUserLoginType(userAgentGetter.getDevice());
        entity.setUserLoginSys(userAgentGetter.getOS());
        entity.setUserLoginBrowser(userAgentGetter.getBrowser());
        log.info("登录日志记录:{}", JSONObject.toJSONString(entity));
        loginLogDOMapper.insert(entity);
    }
}
