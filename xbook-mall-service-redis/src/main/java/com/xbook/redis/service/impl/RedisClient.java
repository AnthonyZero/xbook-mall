package com.xbook.redis.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xbook.common.redis.KeyPrefix;
import com.xbook.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.JedisPool;

@Service
public class RedisClient implements RedisService {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String get(KeyPrefix prefix, String key) {
        return jedisPool.getResource().get(key);
    }
}
