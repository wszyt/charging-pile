package com.zyt.charging.provider.api.impl;

import com.zyt.charging.api.entity.enums.RedisEnum;
import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeInfoChangeReq;
import com.zyt.charging.api.entity.request.ChargeInfoQueryReq;
import com.zyt.charging.api.entity.vo.ChargeInfoVO;
import com.zyt.charging.api.service.ChargeInfoService;
import com.zyt.charging.provider.entity.DO.ChargeInfoDO;
import com.zyt.charging.provider.manager.ChargeInfoManager;
import java.util.ArrayList;
import java.util.List;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Service(version = "${service.version}")
public class ChargeInfoServiceImpl implements ChargeInfoService {

    @Resource
    ChargeInfoManager chargeInfoManager;
    @Resource
    RedisService redisService;

    @Override
    public BaseResult<Void> insertChargeInfo(ChargeInfoChangeReq request) {
        if (request == null || request.getChargeInfoVO() == null) {
            return BaseResult.fail("充电桩信息不能为空");
        }

        ChargeInfoDO chargeInfoDO = new ChargeInfoDO();
        BeanUtils.copyProperties(request.getChargeInfoVO(), chargeInfoDO);
        int i = chargeInfoManager.insertChargeInfo(chargeInfoDO);
        if (i > 0) {
            return BaseResult.success("新增充电桩信息成功");
        } else {
            return BaseResult.fail("新增充电桩信息失败");
        }
    }

    @Override
    public BaseResult<Void> updateChargeInfo(ChargeInfoChangeReq request) {
        if (request.getChargeInfoVO().getId() == null) {
            return BaseResult.fail("充电桩ID不能为空");
        }

        ChargeInfoDO chargeInfoDO = new ChargeInfoDO();
        BeanUtils.copyProperties(request.getChargeInfoVO(), chargeInfoDO);
        int i = chargeInfoManager.updateChargeInfo(chargeInfoDO);
        if (i > 0) {
            return BaseResult.success("修改充电桩信息成功");
        } else {
            return BaseResult.fail("修改充电桩信息失败");
        }
    }

    @Override
    public BaseResult<List<ChargeInfoVO>> selectChargeInfo(ChargeInfoQueryReq request) {
        ChargeInfoDO chargeInfoDO = new ChargeInfoDO();
        chargeInfoDO.setStatus(request.getStatus());
        chargeInfoDO.setCity(request.getCity());
        chargeInfoDO.setType(request.getType());
        List<ChargeInfoDO> chargeInfoDOS = chargeInfoManager.selectChargeInfo(chargeInfoDO);

        List<ChargeInfoVO> chargeInfoVOS = new ArrayList<>();
        chargeInfoDOS.forEach(chargeInfo -> {
            ChargeInfoVO chargeInfoVO = new ChargeInfoVO();
            BeanUtils.copyProperties(chargeInfo, chargeInfoVO);
            chargeInfoVOS.add(chargeInfoVO);
        });
        return BaseResult.success(chargeInfoVOS);
    }

    @Override
    public BaseResult<ChargeInfoVO> selectChargeInfoById(ChargeInfoQueryReq request) {
        if (request == null || request.getId() == null) {
            return BaseResult.fail("参数不能为空");
        }

        ChargeInfoDO chargeInfoDO = chargeInfoManager.selectChargeInfoById(request.getId());
        if (chargeInfoDO == null) {
            return BaseResult.success();
        }
        ChargeInfoVO chargeInfoVO = new ChargeInfoVO();
        BeanUtils.copyProperties(chargeInfoDO, chargeInfoVO);
        return BaseResult.success(chargeInfoVO);
    }

    @Override
    public BaseResult<ChargeInfoVO> selectChargeInfoByCode(ChargeInfoQueryReq request) {
        if (request == null || request.getCode() == null) {
            return BaseResult.fail("参数不能为空");
        }

        ChargeInfoDO chargeInfoDO = chargeInfoManager.selectChargeInfoByCode(request.getCode());
        if (chargeInfoDO == null) {
            return BaseResult.success();
        }
        ChargeInfoVO chargeInfoVO = new ChargeInfoVO();
        BeanUtils.copyProperties(chargeInfoDO, chargeInfoVO);
        return BaseResult.success(chargeInfoVO);
    }


    @Override
    public BaseResult<Void> flashPlaceCode() {
        ChargeInfoDO chargeInfoDO = new ChargeInfoDO();
        List<ChargeInfoDO> chargeInfoDOS = chargeInfoManager.selectChargeInfo(chargeInfoDO);
        redisService.del(RedisEnum.PLACE_CODE.getCode());
        chargeInfoDOS.forEach(chargeInfo -> {
            redisService.lPushListString(RedisEnum.PLACE_CODE.getCode(), chargeInfo.getPlaceCode());
        });
        return BaseResult.success();
    }
}
