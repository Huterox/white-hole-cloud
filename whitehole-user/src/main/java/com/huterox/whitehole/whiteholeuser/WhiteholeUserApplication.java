package com.huterox.whitehole.whiteholeuser;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@MapperScan("com.huterox.whitehole.whiteholeuser.dao")
@EnableDiscoveryClient
@EnableFeignClients("com.huterox.whitehole.whiteholeuser.feign")
@SpringBootApplication
public class WhiteholeUserApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteholeUserApplication.class, args);
    }

}
