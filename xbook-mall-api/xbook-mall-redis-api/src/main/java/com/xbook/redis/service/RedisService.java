package com.xbook.redis.service;

import com.xbook.common.redis.KeyPrefix;

public interface RedisService {

    /**
     * 获取String 值
     *
     * @param prefix
     * @param key
     * @return
     */
    String get(KeyPrefix prefix, String key);

    /**
     * 设置值
     *
     * @param prefix
     * @param key
     * @param value
     * @return
     */
    boolean set(KeyPrefix prefix, String key, String value);


    /**
     * 判断Key是否存在
     *
     * @param prefix
     * @param key
     * @return
     */
    boolean exists(KeyPrefix prefix, String key);


    /**
     * 自增
     *
     * @param prefix
     * @param key
     * @return
     */
    Long incr(KeyPrefix prefix, String key);


    /**
     * 自减
     * @param prefix
     * @param key
     * @return
     */
    Long decr(KeyPrefix prefix, String key);



    /**
     * 删除键
     * @param prefix
     * @param key
     * @return
     */
    public boolean delete(KeyPrefix prefix, String key);
}
