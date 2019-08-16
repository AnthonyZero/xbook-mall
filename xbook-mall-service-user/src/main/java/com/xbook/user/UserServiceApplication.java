package com.xbook.user;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.xbook.dao.*.mapper")
@EnableDubbo(scanBasePackages = "com.xbook.user.service.impl")
public class UserServiceApplication{

    public static void main(String[] args) {
       SpringApplication.run(UserServiceApplication.class, args);
    }
}
