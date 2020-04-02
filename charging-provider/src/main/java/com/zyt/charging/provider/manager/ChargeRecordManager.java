package com.zyt.charging.provider.manager;

import com.zyt.charging.provider.mapper.ChargeRecordMapper;
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
}
