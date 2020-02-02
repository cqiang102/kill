package cn.lacia.kill.business.kill.service.impl;

import cn.lacia.kill.business.kill.service.RedisService;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author 你是电脑
 * @create 2019/8/23 - 18:28
 */
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    private RedisTemplate redisTemplate;
    @Override
    public void put(String key, Object value, long seconds) {
        redisTemplate.opsForValue().set(key,value, seconds,TimeUnit.SECONDS);
    }
    @Override
    public void put(String key, Object value) {
        redisTemplate.opsForValue().set(key,value);
    }

    @Override
    public boolean putIfAbsent(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key,value);
    }

    @Override
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    @Override
    public void deleteKey(String... keys) {
        Set<String> kSet = Stream.of(keys).collect(Collectors.toSet());
        redisTemplate.delete(kSet);
    }
}
