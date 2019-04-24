package com.xiaowei.common.res.author;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

/**
 * Created by MOMO on 2019/1/10.
 */
@Getter
@Setter
@ToString
public class ModuleAclModuleLevelDto {
    //模块名称
    private String moduleName;
    //权限模块以及权限点
    private List<AclModuleLevelDto> aclModuleLevelDtos;
}
