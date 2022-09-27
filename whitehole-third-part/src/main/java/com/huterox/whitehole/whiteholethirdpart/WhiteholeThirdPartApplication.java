package com.huterox.whitehole.whiteholethirdpart;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class WhiteholeThirdPartApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteholeThirdPartApplication.class, args);
    }

}
