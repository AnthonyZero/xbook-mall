package com.xbook.redis.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xbook.redis.service.RedisService;

@Service
public class RedisClient implements RedisService{
    @Override
    public String sayHello(String name) {
        return "hello:" + name;
    }
}
