package com.xiaowei.common.req.home;

import java.util.List;

/**
 * created by 韩金群 2019/2/21
 */
public class AccessReq {
    private List<AccessDeviceReq> list;
    private AreaV2Req areaV2Req;

    public List<AccessDeviceReq> getList() {
        return list;
    }

    public void setList(List<AccessDeviceReq> list) {
        this.list = list;
    }

    public AreaV2Req getAreaV2Req() {
        return areaV2Req;
    }

    public void setAreaV2Req(AreaV2Req areaV2Req) {
        this.areaV2Req = areaV2Req;
    }
}
