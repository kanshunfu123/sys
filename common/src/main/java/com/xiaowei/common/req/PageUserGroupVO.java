package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.*;

/**
 * Created by MOMO on 2019/1/25.
 */
@ToString
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageUserGroupVO extends BaseReq {
    /**
     * userGroupName 用户组名称/第三方公司名称.
     */
    private String userGroupName;
    private String startTime;
    private String endTime;

    //当前页
    private int pageNum=1;
    //每页的数量
    private int pageSize=20;
}
