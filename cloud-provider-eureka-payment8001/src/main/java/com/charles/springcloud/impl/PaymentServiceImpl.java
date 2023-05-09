package com.charles.springcloud.impl;

import com.charles.springcloud.dao.PaymentDao;
import com.charles.springcloud.entities.Payment;
import com.charles.springcloud.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Autowired
    private PaymentDao paymentDao;


    @Override
    public int create(Payment payment) {
        return paymentDao.create(payment);
    }

    @Override
    public Payment getPaymentById(Long id) {
        return paymentDao.getPaymentById(id);
    }

    @Override
    public String testFallbackOk(Long id) {
        return "testFallbackOk : " + Thread.currentThread().getName();
    }

    @Override
    public String testFallbackTimeout(Long id) {
        try {
            TimeUnit.MILLISECONDS.sleep(3000);
        } catch (Exception ignored) {
        }
        return "testFallbackTimeout : " + Thread.currentThread().getName();
    }

    @Override
    public String testCircuitBreak(Long id) {
        return "testCircuitBreak : " + Thread.currentThread().getName();
    }
}
