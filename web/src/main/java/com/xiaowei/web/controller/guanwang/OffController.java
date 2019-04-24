package com.xiaowei.web.controller.guanwang;

import com.xiaowei.common.common.JSONResult;
import com.xiaowei.common.req.guanwang.OffReq;
import com.xiaowei.service.guanwang.OffService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


/**
 * Created by MOMO on 2019/1/10.
 */
@RestController
@Slf4j
@RequestMapping("/platform")
public class OffController {
    @Autowired
    private OffService offService;

    @PostMapping("/off/add/v1")
    public JSONResult add(@Validated(OffReq.Add.class) @RequestBody OffReq offReq
    ) {

        return offService.interOff(offReq);

    }

}
