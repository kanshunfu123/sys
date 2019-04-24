package com.xiaowei.common.req.guanwang;

import com.xiaowei.common.error.BaseReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by kanshunfu on 2019/01/08.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class OffReq extends BaseReq implements Serializable {
    /**
     * id ID.
     */
    private Long id;
    /**
     * com 公司.
     */
    @NotBlank(message = "com 公司必填",groups = {Add.class,Modify.class})
    private String com;
    /**
     * tel 电话.
     */
    @NotBlank(message = "tel 电话必填",groups = {Add.class,Modify.class})
    private String tel;
    /**
     * name 姓名.
     */
    @NotBlank(message = "name 姓名必填",groups = {Add.class,Modify.class})
    private String name;
    /**
     * uuid UUID.
     */
    private String uuid;
    /**
     * office 职位.
     */
    @NotBlank(message = "office 职位必填",groups = {Add.class,Modify.class})
    private String office;
    /**
     * delFlag 删除状态.
     */
    private String delFlag;
    /**
     * message 具体内容.
     */
    @NotBlank(message = "message 具体内容必填",groups = {Add.class,Modify.class})
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCom() {
        return com;
    }

    public void setCom(String com) {
        this.com = com;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getOffice() {
        return office;
    }

    public void setOffice(String office) {
        this.office = office;
    }

    public String getDelFlag() {
        return delFlag;
    }

    public void setDelFlag(String delFlag) {
        this.delFlag = delFlag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
