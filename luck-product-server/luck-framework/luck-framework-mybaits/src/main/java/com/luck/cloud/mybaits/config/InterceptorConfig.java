package com.luck.cloud.mybaits.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 拦截器注册
 *
 * @author luck
 * @Date 2024/7/4
 **/
@Configuration
public class InterceptorConfig {

    @Value("${jdbc.type}")
    private String dbType;

    /**
     * 分页插件
     */
    @Bean
    public PaginationInnerInterceptor paginationInnerInterceptor() {
        return new PaginationInnerInterceptor(DbType.getDbType(dbType));
    }
}
