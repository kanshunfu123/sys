package com.xiaowei.common.req.mh;

import com.xiaowei.common.error.BaseReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * Created by kanshunfu on 2019/01/08.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MhUuidReq extends BaseReq implements Serializable {
    /**
     * equipmentUuid uuid.
     */
    @NotBlank(message = "equipmentUuid uuid不能为空",groups = {Add.class,Modify.class})
    private String equipmentUuid;

    public String getEquipmentUuid() {
        return equipmentUuid;
    }

    public void setEquipmentUuid(String equipmentUuid) {
        this.equipmentUuid = equipmentUuid;
    }
}
