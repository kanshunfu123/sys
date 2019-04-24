package com.xiaowei.service.guanwang;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.guanwang.OffReq;
import com.xiaowei.common.util.StrUtil;
import com.xiaowei.mana.mapper.Util.DateUtils;
import com.xiaowei.mana.mapper.dao.OfficialWebsiteDAO;
import com.xiaowei.mana.mapper.dataobject.OfficialWebsiteDO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by kanshunfu on 2019/01/07.
 */
@Service
@Slf4j
public class OffServiceImpl implements OffService {
    @Autowired
    private OfficialWebsiteDAO officialWebsiteDAO;
    @Override
    public JSONResult interOff(OffReq offReq) {
        OfficialWebsiteDO officialWebsiteDO=new OfficialWebsiteDO();
        BeanUtils.copyProperties(offReq,officialWebsiteDO);
        officialWebsiteDO.setUuid(StrUtil.genUUID());
        officialWebsiteDO.setCreateBy("小为");
        officialWebsiteDO.setUpdateBy("小为");
        officialWebsiteDO.setDelFlag("0");
        officialWebsiteDO.setCreateTime(DateUtils.getCurrentDateTime());
        officialWebsiteDO.setUpdateTime(DateUtils.getCurrentDateTime());
        officialWebsiteDAO.saveOfficial(officialWebsiteDO);
        return JSONResult.ok("新增成功", officialWebsiteDO.getUuid());
    }
}
