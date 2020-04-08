package com.zyt.charging.api.service;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.entity.request.UserInfoChangeReq;
import com.zyt.charging.api.entity.request.UserInfoQueryReq;
import com.zyt.charging.api.entity.vo.UserInfoVO;
import java.util.List;

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
    BaseResult<Void> insertUserInfo(UserInfoChangeReq request);

    /**
     * 修改用户信息
     * @param request
     * @return
     */
    BaseResult<Void> updateUserInfo(UserInfoChangeReq request);

    /**
     * 按条件查询用户信息
     * @param request
     * @return BaseResult
     */
    BaseResult<List<UserInfoVO>> selectUserInfo(UserInfoQueryReq request);

    /**
     * 根据Id查询用户信息
     * @param request
     * @return
     */
    BaseResult<UserInfoVO> selectUserInfoById(UserInfoQueryReq request);

}
