package com.zyt.charging.api.entity.request;

import java.io.Serializable;
import lombok.Data;

/**
 * @Author: zyt
 * @Date: 2020/4/6
 */
@Data
public class ChargeInfoQueryReq implements Serializable {
  private Long id;
  private Integer status;
  private String city;
  private String type;
}
