package com.xiaowei.common.res.author;

import com.google.common.collect.Lists;
import com.xiaowei.mana.mapper.dataobject.AclModuleDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Created by MOMO on 2019/1/10.
 */
@Getter
@Setter
@ToString
public class AclModuleLevelDto extends AclModuleDO{
    // 是否要默认选中
    private boolean checked = false;

    // 是否有权限操作
    private boolean hasAcl = false;

    private List<AclModuleLevelDto> aclModuleList = Lists.newArrayList();

    private List<AclDto> aclList = Lists.newArrayList();

    public static AclModuleLevelDto adapt(AclModuleDO aclModule) {
        AclModuleLevelDto dto = new AclModuleLevelDto();
        dto.setHasAcl(true);
        BeanUtils.copyProperties(aclModule, dto);
        return dto;
    }
}
