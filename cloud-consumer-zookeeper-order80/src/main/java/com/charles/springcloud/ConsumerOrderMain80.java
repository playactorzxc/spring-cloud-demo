package com.charles.springcloud;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ConsumerOrderMain80 {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderMain80.class,args);
    }
}