package com.xiaowei.common;

import com.xiaowei.common.error.BaseReq;
import com.xiaowei.mana.mapper.dataobject.RoleAclDO;
import com.xiaowei.mana.mapper.dataobject.RoleAreaDO;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by MOMO on 2019/2/14.
 */
@Data
@ToString
public class AddRoleAreaReq extends BaseReq{


    private List<RoleAreaDO> areaIds;
    /**
     * 角色uuid
     */
    @NotNull(message = "角色uuid 必填  roleUuid", groups = {Add.class,Query.class})
    private String roleUuid;
    //0 地表水 1井盖 2生活用水 3电梯
    @NotNull(message = "场景类型 必填  deviceType", groups = {Add.class,Query.class})
    private String deviceType;

}
