package com.zyt.charging.web.controller;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeRecordCountReq;
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
}
