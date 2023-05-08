package com.charles.springcloud.service;

import com.charles.springcloud.entities.Payment;
import org.apache.ibatis.annotations.Param;

public interface PaymentService {

    int create(Payment payment);

    Payment getPaymentById(@Param("id") Long id);


    String testFallbackOk(@Param("id") Long id);

    String testFallbackTimeout(@Param("id") Long id);

    String testCircuitBreak(@Param("id") Long id);
}
