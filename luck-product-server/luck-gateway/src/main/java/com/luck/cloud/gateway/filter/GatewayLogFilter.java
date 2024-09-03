package com.luck.cloud.gateway.filter;


import com.luck.cloud.core.service.ILogBaseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * 日志过滤器
 * @author luck
 * @Date 2024/8/1
 */
@Slf4j
@Component
public class GatewayLogFilter implements GlobalFilter, Ordered {

    @Autowired
    private ILogBaseService logBaseService;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        exchange.getRequest().getQueryParams();
        logBaseService.writeLog(exchange.getRequest());
        return chain.filter(exchange);
    }

    @Override
    public int getOrder() {
        return -10;
    }
}
