package com.zyt.charging.web.resp;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zyt
 * @Date: 2020/5/10
 */
@Data
public class ChargeDetailResp implements Serializable {
    private Long id;
    private Long chargeInfoId;
    private Long userInfoId;
    // 充电时长-单位-秒
    private Integer chargeTime;
    // 使用电量
    private Integer powerUsed;
    // 充电开始时间
    private Date startTime;
    // 消费价格
    private Integer cost;
    private Date endTime;
    // 用户名称
    private String name;
    private String isPaid;
}
