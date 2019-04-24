package com.xiaowei.common.res.dept;

import com.google.common.collect.Lists;
import com.xiaowei.mana.mapper.dataobject.DeptDO;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.BeanUtils;

import java.util.List;

/**
 * Created by MOMO on 2019/1/15.
 */
@Getter
@Setter
@ToString
public class DeptLevelDto extends DeptDO {
    private List<DeptLevelDto> deptList = Lists.newArrayList();

    public static DeptLevelDto adapt(DeptDO dept) {
        DeptLevelDto dto = new DeptLevelDto();
        BeanUtils.copyProperties(dept, dto);
        return dto;
    }
}
