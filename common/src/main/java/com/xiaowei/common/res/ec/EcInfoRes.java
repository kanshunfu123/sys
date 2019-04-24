package com.xiaowei.common.res.ec;

import java.io.Serializable;
/**
 * Created by kanshunfu on 2019/01/07.
 */
public class EcInfoRes implements Serializable {
    private EcEquipmentRes ecEquipmentRes;
    private EcSenceRes ecSenceRes;

    public EcEquipmentRes getEcEquipmentRes() {
        return ecEquipmentRes;
    }

    public void setEcEquipmentRes(EcEquipmentRes ecEquipmentRes) {
        this.ecEquipmentRes = ecEquipmentRes;
    }

    public EcSenceRes getEcSenceRes() {
        return ecSenceRes;
    }

    public void setEcSenceRes(EcSenceRes ecSenceRes) {
        this.ecSenceRes = ecSenceRes;
    }
}
