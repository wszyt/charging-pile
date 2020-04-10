package com.zyt.charging.api.entity.enums;


import lombok.Getter;

/**
 * @Author: zyt
 * @Date: 2020/4/9
 */
public enum RedisEnum {
  PLACE_CODE("PLACE_CODE", "充电桩坐标");

  @Getter
  private String code;
  @Getter
  private String desc;

  RedisEnum(String code, String desc) {
    this.code = code;
    this.desc = desc;
  }
}
