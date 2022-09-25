package com.cloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

/**
 * gateway 全局过滤器
 *
 * @author Qiq
 */
@Slf4j
@Order(0)
@Component
public class GatewayGlobalFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        log.info("进入到GateWay网关，进入全局过滤器");
        ServerHttpRequest request = exchange.getRequest();
        log.info("请求地址：" + request.getPath());
        ServerHttpResponse response = exchange.getResponse();
        log.info("返回code：" + response.getStatusCode());
        return chain.filter(exchange);
    }

}
