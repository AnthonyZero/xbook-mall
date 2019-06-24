package com.xbook.redis.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.xbook.common.redis.KeyPrefix;
import com.xbook.redis.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

@Service
public class RedisClient implements RedisService {

    @Autowired
    private JedisPool jedisPool;

    @Override
    public String get(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            // 生成真正的key
            String realKey = prefix.getPrefix() + key;
            String str = jedis.get(realKey);
            return str;
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public boolean set(KeyPrefix prefix, String key, String value) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            if (value == null || value.length() <= 0) {
                return false;
            }
            // 生成真正的key
            String realKey = prefix.getPrefix() + key;
            int seconds = prefix.expireSeconds();
            if (seconds < 1) { //不过期
                jedis.set(realKey, value);
            } else {
                jedis.setex(realKey, seconds, value);
            }
            return true;
        }finally {
            returnToPool(jedis);
        }
    }

    @Override
    public boolean exists(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.exists(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public Long incr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.incr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public Long decr(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            return jedis.decr(realKey);
        } finally {
            returnToPool(jedis);
        }
    }

    @Override
    public boolean delete(KeyPrefix prefix, String key) {
        Jedis jedis = null;
        try {
            jedis = jedisPool.getResource();
            String realKey = prefix.getPrefix() + key;
            long ret = jedis.del(realKey);
            return ret > 0;
        } finally {
            returnToPool(jedis);
        }
    }


    /**
     * 回收redis连接
     * @param jedis
     */
    private void returnToPool(Jedis jedis) {
        if (jedis != null) {
            jedis.close();
        }
    }
}
