package com.zyt.charging.provider.api.impl;

import com.zyt.charging.api.entity.enums.ChargeTypeEnum;
import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.reponse.ChargeCountResp;
import com.zyt.charging.api.entity.request.UserInfoChangeReq;
import com.zyt.charging.api.entity.request.UserInfoQueryReq;
import com.zyt.charging.api.entity.vo.ChargeCountVO;
import com.zyt.charging.api.entity.vo.UserInfoVO;
import com.zyt.charging.api.service.UserInfoService;
import com.zyt.charging.api.utils.DateUtils;
import com.zyt.charging.provider.entity.domain.ChargeBrandsDO;
import com.zyt.charging.provider.entity.domain.CountCondition;
import com.zyt.charging.provider.entity.domain.UserInfoDO;
import com.zyt.charging.provider.manager.ChargeBrandsManager;
import com.zyt.charging.provider.manager.ChargeInfoManager;
import com.zyt.charging.provider.manager.UserInfoManager;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.dubbo.config.annotation.Service;

import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.util.StringUtils;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Service(version = "${service.version}")
public class UserInfoServiceImpl implements UserInfoService {

    @Resource
    UserInfoManager userInfoManager;
    @Resource
    ChargeInfoManager chargeInfoManager;
    @Resource
    ChargeBrandsManager chargeBrandsManager;


    @Override
    public BaseResult<Void> insertUserInfo(UserInfoChangeReq request) {
        if (StringUtils.isEmpty(request.getUserInfoVO().getName()) || StringUtils.isEmpty(request.getUserInfoVO().getUsername())
            || StringUtils.isEmpty(request.getUserInfoVO().getPassword()) || StringUtils.isEmpty(request.getUserInfoVO().getSex())) {
            return BaseResult.fail("参数不能为空");
        }

        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(request.getUserInfoVO(), userInfoDO);
        int i = userInfoManager.insertUserInfo(userInfoDO);
        if (i > 0) {
            return BaseResult.success("新增用户信息成功");
        } else {
            return BaseResult.fail("新增用户信息失败");
        }
    }

    @Override
    public BaseResult<Void> updateUserInfo(UserInfoChangeReq request) {
        if (request.getUserInfoVO().getId() == null) {
            return BaseResult.fail("用户ID不能为空");
        }

        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(request.getUserInfoVO(), userInfoDO);
        int i = userInfoManager.updateUserInfo(userInfoDO);
        if (i > 0) {
            return BaseResult.success("修改用户信息成功");
        } else {
            return BaseResult.fail("修改用户信息失败");
        }
    }

    @Override
    public BaseResult<List<UserInfoVO>> selectUserInfo(UserInfoQueryReq request) {
        UserInfoDO userInfoDO = new UserInfoDO();
        BeanUtils.copyProperties(request, userInfoDO);
        List<UserInfoDO> userInfoDOS = userInfoManager.selectUserInfo(userInfoDO);
        List<UserInfoVO> userInfoVOS = new ArrayList<>();
        userInfoDOS.forEach(userInfo -> {
            UserInfoVO userInfoVO = new UserInfoVO();
            BeanUtils.copyProperties(userInfo, userInfoVO);
            userInfoVOS.add(userInfoVO);
        });

        return BaseResult.success(userInfoVOS);
    }

    @Override
    public BaseResult<UserInfoVO> selectUserInfoById(UserInfoQueryReq request) {
        if (request == null || request.getId() == null) {
            return BaseResult.fail("参数不能为空");
        }

        UserInfoDO userInfoDO = userInfoManager.selectUserInfoById(request.getId());
        if (userInfoDO == null) {
            return BaseResult.success();
        }
        UserInfoVO userInfoVO = new UserInfoVO();
        BeanUtils.copyProperties(userInfoDO, userInfoVO);
        return BaseResult.success(userInfoVO);
    }

    @Override
    public BaseResult<ChargeCountResp> selectChargeCount() {
        ChargeCountResp chargeCountResp = new ChargeCountResp();
        List<ChargeBrandsDO> chargeBrandsDOS = chargeBrandsManager.selectEachChargeBrands();
        Date yesterdayStart = DateUtils.getDayStart(-1);
        Date yesterdayEnd = DateUtils.getDayEnd(-1);
        Date endTime = DateUtils.getDayEnd();
        Date startTime = DateUtils.getDayStart();
        List<ChargeCountVO> chargeCountVOS = new ArrayList<>();
        chargeBrandsDOS.forEach(chargeBrandsDO -> {
            ChargeCountVO chargeCountVO = new ChargeCountVO();
            CountCondition countCondition = new CountCondition();
            countCondition.setBrands(chargeBrandsDO.getBrands());
            Integer typeCount = chargeInfoManager.countChargeBrands(countCondition);
            Integer chargeCount = chargeInfoManager.countChargeInfo(countCondition);
            countCondition.setStatus(ChargeTypeEnum.UN_USABLE.getCode());
            Integer unableCount = chargeInfoManager.countChargeInfo(countCondition);
            CountCondition countCondition2 = new CountCondition();
            countCondition2.setBrands(chargeBrandsDO.getBrands());
            countCondition2.setStartTime(startTime);
            countCondition2.setEndTime(endTime);
            Integer change = chargeInfoManager.countChargeInfo(countCondition2);
            chargeCountVO.setBrands(chargeBrandsDO.getBrands());
            chargeCountVO.setTypeCount(typeCount);
            chargeCountVO.setChargeCount(chargeCount);
            chargeCountVO.setUnableCount(unableCount);
            chargeCountVO.setChanged(change);
            chargeCountVOS.add(chargeCountVO);
        });

        CountCondition countCondition = new CountCondition();
        Integer chargeTotal = chargeInfoManager.countChargeInfo(countCondition);
        Integer userTotal = userInfoManager.countUserInfo(countCondition);
        countCondition.setStartTime(startTime);
        countCondition.setEndTime(endTime);
        Integer chargeAdd = chargeInfoManager.countChargeInfo(countCondition);
        Integer userAdd = userInfoManager.countUserInfo(countCondition);
        countCondition.setStartTime(yesterdayStart);
        countCondition.setEndTime(yesterdayEnd);
        Integer yesterdayChargeAdd = chargeInfoManager.countChargeInfo(countCondition);
        Integer yesterdayUserAdd = userInfoManager.countUserInfo(countCondition);
        chargeCountResp.setChargeCountVOS(chargeCountVOS);
        chargeCountResp.setUserTotal(userTotal);
        chargeCountResp.setUserChange(userAdd);
        int add1 = userAdd - yesterdayUserAdd;
        chargeCountResp.setUserChangeAdd(yesterdayUserAdd == 0 ? 100 : add1/yesterdayUserAdd * 100);
        chargeCountResp.setChargeTotal(chargeTotal);
        chargeCountResp.setChargeChange(chargeAdd);
        int add2 = chargeAdd - yesterdayChargeAdd;
        chargeCountResp.setChargeChangeAdd(yesterdayChargeAdd == 0 ? 100 : add2/yesterdayChargeAdd * 100);
        return BaseResult.success(chargeCountResp);
    }
}
