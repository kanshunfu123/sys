package com.xiaowei.common.req.area;

import com.xiaowei.common.error.BaseReq;
import com.xiaowei.common.error.ParamsUtil;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class AreaDelReq extends BaseReq implements Serializable {
    @NotEmpty(message = "字典uuid不能为空",groups = {Delete.class})
        private String sysAreaUuid;

    public String getSysAreaUuid() {
        return sysAreaUuid;
    }

    public void setSysAreaUuid(String sysAreaUuid) {
        this.sysAreaUuid = sysAreaUuid;
    }

    @Override
    public void checkData() {
        Map<String, Object> checkParam = new HashMap<>();
        checkParam.put("sysAreaUuid",sysAreaUuid);
        ParamsUtil.hasEmptyParamMap(checkParam);
    }
}
