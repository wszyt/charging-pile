package com.zyt.charging.provider.entity.DO;

import lombok.Data;

import java.util.Date;

/**
 * @author: zyt
 * @Date: 2020/4/1
 */
@Data
public class ChargeRecordDO {
    private Long id;
    private Long chargeInfoId;
    private Long userInfoId;
    // 充电时长-单位-秒
    private Integer chargeTime;
    // 使用电量
    private Integer powerUsed;
    // 计价方式
    private Integer priceType;
    // 消费价格
    private Integer cost;
    // 充电开始时间
    private Date startTime;
    private Date createTime;
    private Date updateTime;
}
