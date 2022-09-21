package com.cloud.server;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author Qiq
 */
@FeignClient("web-server")
public interface WebServer {

    /**
     * 获取服务 /info 端点
     *
     * @return string
     */
    @GetMapping("/web/actuator/info")
    String getInfo();

    /**
     * 获取服务 /health 端点
     *
     * @return string
     */
    @GetMapping("/web/actuator/health")
    String getHealth();

}
