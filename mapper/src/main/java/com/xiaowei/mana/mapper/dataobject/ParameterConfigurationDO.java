package com.xiaowei.mana.mapper.dataobject;

import java.util.Date;
import com.xiaowei.mana.mapper.dataobject.ParameterConfigurationDO;

/**
 * The table 参数配置
 * 注意:此结构有系统生成,禁止手工修改,以免被覆盖,请通过dalgen生成
 */
public class ParameterConfigurationDO{

    /**
     * id ID.
     */
    private Long id;
    /**
     * dictionaryId 关联字典id.
     */
    private Long dictionaryId;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * parameterName 参数名称.
     */
    private String parameterName;
    /**
     * sysParameterUuid 参数uuid.
     */
    private String sysParameterUuid;
    /**
     * sysParameterRemark 备注.
     */
    private String sysParameterRemark;
    /**
     * sysParameterSeq 参数在当前层级下的顺序，由小到大.
     */
    private Integer sysParameterSeq;
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
     * Set dictionaryId 关联字典id.
     */
    public void setDictionaryId(Long dictionaryId){
        this.dictionaryId = dictionaryId;
    }

    /**
     * Get dictionaryId 关联字典id.
     *
     * @return the string
     */
    public Long getDictionaryId(){
        return dictionaryId;
    }

    /**
     * Set delFlag 删除状态(0-正常，1-删除).
     */
    public void setDelFlag(String delFlag){
        this.delFlag = delFlag;
    }

    /**
     * Get delFlag 删除状态(0-正常，1-删除).
     *
     * @return the string
     */
    public String getDelFlag(){
        return delFlag;
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
     * Set parameterName 参数名称.
     */
    public void setParameterName(String parameterName){
        this.parameterName = parameterName;
    }

    /**
     * Get parameterName 参数名称.
     *
     * @return the string
     */
    public String getParameterName(){
        return parameterName;
    }

    /**
     * Set sysParameterUuid 参数uuid.
     */
    public void setSysParameterUuid(String sysParameterUuid){
        this.sysParameterUuid = sysParameterUuid;
    }

    /**
     * Get sysParameterUuid 参数uuid.
     *
     * @return the string
     */
    public String getSysParameterUuid(){
        return sysParameterUuid;
    }

    /**
     * Set sysParameterRemark 备注.
     */
    public void setSysParameterRemark(String sysParameterRemark){
        this.sysParameterRemark = sysParameterRemark;
    }

    /**
     * Get sysParameterRemark 备注.
     *
     * @return the string
     */
    public String getSysParameterRemark(){
        return sysParameterRemark;
    }

    /**
     * Set sysParameterSeq 参数在当前层级下的顺序，由小到大.
     */
    public void setSysParameterSeq(Integer sysParameterSeq){
        this.sysParameterSeq = sysParameterSeq;
    }

    /**
     * Get sysParameterSeq 参数在当前层级下的顺序，由小到大.
     *
     * @return the string
     */
    public Integer getSysParameterSeq(){
        return sysParameterSeq;
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
