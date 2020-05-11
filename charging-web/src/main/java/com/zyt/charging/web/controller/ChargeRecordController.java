package com.zyt.charging.web.controller;

import com.zyt.charging.api.entity.enums.ChargeStatusEnum;
import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeInfoQueryReq;
import com.zyt.charging.api.entity.request.ChargeRecordChangeReq;
import com.zyt.charging.api.entity.request.ChargeRecordCountReq;
import com.zyt.charging.api.entity.request.ChargeRecordQueryReq;
import com.zyt.charging.api.entity.vo.ChargeInfoVO;
import com.zyt.charging.api.entity.vo.ChargeRecordVO;
import com.zyt.charging.api.service.ChargeInfoService;
import com.zyt.charging.api.service.ChargeRecordService;
import com.zyt.charging.api.service.UserInfoService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;

/**
 * @Author: zyt
 * @Date: 2020/4/8
 */
@Controller
public class ChargeRecordController {

    @Reference(version = "${service.version}")
    ChargeRecordService chargeRecordService;
    @Reference(version = "${service.version}")
    ChargeInfoService chargeInfoService;
    @Reference(version = "${service.version}")
    UserInfoService userInfoService;

    @ModelAttribute
    public ChargeRecordVO getChargeRecordVO(Long id) {
        ChargeRecordVO chargeRecordVO = null;
        ChargeRecordQueryReq req = new ChargeRecordQueryReq();
        //id不为空，则从数据库获取
        if (id != null) {
            req.setId(id);
            BaseResult<ChargeRecordVO> chargeInfoVOBaseResult = chargeRecordService.selectRecordById(req);
            chargeRecordVO = chargeInfoVOBaseResult.getData();
        } else {
            chargeRecordVO = new ChargeRecordVO();
        }
        return chargeRecordVO;
    }

    @RequestMapping(value = "countChargeRecord", method = RequestMethod.GET)
    public BaseResult<Integer> countChargeRecord(ChargeRecordCountReq chargeRecordCountReq) {
        return chargeRecordService.countChargeRecord(chargeRecordCountReq);
    }

    @RequestMapping(value = "charging", method = RequestMethod.POST)
    public String charging(ChargeRecordChangeReq request) {
        if (request == null || !request.checkParam()) {
            return "缺少必要参数";
        }

        BaseResult<Integer> baseResult = chargeRecordService.insertChargeRecord(request);
        return null;
    }

    @RequestMapping(value = "/startCharge")
    public String startCharge(ChargeRecordVO chargeRecordVO, Model model, RedirectAttributes redirectAttributes) {
        chargeRecordVO.setStartTime(new Date());
        BaseResult<ChargeRecordVO> baseResult = chargeRecordService.startCharge(chargeRecordVO);
        model.addAttribute("baseResult", baseResult);
        if (baseResult.getStatus().equals(BaseResult.STATUS_FAIL)) {
            return "charge";
        }
        chargeRecordVO.setId(baseResult.getData().getId());
        return "endCharge";
    }

    @RequestMapping(value = "/endCharge")
    public String endCharge(ChargeRecordVO chargeRecordVO) {
        BaseResult<Void> baseResult = chargeRecordService.endCharge(chargeRecordVO);
        if (baseResult.getStatus().equals(BaseResult.STATUS_FAIL)) {
            return "endCharge";
        }
        return "index";
    }

    @RequestMapping(value = "/endChargePage")
    public String endChargePage() {
        return "endCharge";
    }

    @RequestMapping(value = "/charge")
    public String charge() {
        return "charge";
    }
}
