package com.xiaowei.common.res;

import com.xiaowei.mana.mapper.res.userpage.UserPage;

/**
 * Created by MOMO on 2019/1/8.
 */
public class UserPageRes extends UserPage {
    //是否被禁用  0否 1禁用
    private String forbidden;

    //是否被禁用  0否 1禁用
    private String sysStartStr;
    private String delFlagStr;

    public String getDelFlagStr() {
        if ("0".equals(getDelFlag())){
            return "未删除";
        }
        return "已删除";
    }

    public void setDelFlagStr(String delFlagStr) {
        this.delFlagStr = delFlagStr;
    }

    public String getSysStartStr() {
        if ("0".equals(getSysStart())) {
            return "已启用";
        }
        return "已禁用";
    }

    public void setSysStartStr(String sysStartStr) {
        this.sysStartStr = sysStartStr;
    }

    public String getForbidden() {
        if ("0".equals(getIsForbidden())) {
            return "已启用";
        }
        return "已禁用";
    }

    public void setForbidden(String forbidden) {
        this.forbidden = forbidden;
    }
}
