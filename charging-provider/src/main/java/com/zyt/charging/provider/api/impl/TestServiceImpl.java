package com.zyt.charging.provider.api.impl;

import com.zyt.charging.api.service.TestService;
import org.apache.dubbo.config.annotation.Service;

@Service(version = "${service.version}")
public class TestServiceImpl implements TestService {
    @Override
    public String test() {
        return "hello";
    }
}
