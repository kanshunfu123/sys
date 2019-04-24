package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * Created by MOMO on 2019/1/7.
 */
@Getter
@Setter
public class SysUserReq extends BaseReq {
    @NotBlank(message = "userName 不能为空", groups = {BaseReq.Query.class})
    private String userName;

    @NotBlank(message = "userPwd  密码不能为空", groups = {BaseReq.Query.class})
    private String userPwd;

//    @NotBlank(message = "authCode  验证码不能为空", groups = {BaseReq.Query.class})
    private String authCode;

}
