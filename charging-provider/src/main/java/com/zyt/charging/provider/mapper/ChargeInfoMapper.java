package com.zyt.charging.provider.mapper;

import com.zyt.charging.provider.entity.DO.ChargeInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zyt
 * @Date: 2020/4/1
 */
@Mapper
public interface ChargeInfoMapper {
    int updateChargeInfo(ChargeInfoDO chargeInfoDO);
}
