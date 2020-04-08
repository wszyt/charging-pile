package com.zyt.charging.provider.mapper;

import com.zyt.charging.provider.entity.DO.ChargeInfoDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zyt
 * @Date: 2020/4/1
 */
@Mapper
public interface ChargeInfoMapper {

    /**
     * 插入
     * @param chargeInfoDO
     * @return
     */
    int insertChargeInfo(ChargeInfoDO chargeInfoDO);

    /**
     * 更新
     * @param chargeInfoDO
     * @return
     */
    int updateChargeInfo(ChargeInfoDO chargeInfoDO);

    /**
     * 根据条件查询
     * @param chargeInfoDO
     * @return
     */
    List<ChargeInfoDO> selectChargeInfo(ChargeInfoDO chargeInfoDO);

    /**
     * 根据Id查询ChargeInfo
     * @param id
     * @return
     */
    ChargeInfoDO selectChargeInfoById(Long id);
}
