package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by MOMO on 2019/1/12.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class InsertRoleDeviceReq extends BaseReq{


    /**
     * 角色uuid
     */
    @NotNull(message = "角色uuid 必填  roleUuid", groups = {Add.class})
    private String roleUuid;

    //设备列表
    private List<GroupDeviceReq> deviceNos;

}
