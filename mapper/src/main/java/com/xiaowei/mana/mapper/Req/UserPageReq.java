package com.xiaowei.mana.mapper.Req;

/**
 * Created by MOMO on 2019/1/8.
 */
public class UserPageReq{

    //当前页
    private int pageNum=1;
    //每页的数量
    private int pageSize=20;

    //组的id 按照权限查
    private Long groupId;
    //用户姓名
    private String userName;
    //是否删除 0否 1删除
    private String delFlag;
    //手机号
    private String phone;
    //邮箱
    private String email;
    //组的名称
    private String groupName;
    //  冗余角色类型 1管理员(专为第三方老板而设置） 2普通员工
    private String roleType;

    //登录名
    private String userLoginName;


    public String getUserLoginName() {
        return userLoginName;
    }

    public void setUserLoginName(String userLoginName) {
        this.userLoginName = userLoginName;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getRoleType() {
        return roleType;
    }

    public void setRoleType(String roleType) {
        this.roleType = roleType;
    }
}
