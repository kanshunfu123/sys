package com.xiaowei.mana.mapper.dataobject;

import com.xiaowei.mana.mapper.dataobject.RoleAreaDO;

/**
 * The table SYS_ROLE_AREA
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class RoleAreaDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * areaId 区域id.
     */
    private Long areaId;
    /**
     * roleId 角色id.
     */
    private Long roleId;
    /**
     * groupId 第三方组 小为默认为1.
     */
    private Long groupId;
    /**
     * deviceType 0 地表水 1井盖 2生活用水 3电梯.
     */
    private String deviceType;
    /**
     * areaLevel 层级 0省 1市 2区 3街道  4 小区) .
     */
    private Integer areaLevel;

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
     * Set areaId 区域id.
     */
    public void setAreaId(Long areaId){
        this.areaId = areaId;
    }

    /**
     * Get areaId 区域id.
     *
     * @return the string
     */
    public Long getAreaId(){
        return areaId;
    }

    /**
     * Set roleId 角色id.
     */
    public void setRoleId(Long roleId){
        this.roleId = roleId;
    }

    /**
     * Get roleId 角色id.
     *
     * @return the string
     */
    public Long getRoleId(){
        return roleId;
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
     * Set deviceType 0 地表水 1井盖 2生活用水 3电梯.
     */
    public void setDeviceType(String deviceType){
        this.deviceType = deviceType;
    }

    /**
     * Get deviceType 0 地表水 1井盖 2生活用水 3电梯.
     *
     * @return the string
     */
    public String getDeviceType(){
        return deviceType;
    }

    /**
     * Set areaLevel 层级 0省 1市 2区 3街道  4 小区) .
     */
    public void setAreaLevel(Integer areaLevel){
        this.areaLevel = areaLevel;
    }

    /**
     * Get areaLevel 层级 0省 1市 2区 3街道  4 小区) .
     *
     * @return the string
     */
    public Integer getAreaLevel(){
        return areaLevel;
    }
}
