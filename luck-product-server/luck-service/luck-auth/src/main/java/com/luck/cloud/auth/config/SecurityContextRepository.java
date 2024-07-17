package com.luck.cloud.auth.config;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import javax.annotation.Resource;
import java.util.List;

/**
 * @author luck
 * @version 1.0.0
 * @date 2021/3/11 16:27
 * @description 用户信息上下文存储类
 */
@Component
public class SecurityContextRepository implements ServerSecurityContextRepository {

    public final static String TOKEN_HEADER = "Authorization";

    public final static String BEARER = "Bearer ";

    @Resource
    private TokenAuthenticationManager tokenAuthenticationManager;

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        return Mono.empty();
    }


    /**
     * todo 具体功能
     * @param exchange
     * @return
     */
    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        ServerHttpRequest request = exchange.getRequest();
        List<String> headers = request.getHeaders().get(TOKEN_HEADER);
        if (!CollectionUtils.isEmpty(headers)) {
            String authorization = headers.get(0);
            if (StringUtils.isNotEmpty(authorization)) {
                String token = authorization.substring(BEARER.length());
                if (StringUtils.isNotEmpty(token)) {
                    return tokenAuthenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(token, null)
                    ).map(SecurityContextImpl::new);
                }
            }
        }
        return Mono.empty();
    }
}
