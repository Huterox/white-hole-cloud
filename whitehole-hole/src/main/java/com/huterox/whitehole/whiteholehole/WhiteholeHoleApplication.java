package com.huterox.whitehole.whiteholehole;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.huterox.whitehole.whiteholehole.dao")
@EnableDiscoveryClient
@EnableFeignClients(basePackages = {
        "com.huterox.whiteholecould.feign.user",
        "com.huterox.whiteholecould.feign.message",
}
)
@EnableTransactionManagement
@EnableCaching
@SpringBootApplication
public class WhiteholeHoleApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteholeHoleApplication.class, args);
    }

}
