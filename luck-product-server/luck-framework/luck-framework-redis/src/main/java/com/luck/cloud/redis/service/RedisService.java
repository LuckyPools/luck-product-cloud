package com.luck.cloud.redis.service;

import com.luck.cloud.common.utils.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;


/**
 * redis服务封装
 * @author luck
 * @Date 2024/7/18
 */
@Slf4j
@Service
public class RedisService {

    private static final String ZSET_READ_LOCK = "ZSET_READ_LOCK";

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 根据key获取对象
     *
     * @param key the key
     * @return the string
     */
    public <T> T get(final String key) {
        log.info("[redisTemplate redis]取出 缓存  url:{} ", key);
        ValueOperations<String, T> operation = redisTemplate.opsForValue();
        return operation.get(key);
    }

    /**
     * 添加到缓存
     *
     * @param key   the key
     * @param value the value
     */
    public <T> void set(final String key, final T value) {
        redisTemplate.opsForValue().set(key, value);
        log.info("[redisTemplate redis]放入 缓存  url:{}", key);
    }

    /**
     * 添加到带有 过期时间的  缓存
     *
     * @param key   redis主键
     * @param value 值
     * @param time  过期时间
     */
    public <T> void set(final String key, final T value, final long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
        log.info("[redisTemplate redis]放入 缓存  url:{} ========缓存时间为{}秒", key, time);
    }

    /**
     * 一次性添加数组到   过期时间的  缓存，不用多次连接，节省开销
     *
     * @param keys   redis主键数组
     * @param values 值数组
     * @param time   过期时间
     */
    public void set(final String[] keys, final String[] values, final long time) {
        redisTemplate.execute((RedisCallback<Long>) connection -> {
            RedisSerializer<String> serializer = getRedisSerializer();
            for (int i = 0; i < keys.length; i++) {
                byte[] bKeys = serializer.serialize(keys[i]);
                byte[] bValues = serializer.serialize(values[i]);
                connection.set(bKeys, bValues);
                connection.expire(bKeys, time);
                log.info("[redisTemplate redis]放入 缓存  url:{} ========缓存时间为:{}秒", keys[i], time);
            }
            return 1L;
        });
    }

    /**
     * 设置key的过期时间
     * @param key
     * @param time
     * @return
     */
    public boolean setExpire(String key,long time){
        try{
            if (time > 0){
                redisTemplate.expire(key,time,TimeUnit.SECONDS);
            }
            return true;
        }catch (Exception e){
            log.error("Redis设置过期时间失败:"+e);
            return false;
        }
    }

    /**
     * 根据key 获取过期时间
     * @param key
     * @return
     */
    public long getExpire(String key){
        return redisTemplate.getExpire(key,TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public boolean exists(String key){
        return redisTemplate.hasKey(key);
    }

    /**
     * 根据key删除缓存
     * @param keys
     * @return
     */
    public boolean del(String... keys){
        try {
            for (int i = 0 ;i < keys.length;i++){
                redisTemplate.delete(keys[i]);
            }
            return true;
        }catch (Exception e){
            log.error("redis删除Key失败："+e);
            return false;
        }
    }

    /**
     * 删除key 根据前缀删除
     */
    public void delByPre(final String keyPatten) {
        Set<String> keys = redisTemplate.keys(keyPatten);
        redisTemplate.delete(keys);
    }


    /**
     * 查询在以keyPatten的所有  key
     *
     * @param keyPatten the key patten
     * @return the set
     */
    public Set<String> keys(final String keyPatten) {
        return redisTemplate.keys(keyPatten + "*");
    }

    /**
     * 获取 RedisSerializer
     *
     * @return the redis serializer
     */
    protected RedisSerializer<String> getRedisSerializer() {
        return redisTemplate.getStringSerializer();
    }

    /**
     * Ops for hash hash operations.
     *
     * @return the hash operations
     */
    public HashOperations<String, String, String> opsForHash() {
        return redisTemplate.opsForHash();
    }

    /**
     * redis List 引擎
     *
     * @return the list operations
     */
    public ListOperations<String, String> opsForList() {
        return redisTemplate.opsForList();
    }

    public ZSetOperations<String, String> opsForZSet() {
        return redisTemplate.opsForZSet();
    }

    /**
     * 对HashMap操作
     *
     * @param key       the key
     * @param hashKey   the hash key
     * @param hashValue the hash value
     */
    public void putHashValue(String key, String hashKey, String hashValue) {
        log.info("[redisTemplate redis]  putHashValue()  key={},hashKey={},hashValue={} ", key, hashKey, hashValue);
        opsForHash().put(key, hashKey, hashValue);
    }

    /**
     * 获取单个field对应的值
     *
     * @param key     the key
     * @param hashKey the hash key
     * @return the hash values
     */
    public Object getHashValues(String key, String hashKey) {
        log.info("[redisTemplate redis]  getHashValues()  key={},hashKey={}", key, hashKey);
        return opsForHash().get(key, hashKey);
    }

    /**
     * 根据key值删除
     *
     * @param key      the key
     * @param hashKeys the hash keys
     */
    public void delHashValues(String key, Object... hashKeys) {
        log.info("[redisTemplate redis]  delHashValues()  key={}", key);
        opsForHash().delete(key, hashKeys);
    }

    /**
     * key只匹配map
     *
     * @param key the key
     * @return the hash value
     */
    public Map<String, String> getHashValue(String key) {
        log.info("[redisTemplate redis]  getHashValue()  key={}", key);
        return opsForHash().entries(key);
    }

    public Map<String, String> multiGetHashValue(String key, List<String> keys) {
        Map<String, String> maps = new HashMap<>();
        List<String> value = opsForHash().multiGet(key, keys);
        int i = 0;
        for (String k : keys) {
            String v = value.get(i);
            i++;
            maps.put(k, v);
        }
        return maps;
    }

    /**
     * 批量添加
     *
     * @param key the key
     * @param map the map
     */
    public void putHashValues(String key, Map<String, String> map) {
        opsForHash().putAll(key, map);
    }

    /**
     * redis List数据结构 : 将一个或多个值 value 插入到列表 key 的表头
     *
     * @param key   the key
     * @param value the value
     * @return the long
     */
    public Long leftPush(String key, String value) {
        return opsForList().leftPush(key, value);
    }

    /**
     * redis List数据结构 : 移除并返回列表 key 的头元素
     *
     * @param key the key
     * @return the string
     */
    public String leftPop(String key) {
        return opsForList().leftPop(key);
    }

    /**
     * redis List数据结构 :将一个或多个值 value 插入到列表 key 的表尾(最右边)。
     *
     * @param key   the key
     * @param value the value
     * @return the long
     */
    public Long rightPush(String key, String value) {
        return opsForList().rightPush(key, value);
    }

    /**
     * redis List数据结构 : 移除并返回列表 key 的末尾元素
     *
     * @param key the key
     * @return the string
     */
    public String rightPop(String key) {
        return opsForList().rightPop(key);
    }


    /**
     * redis List数据结构 : 返回列表 key 的长度 ; 如果 key 不存在，则 key 被解释为一个空列表，返回 0 ; 如果 key 不是列表类型，返回一个错误。
     *
     * @param key the key
     * @return the long
     */
    public Long length(String key) {
        return opsForList().size(key);
    }


    /**
     * redis List数据结构 : 根据参数 i 的值，移除列表中与参数 value 相等的元素
     *
     * @param key   the key
     * @param i     the
     * @param value the value
     */
    public void remove(String key, long i, String value) {
        opsForList().remove(key, i, value);
    }

    /**
     * redis List数据结构 : 将列表 key 下标为 index 的元素的值设置为 value
     *
     * @param key   the key
     * @param index the index
     * @param value the value
     */
    public void set(String key, long index, String value) {
        opsForList().set(key, index, value);
    }

    /**
     * redis List数据结构 : 返回列表 key 中指定区间内的元素，区间以偏移量 start 和 end 指定。
     *
     * @param key   the key
     * @param start the start
     * @param end   the end
     * @return the list
     */
    public List<String> getList(String key, int start, int end) {
        return opsForList().range(key, start, end);
    }

    /**
     * redis List数据结构 : 批量存储
     *
     * @param key  the key
     * @param list the list
     * @return the long
     */
    public Long leftPushAll(String key, List<String> list) {
        return opsForList().leftPushAll(key, list);
    }

    /**
     * redis List数据结构 : 将值 value 插入到列表 key 当中，位于值 index 之前或之后,默认之后。
     *
     * @param key   the key
     * @param index the index
     * @param value the value
     */
    public void insert(String key, long index, String value) {
        opsForList().set(key, index, value);
    }

    public Set<String> leftPopZSet(String key, double min, double max, long offset, long count) {
        //最大6秒超时
        ValueOperations<String, String> operation = redisTemplate.opsForValue();
        String value = String.valueOf(6000 + System.currentTimeMillis());
        boolean isLock = redisTemplate.opsForValue().setIfAbsent(ZSET_READ_LOCK, value);
        if (isLock) {
            redisTemplate.expire(ZSET_READ_LOCK, 5, TimeUnit.SECONDS);
            Set<String> stringSet = redisTemplate.opsForZSet().rangeByScore(key, min
                    , max, offset, count);
            if (stringSet != null && stringSet.size() > 0) {
                redisTemplate.opsForZSet().remove(key, stringSet.toArray());
            }
            redisTemplate.delete(ZSET_READ_LOCK);
            return stringSet;
        } else {
            //判断锁超时,防止死锁
            String currentValue = operation.get(ZSET_READ_LOCK);
            //手动校检 如果锁过期
            if (!StringUtils.isEmpty(currentValue) && Long.parseLong(currentValue) < System.currentTimeMillis()) {
                //设置直接过期删除
                redisTemplate.delete(ZSET_READ_LOCK);
            }
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return leftPopZSet(key, min, max, offset, count);
        }
    }

    public Set<String> getZSetValue(String key, long start, long end) {
        return redisTemplate.opsForZSet().rangeByScore(key, start, end);
    }

    public void addZSet(String key, String value, double delta) {
        redisTemplate.opsForZSet().add(key, value, delta);
    }

    public void addZSetList(String key, Set<ZSetOperations.TypedTuple<String>> sets) {
        redisTemplate.opsForZSet().add(key, sets);
    }
}


