package com.zyt.charging.provider.mapper;

import com.zyt.charging.provider.entity.domain.ChargeInfoDO;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: zyt
 * @Date: 2020/4/1
 */
@Mapper
public interface ChargeInfoMapper {

  /**
   * 插入
   *
   * @param chargeInfoDO
   * @return
   */
  int insertChargeInfo(ChargeInfoDO chargeInfoDO);

  /**
   * 更新
   *
   * @param chargeInfoDO
   * @return
   */
  int updateChargeInfo(ChargeInfoDO chargeInfoDO);

  /**
   * 根据条件查询
   *
   * @param chargeInfoDO
   * @return
   */
  List<ChargeInfoDO> selectChargeInfo(ChargeInfoDO chargeInfoDO);

  /**
   * 根据Id查询ChargeInfo
   *
   * @param id
   * @return
   */
  ChargeInfoDO selectChargeInfoById(@Param("id") Long id);

  /**
   * 根据充电桩编号查询充电桩信息
   * @param code
   * @return
   */
  ChargeInfoDO selectChargeInfoByCode(@Param("code") String code);
}
