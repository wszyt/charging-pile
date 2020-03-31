package com.zyt.charging.web.controller;

import com.zyt.charging.api.service.TestService;
import com.zyt.charging.web.vo.Test;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @Reference(version = "${service.version}")
    TestService testService;

    @RequestMapping(value = "/test", method = RequestMethod.GET)
    public Test test() {
        String test = testService.test();
        System.out.println(test);
        Test test1 = new Test();
        test1.setName("ffasfasfasfas哈哈哈哈");
        return test1;
    }
}
