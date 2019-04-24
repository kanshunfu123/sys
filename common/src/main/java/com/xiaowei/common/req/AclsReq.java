package com.xiaowei.common.req;

import lombok.*;

/**
 * Created by MOMO on 2019/1/9.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AclsReq {
    //权限点
    private Long aclId;
    /**
     * sysAclPermissionType 菜单系统类型 1 系统管理 2资产管理.
     */
    private Long sysAclPermissionType;
}
