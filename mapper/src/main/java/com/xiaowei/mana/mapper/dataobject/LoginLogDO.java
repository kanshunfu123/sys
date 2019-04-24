package com.xiaowei.mana.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mana.mapper.dataobject.LoginLogDO;

/**
 * The table 登录日志
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class LoginLogDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * userId 用户id.
     */
    private Long userId;
    /**
     * groupId 第三方组 小为默认为1.
     */
    private Long groupId;
    /**
     * userIp 用户ip.
     */
    private String userIp;
    /**
     * userLoginSys 操作系统.
     */
    private String userLoginSys;
    /**
     * userUserName 用户名.
     */
    private String userUserName;
    /**
     * userLoginName 登录名.
     */
    private String userLoginName;
    /**
     * userLoginType 登录设备.
     */
    private String userLoginType;
    /**
     * userLoginBrowser 获取浏览器类型.
     */
    private String userLoginBrowser;
    /**
     * createTime 登录时间.
     */
    private Date createTime;

    /**
     * Set id ID.
     */
    public void setId(Long id){
        this.id = id;
    }

    /**
     * Get id ID.
     *
     * @return the string
     */
    public Long getId(){
        return id;
    }

    /**
     * Set userId 用户id.
     */
    public void setUserId(Long userId){
        this.userId = userId;
    }

    /**
     * Get userId 用户id.
     *
     * @return the string
     */
    public Long getUserId(){
        return userId;
    }

    /**
     * Set groupId 第三方组 小为默认为1.
     */
    public void setGroupId(Long groupId){
        this.groupId = groupId;
    }

    /**
     * Get groupId 第三方组 小为默认为1.
     *
     * @return the string
     */
    public Long getGroupId(){
        return groupId;
    }

    /**
     * Set userIp 用户ip.
     */
    public void setUserIp(String userIp){
        this.userIp = userIp;
    }

    /**
     * Get userIp 用户ip.
     *
     * @return the string
     */
    public String getUserIp(){
        return userIp;
    }

    /**
     * Set userLoginSys 操作系统.
     */
    public void setUserLoginSys(String userLoginSys){
        this.userLoginSys = userLoginSys;
    }

    /**
     * Get userLoginSys 操作系统.
     *
     * @return the string
     */
    public String getUserLoginSys(){
        return userLoginSys;
    }

    /**
     * Set userUserName 用户名.
     */
    public void setUserUserName(String userUserName){
        this.userUserName = userUserName;
    }

    /**
     * Get userUserName 用户名.
     *
     * @return the string
     */
    public String getUserUserName(){
        return userUserName;
    }

    /**
     * Set userLoginName 登录名.
     */
    public void setUserLoginName(String userLoginName){
        this.userLoginName = userLoginName;
    }

    /**
     * Get userLoginName 登录名.
     *
     * @return the string
     */
    public String getUserLoginName(){
        return userLoginName;
    }

    /**
     * Set userLoginType 登录设备.
     */
    public void setUserLoginType(String userLoginType){
        this.userLoginType = userLoginType;
    }

    /**
     * Get userLoginType 登录设备.
     *
     * @return the string
     */
    public String getUserLoginType(){
        return userLoginType;
    }

    /**
     * Set userLoginBrowser 获取浏览器类型.
     */
    public void setUserLoginBrowser(String userLoginBrowser){
        this.userLoginBrowser = userLoginBrowser;
    }

    /**
     * Get userLoginBrowser 获取浏览器类型.
     *
     * @return the string
     */
    public String getUserLoginBrowser(){
        return userLoginBrowser;
    }

    /**
     * Set createTime 登录时间.
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     * Get createTime 登录时间.
     *
     * @return the string
     */
    public Date getCreateTime(){
        return createTime;
    }
}
