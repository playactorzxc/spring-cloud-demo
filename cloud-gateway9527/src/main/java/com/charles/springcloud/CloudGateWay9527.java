package com.charles.springcloud;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class CloudGateWay9527 {
    public static void main(String[] args) {
        SpringApplication.run(CloudGateWay9527.class, args);
    }
}