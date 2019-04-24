package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
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
public class BatchRoleUserReq extends BaseReq {

    /**
     * 用户uuid
     */
    @NotNull(message = "用户uuid 必填  userUuid", groups = {Add.class})
    private String userUuid;

    //角色列表
    private List<Long> roleIds;
}
