package com.xiaowei.common.req;

import com.xiaowei.common.error.BaseReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by kanshunfu on 2019/01/08.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class DictionaryAddReq extends BaseReq implements Serializable {
    /**
     * id ID.
     */
    private Long id;
    /**
     * parentId 上级id.
     */
    @NotNull
    private Long parentId;
    /**
     * delFlag 删除状态(0-正常，1-删除).
     */
    private String delFlag;
    /**
     * codeName 代码名称.
     */
    @NotEmpty(message = "codeName 不能为空",groups = {Add.class})
    private String codeName;
    /**
     * createBy 创建人.
     */
    private String createBy;
    /**
     * updateBy 修改人.
     */
    private String updateBy;
    /**
     * codeLevel 字典层级.
     */
    private String codeLevel;
    /**
     * sysDictionaryUuid 唯一，32位字符串，查询用.
     */
    private String sysDictionaryUuid;
    /**
     * sysDictionaryRemark 备注.
     */
    private String sysDictionaryRemark;
    /**
     * sysDictionarySeq 字典在当前层级下的顺序，由小到大.
     */
    @Min(value = 0, message = "字典在当前层级下的顺序不能为空，只能是大于等于0的数字",groups = {Add.class,Modify.class})
    private Integer sysDictionarySeq;
    /**
     * createTime 创建时间.
     */
    private Date createTime;
    /**
     * updateTime 修改时间.
     */
    private Date updateTime;
    @NotNull(message = "codeValue 不能为空",groups = {Add.class})
    private Long codeValue;

    public Long getCodeValue() {
        return codeValue;
    }

    public void setCodeValue(Long codeValue) {
        this.codeValue = codeValue;
    }

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
     * Set parentId 上级id.
     */
    public void setParentId(Long parentId){
        this.parentId = parentId;
    }

    /**
     * Get parentId 上级id.
     *
     * @return the string
     */
    public Long getParentId(){
        return parentId;
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
     * Set codeName 代码名称.
     */
    public void setCodeName(String codeName){
        this.codeName = codeName;
    }

    /**
     * Get codeName 代码名称.
     *
     * @return the string
     */
    public String getCodeName(){
        return codeName;
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
     * Set codeLevel 字典层级.
     */
    public void setCodeLevel(String codeLevel){
        this.codeLevel = codeLevel;
    }

    /**
     * Get codeLevel 字典层级.
     *
     * @return the string
     */
    public String getCodeLevel(){
        return codeLevel;
    }

    /**
     * Set sysDictionaryUuid 唯一，32位字符串，查询用.
     */
    public void setSysDictionaryUuid(String sysDictionaryUuid){
        this.sysDictionaryUuid = sysDictionaryUuid;
    }

    /**
     * Get sysDictionaryUuid 唯一，32位字符串，查询用.
     *
     * @return the string
     */
    public String getSysDictionaryUuid(){
        return sysDictionaryUuid;
    }

    /**
     * Set sysDictionaryRemark 备注.
     */
    public void setSysDictionaryRemark(String sysDictionaryRemark){
        this.sysDictionaryRemark = sysDictionaryRemark;
    }

    /**
     * Get sysDictionaryRemark 备注.
     *
     * @return the string
     */
    public String getSysDictionaryRemark(){
        return sysDictionaryRemark;
    }

    /**
     * Set sysDictionarySeq 字典在当前层级下的顺序，由小到大.
     */
    public void setSysDictionarySeq(Integer sysDictionarySeq){
        this.sysDictionarySeq = sysDictionarySeq;
    }

    /**
     * Get sysDictionarySeq 字典在当前层级下的顺序，由小到大.
     *
     * @return the string
     */
    public Integer getSysDictionarySeq(){
        return sysDictionarySeq;
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
