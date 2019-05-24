package com.graduate.jrses.controller;

import com.graduate.jrses.bean.Mix;
import com.graduate.jrses.service.MixService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/Mix")
@Api(description = "多源数据")
public class MixController {

    @Autowired
    private MixService mixServiceImpl;

    @PostMapping("/StoreIndex")
    public String storeCase(@RequestBody Mix mix) {
        return mixServiceImpl.storeIndex(mix);
    }

}
