package com.zyt.charging.provider.entity.DO;

import java.util.Date;

/**
 * @author: zyt
 * @Date: 2020/4/1
 */
public class ChargeRecordDO {
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
