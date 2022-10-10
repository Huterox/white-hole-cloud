package com.huterox.whitehole.whiteholesearch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
public class WhiteholeSearchApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteholeSearchApplication.class, args);
    }

}
