package com.zyt.charging.api.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @Author: zyt
 * @Date: 2020/5/17
 */
@Data
public class ReceiveReportVO implements Serializable {
    /**
     * 主键
     */
    private Long id;

    /**
     * 电压
     */
    private String voltage;

    /**
     * 频率
     */
    private String frequency;

    /**
     * 电流
     */
    private String current;

    /**
     * 有功功率
     */
    private String activePower;

    /**
     * 无功功率
     */
    private String reactivePower;

    /**
     * 有功电量
     */
    private String activeElectric;

    /**
     * 无功电量
     */
    private String reactiveElectric;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    private String code;

}
