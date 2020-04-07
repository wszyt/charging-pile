package com.zyt.charging.api.service;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.ChargeInfoChangeReq;
import com.zyt.charging.api.entity.request.ChargeInfoQueryReq;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
public interface ChargeInfoService {

  /**
   * 插入充电桩信息
   * @param request
   * @return
   */
  BaseResult insertChargeInfo(ChargeInfoChangeReq request);

  /**
   * 更新充电桩信息
   * @param request
   * @return
   */
  BaseResult updateChargeInfo(ChargeInfoChangeReq request);

  /**
   * 根据条件查询充电桩信息
   * @param request
   * @return
   */
  BaseResult selectChargeInfo(ChargeInfoQueryReq request);
}
