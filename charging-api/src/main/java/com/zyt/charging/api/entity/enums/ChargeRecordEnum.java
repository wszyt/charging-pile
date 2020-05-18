package com.zyt.charging.api.entity.enums;

import lombok.Getter;

/**
 * @Author: zyt
 * @Date: 2020/5/17
 */
public class ChargeRecordEnum {

    public enum IsPaidEnum {
        YES("是"),
        NO("否"),
        ;

        @Getter
        private String desc;

        IsPaidEnum(String desc) {
            this.desc = desc;
        }

    }
}
