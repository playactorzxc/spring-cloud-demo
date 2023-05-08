package com.charles.springcloud.controller;

import com.charles.springcloud.entities.CommonResult;
import com.charles.springcloud.entities.Payment;
import com.charles.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import sun.nio.ch.ThreadPool;

import java.util.List;

@RestController
@Slf4j
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String port;

    @Value("${spring.application.name}")
    private String serverName;

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
            return new CommonResult<>(200, "create success, port:" + port, payment);
        } else {
            return new CommonResult<>(444, "create failed, id: " + id, null);
        }
    }

    @GetMapping(value = "/payment/discovery")
    @ResponseBody
    public Object discovery(){
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info("**********serverName:" + service);
        }

        List<ServiceInstance> instances = discoveryClient.getInstances(serverName);
        for (ServiceInstance instance : instances) {
            log.info("######## instance:" +instance.getHost() + "\t" +
                    instance.getPort() + "\t" + instance.getUri());
        }

        return discoveryClient;
    }


    @GetMapping(value = "/payment/fallback/ok/{id}")
    public String testFallbackOk(@PathVariable("id") Long id) {
        String result = paymentService.testFallbackOk(id);
        log.info("testFallbackOk:" + result);
        return result;
    }


    @GetMapping(value = "/payment/fallback/timeout/{id}")
    public String testFallbackTimeout(@PathVariable("id") Long id) {
        String result = paymentService.testFallbackTimeout(id);
        log.info("testFallbackTimeout:" + result);
        return result;
    }


    @GetMapping(value = "/payment/circuit/break/{id}")
    public String testCircuitBreak(@PathVariable("id") Long id) {
        if (id > 10) throw new RuntimeException("id cannot over 10");
        String result = paymentService.testCircuitBreak(id);
        log.info("testCircuitBreak:" + result);
        return result;
    }

}
