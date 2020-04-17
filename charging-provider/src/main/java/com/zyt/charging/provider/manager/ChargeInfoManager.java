package com.zyt.charging.provider.manager;

import com.zyt.charging.api.entity.enums.RedisEnum;
import com.zyt.charging.provider.entity.DO.ChargeInfoDO;
import com.zyt.charging.provider.mapper.ChargeInfoMapper;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Component
public class ChargeInfoManager {
    @Resource
    ChargeInfoMapper chargeInfoMapper;
    @Resource
    RedisManager redisManager;

    @Transactional
    public int insertChargeInfo(ChargeInfoDO chargeInfoDO) {
        int i = chargeInfoMapper.insertChargeInfo(chargeInfoDO);
        if (i > 0) {
            redisManager.lPushListString(RedisEnum.PLACE_CODE.getCode(), chargeInfoDO.getPlaceCode());
        }
        return i;
    }

    public int updateChargeInfo(ChargeInfoDO chargeInfoDO) {
        return chargeInfoMapper.updateChargeInfo(chargeInfoDO);
    }

    public List<ChargeInfoDO> selectChargeInfo(ChargeInfoDO chargeInfoDO) {
        return chargeInfoMapper.selectChargeInfo(chargeInfoDO);
    }

    public ChargeInfoDO selectChargeInfoById(Long id) {
        return chargeInfoMapper.selectChargeInfoById(id);
    }

    public ChargeInfoDO selectChargeInfoByCode(String code) {
    return chargeInfoMapper.selectChargeInfoByCode(code);
  }
}
