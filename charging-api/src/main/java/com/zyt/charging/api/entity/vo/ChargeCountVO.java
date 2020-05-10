package com.zyt.charging.api.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @Author: zyt
 * @Date: 2020/5/10
 */
@Data
public class ChargeCountVO implements Serializable {
    private String brands;
    private Integer typeCount;
    private Integer chargeCount;
    private Integer unableCount;
    private Integer changed;
}
