package com.charles.springcloud.controller;

import com.charles.springcloud.entities.CommonResult;
import com.charles.springcloud.entities.Payment;
import com.charles.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    PaymentService paymentService;

    @PostMapping(value = "/payment/create")
    public CommonResult<Payment> create(@RequestBody Payment payment) {
        log.info("payment create " + payment);
        int index = paymentService.create(payment);
        log.info("create result index : " + index);
        if (index > 0) {
            return new CommonResult<>(200, "create success", payment);
        } else {
            return new CommonResult<>(444, "create failed", payment);
        }
    }

    @GetMapping(value = "/payment/get/{id}")
    public CommonResult<Payment> getPaymentBuId(@PathVariable("id") Long id) {
        Payment payment = paymentService.getPaymentById(id);
        log.info("get payment id : " + id + ", result : " + payment);
        if (payment != null) {
            return new CommonResult<>(200, "create success", payment);
        } else {
            return new CommonResult<>(444, "create failed, id: " + id, null);
        }
    }


}
