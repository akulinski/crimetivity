package com.akulinski.crimetivity.pointsaftyservice.config;


import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RoundRobinRule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;

@Slf4j
@RibbonClient(name = "crimefilteringservice")
public class RibbonConfiguration {

    @Bean
    public IRule loadBlancingRule() {
        return new RoundRobinRule();
    }
}