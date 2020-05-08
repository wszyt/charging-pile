package com.zyt.charging.api.entity.enums;

import lombok.Getter;

/**
 * @Author: zyt
 * @Date: 2020/5/8
 */
public enum ChargeTypeEnum {
    NORMAL(0, "可使用"),
    USED(1, "使用中"),
    UN_USABLE(2, "不可用")
    ;

    @Getter
    private Integer code;
    @Getter
    private String desc;

    ChargeTypeEnum(Integer code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public static String getByCode(Integer code) {
        for (ChargeTypeEnum value : ChargeTypeEnum.values()) {
            if (value.getCode().equals(code)) {
                return value.getDesc();
            }
            return "状态未知";
        }
        return "状态未知";
    }
}
