package com.xbook.redis;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableDubbo(scanBasePackages = "com.xbook.redis.service.impl")
public class XbookRedisApplication {

    public static void main(String[] args) {
        SpringApplication.run(XbookRedisApplication.class, args);
    }
}
