package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import com.xiaowei.mana.mapper.dataobject.AclDO;
import lombok.*;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by MOMO on 2019/1/9.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BatchRoleAclReq extends BaseReq {

    /**
     * 角色uuid
     */
    @NotNull(message = "角色uuid 必填  roleUuid", groups = {Add.class})
    private String roleUuid;

    //权限点列表
    private List<AclsReq> aclIds;
}
