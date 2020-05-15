package com.zyt.charging.api.entity.request;

import com.zyt.charging.api.entity.vo.UserInfoVO;

import java.io.Serializable;

import lombok.Data;

/**
 * @author: zyt
 * @Date: 2020/4/7
 */
@Data
public class UserInfoChangeReq implements Serializable {
    UserInfoVO userInfoVO;
}
