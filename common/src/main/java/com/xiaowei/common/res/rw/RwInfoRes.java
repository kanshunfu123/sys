package com.xiaowei.common.res.rw;



import java.io.Serializable;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public class RwInfoRes implements Serializable {
    private RwEquipmentRes rwEquipmentRes;
    private RwSenceRes rwSenceRes;

    public RwEquipmentRes getRwEquipmentRes() {
        return rwEquipmentRes;
    }

    public void setRwEquipmentRes(RwEquipmentRes rwEquipmentRes) {
        this.rwEquipmentRes = rwEquipmentRes;
    }

    public RwSenceRes getRwSenceRes() {
        return rwSenceRes;
    }

    public void setRwSenceRes(RwSenceRes rwSenceRes) {
        this.rwSenceRes = rwSenceRes;
    }
}
