package com.zyt.charging.provider.api.impl;

import com.zyt.charging.api.service.ChargeInfoService;
import com.zyt.charging.provider.manager.ChargeInfoManager;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Service(version = "${service.version}")
public class ChargeInfoServiceImpl implements ChargeInfoService {

    @Resource
    ChargeInfoManager chargeInfoManager;
}
