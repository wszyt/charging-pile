package com.zyt.charging.provider.mapper;

import com.zyt.charging.provider.entity.DO.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author: zyt
 * @Date: 2020/4/1
 */
@Mapper
public interface UserInfoMapper {
    int updateUserInfo(UserInfoDO userInfoDO);

    int insertUserInfo(UserInfoDO userInfoDO);
}
