package com.charles.springcloud.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignOrderConfig {
    @Bean
    Logger.Level feignLoggerLever() {
        return Logger.Level.FULL;
    }
}
