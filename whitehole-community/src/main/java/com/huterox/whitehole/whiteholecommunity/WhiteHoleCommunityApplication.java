package com.huterox.whitehole.whiteholecommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.huterox.whitehole.whiteholecommunity.dao")
@EnableFeignClients("com.huterox.whitehole.whiteholecommunity.feign")
public class WhiteHoleCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteHoleCommunityApplication.class, args);
    }

}
