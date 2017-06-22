package com.yanggy.cloud;

import com.netflix.loadbalancer.IRule;
import com.netflix.loadbalancer.RandomRule;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.circuitbreaker.EnableCircuitBreaker;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Created by yangguiyun on 2017/6/12.
 */

@EnableDiscoveryClient
@SpringBootApplication
//@EnableCircuitBreaker
@EnableHystrixDashboard
public class ServiceConsumer {
    @Bean
    public IRule ribbonRule() {
//        return new RandomRule();
//        return new BestAvailableRule();
        return new RandomRule();
    }

    @Bean
    @LoadBalanced
    RestTemplate restTemplate() {
        return new RestTemplate();
    }

    public static void main(String[] args) {
        SpringApplication.run(ServiceConsumer.class, args);
    }
}
