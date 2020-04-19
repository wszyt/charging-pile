package com.zyt.charging.provider.manager;

import com.zyt.charging.provider.entity.domain.UserInfoDO;
import com.zyt.charging.provider.mapper.UserInfoMapper;
import javax.annotation.Resource;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: zyt
 * @Date: 2020/4/2
 */
@Component
public class UserInfoManager {
    @Resource
    UserInfoMapper userInfoMapper;

    public int insertUserInfo(UserInfoDO userInfoDO) {
      return userInfoMapper.insertUserInfo(userInfoDO);
    }

    public int updateUserInfo(UserInfoDO userInfoDO) {
      return userInfoMapper.updateUserInfo(userInfoDO);
    }

    public List<UserInfoDO> selectUserInfo(UserInfoDO userInfoDO) {
        return userInfoMapper.selectUserInfo(userInfoDO);
    }

    public UserInfoDO selectUserInfoById(Long id) {
        return userInfoMapper.selectUserInfoById(id);
    }
}
