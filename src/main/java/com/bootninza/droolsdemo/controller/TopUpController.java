package com.bootninza.droolsdemo.controller;

import com.bootninza.droolsdemo.model.TopUpModel;
import com.bootninza.droolsdemo.service.TopUpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TopUpController {

    @Autowired
    TopUpService topUpService;

    @PostMapping("recharge")
    public TopUpModel recharge(@RequestBody TopUpModel topUpModel){
        return topUpService.processTopUp(topUpModel);
    }
}
