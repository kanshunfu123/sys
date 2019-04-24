package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Map;

/**
 * Created by MOMO on 2019/1/10.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RoleTreeReq extends BaseReq {
    //角色uuid
    @NotBlank(message = "角色uuid必填 roleuuid",groups = {Query.class})
    private String roleuuid;
    //菜单系统类型
    @NotNull(message = "菜单系统类型 必填 sysAclPermissionType",groups = {Permission.class})
    private Long sysAclPermissionType;

    @NotBlank(message = "第三方组织uuid必填  groupUUID",groups = {select.class})
    private String groupUUID;
}
