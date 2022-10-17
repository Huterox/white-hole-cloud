package com.huterox.whitehole.whiteholemessage;

import com.huterox.whitehole.whiteholemessage.annotation.EnableNettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
@EnableNettyServer
public class WhiteholeMessageApplication {

    public static void main(String[] args) {
        SpringApplication.run(WhiteholeMessageApplication.class, args);
    }

}
