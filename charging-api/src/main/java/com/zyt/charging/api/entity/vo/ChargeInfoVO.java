package com.zyt.charging.api.entity.vo;

import java.io.Serializable;
import lombok.Data;

import java.util.Date;

/**
 * @author: zyt
 * @Date: 2020/4/6
 */
@Data
public class ChargeInfoVO implements Serializable {
    private Long id;
    // 充电次数
    private Integer chargeTimes;
    // 充电单价
    private Integer price;
    // 计费类型
    private Integer priceType;
    // 充电桩状态
    private Integer status;
    // 充电桩编号
    private String code;
    // 所在城市
    private String city;
    // 所在地坐标
    private String placeCode;
    // 充电桩类型
    private String type;
    private Date createTime;
    private Date updateTime;
}
