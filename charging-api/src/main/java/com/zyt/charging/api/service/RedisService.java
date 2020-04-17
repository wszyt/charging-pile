package com.zyt.charging.api.service;

import com.zyt.charging.api.entity.reponse.BaseResult;
import java.util.List;

/**
 * @Author: zyt
 * @Date: 2020/4/17
 */
public interface RedisService {

  /**
   * redis插入缓存，并设置过期时间
   * @param key
   * @param value
   * @param time
   * @return
   */
  BaseResult<Void> setString(String key, String value, Long time);

  /**
   * redis获取List数据类型
   * @param key
   * @param start
   * @param end
   * @return
   */
  BaseResult<List<String>> getStringList(String key, Long start, Long end);
}
