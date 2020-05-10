package com.zyt.charging.provider.entity.domain;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: zyt
 * @Date: 2020/5/9
 */
@Builder
@Data
public class ChargeBrandsDO {
    private Long id;

    /**
    * 品牌
    */
    private String brands;

    /**
    * 型号
    */
    private String type;

    private String desc;

    private Date updateTime;

    private Date createTime;
}