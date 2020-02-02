package cn.lacia.kill.business.kill.service;

/**
 * @author 你是电脑
 * @create 2019/8/23 - 18:25
 */
public interface RedisService {
    /**
     * 往缓存中存数据
     * @param key
     * @param value
     * @param seconds 超时时间
     */
     void put(String key, Object value, long seconds);

    /**
     * 往缓存中存数据
     * @param key
     * @param value
     */
     void put(String key, Object value);
     boolean putIfAbsent(String key, Object value);

    /**
     * 取出缓存中的数据
     * @param key
     * @return
     */
     Object get(String key);

    /**
     * 删除缓存中的数据
     * @param keys
     */
     void deleteKey(String... keys);
}
