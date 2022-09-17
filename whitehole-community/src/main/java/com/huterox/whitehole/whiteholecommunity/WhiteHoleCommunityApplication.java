package com.huterox.whitehole.whiteholecommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@MapperScan("com.huterox.whitehole.whiteholecommunity.dao")
public class WhiteHoleCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteHoleCommunityApplication.class, args);
    }

}
