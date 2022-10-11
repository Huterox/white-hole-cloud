package com.huterox.whitehole.whiteholecommunity;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@MapperScan("com.huterox.whitehole.whiteholecommunity.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {"com.huterox.whiteholecould.feign.user"})
@SpringBootApplication
@EnableTransactionManagement
@EnableCaching
public class WhiteHoleCommunityApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteHoleCommunityApplication.class, args);
    }

}
