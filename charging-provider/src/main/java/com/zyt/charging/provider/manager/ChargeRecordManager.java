package com.zyt.charging.provider.manager;

import com.zyt.charging.api.entity.vo.ChargeInfoVO;
import com.zyt.charging.provider.entity.domain.ChargeInfoDO;
import com.zyt.charging.provider.entity.domain.ChargeRecordDO;
import com.zyt.charging.provider.mapper.ChargeInfoMapper;
import com.zyt.charging.provider.mapper.ChargeRecordMapper;
import java.util.Date;
import java.util.List;
import javax.annotation.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Component
public class ChargeRecordManager {
    @Resource
    ChargeRecordMapper chargeRecordMapper;
    @Resource
    ChargeInfoMapper chargeInfoMapper;

    public int countChargeRecord(Date startTime, Date endTime) {
        return chargeRecordMapper.countChargeRecord(startTime, endTime);
    }

    @Transactional
    public int insertChargeRecord(ChargeRecordDO chargeRecordDO, ChargeInfoDO chargeInfoDO) {
        if (chargeInfoDO != null) {
            chargeInfoMapper.updateChargeInfo(chargeInfoDO);
        }
        return chargeRecordMapper.insertChargeRecord(chargeRecordDO);
    }

    @Transactional
    public int updateChargeRecordAndChargeInfo(ChargeRecordDO chargeRecordDO, ChargeInfoDO chargeInfoDO) {
        chargeInfoMapper.updateChargeInfo(chargeInfoDO);
        return chargeRecordMapper.updateChargeRecord(chargeRecordDO);
    }

    public int updateChargeRecord(ChargeRecordDO chargeRecordDO) {
        return chargeRecordMapper.updateChargeRecord(chargeRecordDO);
    }

    public ChargeRecordDO selectRecordById(Long id) {
        return chargeRecordMapper.selectRecordById(id);
    }

    public List<ChargeRecordDO> selectRecordByUserId(Long userId) {
        return chargeRecordMapper.selectRecordByUserId(userId);
    }

    public List<ChargeRecordDO> selectRecordByChargeInfoId(Long chargeInfoId) {
        return chargeRecordMapper.selectRecordByChargeInfoId(chargeInfoId);
    }

    public int countChargeRecordByUser(Long chargeInfoId) {
        List<ChargeRecordDO> chargeRecordDOS = chargeRecordMapper.countChargeRecordByUser(chargeInfoId);
        return chargeRecordDOS.size();
    }
}
