package com.luck.cloud.common.utils;


import com.alibaba.fastjson.JSONObject;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import org.springframework.util.DigestUtils;

import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * uuid工具类
 *
 * @author luck
 * @date 2024-03-14
 */
public abstract class UuidUtils {

    /**
     * 盐值
     */
    private static String SLAT = "354816d26912441ab280f08831c38453";

    /**
     * 路径匹配
     */
    public final static AntPathMatcher antPathMatcher = new AntPathMatcher();


    /**
     * 判断字符串是否在集合里
     * @param list
     * @param path
     * @return
     */
    public static boolean matchPath(List<String> list, String path) {
        if (CollectionUtils.isEmpty(list)) {
            return false;
        }
        if (!list.contains(path)) {
            Optional<String> any = list.stream().filter(s -> antPathMatcher.match(s, path)).findAny();
            return any.isPresent();
        }
        return true;
    }


    /**
     * 获取passkey.
     * @param id
     * @return 密码
     */
    public static String getPassKey(String id) {
        return DigestUtils.md5DigestAsHex((SLAT + "_" + id).getBytes(Charset.forName("UTF-8")));
    }


    /**
     * 获取UUID去掉横杠
     * @return
     */
    public static String getUUID() {
        return UUID.randomUUID().toString().replaceAll("\\-","");
    }

    /**
     * 将对象转换为Json字符串
     * @param object
     * @return
     */
    public static String toJSONString(Object object) {
        return JSONObject.toJSONString(object);
    }

    /**
     * 匹配关键字，替换成真正文本
     */
    public static Pattern pattern = Pattern.compile("\\$\\{(\\w+)}");

    /**
     * 格式化替换，如${username} is ${age} 替换成 admin is 24
     * @param source
     * @param map  替换参数
     * @return
     */
    public static String replaceFormatString(String source, Map<String,Object> map) {
        Matcher matcher = pattern.matcher(source);
        StringBuffer sb = new StringBuffer();
        while (matcher.find()) {
            String variable = matcher.group(1);
            Object value = map.get(variable);
            if (value != null) {
                matcher.appendReplacement(sb, String.valueOf(value));
            }

        }
        return sb.toString();
    }

    /**
     * 重载
     * @param source
     * @param param
     * @return
     */
    public static String replaceFormatString(String source, Object param) {
        if(param instanceof String){
            return replaceFormatString(source,param+"","");
        }
        if(param instanceof Integer || param instanceof Long){
            return replaceFormatString(source,param+"","");
        }
        JSONObject jsonObject = JSONObject.parseObject(JSONObject.toJSONString(param));
        return replaceFormatString(source, jsonObject);
    }

    /**
     * @param source meta:goods:code_name:${tenantCode}:${goodsOwnerCode}:${goodsCode}
     * @param args 按顺序用args替换${}
     * @return
     */
    public static String replaceFormatString(String source, String... args) {
        if(args == null || args.length == 0){
            return replaceFormatString(source, new HashMap<>());
        }

        Matcher matcher = pattern.matcher(source);
        StringBuffer sb = new StringBuffer();
        int index = 0;
        while (matcher.find()) {
            String variable = matcher.group(1);
            String value = null;
            if(index <= args.length - 1){
                value = args[index];
            }
            if (value != null) {
                matcher.appendReplacement(sb, value);
            }
            index++;
        }
        return sb.toString();
    }

}
