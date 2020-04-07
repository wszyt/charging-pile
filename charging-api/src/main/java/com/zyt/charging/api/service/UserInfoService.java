package com.zyt.charging.api.service;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.UserInfoChangeReq;
import com.zyt.charging.api.entity.request.UserInfoQueryReq;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
public interface UserInfoService {

    /**
     * 新增用户信息
     * @param request
     * @return
     */
    BaseResult insertUserInfo(UserInfoChangeReq request);

    /**
     * 修改用户信息
     * @param request
     * @return
     */
    BaseResult updateUserInfo(UserInfoChangeReq request);

    /**
     * 按条件查询用户信息
     * @param request
     * @return BaseResult
     */
    BaseResult selectUserInfo(UserInfoQueryReq request);

}
