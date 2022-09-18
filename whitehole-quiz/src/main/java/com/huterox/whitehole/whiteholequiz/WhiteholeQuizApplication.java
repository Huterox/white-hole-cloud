package com.huterox.whitehole.whiteholequiz;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.huterox.whitehole.whiteholequiz.dao")
@EnableDiscoveryClient
@EnableFeignClients("com.huterox.whitehole.whiteholequiz.feign")
@SpringBootApplication
public class WhiteholeQuizApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteholeQuizApplication.class, args);
    }

}
