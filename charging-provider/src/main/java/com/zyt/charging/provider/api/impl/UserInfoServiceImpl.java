package com.zyt.charging.provider.api.impl;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.UserInfoChangeReq;
import com.zyt.charging.api.entity.request.UserInfoQueryReq;
import com.zyt.charging.api.entity.vo.UserInfoVO;
import com.zyt.charging.api.service.UserInfoService;
import com.zyt.charging.provider.entity.DO.UserInfoDO;
import com.zyt.charging.provider.manager.UserInfoManager;
import java.util.ArrayList;
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
        userInfoDO.setPhone(request.getPhone());
        userInfoDO.setName(request.getName());
        userInfoDO.setEmail(request.getEmail());
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
}
