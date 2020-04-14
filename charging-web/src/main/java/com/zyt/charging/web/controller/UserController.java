package com.zyt.charging.web.controller;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.UserInfoQueryReq;
import com.zyt.charging.api.entity.request.UserInfoChangeReq;
import com.zyt.charging.api.entity.vo.UserInfoVO;
import com.zyt.charging.api.service.UserInfoService;
import java.util.List;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Reference(version = "${service.version}")
    UserInfoService userInfoService;

    @RequestMapping(value = "/selectUserInfo", method = RequestMethod.POST)
    public BaseResult<UserInfoVO> selectUserInfoById(UserInfoQueryReq request) {
       return userInfoService.selectUserInfoById(request);
    }

    @RequestMapping(value = "/selectUserInfo", method = RequestMethod.POST)
    public BaseResult<List<UserInfoVO>> selectUserInfo(UserInfoQueryReq request) {
        return userInfoService.selectUserInfo(request);
    }

    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST)
    public BaseResult<Void> modifyUserInfo(@RequestBody UserInfoChangeReq request) {
        if (request == null || request.getUserInfoVO() == null) {
            return BaseResult.fail("参数不能为空");
        }

        UserInfoVO chargeInfoVO = request.getUserInfoVO();
        // 存在Id时修改
        if (chargeInfoVO.getId() != null) {
            UserInfoQueryReq chargeInfoQueryReq = new UserInfoQueryReq();
            chargeInfoQueryReq.setId(chargeInfoVO.getId());
            BaseResult<UserInfoVO> chargeInfoVOBaseResult = userInfoService.selectUserInfoById(chargeInfoQueryReq);
            if (BaseResult.STATUS_FAIL.equals(chargeInfoVOBaseResult.getStatus())) {
                return BaseResult.fail(chargeInfoVOBaseResult.getMessage());
            }

            if (chargeInfoVOBaseResult.getData() == null) {
                return BaseResult.fail("改Id没有对应用户信息");
            }
            UserInfoChangeReq chargeInfoChangeReq = new UserInfoChangeReq();
            chargeInfoChangeReq.setUserInfoVO(request.getUserInfoVO());
            return userInfoService.updateUserInfo(chargeInfoChangeReq);
        } else {
            UserInfoChangeReq chargeInfoChangeReq = new UserInfoChangeReq();
            chargeInfoChangeReq.setUserInfoVO(request.getUserInfoVO());
            return userInfoService.insertUserInfo(chargeInfoChangeReq);
        }
    }
}
