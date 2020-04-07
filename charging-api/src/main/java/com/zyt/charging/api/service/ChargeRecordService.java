package com.zyt.charging.api.service;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeRecordCountReq;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
public interface ChargeRecordService {

  /**
   * 根据条件查询充电记录数量
   * @param request
   * @return
   */
    BaseResult countChargeRecord(ChargeRecordCountReq request);
}
