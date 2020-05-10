package com.zyt.charging.provider.entity.domain;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zyt
 * @Date: 2020/5/10
 */
@Data
public class CountCondition implements Serializable {
    private Date startTime;
    private Date endTime;
    private Integer status;
    private String brands;
}
