package com.zyt.charging.api.entity.request;

import java.io.Serializable;

import lombok.Builder;
import lombok.Data;

/**
 * @author: zyt
 * @Date: 2020/4/7
 */
@Data
public class UserInfoQueryReq implements Serializable {
    private Long id;
    private Integer phone;
    private String name;
    private String email;
    private String username;
    private String password;
    private Integer userType;
}
