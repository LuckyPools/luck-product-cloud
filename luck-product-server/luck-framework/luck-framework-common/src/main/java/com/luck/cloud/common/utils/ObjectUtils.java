/**
 * Copyright (c) 2013-Now http://jeesite.com All rights reserved.
 */
package com.luck.cloud.common.utils;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.springframework.beans.BeanUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 对象操作工具类, 继承org.apache.commons.lang3.ObjectUtils类
 *
 * @author luck
 * @date 2023-10-26
 */
public class ObjectUtils extends org.apache.commons.lang3.ObjectUtils {

    /**
     * 转换为Double类型
     */
    public static Double toDouble(final Object val) {
        if (val == null) {
            return 0D;
        }
        try {
            return NumberUtils.toDouble(StringUtils.trim(val.toString()));
        } catch (Exception e) {
            return 0D;
        }
    }

    /**
     * 转换为Float类型
     */
    public static Float toFloat(final Object val) {
        return toDouble(val).floatValue();
    }

    /**
     * 转换为Long类型
     */
    public static Long toLong(final Object val) {
        return toDouble(val).longValue();
    }

    /**
     * 转换为Integer类型
     */
    public static Integer toInteger(final Object val) {
        return toLong(val).intValue();
    }

    /**
     * 转换为Boolean类型 'true', 'on', 'y', 't', 'yes' or '1' (case insensitive) will return true. Otherwise, false is returned.
     */
    public static Boolean toBoolean(final Object val) {
        if (val == null) {
            return false;
        }
        return BooleanUtils.toBoolean(val.toString()) || "1".equals(val.toString());
    }


    /**
     * 空转空字符串（"" to "" ; null to "" ; "null" to "" ; "NULL" to "" ; "Null" to ""）
     *
     * @param val 需转换的值
     * @return 返回转换后的值
     */
    public static String toStringIgnoreNull(final Object val) {
        return ObjectUtils.toStringIgnoreNull(val, StringUtils.EMPTY);
    }

    /**
     * 空对象转空字符串 （"" to defaultVal ; null to defaultVal ; "null" to defaultVal ; "NULL" to defaultVal ; "Null" to defaultVal）
     *
     * @param val        需转换的值
     * @param defaultVal 默认值
     * @return 返回转换后的值
     */
    public static String toStringIgnoreNull(final Object val, String defaultVal) {
        String str = Objects.toString(val);
        return !"".equals(str) && !"null".equals(str.trim().toLowerCase()) ? str : defaultVal;
    }

    /**
     * 拷贝一个对象（但是子对象无法拷贝）
     *
     * @param source
     * @param ignoreProperties
     */
    public static Object copyBean(Object source, String... ignoreProperties) {
        if (source == null) {
            return null;
        }
        Object target = BeanUtils.instantiateClass(source.getClass());
        BeanUtils.copyProperties(source, target, ignoreProperties);
        return target;
    }

    /**
     * 注解到对象复制，只复制能匹配上的方法。 硕正组件用。
     *
     * @param annotation
     * @param object
     */
    public static void annotationToObject(Object annotation, Object object) {
        if (annotation != null && object != null) {
            Class<?> annotationClass = annotation.getClass();
            Class<?> objectClass = object.getClass();
            for (Method m : objectClass.getMethods()) {
                if (StringUtils.startsWith(m.getName(), "set")) {
                    try {
                        String s = StringUtils.uncapitalize(StringUtils.substring(m.getName(), 3));
                        Object obj = annotationClass.getMethod(s).invoke(annotation);
                        if (obj != null && !"".equals(obj.toString())) {
                            m.invoke(object, obj);
                        }
                    } catch (Exception e) {
                        // 忽略所有设置失败方法
                    }
                }
            }
        }
    }
}
