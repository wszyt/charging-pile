package com.zyt.charging.provider.api.impl;

import com.zyt.charging.api.entity.reponse.BaseResult;
import com.zyt.charging.api.service.RedisService;
import com.zyt.charging.provider.manager.RedisManager;
import java.util.List;
import javax.annotation.Resource;
import org.apache.dubbo.config.annotation.Service;

/**
 * @Author: zyt
 * @Date: 2020/4/17
 */
@Service(version = "${service.version}")
public class RedisServiceImpl implements RedisService {

  @Resource
  RedisManager redisManager;

  @Override
  public BaseResult<Void> setString(String key, String value, Long time) {
    redisManager.setString(key, value, time);
    return BaseResult.success();
  }

  @Override
  public BaseResult<List<String>> getStringList(String key, Long start, Long end) {
    List<String> listString = redisManager.getListString(key, start, end);
    return BaseResult.success(listString);
  }
}
