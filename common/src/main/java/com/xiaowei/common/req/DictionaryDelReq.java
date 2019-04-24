package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import com.xiaowei.common.error.ParamsUtil;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class DictionaryDelReq extends BaseReq implements Serializable {

    @NotEmpty(message = "字典uuid不能为空",groups = {Delete.class})
    private String sysDictionaryUuid;

    public String getSysDictionaryUuid() {
        return sysDictionaryUuid;
    }

    public void setSysDictionaryUuid(String sysDictionaryUuid) {
        this.sysDictionaryUuid = sysDictionaryUuid;
    }

    @Override
    public void checkData() {
        Map<String, Object> checkParam = new HashMap<>();
        checkParam.put("sysDictionaryUuid",sysDictionaryUuid);
        ParamsUtil.hasEmptyParamMap(checkParam);
    }
}
