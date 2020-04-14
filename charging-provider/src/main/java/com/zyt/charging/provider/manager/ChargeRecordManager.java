package com.zyt.charging.provider.manager;

import com.zyt.charging.provider.entity.DO.ChargeRecordDO;
import com.zyt.charging.provider.mapper.ChargeRecordMapper;
import java.util.Date;
import javax.annotation.Resource;
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
}
