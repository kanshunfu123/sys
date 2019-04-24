package com.xiaowei.mana.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mana.mapper.dataobject.OfficialWebsiteDO;

/**
 * The table OFFICIAL_WEBSITE
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class OfficialWebsiteDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * com 公司.
     */
    private String com;
    /**
     * tel 电话.
     */
    private String tel;
    /**
     * name 姓名.
     */
    private String name;
    /**
     * uuid UUID.
     */
    private String uuid;
    /**
     * office 职位.
     */
    private String office;
    /**
     * delFlag 删除状态.
     */
    private String delFlag;
    /**
     * message 具体内容.
     */
    private String message;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;

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
     * Set com 公司.
     */
    public void setCom(String com){
        this.com = com;
    }

    /**
     * Get com 公司.
     *
     * @return the string
     */
    public String getCom(){
        return com;
    }

    /**
     * Set tel 电话.
     */
    public void setTel(String tel){
        this.tel = tel;
    }

    /**
     * Get tel 电话.
     *
     * @return the string
     */
    public String getTel(){
        return tel;
    }

    /**
     * Set name 姓名.
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     * Get name 姓名.
     *
     * @return the string
     */
    public String getName(){
        return name;
    }

    /**
     * Set uuid UUID.
     */
    public void setUuid(String uuid){
        this.uuid = uuid;
    }

    /**
     * Get uuid UUID.
     *
     * @return the string
     */
    public String getUuid(){
        return uuid;
    }

    /**
     * Set office 职位.
     */
    public void setOffice(String office){
        this.office = office;
    }

    /**
     * Get office 职位.
     *
     * @return the string
     */
    public String getOffice(){
        return office;
    }

    /**
     * Set delFlag 删除状态.
     */
    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag 删除状态.
     *
     * @return the string
     */
    public String getDelFlag(){
        return delFlag;
    }

    /**
     * Set message 具体内容.
     */
    public void setMessage(String message){
        this.message = message;
    }

    /**
     * Get message 具体内容.
     *
     * @return the string
     */
    public String getMessage(){
        return message;
    }

    /**
     * Set createBy 创建人.
     */
    public void setCreateBy(String createBy){
        this.createBy = createBy;
    }

    /**
     * Get createBy 创建人.
     *
     * @return the string
     */
    public String getCreateBy(){
        return createBy;
    }

    /**
     * Set updateBy 修改人.
     */
    public void setUpdateBy(String updateBy){
        this.updateBy = updateBy;
    }

    /**
     * Get updateBy 修改人.
     *
     * @return the string
     */
    public String getUpdateBy(){
        return updateBy;
    }

    /**
     * Set createTime 创建时间.
     */
    public void setCreateTime(Date createTime){
        this.createTime = createTime;
    }

    /**
     * Get createTime 创建时间.
     *
     * @return the string
     */
    public Date getCreateTime(){
        return createTime;
    }

    /**
     * Set updateTime 修改时间.
     */
    public void setUpdateTime(Date updateTime){
        this.updateTime = updateTime;
    }

    /**
     * Get updateTime 修改时间.
     *
     * @return the string
     */
    public Date getUpdateTime(){
        return updateTime;
    }
}
