package com.zyt.charging.provider.mapper;

import com.zyt.charging.provider.entity.DO.UserInfoDO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @author: zyt
 * @Date: 2020/4/1
 */
@Mapper
public interface UserInfoMapper {

    /**
     * 更新
     * @param userInfoDO
     * @return
     */
    int updateUserInfo(UserInfoDO userInfoDO);

    /**
     * 插入用户信息
     * @param userInfoDO
     * @return
     */
    int insertUserInfo(UserInfoDO userInfoDO);

    /**
     * 根据条件查询用户信息
     * @param userInfoDO
     * @return
     */
    List<UserInfoDO> selectUserInfo(UserInfoDO userInfoDO);
}
