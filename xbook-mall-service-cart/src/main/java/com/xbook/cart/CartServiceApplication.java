package com.xbook.cart;


import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xbook.dao.*")
@EnableDubbo(scanBasePackages = "com.xbook.cart.service.impl")
public class CartServiceApplication {


    public static void main(String[] args) {
        SpringApplication.run(CartServiceApplication.class, args);
    }
}
