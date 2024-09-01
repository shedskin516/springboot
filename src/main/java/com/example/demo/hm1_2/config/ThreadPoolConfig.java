package com.example.demo.hm1_2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Configuration
public class ThreadPoolConfig {

    @Bean
    public ExecutorService cachedThreadPool() {
        return Executors.newFixedThreadPool(20);
    }
}
