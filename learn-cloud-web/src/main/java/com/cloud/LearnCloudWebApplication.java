package com.cloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.mongo.MongoDataAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;


/**
 * @author Qiq
 */
@ServletComponentScan
@SpringBootApplication(exclude = {
        DataSourceAutoConfiguration.class,
        MongoDataAutoConfiguration.class,
        MongoAutoConfiguration.class,
})
public class LearnCloudWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(LearnCloudWebApplication.class, args);
    }

}
