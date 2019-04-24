package com.xiaowei.common.res.mh;


import java.io.Serializable;
/**
 * Created by kanshunfu on 2019/01/07.
 */
public class MhInfoRes implements Serializable {
    private MhEquipmentRes mhEquipmentRes;
    private MhSenceRes mhSenceRes;

    public MhEquipmentRes getMhEquipmentRes() {
        return mhEquipmentRes;
    }

    public void setMhEquipmentRes(MhEquipmentRes mhEquipmentRes) {
        this.mhEquipmentRes = mhEquipmentRes;
    }

    public MhSenceRes getMhSenceRes() {
        return mhSenceRes;
    }

    public void setMhSenceRes(MhSenceRes mhSenceRes) {
        this.mhSenceRes = mhSenceRes;
    }
}
