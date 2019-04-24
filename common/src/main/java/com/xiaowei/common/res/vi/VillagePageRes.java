package com.xiaowei.common.res.vi;

import java.io.Serializable;

public class VillagePageRes implements Serializable {
    /**
     * 小区名称
     */
    private String villageName;
    /**
     * 小区类型
     */
    private Integer villageType;
    /**
     * 类型名称
     */
    private String typeName;
    /**
     * 创建时间
     */
  private String createTime;
    /**
     * 位置
     */
    private String location;
    /**
     * 小区uuid
     */
    private String villageUuid;

    public String getVillageName() {
        return villageName;
    }

    public void setVillageName(String villageName) {
        this.villageName = villageName;
    }

    public Integer getVillageType() {
        return villageType;
    }

    public void setVillageType(Integer villageType) {
        this.villageType = villageType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getVillageUuid() {
        return villageUuid;
    }

    public void setVillageUuid(String villageUuid) {
        this.villageUuid = villageUuid;
    }
}
