package com.zyt.charging.provider.api.impl;

import com.zyt.charging.api.entity.enums.ChargeStatusEnum;
import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeInfoQueryReq;
import com.zyt.charging.api.entity.request.ChargeRecordChangeReq;
import com.zyt.charging.api.entity.request.ChargeRecordCountReq;
import com.zyt.charging.api.entity.request.ChargeRecordQueryReq;
import com.zyt.charging.api.entity.vo.ChargeInfoVO;
import com.zyt.charging.api.entity.vo.ChargeRecordVO;
import com.zyt.charging.api.service.ChargeRecordService;
import com.zyt.charging.api.utils.DateUtils;
import com.zyt.charging.provider.entity.domain.ChargeInfoDO;
import com.zyt.charging.provider.entity.domain.ChargeRecordDO;
import com.zyt.charging.provider.entity.domain.UserInfoDO;
import com.zyt.charging.provider.manager.ChargeInfoManager;
import com.zyt.charging.provider.manager.ChargeRecordManager;
import com.zyt.charging.provider.manager.UserInfoManager;
import org.apache.dubbo.config.annotation.Service;
import org.springframework.beans.BeanUtils;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Service(version = "${service.version}")
public class ChargeRecordServiceImpl implements ChargeRecordService {

    @Resource
    ChargeRecordManager chargeRecordManager;
    @Resource
    ChargeInfoManager chargeInfoManager;
    @Resource
    UserInfoManager userInfoManager;

    @Override
    public BaseResult<Integer> countChargeRecord(ChargeRecordCountReq request) {
        int i = chargeRecordManager.countChargeRecord(request.getStartTime(), request.getEndTime());
        return BaseResult.success(i);
    }

    @Override
    public BaseResult<Integer> insertChargeRecord(ChargeRecordChangeReq request) {
        if (request == null || !request.checkParam()) {
            return BaseResult.fail("缺少必要参数");
        }

        ChargeInfoDO chargeInfoDO = chargeInfoManager.selectChargeInfoById(request.getChargeInfoId());
        UserInfoDO userInfoDO = userInfoManager.selectUserInfoById(request.getUserInfoId());
        if (chargeInfoDO == null) {
            return BaseResult.fail("查不到充电桩信息");
        }
        if (userInfoDO == null) {
            return BaseResult.fail("查不到用户信息");
        }

        ChargeRecordDO chargeRecordDO = new ChargeRecordDO();
        chargeRecordDO.setChargeInfoId(request.getChargeInfoId());
        chargeRecordDO.setUserInfoId(request.getUserInfoId());
        chargeRecordDO.setChargeTime(request.getChargeTime());
        chargeRecordDO.setCost(request.getCost());
        chargeRecordDO.setPowerUsed(request.getPowerUsed());
        chargeRecordDO.setPriceType(chargeInfoDO.getPriceType());
        chargeRecordDO.setStartTime(request.getStartTime());
        int i = chargeRecordManager.insertChargeRecord(chargeRecordDO, null);
        return BaseResult.success(i, "记录充电记录成功");
    }

    @Override
    public BaseResult<List<ChargeRecordVO>> selectRecordByUserId(ChargeRecordQueryReq request) {
        List<ChargeRecordVO> chargeRecordVOS = new ArrayList<>();
        List<ChargeRecordDO> chargeRecordDOS = chargeRecordManager.selectRecordByUserId(request.getUserId());
        chargeRecordDOS.forEach(chargeRecordDO -> {
            ChargeRecordVO chargeRecordVO = new ChargeRecordVO();
            BeanUtils.copyProperties(chargeRecordDO, chargeRecordVO);
            chargeRecordVOS.add(chargeRecordVO);
        });

        return BaseResult.success(chargeRecordVOS);
    }

    @Override
    public BaseResult<ChargeRecordVO> selectRecordById(ChargeRecordQueryReq request) {
        ChargeRecordVO chargeRecordVO = new ChargeRecordVO();
        ChargeRecordDO chargeRecordDO = chargeRecordManager.selectRecordById(request.getId());
        BeanUtils.copyProperties(chargeRecordDO, chargeRecordVO);
        return BaseResult.success(chargeRecordVO);
    }

    @Override
    public BaseResult<List<ChargeRecordVO>> selectRecordByChargeInfoId(ChargeRecordQueryReq request) {
        List<ChargeRecordVO> chargeRecordVOS = new ArrayList<>();
        List<ChargeRecordDO> chargeRecordDOS = chargeRecordManager.selectRecordByUserId(request.getChargeInfoId());
        chargeRecordDOS.forEach(chargeRecordDO -> {
            ChargeRecordVO chargeRecordVO = new ChargeRecordVO();
            BeanUtils.copyProperties(chargeRecordDO, chargeRecordVO);
            chargeRecordVOS.add(chargeRecordVO);
        });

        return BaseResult.success(chargeRecordVOS);
    }

    @Override
    public BaseResult<Integer> countChargeRecordByUser(ChargeRecordCountReq request) {
        int i = chargeRecordManager.countChargeRecordByUser(request.getChargeInfoId());
        return BaseResult.success(i, "成功");
    }

    @Override
    public BaseResult<ChargeRecordVO> startCharge(ChargeRecordVO chargeRecordVO) {
        ChargeInfoQueryReq chargeInfoQueryReq = new ChargeInfoQueryReq();
        chargeInfoQueryReq.setId(chargeRecordVO.getChargeInfoId());
        ChargeInfoDO chargeInfoDO = chargeInfoManager.selectChargeInfoById(chargeRecordVO.getChargeInfoId());
        if (chargeInfoDO == null || !chargeInfoDO.getStatus().equals(ChargeStatusEnum.NORMAL.getCode())) {
            return BaseResult.fail("充电桩不存在或充电桩不可用");
        }
        chargeInfoDO.setStatus(ChargeStatusEnum.USED.getCode());
        ChargeRecordDO chargeRecordDO = new ChargeRecordDO();
        BeanUtils.copyProperties(chargeRecordVO, chargeRecordDO);
        chargeRecordDO.setEndTime(DateUtils.getDefaultDate());
        chargeRecordDO.setCost(0);
        chargeRecordDO.setPowerUsed(0);
        chargeRecordDO.setChargeTime(0);
        chargeRecordDO.setPriceType(0);
        chargeRecordManager.insertChargeRecord(chargeRecordDO, chargeInfoDO);
        chargeRecordVO.setId(chargeRecordDO.getId());
        return BaseResult.success(chargeRecordVO);
    }

    @Override
    public BaseResult<Void> endCharge(ChargeRecordVO chargeRecordVO) {
        ChargeInfoQueryReq chargeInfoQueryReq = new ChargeInfoQueryReq();
        chargeInfoQueryReq.setId(chargeRecordVO.getChargeInfoId());
        ChargeInfoDO chargeInfoDO = chargeInfoManager.selectChargeInfoById(chargeRecordVO.getChargeInfoId());
        if (chargeInfoDO == null || !chargeInfoDO.getStatus().equals(ChargeStatusEnum.USED.getCode())) {
            return BaseResult.fail("充电桩不存在或充电桩不可用");
        }
        chargeInfoDO.setStatus(ChargeStatusEnum.NORMAL.getCode());
        ChargeRecordDO chargeRecordDO = new ChargeRecordDO();
        BeanUtils.copyProperties(chargeRecordVO, chargeRecordDO);
        chargeRecordDO.setEndTime(DateUtils.addSecond(chargeRecordDO.getStartTime(), chargeRecordDO.getChargeTime()));
        chargeRecordDO.setPriceType(chargeInfoDO.getPriceType());
        if (chargeInfoDO.getPriceType().equals(1)) {
            Integer time = chargeRecordDO.getChargeTime() / 60 / 60 + 1;
            chargeRecordDO.setCost(time * chargeInfoDO.getPrice());

        } else {
            chargeRecordDO.setCost(chargeRecordDO.getPowerUsed() * chargeInfoDO.getPrice());
        }
        chargeRecordManager.updateChargeRecord(chargeRecordDO, chargeInfoDO);
        return BaseResult.success();
    }
}
