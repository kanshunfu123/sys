package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by MOMO on 2019/1/14.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserGroupAclReq extends BaseReq {

    /**
     * sysAclId SYS_ACL_ID.
     */
    private List<AclsReq> sysAclId;
    private List<GroupDeviceReq> sysDeviceId;
    //设备号列表
    private List<String> deviceNos;
    /**
     * sysUserGroupId SYS_USER_GROUP_ID.
     */
    @NotBlank(message = "组必填必填 sysUserGroupUuid", groups = {Add.class,select.class})
    private String sysUserGroupUuid;
    @NotNull(message = "组织id必填  groupId",groups = {Delete.class})
    private Long groupId;
}
