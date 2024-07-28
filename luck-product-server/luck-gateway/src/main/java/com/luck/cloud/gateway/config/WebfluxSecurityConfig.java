package com.luck.cloud.gateway.config;

import cn.hutool.core.util.ArrayUtil;
import com.luck.cloud.gateway.handler.AccessDeniedHandler;
import com.luck.cloud.gateway.handler.AuthenticationEntryPoint;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.LinkedList;

/**
 * webflux security核心配置类
 * @author luck
 * @date 2021/3/11 10:56
 * WebfluxSecurityConfig
 */
@EnableWebFluxSecurity
@Configuration
@Slf4j
public class WebfluxSecurityConfig {

    @Autowired
    private AuthorizationManager authorizationManager;

    @Autowired
    private TokenAuthenticationManager tokenAuthenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private WhiteUrlsConfig whiteUrlsConfig;


    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
            // 认证处理
            .authenticationManager(reactiveAuthenticationManager())
            .securityContextRepository(securityContextRepository)
            // 请求拦截处理
            .authorizeExchange(exchange -> exchange
                    .pathMatchers(ArrayUtil.toArray(whiteUrlsConfig.getUrls(),String.class)).permitAll()
                    .pathMatchers(HttpMethod.OPTIONS).permitAll()
                    .anyExchange().access(authorizationManager)
            )
            .formLogin()
            .and()
            .exceptionHandling()
            .authenticationEntryPoint(authenticationEntryPoint)
            .and()
            .exceptionHandling()
            // 权限不足处理
            .accessDeniedHandler(accessDeniedHandler)
            .and()
            .csrf().disable()
        ;
        return httpSecurity.build();
    }

    /**
     * 注册用户信息验证管理器，可按需求添加多个按顺序执行
     */
    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager() {
        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();
        managers.add(authentication -> Mono.empty());
        managers.add(tokenAuthenticationManager);
        return new DelegatingReactiveAuthenticationManager(managers);
    }

}

