package com.zyt.charging.web.controller;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.UserInfoQueryReq;
import com.zyt.charging.api.entity.request.UserInfoChangeReq;
import com.zyt.charging.api.entity.vo.UserInfoVO;
import com.zyt.charging.api.service.UserInfoService;
import java.util.List;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {

    @Reference(version = "${service.version}")
    UserInfoService userInfoService;

    @ModelAttribute
    public UserInfoVO getUserInfoVO(Long id) {
        UserInfoVO userInfoVO = null;

        UserInfoQueryReq req = new UserInfoQueryReq();
        //id不为空，则从数据库获取
        if(id != null) {
            req.setId(id);
            BaseResult<UserInfoVO> userInfoResult = userInfoService.selectUserInfoById(req);
            userInfoVO = userInfoResult.getData();
        }

        else {
            userInfoVO = new UserInfoVO ();
        }
        return userInfoVO;
    }

    @RequestMapping(value = "/selectUserInfo", method = RequestMethod.POST)
    public BaseResult<UserInfoVO> selectUserInfoById(UserInfoQueryReq request) {
       return userInfoService.selectUserInfoById(request);
    }

    @RequestMapping(value = "/selectAllUserInfoList", method = RequestMethod.GET)
    public String selectAllUserInfoList(Model model) {
        UserInfoQueryReq request = new UserInfoQueryReq();
        BaseResult<List<UserInfoVO>> listBaseResult = userInfoService.selectUserInfo(request);
        model.addAttribute("baseResult", listBaseResult);
        return "userList";
    }

    @RequestMapping(value = "/selectUserInfoList", method = RequestMethod.GET)
    public String selectUserInfoList(Model model) {
        UserInfoQueryReq request = new UserInfoQueryReq();
        BaseResult<List<UserInfoVO>> listBaseResult = userInfoService.selectUserInfo(request);
        model.addAttribute("baseResult", listBaseResult);
        return "userList";
    }

    @RequestMapping(value = "/modifyUserInfoPage", method = RequestMethod.GET)
    public String modifyUserInfoPage() {
        return "editorUserInfo";
    }

    @RequestMapping(value = "/modifyUserInfo", method = RequestMethod.POST)
    public String modifyUserInfo(UserInfoVO userInfoVO, Model model) {
        // 存在Id时修改
        if (userInfoVO.getId() != null) {
            UserInfoQueryReq chargeInfoQueryReq = new UserInfoQueryReq();
            chargeInfoQueryReq.setId(userInfoVO.getId());
            BaseResult<UserInfoVO> userInfoVOBaseResult = userInfoService.selectUserInfoById(chargeInfoQueryReq);
            if (BaseResult.STATUS_FAIL.equals(userInfoVOBaseResult.getStatus())) {
                model.addAttribute("baseResult", userInfoVOBaseResult);
                return "editorUserInfo";
            }

            if (userInfoVOBaseResult.getData() == null) {
                model.addAttribute("baseResult", BaseResult.fail("改Id没有对应用户信息"));
                return "editorUserInfo";
            }
            UserInfoChangeReq chargeInfoChangeReq = new UserInfoChangeReq();
            chargeInfoChangeReq.setUserInfoVO(userInfoVO);
            BaseResult<Void> updateResult = userInfoService.updateUserInfo(chargeInfoChangeReq);
            model.addAttribute("baseResult", updateResult);
            if (updateResult.getStatus().equals(BaseResult.STATUS_FAIL)) {
                return "editorUserInfo";
            }
            return "redirect:/selectAllUserInfoList";
        } else {
            UserInfoChangeReq chargeInfoChangeReq = new UserInfoChangeReq();
            chargeInfoChangeReq.setUserInfoVO(userInfoVO);
            BaseResult<Void> insertResult = userInfoService.insertUserInfo(chargeInfoChangeReq);
            model.addAttribute("baseResult", insertResult);
            if (insertResult.getStatus().equals(BaseResult.STATUS_FAIL)) {
                return "editorUserInfo";
            }
            return "redirect:/selectAllUserInfoList";
        }
    }
}
