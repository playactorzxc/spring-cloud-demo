package com.charles.springcloud.service;

import com.charles.springcloud.service.impl.FeignHystrixPaymentFallbackImpl;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(value = "cloud-payment-service",fallback = FeignHystrixPaymentFallbackImpl.class)
public interface FeignHystrixPaymentService {
    @GetMapping(value = "/payment/fallback/ok/{id}")
    String testFallbackOk(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/fallback/timeout/{id}")
    String testFallbackTimeout(@PathVariable("id") Long id);

    @GetMapping(value = "/payment/circuit/break/{id}")
    String testCircuitBreak(@PathVariable("id") Long id);
}
