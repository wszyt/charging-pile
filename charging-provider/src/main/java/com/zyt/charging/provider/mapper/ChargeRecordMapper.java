package com.zyt.charging.provider.mapper;

import java.util.Date;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @author: zyt
 * @Date: 2020/4/1
 */
@Mapper
public interface ChargeRecordMapper {
    int countChargeRecord(@Param("startTime") Date startTime, @Param("endTime") Date endTime);
}
