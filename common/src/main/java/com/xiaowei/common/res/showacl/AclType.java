package com.xiaowei.common.res.showacl;

import lombok.*;

import java.util.List;

/**
 * Created by MOMO on 2019/1/17.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AclType {
    List<ShowAclModuleLevelDto> child;
    private Long id;
    /**
     * sysAclTypeName 权限类型名称.
     */
    private String sysAclTypeName;

}
