package com.luck.cloud.auth.handler;

import com.alibaba.fastjson.JSONObject;
import com.luck.cloud.auth.entity.User;
import com.luck.cloud.auth.utils.JwtTokenUtil;
import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.common.utils.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.security.web.server.authentication.ServerAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;
import java.util.HashMap;
import java.util.Map;

/**
 * @author ShiLei
 * @version 1.0.0
 * @date 2021/3/11 15:00
 * @description 登录成功处理
 */
@Component
public class DefaultAuthenticationSuccessHandler implements ServerAuthenticationSuccessHandler {

    /**
     * token 过期时间
     */
    @Value("${jwt.token.expired}")
    private int jwtTokenExpired;

    /**
     * 刷新token 时间
     */
    @Value("${jwt.token.refresh.expired}")
    private int jwtTokenRefreshExpired;

    @Override
    public Mono<Void> onAuthenticationSuccess(WebFilterExchange webFilterExchange, Authentication authentication) {
        return Mono.defer(() -> Mono.just(webFilterExchange.getExchange().getResponse()).flatMap(response -> {
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            // 生成JWT token
            Map<String, Object> map = new HashMap<>(2);
            User user = (User) authentication.getPrincipal();
            map.put("userId", user.getId());
            map.put("username", user.getLoginName());
            // todo 权限
            map.put("roles", StringUtils.EMPTY);
            String token = JwtTokenUtil.generateToken(map, user.getLoginName(), jwtTokenExpired);
            String refreshToken = JwtTokenUtil.generateToken(map, user.getLoginName(), jwtTokenRefreshExpired);
            Map<String, Object> tokenMap = new HashMap<>(2);
            tokenMap.put("token", token);
            tokenMap.put("refreshToken", refreshToken);
            DataBuffer dataBuffer = dataBufferFactory.wrap(JSONObject.toJSONString(ResultVO.success(tokenMap)).getBytes());
            return response.writeWith(Mono.just(dataBuffer));
        }));
    }
}
