package com.huterox.whitehole.whiteholequiz.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

@Configuration
public class ThreadConfig {
//    每一个服务我们这边定义是1W个，多了可能顶不住
    @Bean
    public ThreadPoolExecutor threadPoolExecutor(ThreadPoolConfig poolConfig){
        return new ThreadPoolExecutor(
                poolConfig.getCoreSize(),
                poolConfig.getMaxSize(),
                poolConfig.getKeepAliveTime(),
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(10000),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }
}
