package com.zyt.charging.api.entity.request;

import java.io.Serializable;
import lombok.Data;

/**
 * @author: zyt
 * @Date: 2020/4/7
 */
@Data
public class UserInfoQueryReq implements Serializable {
    private Integer phone;
    private String name;
    private String email;
}
