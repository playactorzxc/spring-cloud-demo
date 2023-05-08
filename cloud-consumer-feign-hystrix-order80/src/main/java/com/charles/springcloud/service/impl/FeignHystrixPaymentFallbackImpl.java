package com.charles.springcloud.service.impl;

import com.charles.springcloud.service.FeignHystrixPaymentService;
import org.springframework.stereotype.Service;

@Service
public class FeignHystrixPaymentFallbackImpl implements FeignHystrixPaymentService {
    @Override
    public String testFallbackOk(Long id) {
        return "FeignHystrixPaymentFallbackImpl testFallbackOk : " + id;
    }

    @Override
    public String testFallbackTimeout(Long id) {
        return "FeignHystrixPaymentFallbackImpl testFallbackTimeout: " + id;
    }

    @Override
    public String testCircuitBreak(Long id) {
        return "FeignHystrixPaymentFallbackImpl testCircuitBreak: " + id;
    }
}
