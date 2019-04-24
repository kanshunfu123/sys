package com.xiaowei.common.res.cw;

import java.io.Serializable;

/**
 * Created by kanshunfu on 2019/01/07.
 */
public class CwInfoRes implements Serializable {
    private CwEquipmentRes equipment;
    private CwSenceRes sence;

    public CwEquipmentRes getEquipment() {
        return equipment;
    }

    public void setEquipment(CwEquipmentRes equipment) {
        this.equipment = equipment;
    }

    public CwSenceRes getSence() {
        return sence;
    }

    public void setSence(CwSenceRes sence) {
        this.sence = sence;
    }
}
