package com.luck.cloud.gateway.config;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.common.enums.AuthCodeEnum;
import com.luck.cloud.redis.utils.RedisCacheUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authorization.AuthorizationDecision;
import org.springframework.security.authorization.ReactiveAuthorizationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.server.authorization.AuthorizationContext;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;
import org.springframework.util.CollectionUtils;
import reactor.core.publisher.Mono;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 鉴权
 * @author luck
 * @date 2021/3/11 13:10
 */
@Component
@Slf4j
public class AuthorizationManager implements ReactiveAuthorizationManager<AuthorizationContext> {

    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Mono<AuthorizationDecision> check(Mono<Authentication> authentication, AuthorizationContext authorizationContext) {
        ServerHttpRequest request = authorizationContext.getExchange().getRequest();
        String path = request.getURI().getPath();
        // 获取访问资源所需的角色
        List<String> authorizedRoles = new ArrayList<>();
        // 拥有访问权限的角色
        // roleMap存储的是url:roles
        Map<Object, Object> urlRoleMap = RedisCacheUtils.get("roleMap");
        if(urlRoleMap != null){
            for (Map.Entry<Object, Object> entry : urlRoleMap.entrySet()) {
                String permissionUrl = (String) entry.getKey();
                List<String> roles = JSON.parseArray((String) entry.getValue(), String.class);
                if (antPathMatcher.match(permissionUrl, path)) {
                    authorizedRoles.addAll(roles);
                }
            }
        }
        // 没有配置权限规则表示无需授权，直接放行
        if (CollectionUtils.isEmpty(authorizedRoles)) {
            return Mono.just(new AuthorizationDecision(true));
        }
        return authentication
                .filter(Authentication::isAuthenticated)
                .flatMapIterable(Authentication::getAuthorities)
                .map(GrantedAuthority::getAuthority)
                .any(authorizedRoles::contains)
                .map(AuthorizationDecision::new)
                .defaultIfEmpty(new AuthorizationDecision(false));
    }

    @Override
    public Mono<Void> verify(Mono<Authentication> authentication, AuthorizationContext object) {
        return check(authentication, object)
                .filter(AuthorizationDecision::isGranted)
                .switchIfEmpty(Mono.defer(() -> {
                    String body = JSONObject.toJSONString(ResultVO.error(AuthCodeEnum.PERMISSION_DENIED.getCode()));
                    return Mono.error(new AccessDeniedException(body));
                })).flatMap(d -> Mono.empty());
    }
}
