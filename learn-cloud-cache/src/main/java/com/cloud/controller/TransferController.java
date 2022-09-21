package com.cloud.controller;

import com.cloud.server.WebServer;
import com.cloud.template.WebClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 *  调用注册服务类
 *
 * @author Qiq
 */
@RestController
public class TransferController {

    @Autowired
    private WebClient webClient;

    @Autowired
    private WebServer webServer;

    @GetMapping("/webClientInfo")
    public String getWebClientInfo() throws IOException {
        String info = webClient.getInfo();
        System.out.println(info);
        return info;
    }

    @GetMapping("/webClientHealth")
    public String getWebClientHealth() {
        String health = webClient.getHealth();
        System.out.println(health);
        return health;
    }

    @GetMapping("/webHealth")
    public String getHealth() throws IOException {
        return webClient.getHealthByClient();
    }

    @GetMapping("/getByFeign")
    public String getByFeign() {
        String info = webServer.getInfo();
        String health = webServer.getHealth();
        return "{info: " + info + ",health:" + health + "}";
    }

}
