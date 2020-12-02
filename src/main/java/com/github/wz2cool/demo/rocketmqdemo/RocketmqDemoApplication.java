package com.github.wz2cool.demo.rocketmqdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
public class RocketmqDemoApplication {

    public static void main(String[] args) {

        SpringApplication.run(RocketmqDemoApplication.class, args);
    }

}
