package com.charles.myrule;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@Slf4j
public class MyRandomRule {

    // 函数名必须为myRule，类名不可为MyRule
    @Bean
    public IRule myRule() {
        log.info("**********************自定义负载均衡***************************");
        return new RandomRule();
    }
}
