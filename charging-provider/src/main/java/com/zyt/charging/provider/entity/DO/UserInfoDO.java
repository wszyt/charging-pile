package com.zyt.charging.provider.entity.DO;

import java.util.Date;

/**
 * @author: zyt
 * @date: 2020/4/1
 */
public class UserInfoDO {
    private Long id;
    private Integer sex;
    private Integer phone;
    // 用户类型- 0-用户 1-管理员
    private Integer userType;
    private String username;
    private String password;
    private String name;
    private Date createTime;
    private Date updateTime;
}
