package com.zyt.charging.provider.api.impl;

import com.zyt.charging.api.entity.enums.ChargeStatusEnum;
import com.zyt.charging.api.entity.enums.RedisEnum;
import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeInfoChangeReq;
import com.zyt.charging.api.entity.request.ChargeInfoQueryReq;
import com.zyt.charging.api.entity.vo.ChargeInfoVO;
import com.zyt.charging.api.entity.vo.NodeInfoVO;
import com.zyt.charging.api.entity.vo.ReceiveReportVO;
import com.zyt.charging.api.service.ChargeInfoService;
import com.zyt.charging.api.service.EmailService;
import com.zyt.charging.provider.entity.domain.ChargeBrandsDO;
import com.zyt.charging.provider.entity.domain.ChargeInfoDO;
import com.zyt.charging.provider.entity.domain.NodeInfoDO;
import com.zyt.charging.provider.entity.domain.ReceiveReportDO;
import com.zyt.charging.provider.manager.ChargeBrandsManager;
import com.zyt.charging.provider.manager.ChargeInfoManager;
import com.zyt.charging.provider.manager.RedisManager;
import java.util.ArrayList;
import java.util.List;

import com.zyt.charging.provider.mapper.NodeInfoMapper;
import com.zyt.charging.provider.mapper.ReceiveReportMapper;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.dao.DuplicateKeyException;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Service(version = "${service.version}")
public class ChargeInfoServiceImpl implements ChargeInfoService {

    @Resource
    ChargeInfoManager chargeInfoManager;
    @Resource
    RedisManager redisManager;
    @Resource
    ChargeBrandsManager chargeBrandsManager;
    @Resource
    ReceiveReportMapper receiveReportMapper;
    @Resource
    EmailService emailService;

    @Override
    public BaseResult<Void> insertChargeInfo(ChargeInfoChangeReq request) {
        if (request == null || request.getChargeInfoVO() == null) {
            return BaseResult.fail("充电桩信息不能为空");
        }

        ChargeInfoDO chargeInfoDO = new ChargeInfoDO();
        BeanUtils.copyProperties(request.getChargeInfoVO(), chargeInfoDO);
        int i = 0;
        try {
            i = chargeInfoManager.insertChargeInfo(chargeInfoDO);
        } catch (DuplicateKeyException e) {
            return BaseResult.fail("充电桩编号不能重复");
        }
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
        ChargeBrandsDO chargeBrandsDO = chargeBrandsManager.selectByPrimaryKey(chargeInfoDO.getBrandsId());
        chargeInfoVO.setDesc(chargeBrandsDO.getDesc());
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
        redisManager.del(RedisEnum.PLACE_CODE.getCode());
        chargeInfoDOS.forEach(chargeInfo -> {
            String info = chargeInfo.getPlaceCode() + "," + chargeInfo.getBrands() + "," + chargeInfo.getType()
                    + "," + ChargeStatusEnum.getByCode(chargeInfo.getStatus()) + "," + chargeInfo.getPicUrl() + "," + chargeInfo.getId();
            redisManager.lPushListString(RedisEnum.PLACE_CODE.getCode(), info);
        });
        return BaseResult.success();
    }

    @Override
    public BaseResult<Void> checkChargeInfo(ChargeInfoVO chargeInfoVO) {
        ChargeInfoDO chargeInfoDO = chargeInfoManager.selectChargeInfoByCode(chargeInfoVO.getCode());
        if (chargeInfoDO == null) {
            return BaseResult.fail("充电桩编号不存在");
        }
        if (chargeInfoDO.getStatus().equals(ChargeStatusEnum.UN_USABLE.getCode())) {
            return BaseResult.fail("充电桩状态为不可用");
        }
        try {
            int ae = Math.abs(Integer.parseInt(chargeInfoVO.getActiveElectric()) - Integer.parseInt(chargeInfoDO.getActiveElectric()));
            int ap = Math.abs(Integer.parseInt(chargeInfoVO.getActivePower()) - Integer.parseInt(chargeInfoDO.getActivePower()));
            int cu = Math.abs(Integer.parseInt(chargeInfoVO.getCurrent()) - Integer.parseInt(chargeInfoDO.getCurrent()));
            int fq = Math.abs(Integer.parseInt(chargeInfoVO.getFrequency()) - Integer.parseInt(chargeInfoDO.getFrequency()));
            int vl = Math.abs(Integer.parseInt(chargeInfoVO.getVoltage()) - Integer.parseInt(chargeInfoDO.getVoltage()));
            int re = Math.abs(Integer.parseInt(chargeInfoVO.getReactiveElectric()) - Integer.parseInt(chargeInfoDO.getReactiveElectric()));
            int rp = Math.abs(Integer.parseInt(chargeInfoVO.getReactivePower()) - Integer.parseInt(chargeInfoDO.getReactivePower()));
            if (ae > 3 || ap > 3 || cu > 3 || fq > 3 || vl > 3 || re > 3 || rp > 3) {
                chargeInfoDO.setStatus(ChargeStatusEnum.UN_USABLE.getCode());
                chargeInfoManager.updateChargeInfo(chargeInfoDO);
                emailService.sendSimpleMail("173982112@qq.com", "充电桩参数偏差通知", "充电桩(编号：" + chargeInfoDO.getCode() + ")参数有误，请及时处理。 地址:http://localhost:8080/chargeDetail?id=" + chargeInfoDO.getId());
                return BaseResult.success();
            }
        } catch (Exception e) {
            return BaseResult.fail("请输入整数");
        }
        return BaseResult.success("充电桩状态正确");
    }
}
