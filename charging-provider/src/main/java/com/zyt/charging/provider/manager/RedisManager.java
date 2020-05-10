package com.zyt.charging.provider.manager;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import javax.annotation.Resource;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

@Component
public class RedisManager {

    @Resource
    private StringRedisTemplate stringRedisTemplate;

    public void setString(String key, Object object) {
        // 如果是String 类型
        String value = (String) object;
        stringRedisTemplate.opsForValue().set(key, value);
    }

    /**
     * Redis储存String并且设置有效时间
     * @param key
     * @param value
     * @param time
     */
    public void setString(String key, String value, Long time) {
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

    /**
     * redis对list添加一个数据
     * @param key
     * @param value
     */
    public void lPushListString(String key, String value) {
        stringRedisTemplate.opsForList().leftPush(key, value);
    }

    /**
     * 获取list数据
     * @param key
     * @param start
     * @param end
     * @return
     */
    public List<String> getListString(String key, Long start, Long end) {
        return stringRedisTemplate.opsForList().range(key, start, end);
    }

    /**
     * Redis删除对应数据
     * @param key
     */
    public void del(String key) {
        stringRedisTemplate.delete(key);
    }

    public String getString(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }
}
