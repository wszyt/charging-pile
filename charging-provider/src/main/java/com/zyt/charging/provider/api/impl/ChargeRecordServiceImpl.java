package com.zyt.charging.provider.api.impl;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeRecordChangeReq;
import com.zyt.charging.api.entity.request.ChargeRecordCountReq;
import com.zyt.charging.api.service.ChargeRecordService;
import com.zyt.charging.provider.entity.domain.ChargeInfoDO;
import com.zyt.charging.provider.entity.domain.ChargeRecordDO;
import com.zyt.charging.provider.entity.domain.UserInfoDO;
import com.zyt.charging.provider.manager.ChargeInfoManager;
import com.zyt.charging.provider.manager.ChargeRecordManager;
import com.zyt.charging.provider.manager.UserInfoManager;
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
    @Resource
    ChargeInfoManager chargeInfoManager;
    @Resource
    UserInfoManager userInfoManager;

    @Override
    public BaseResult<Integer> countChargeRecord(ChargeRecordCountReq request) {
        int i = chargeRecordManager.countChargeRecord(request.getStartTime(), request.getEndTime());
        return BaseResult.success(i);
    }

    @Override
    public BaseResult<Integer> insertChargeRecord(ChargeRecordChangeReq request) {
        if (request == null || !request.checkParam()) {
            return BaseResult.fail("缺少必要参数");
        }

        ChargeInfoDO chargeInfoDO = chargeInfoManager.selectChargeInfoById(request.getChargeInfoId());
        UserInfoDO userInfoDO = userInfoManager.selectUserInfoById(request.getUserInfoId());
        if (chargeInfoDO == null) {
            return BaseResult.fail("查不到充电桩信息");
        }
        if (userInfoDO == null) {
            return BaseResult.fail("查不到用户信息");
        }

        ChargeRecordDO chargeRecordDO = new ChargeRecordDO();
        chargeRecordDO.setChargeInfoId(request.getChargeInfoId());
        chargeRecordDO.setUserInfoId(request.getUserInfoId());
        chargeRecordDO.setChargeTime(request.getChargeTime());
        chargeRecordDO.setCost(request.getCost());
        chargeRecordDO.setPowerUsed(request.getPowerUsed());
        chargeRecordDO.setPriceType(chargeInfoDO.getPriceType());
        chargeRecordDO.setStartTime(request.getStartTime());
        int i = chargeRecordManager.insertChargeRecord(chargeRecordDO);
        return BaseResult.success(i, "记录充电记录成功");
    }
}
