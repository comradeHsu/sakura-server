package com.sakura.study.utils;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.core.ZSetOperations;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class RedisUtil {

    @SuppressWarnings("unchecked")
    private static final RedisTemplate<Serializable, Object> redisTemplate = SpringContextUtil
            .getBean(RedisTemplate.class);

    public static final String readRequestTopic = "readRequest";

    public static final String messageTopic = "messageTopic";

    /**
     * 批量删除对应的value
     *
     * @param keys
     */
    public static void remove(final Collection<Serializable> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 批量删除key
     *
     * @param pattern
     */
    public static void removePattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        if (keys.size() > 0)
            redisTemplate.delete(keys);
    }

    /**
     * 按模式查找Keys
     *
     * @param pattern
     * @return
     */
    public static Set<Serializable> getKeysByPattern(final String pattern) {
        Set<Serializable> keys = redisTemplate.keys(pattern);
        return keys;
    }

    /**
     * 删除对应的value
     *
     * @param key
     */
    public static void remove(final String key) {
        if (exists(key)) {
            redisTemplate.delete(key);
        }
    }

    /**
     * 判断缓存中是否有对应的value
     *
     * @param key
     * @return
     */
    public static boolean exists(final String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 读取缓存
     *
     * @param key
     * @return
     */
    public static Object get(final String key) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return result;
    }

    /**
     * 批量获取缓存缓存
     *
     * @param keys
     * @return
     */
    public static List<Object> multiGet(final Collection<Serializable> keys) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.multiGet(keys);
    }

    /**
     * 批量设置缓存 建议每次最大不超过50个key
     *
     * @param map
     */
    public static void multiSet(final Map<Serializable, Object> map) {
        redisTemplate.opsForValue().multiSet(map);
    }

    /**
     * 批量设置key的失效时间
     *
     * @param keys
     * @param expireTime
     */
    public static void multiSetExpire(Collection<Serializable> keys, Long expireTime) {
        for (Serializable key : keys) {
            expire((String) key, expireTime);
        }
    }

    /**
     * 批量设置缓存 建议每次最大不超过50个key 如果包含的key中已经有数据存在 则不会set
     *
     * @param map
     * @return
     */
    public static boolean multiSetIfAbsent(final Map<Serializable, Object> map) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();

        return operations.multiSetIfAbsent(map);
    }

    /**
     * 读取缓存转化为指定的类型
     *
     * @param <T>
     * @param key
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public static <T> T get(final String key, final Class<T> clazz) {
        Object result = null;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        result = operations.get(key);
        return (T) result;
    }

    /**
     * 写入缓存
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean set(final String key, Object value) {
        boolean result = false;
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        result = true;
        return result;
    }

    /**
     * 写入缓存,返回old value
     *
     * @param key
     * @param value
     * @return
     */
    public static Object getAndSet(final String key, Object value) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        return operations.getAndSet(key, value);
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     * @param expireTime
     * @return
     */
    public static boolean set(final String key, Object value, Long expireTime) {
        ValueOperations<Serializable, Object> operations = redisTemplate.opsForValue();
        operations.set(key, value);
        return redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 设置key过期时间
     * @param key
     * @param seconds
     */
    public static void expire(String key, long seconds) {
        redisTemplate.expire(key, seconds, TimeUnit.SECONDS);
    }

    /**
     * 往list中批量加入值
     *
     * @param key
     * @param values
     * @param expireTime
     */
    public static void rightPushAll(String key, Collection<?> values, Long expireTime) {
        redisTemplate.opsForList().rightPushAll(key, values);
        redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
    }

    /**
     * 往list中批量加入值
     *
     * @param key
     * @param values
     */
    public static void rightPushAll(String key, Collection<?> values) {
        redisTemplate.opsForList().rightPushAll(key, values);
    }

    /**
     * 获取list中的全部值
     * @param key
     * @return
     */
    public static List<?> getAllFormList(String key) {
        return redisTemplate.opsForList().range(key, 0L, -1L);
    }

    public static void pushValueToSet(String key, Collection<?> values) {
        redisTemplate.opsForSet().add(key, values);
    }

    public static void pushValueToSet(String key, Object value) {
        redisTemplate.opsForSet().add(key, value);
    }

    public static boolean containsSetValue(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    public static long incr(String key, int step) {
        return redisTemplate.opsForValue().increment(key, step);
    }

    public static long decr(String key, int step) {
        return redisTemplate.opsForValue().increment(key, -step);
    }

    public static long hashIncr(String key, String hashKey, long step) {
        return redisTemplate.opsForHash().increment(key, hashKey, step);
    }

    public static void hashSet(String key, String hashKey, Object value) {
        redisTemplate.opsForHash().put(key, hashKey, value);
    }

    public static void hashSetAll(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    public static Object hashGet(String key, String hashKey) {
        return redisTemplate.opsForHash().get(key, hashKey);
    }

    public static boolean hashHasKey(String key, String hashKey) {
        return redisTemplate.opsForHash().hasKey(key, hashKey);
    }

    public static boolean putIfAbsent(String key, String hashKey, Object value) {
        return redisTemplate.opsForHash().putIfAbsent(key, hashKey, value);
    }

    public static void hashRemove(String key, String hashKey) {
        redisTemplate.opsForHash().delete(key, hashKey);
    }

    public static Map<Object, Object> hashGetCollection(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * 如果key不存在，设置成功，否则设置失败
     *
     * @param key
     * @param value
     * @return
     */
    public static boolean setNX(String key, Object value) {
        return redisTemplate.opsForValue().setIfAbsent(key, value);
    }

    /**
     * 如果key不存在，设置成功并且设置超时时间 ，否则设置失败
     *
     * @param key
     * @param value
     * @param seconds 秒数
     * @return
     */
    public static boolean setNXAndExpire(String key, Object value, Long seconds) {
        boolean flag = setNX(key, value);
        if (flag) {
            RedisUtil.expire(key, seconds);
        }
        return flag;
    }

    /**
     * redis消息队列
     * @param message
     */
    public static void sendMessage(Object message){
        redisTemplate.convertAndSend(readRequestTopic,message);
    }

    /**
     * redis消息队列
     * @param message
     */
    public static void sendMessage(String topic,Object message){
        redisTemplate.convertAndSend(topic,message);
    }

    /**
     * redis zset存入集合
     * @param key
     * @param tuples
     * @return
     */
    public static Long zsetAddAll(String key, Set<ZSetOperations.TypedTuple<Object>> tuples){
        return redisTemplate.opsForZSet().add(key, tuples);
    }

    /**
     * redis 分页取出数据
     * @param key
     * @param start
     * @param end
     * @return
     */
    public static Set<Object> zsetRange(String key, int start, int end){
        return redisTemplate.opsForZSet().range(key,start,end);
    }

    /**
     * redis 移除一个或多个成员
     * @param key
     * @param values
     * @return
     */
    public static Long zsetRemove(String key, Object... values){
        return redisTemplate.opsForZSet().remove(key,values);
    }

}
