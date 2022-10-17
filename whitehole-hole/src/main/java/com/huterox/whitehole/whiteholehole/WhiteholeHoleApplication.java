package com.huterox.whitehole.whiteholehole;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.huterox.whitehole.whiteholehole.dao")
@EnableDiscoveryClient
//@EnableFeignClients("com.huterox.whitehole.whiteholehole.feign")
@SpringBootApplication
public class WhiteholeHoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteholeHoleApplication.class, args);
    }

}
