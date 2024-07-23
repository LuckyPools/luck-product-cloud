package com.luck.cloud.redis.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.luck.cloud.common.utils.SpringBeanUtils;
import com.luck.cloud.common.utils.StringUtils;
import com.luck.cloud.redis.service.RedisService;

import java.util.List;

/**
 * redis缓存操作工具类
 * @author luck
 * @Date 2024/7/18
 */
public class RedisCacheUtils {

    private static final class Static {
        private static RedisService getRedisService() {
            return SpringBeanUtils.getBean(RedisService.class);
        }

        /**
         * todo 区分不同环境的前缀
         * 获取redis系统缓存前缀
         * 缓存命名空间：项目编码:环境编码:大版本号
         */
        private static String getCachePrefix() {
            return "redis" + ":";
        }
    }

    public static <T> T get(String key) {
        return Static.getRedisService().get(Static.getCachePrefix() + key);
    }

    public static <T> T get(String key, Class<T> clazz) {
        String str = get(key);
        if(StringUtils.isNotEmpty(str)){
            String objStr = (String) JSON.parse(str);
            return JSONObject.parseObject(objStr, clazz);
        }
        return null;
    }

    public static <T> void put(String key, T value) {
        String str;
        if( value instanceof List){
            str = JSONArray.toJSONString(value);
        } else {
            str = JSONObject.toJSONString(value);
        }
        Static.getRedisService().set(Static.getCachePrefix() + key, str);
    }

    public static <T> void put(String key, T value, long time) {
        String str;
        if( value instanceof List){
            str = JSONArray.toJSONString(value);
        } else {
            str = JSONObject.toJSONString(value);
        }
        Static.getRedisService().set(Static.getCachePrefix() + key, str, time);
    }

    /**
     * 判断key是否存在
     * @param key
     * @return
     */
    public static boolean exists(String key){
        return Static.getRedisService().exists(Static.getCachePrefix() + key);
    }

    public static void remove(String key) {
        Static.getRedisService().del(Static.getCachePrefix() + key);
    }

    /**
     * 获取系统缓存前缀
     */
    public static String getCachePrefix() {
        return Static.getCachePrefix();
    }
}
