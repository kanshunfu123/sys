package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
/**
 * Created by kanshunfu on 2019/01/09.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ParameterByUuidReq extends BaseReq implements Serializable {
    @NotEmpty(message = "参数uuid不能为空",groups = {Delete.class})
    private String sysParameterUuid;

    public String getSysParameterUuid() {
        return sysParameterUuid;
    }

    public void setSysParameterUuid(String sysParameterUuid) {
        this.sysParameterUuid = sysParameterUuid;
    }
}
