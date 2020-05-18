package com.zyt.charging.api.entity.vo;

import java.io.Serializable;
import lombok.Data;

import java.util.Date;

/**
 * @author: zyt
 * @Date: 2020/4/6
 */
@Data
public class ChargeRecordVO implements Serializable {
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
    private String isPaid;
    private Date createTime;
    private Date updateTime;
}
