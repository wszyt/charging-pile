package com.zyt.charging.web.controller;

import com.zyt.charging.api.service.UserInfoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;

@Controller
public class UserController {

    @Reference(version = "${service.version}")
    UserInfoService userInfoService;
}
