package com.luck.cloud.auth.config;

import com.luck.cloud.auth.handler.*;
import com.luck.cloud.auth.service.ILoginUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.DelegatingReactiveAuthenticationManager;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UserDetailsRepositoryReactiveAuthenticationManager;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;
import java.util.LinkedList;

/**
 * @author luck
 * @version 1.0.0
 * @date 2021/3/11 10:56
 * @description webflux security核心配置类
 */
@EnableWebFluxSecurity
@Configuration
@Slf4j
public class WebfluxSecurityConfig {

    @Autowired
    private AuthorizationManager authorizationManager;

    @Autowired
    private AuthenticationSuccessHandler authenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler authenticationFailureHandler;

    @Autowired
    private TokenAuthenticationManager tokenAuthenticationManager;

    @Autowired
    private SecurityContextRepository securityContextRepository;

    @Autowired
    private AuthenticationEntryPoint authenticationEntryPoint;

    @Autowired
    private AccessDeniedHandler accessDeniedHandler;

    @Autowired
    private ILoginUserService userService;


    /**
     * 自定义过滤权限
     */
    @Value("${security.noFilter}")
    private String noFilter;

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity httpSecurity) {
        httpSecurity
                // 登录认证处理
                .authenticationManager(reactiveAuthenticationManager())
                .securityContextRepository(securityContextRepository)
                // 请求拦截处理
                .authorizeExchange(exchange -> exchange
                        .pathMatchers(noFilter).permitAll()
                        .pathMatchers(HttpMethod.OPTIONS).permitAll()
                        .anyExchange().access(authorizationManager)
                )
                .formLogin().loginPage("/login")
                // 自定义处理
                .authenticationSuccessHandler(authenticationSuccessHandler)
                .authenticationFailureHandler(authenticationFailureHandler)
                .and()
                .exceptionHandling()
                .authenticationEntryPoint(authenticationEntryPoint)
                .and()
                .exceptionHandling()
                .accessDeniedHandler(accessDeniedHandler)
                .and()
                .csrf().disable()
        ;
        return httpSecurity.build();
    }

    /**
     * BCrypt密码编码
     */
    @Bean("passwordEncoder")
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    /**
     * 注册用户信息验证管理器，可按需求添加多个按顺序执行
     */
    @Bean
    ReactiveAuthenticationManager reactiveAuthenticationManager() {
        LinkedList<ReactiveAuthenticationManager> managers = new LinkedList<>();
        managers.add(authentication -> {
            // 其他登陆方式 (比如手机号验证码登陆) 可在此设置不得抛出异常或者 Mono.error
            return Mono.empty();
        });
        // 必须放最后不然会优先使用用户名密码校验但是用户名密码不对时此 AuthenticationManager 会调用 Mono.error 造成后面的 AuthenticationManager 不生效
        managers.add(new UserDetailsRepositoryReactiveAuthenticationManager(userService));
        managers.add(tokenAuthenticationManager);
        return new DelegatingReactiveAuthenticationManager(managers);
    }
}

