package com.huterox.whiteholecould;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@EnableFeignClients(basePackages = "com.huterox.whiteholecould.feign")
@SpringBootApplication

public class WhiteholeOpenfeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(WhiteholeOpenfeignApplication.class, args);
	}

}
