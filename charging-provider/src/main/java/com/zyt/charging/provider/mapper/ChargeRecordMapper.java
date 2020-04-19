package com.zyt.charging.provider.mapper;

import com.zyt.charging.provider.entity.domain.ChargeRecordDO;
import java.util.Date;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: zyt
 * @Date: 2020/4/1
 */
@Mapper
public interface ChargeRecordMapper {

  /**
   * 根据条件获取记录条数
   * @param startTime
   * @param endTime
   * @return
   */
  int countChargeRecord(@Param("startTime") Date startTime, @Param("endTime") Date endTime);

  /**
   * 插入充电记录
   * @param chargeRecordDO
   * @return
   */
  int insertChargeRecord(ChargeRecordDO chargeRecordDO);

  /**
   * 根据用户id查询用户充电记录
   * @param userId
   * @return
   */
  List<ChargeRecordDO> selectRecordByUserId(@Param("userId") Integer userId);
}
