package com.zyt.charging.api.entity.request;

import java.io.Serializable;
import java.util.Date;

import lombok.Builder;
import lombok.Data;

/**
 * @author: zyt
 * @Date: 2020/4/7
 */
@Data
@Builder
public class ChargeRecordCountReq implements Serializable {
    private Long chargeInfoId;
    private Date startTime;
    private Date endTime;
}
