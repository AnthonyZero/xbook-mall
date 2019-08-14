package com.xbook.user;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import com.xbook.user.service.UserService;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
@MapperScan("com.xbook.dao.*.mapper")
@EnableDubbo(scanBasePackages = "com.xbook.user.service.impl")
public class UserServiceApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(UserServiceApplication.class, args);
        UserService userService = (UserService) context.getBean("userServiceImpl");
        userService.login("x", "x");
    }
}
