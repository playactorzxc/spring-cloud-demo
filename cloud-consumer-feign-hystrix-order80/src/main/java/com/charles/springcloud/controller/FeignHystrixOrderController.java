package com.charles.springcloud.controller;


import com.charles.springcloud.service.FeignHystrixPaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class FeignHystrixOrderController {

    @Autowired
    FeignHystrixPaymentService feignHystrixPaymentService;


    @GetMapping(value = "/consumer/payment/fallback/ok/{id}")
    String testFallbackOk(@PathVariable("id") Long id) {
    return feignHystrixPaymentService.testFallbackOk(id);
    }

    @HystrixCommand(fallbackMethod = "fallbackTimeout", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "2000")
    })
    @GetMapping(value = "/consumer/payment/fallback/timeout/{id}")
    String testFallbackTimeout(@PathVariable("id") Long id) {
        return feignHystrixPaymentService.testFallbackTimeout(id);
    }

    @HystrixCommand(fallbackMethod = "circuitBreak", commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),// 请求次数
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "10000"), // 时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "60"),// 失败率达到多少后跳闸
//            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")// 超时处理
    })
    @GetMapping(value = "/consumer/payment/circuit/break/{id}")
    String testCircuitBreak(@PathVariable("id") Long id) {
        return feignHystrixPaymentService.testCircuitBreak(id);
    }

    String fallbackTimeout(Long id) {
        return "timeout fallback :" + id;
    }

    String circuitBreak(Long id) {
        return "circuit break : " + id;
    }

}
