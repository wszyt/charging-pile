package com.zyt.charging.provider.manager;

import com.zyt.charging.provider.entity.domain.ChargeRecordDO;
import com.zyt.charging.provider.mapper.ChargeRecordMapper;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Component
public class ChargeRecordManager {
    @Resource
    ChargeRecordMapper chargeRecordMapper;

    public int countChargeRecord(Date startTime, Date endTime) {
        return chargeRecordMapper.countChargeRecord(startTime, endTime);
    }

    public int insertChargeRecord(ChargeRecordDO chargeRecordDO) {
        return chargeRecordMapper.insertChargeRecord(chargeRecordDO);
    }

    public List<ChargeRecordDO> selectRecordByUserId(Long userId) {
        return chargeRecordMapper.selectRecordByUserId(userId);
    }

    public List<ChargeRecordDO> selectRecordByChargeInfoId(Long chargeInfoId) {
        return chargeRecordMapper.selectRecordByChargeInfoId(chargeInfoId);
    }

    public int countChargeRecordByUser(Long chargeInfoId) {
        Integer count = chargeRecordMapper.countChargeRecordByUser(chargeInfoId);
        if (count == null) {
            count = 0;
        }
        return count;
    }
}
