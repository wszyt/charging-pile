package com.zyt.charging.web.controller;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeRecordChangeReq;
import com.zyt.charging.api.entity.request.ChargeRecordCountReq;
import com.zyt.charging.api.service.ChargeInfoService;
import com.zyt.charging.api.service.ChargeRecordService;
import org.apache.dubbo.config.annotation.Reference;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @Author: zyt
 * @Date: 2020/4/8
 */
@Controller
public class ChargeRecordController {

  @Reference(version = "${service.version}")
  ChargeRecordService chargeRecordService;

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

  @RequestMapping(value = "startCharge")
  public String startCharge() {
    return null;
  }

  @RequestMapping(value = "startCharge")
  public String endCharge() {
    return null;
  }
}
