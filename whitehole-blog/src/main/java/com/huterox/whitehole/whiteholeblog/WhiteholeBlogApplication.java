package com.huterox.whitehole.whiteholeblog;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.huterox.whitehole.whiteholeblog.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.huterox.whitehole.whiteholeblog.feign")
@SpringBootApplication
public class WhiteholeBlogApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteholeBlogApplication.class, args);
    }

}
