package com.zyt.charging.api.entity.request;

import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author: zyt
 * @Date: 2020/4/13
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeRecordChangeReq implements Serializable {
    private Long id;
    private Long chargeInfoId;
    private Long userInfoId;
    // 充电时长
    private Integer chargeTime;
    private Date startTime;
    private Integer powerUsed;
    private Integer cost;
    private String isPaid;

    public boolean checkParam() {
        return chargeInfoId != null && userInfoId != null && chargeTime != null
                && startTime != null && powerUsed != null && cost != null;
    }
}
