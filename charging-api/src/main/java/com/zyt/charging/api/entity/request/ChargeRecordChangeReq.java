package com.zyt.charging.api.entity.request;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * @Author: zyt
 * @Date: 2020/4/13
 */
@Data
public class ChargeRecordChangeReq implements Serializable {
  private Long chargeInfoId;
  private Long userInfoId;
  // 充电时长
  private Integer chargeTime;
  private Date startTime;
  private Integer powerUsed;
  private Integer cost;

  public boolean checkParam() {
    return chargeInfoId != null && userInfoId != null && chargeTime != null
        && startTime != null && powerUsed != null && cost != null;
  }
}
