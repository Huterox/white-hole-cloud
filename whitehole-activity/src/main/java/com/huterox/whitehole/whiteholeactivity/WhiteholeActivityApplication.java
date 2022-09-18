package com.huterox.whitehole.whiteholeactivity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@MapperScan("com.huterox.whitehole.whiteholeactivity.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.huterox.whitehole.whiteholeactivity.feign")
@SpringBootApplication
public class WhiteholeActivityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteholeActivityApplication.class, args);
    }

}
