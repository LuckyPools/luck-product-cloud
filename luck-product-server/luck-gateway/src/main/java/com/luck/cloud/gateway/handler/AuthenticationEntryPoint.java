package com.luck.cloud.gateway.handler;

import com.alibaba.fastjson.JSONObject;
import com.luck.cloud.base.vo.ResultVO;
import com.luck.cloud.common.enums.AuthCodeEnum;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.server.ServerAuthenticationEntryPoint;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * @author luck
 * @version 1.0.0
 * @date 2021/3/11 15:17
 * @description 未认证处理
 */
@Component
public class AuthenticationEntryPoint implements ServerAuthenticationEntryPoint {

    @Override
    public Mono<Void> commence(ServerWebExchange exchange, AuthenticationException ex) {
        return Mono.defer(() -> Mono.just(exchange.getResponse())).flatMap(response -> {
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            String result = JSONObject.toJSONString(ResultVO.error(AuthCodeEnum.USER_UNAUTHORIZED.getCode()));
            DataBuffer buffer = dataBufferFactory.wrap(result.getBytes(
                    Charset.defaultCharset()));
            return response.writeWith(Mono.just(buffer));
        });
    }
}

