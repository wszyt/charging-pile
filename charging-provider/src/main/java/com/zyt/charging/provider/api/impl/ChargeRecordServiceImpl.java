package com.zyt.charging.provider.api.impl;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeRecordCountReq;
import com.zyt.charging.api.service.ChargeRecordService;
import com.zyt.charging.provider.manager.ChargeRecordManager;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Service(version = "${service.version}")
public class ChargeRecordServiceImpl implements ChargeRecordService {

    @Resource
    ChargeRecordManager chargeRecordManager;

    @Override
    public BaseResult<Integer> countChargeRecord(ChargeRecordCountReq request) {
        int i = chargeRecordManager.countChargeRecord(request.getStartTime(), request.getEndTime());
        return BaseResult.success(i);
    }
}
