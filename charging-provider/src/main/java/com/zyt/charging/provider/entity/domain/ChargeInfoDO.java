package com.zyt.charging.provider.entity.domain;

import lombok.Data;

import java.util.Date;

/**
 * @author: zyt
 * @Date: 2020/4/1
 */
@Data
public class ChargeInfoDO {
    private Long id;
    private Long brandsId;
    // 充电次数
    private Integer chargeTimes;
    // 充电单价
    private Integer price;
    // 功率
    private Integer power;
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
    // 充电桩品牌
    private String brands;
    // 图片
    private String picUrl;
    private Date createTime;
    private Date updateTime;
}
