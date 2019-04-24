package com.xiaowei.mana.mapper.res.userpage;

import com.xiaowei.mana.mapper.dataobject.RoleDO;

/**
 * Created by MOMO on 2019/1/8.
 */
public class SysRole extends RoleDO {
    //冗余角色类型 0管理员(专为第三方老板而设置） 1普通员工
    private String roleTypeStr;
    public String getRoleTypeStr() {
        if ("0".equals(getSysRoleType())){
            return "管理员";
        }
        return "普通员工";
    }

    public void setRoleTypeStr(String roleTypeStr) {
        this.roleTypeStr = roleTypeStr;
    }
}
