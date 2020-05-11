package com.zyt.charging.api.entity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @Author: zyt
 * @Date: 2020/5/9
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ChargeRecordQueryReq implements Serializable {
    private Long id;
    private Long userId;
    private Long chargeInfoId;
    private String username;
    private String name;
    private String phone;
    private String email;
    private Integer isAdmin;
}
