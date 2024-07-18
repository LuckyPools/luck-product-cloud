package com.luck.cloud.common.utils;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 *
 * @author luck
 * @Date 2024/7/18
 */
@Slf4j
@Component
public class SpringBeanUtils implements ApplicationContextAware, DisposableBean {
    private static ApplicationContext applicationContext;
    private static Environment environment;

    @Override
    public  void setApplicationContext(ApplicationContext applicationContext){
        SpringBeanUtils.applicationContext = applicationContext;
        SpringBeanUtils.environment = applicationContext.getEnvironment();
    }

    public static ApplicationContext getApplicationContext(){
        return applicationContext;
    }

    public static Environment getEnvironment() {
        return environment;
    }

    /**
     * 通过类名从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型
     *
     * @param name
     * @param <T>
     */
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    /**
     * 通过类型从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型
     *
     * @param requiredType
     * @param <T>
     */
    public static <T> T getBean(Class<T> requiredType) {
        return applicationContext.getBean(requiredType);
    }

    /**
     * 通过类型从静态变量applicationContext中取得Bean, 自动转型为所赋值对象的类型
     *
     * @param requiredType
     * @param <T>
     */
    public static <T> List<T> getBeans(Class<T> requiredType) {
        List<T> beanList = new ArrayList<>();
        Map<String, T> beanMap = applicationContext.getBeansOfType(requiredType);
        Set<Map.Entry<String, T>> set = beanMap.entrySet();
        Iterator<Map.Entry<String, T>> it = set.iterator();
        Map<String, T> data = new HashMap<>();
        while (it.hasNext()) {
            Map.Entry<String, T> entry = it.next();
            beanList.add(entry.getValue());
        }
        return beanList;
    }

    @Override
    public void destroy() throws Exception {
        log.debug("清除SpringContextHolder中的ApplicationContext:" + applicationContext);
        applicationContext = null;
    }
}
