package top.wmd;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.mybatis.spring.annotation.MapperScan;

@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
@RefreshScope
@MapperScan("top.wmd.mapper")
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
