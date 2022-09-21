package com.cloud.template;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.cloud.client.loadbalancer.LoadBalancerRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;

/**
 * 访问 eureka client 的几种方式
 *
 * @author Qiq
 */
@Component
public class WebClient {

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private LoadBalancerClient loadBalancerClient;

    public String getInfo() throws IOException {
        String baseUrl = "http://localhost:8001/web";
        URL url = new URL(baseUrl + "/actuator/health");
        InputStream inputStream = url.openStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = bufferedReader.readLine();
        System.out.println(line);
        /// 可能是引入feign之后restTemplate发出的请求被feign代理了，会自动寻找instance.name = localhost的服务，结果找不到，返回404，使用普通url请求则可以正常返回
//        return restTemplate.getForObject(baseUrl + "/actuator/health", String.class);
        return line;
    }

    public String getHealth() {
        String baseUrl = "http://web-server:8001/web";
        return restTemplate.getForObject(baseUrl + "/actuator/health", String.class);
    }

    public String getHealthByClient() throws IOException {
        String url = String.format("http://%s/web/actuator/health", "web-server");
        LoadBalancerRequest<String> loadBalancerRequest = server -> restTemplate.getForObject(url, String.class);
        String execute = loadBalancerClient.execute("web-server", loadBalancerRequest);
        System.out.println(execute);
        return execute;
    }

}
