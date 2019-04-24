package com.xiaowei.common.req;

import com.xiaowei.mana.mapper.dataobject.UserGroupDO;
import lombok.*;

import java.util.Date;

/**
 * Created by MOMO on 2019/1/24.
 */
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageRoleRes {
    private UserGroupDO userGroupDO;
    /**
     * id 角色id.
     */
    private Long id;
    /**
     * groupId 第三方组 小为默认为1.
     */
    private Long groupId;
    /**
     * remark 备注.
     */
    private String remark;


    /**
     * sysRoleName 角色名称.
     */
    private String sysRoleName;
    /**
     * sysRoleType 角色的类型，0：管理员角色，1：普通用户 2其他.
     */
    private String sysRoleType;
    private String sysRoleTypeStr;
    /**
     * sysRoleUuid 唯一，32位字符串，查询用.
     */
    private String sysRoleUuid;
    /**
     * sysRoleStatus 是否被禁用  0否 1禁用.
     */
    private String sysRoleStatus;
    private String sysRoleStatusStr;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * createTime 创建时间.
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getSysRoleName() {
        return sysRoleName;
    }

    public void setSysRoleName(String sysRoleName) {
        this.sysRoleName = sysRoleName;
    }


    public String getSysRoleType() {
        return sysRoleType;
    }

    public void setSysRoleType(String sysRoleType) {
        this.sysRoleType = sysRoleType;
    }

    public String getSysRoleUuid() {
        return sysRoleUuid;
    }

    public void setSysRoleUuid(String sysRoleUuid) {
        this.sysRoleUuid = sysRoleUuid;
    }

    public String getSysRoleStatus() {
        return sysRoleStatus;
    }

    public void setSysRoleStatus(String sysRoleStatus) {
        this.sysRoleStatus = sysRoleStatus;
    }

    //是否被禁用  0否 1禁用.
    public String getSysRoleStatusStr() {
       if ("0".equals(getSysRoleStatus())){
           return "已启用";
       }else if ("1".equals(getSysRoleStatus())){
           return "已禁用";
       }
       return "";
    }

    public void setSysRoleStatusStr(String sysRoleStatusStr) {
        this.sysRoleStatusStr = sysRoleStatusStr;
    }

    public String getSysRoleTypeStr() {
        //0：管理员角色，1：普通用户 2其他.
        if ("0".equals(getSysRoleType())){
            return "管理员";
        }else if("1".equals(getSysRoleType())){
            return "普通角色";
        }
        return "普通角色";
    }

    public void setSysRoleTypeStr(String sysRoleTypeStr) {
        this.sysRoleTypeStr = sysRoleTypeStr;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public UserGroupDO getUserGroupDO() {
        return userGroupDO;
    }

    public void setUserGroupDO(UserGroupDO userGroupDO) {
        this.userGroupDO = userGroupDO;
    }
}
