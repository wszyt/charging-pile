package com.zyt.charging.provider.manager;

import com.zyt.charging.provider.entity.DO.ChargeInfoDO;
import com.zyt.charging.provider.mapper.ChargeInfoMapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Component
public class ChargeInfoManager {
    @Resource
    ChargeInfoMapper chargeInfoMapper;

    public int updateChargeInfo(ChargeInfoDO chargeInfoDO) {
        return chargeInfoMapper.updateChargeInfo(chargeInfoDO);
    }
}
