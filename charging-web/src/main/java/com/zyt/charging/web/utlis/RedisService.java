package com.zyt.charging.web.utlis;

import java.util.List;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key, Object object) {
        // 如果是String 类型
        String value = (String) object;
        stringRedisTemplate.opsForValue().set(key, value);
    }

    public void setString(String key, Object object, Long time) {
        // 如果是String 类型
        String value = (String) object;
        stringRedisTemplate.opsForValue().set(key, value);
        // 设置有效期 以秒为单位
        stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    public void setSet(String key, Object object) {
        Set<String> value = (Set<String>) object;
        for (String oj : value) {
            stringRedisTemplate.opsForSet().add(key, oj);
        }
    }

    public void setListString(String key, String value) {
        stringRedisTemplate.opsForList().leftPush(key, value);
    }

    public List<String> getListString(String key, Long start, Long end) {
        return stringRedisTemplate.opsForList().range(key, start, -end);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
