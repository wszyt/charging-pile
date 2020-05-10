package com.zyt.charging.provider.manager;

import com.zyt.charging.api.entity.enums.RedisEnum;
import com.zyt.charging.provider.entity.domain.ChargeBrandsDO;
import com.zyt.charging.provider.entity.domain.ChargeInfoDO;
import com.zyt.charging.provider.entity.domain.CountCondition;
import com.zyt.charging.provider.mapper.ChargeBrandsMapper;
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
    ChargeBrandsMapper chargeBrandsMapper;
    @Resource
    RedisManager redisManager;

    @Transactional
    public int insertChargeInfo(ChargeInfoDO chargeInfoDO) {
        ChargeBrandsDO brandsAndTypeDO = ChargeBrandsDO.builder().brands(chargeInfoDO.getBrands()).type(chargeInfoDO.getType()).build();
        ChargeBrandsDO chargeBrandsDO = chargeBrandsMapper.selectByBrandsAndType(brandsAndTypeDO);
        if (chargeBrandsDO == null) {
            brandsAndTypeDO.setDesc("");
            chargeBrandsMapper.insert(brandsAndTypeDO);
            chargeInfoDO.setBrandsId(brandsAndTypeDO.getId());
        } else {
            chargeInfoDO.setBrandsId(chargeBrandsDO.getId());
        }
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

    public Integer countChargeInfo(CountCondition countCondition) {
        Integer count = chargeInfoMapper.countChargeInfo(countCondition);
        return count == null ? 0 : count;
    }

    public Integer countChargeBrands(CountCondition countCondition) {
        Integer count = chargeBrandsMapper.countChargeBrands(countCondition);
        return count == null ? 0 : count;
    }
}
