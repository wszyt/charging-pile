package com.zyt.charging.api.entity.enums;

import lombok.Getter;

public enum UserTypeEnum {

    ADMIN("admin", "管理员"),
    USER("user", "用户");

    @Getter
    private String code;
    @Getter
    private String desc;

    UserTypeEnum(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }
}
