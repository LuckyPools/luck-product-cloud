package com.luck.cloud.gateway.config;

import cn.hutool.core.map.MapUtil;
import com.luck.cloud.common.utils.JwtUtils;
import com.luck.cloud.common.utils.MapUtils;
import com.luck.cloud.redis.utils.RedisCacheUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.Collection;

/**
 * 校验令牌是否有效，比对登录信息
 * @author luck
 * @date 2021/3/11 13:23
 */
@Component
@Primary
public class TokenAuthenticationManager implements ReactiveAuthenticationManager {

    @Override
    @SuppressWarnings("unchecked")
    public Mono<Authentication> authenticate(Authentication authentication) {
        return Mono.just(authentication)
                .map(auth -> JwtUtils.parseJwtRsa256(auth.getPrincipal().toString()))
                .map(claims -> {
                    String userId = MapUtil.getStr(claims, "userId");
                    UserDetails userDetails = RedisCacheUtils.get("user:" + userId);
                    Collection<? extends GrantedAuthority> roles = userDetails.getAuthorities();
                    return new UsernamePasswordAuthenticationToken(
                            claims.getSubject(),
                            null,
                            roles
                    );
                });
    }
}

