package com.luck.cloud.gateway.config;

import com.luck.cloud.common.utils.JwtUtils;
import org.springframework.context.annotation.Primary;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.Collection;

/**
 * 校验令牌是否有效
 * @author luck
 * @date 2021/3/11 13:23
 */
@Component
@Primary
public class TokenAuthenticationManager implements ReactiveAuthenticationManager {

    // todo 这里将token转化为Authentication
    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> JwtUtils.parseJwtRsa256(auth.getPrincipal().toString()))
                .map(claims -> {
                    Collection<? extends GrantedAuthority> roles = (Collection<? extends GrantedAuthority>) claims.get("roles");
                    return new UsernamePasswordAuthenticationToken(
                            claims.getSubject(),
                            null,
                            roles
                    );
                });
    }
}

