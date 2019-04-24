package com.xiaowei.web.controller.MMPgroupDevice;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.MMPgroupDeviceReq;
import com.xiaowei.service.MMPgroupDevice.MMPgroupDeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by MOMO on 2019/1/25.
 */
@RestController
@RequestMapping("/platform/mmp")
@Slf4j
public class MMPgroupDeviceController {
    @Autowired
    private MMPgroupDeviceService mmPgroupDeviceService;

    /**
     * 待授权设备列表  不包含已经授权的设备列表
     * @param mmPgroupDeviceReq
     * @return
     */
    @PostMapping("/mmp/v1")
    public JSONResult mmp(@Validated(MMPgroupDeviceReq.Query.class) @RequestBody MMPgroupDeviceReq mmPgroupDeviceReq) {
        return mmPgroupDeviceService.mmp(mmPgroupDeviceReq);
    }

    /**
     * 待授权设备列表  不包含已经授权的设备列表
     * @param mmPgroupDeviceReq
     * @return
     */
    @PostMapping("/aclmmp/v1")
    public JSONResult aclmmp(@Validated(MMPgroupDeviceReq.Query.class) @RequestBody MMPgroupDeviceReq mmPgroupDeviceReq) {
        return mmPgroupDeviceService.aclmmp(mmPgroupDeviceReq);
    }

}
