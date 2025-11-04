package top.wmd;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
public class CloudAuthApplication {

    @Value("${test.key}")
    private String testKey;

    public static void main(String[] args) {
        SpringApplication.run(CloudAuthApplication.class, args);
    }

    @PostConstruct
    public void init() {
        System.out.println("test.key: " + testKey);
    }

}
