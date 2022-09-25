package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author Qiq
 */
@EnableEurekaClient
@SpringBootApplication
public class LearnCloudGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnCloudGatewayApplication.class, args);
    }

}
