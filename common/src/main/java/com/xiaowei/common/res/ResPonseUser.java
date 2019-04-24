package com.xiaowei.common.res;

import lombok.*;

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
public class ResPonseUser {

    private List<String> scenes;

    /**
     * nameTop 顶部名称.
     */
    private String nameTop;
    /**
     * nameBottom 版权.
     */
    private String nameBottom;

    //token
    private String access_token;

    /**
     * groupId 第三方组 小为默认为1
     */
    private Long groupId;

    /**
     * roleType 冗余角色类型 1管理员(专为第三方老板而设置） 2普通员工.
     */
    private String roleType;

    /**
     * sysUserName 姓名.
     */
    private String sysUserName;

    /**
     * sysUserEmail 邮箱.
     */
    private String sysUserEmail;
    /**
     * sysUserPhone 手机号.
     */
    private String sysUserPhone;

}
