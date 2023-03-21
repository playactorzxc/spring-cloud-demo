package com.charles.springcloud.controller;

import com.charles.springcloud.entities.CommonResult;
import com.charles.springcloud.entities.Payment;
import com.charles.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Value("${server.port}")
    private String port;

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        log.info("payment create " + payment);
        int index = paymentService.create(payment);
        log.info("create result index : " + index);
        if (index > 0) {
            return new CommonResult<>(200, "create success, port:" + port, payment);
        } else {
            return new CommonResult<>(444, "create failed", payment);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentBuId(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("get payment id : " + id + ",payment result : " + payment);
        if (payment != null) {
            return new CommonResult<>(200, "query success, port:" + port, payment);
        } else {
            return new CommonResult<>(444, "query failed, id: " + id, null);
        }
    }


}
