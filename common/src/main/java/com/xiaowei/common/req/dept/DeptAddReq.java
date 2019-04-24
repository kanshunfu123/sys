package com.xiaowei.common.req.dept;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 * Created by MOMO on 2019/1/15.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DeptAddReq extends BaseReq {

    @NotBlank(message = "部门uuid必填 ", groups = {Modify.class})
    private String uuid;
    /**
     * sysDeptParentId 上级部门id.
     */
    @NotNull(message = "上级部门id 必填",groups = {Add.class, Modify.class})
    @Min(value = 0,message = "最小值为0",groups = {Add.class, Modify.class})
    private Long sysDeptParentId = 0L;

    /**
     * sysDeptName 部门名称.
     */
    @NotBlank(message = "部门名称 必填 sysDeptName", groups = {Add.class, Modify.class})
    private String sysDeptName;

    /**
     * sysDeptRemark 备注.
     */
    private String sysDeptRemark;
    /**
     * sysDeptSeq 部门在当前层级下的顺序，由小到大.
     */
    @NotNull(message = "部门在当前层级下的顺序 必填 sysDeptSeq", groups = {Add.class, Modify.class})
    private Integer sysDeptSeq;


}
