package com.zyt.charging.web.controller;

import com.zyt.charging.api.service.ChargeInfoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;

@Controller
public class ChargeController {

    @Reference(version = "${service.version}")
    ChargeInfoService chargeInfoService;
}
