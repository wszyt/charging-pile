package com.zyt.charging.api.entity.vo;

import lombok.Data;

import java.util.Date;

/**
 * @author: zyt
 * @Date: 2020/4/6
 */
@Data
public class ChargeRecordVO {
    private Long id;
    private Integer chargeInfoId;
    private Integer userInfoId;
    // 充电时长-单位-秒
    private Integer chargeTime;
    // 使用电量
    private Integer chargeUser;
    private Date createTime;
    private Date updateTime;
}
