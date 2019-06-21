package com.xbook.redis.service;

import com.xbook.common.redis.KeyPrefix;

public interface RedisService {

    /**
     * 获取String 值
     * @param prefix
     * @param key
     * @return
     */
    String get(KeyPrefix prefix, String key);
}
