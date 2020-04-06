package com.zyt.charging.api.service;

import com.zyt.charging.api.entity.reponse.BaseResponse;
import com.zyt.charging.api.entity.vo.UserInfoVO;

import java.util.List;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
public interface UserInfoService {

    BaseResponse<List<UserInfoVO>> selectUserInfo();

}
