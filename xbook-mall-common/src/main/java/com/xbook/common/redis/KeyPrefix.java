package com.xbook.common.redis;

/**
 * redis前缀
 */
public interface KeyPrefix {

    /**
     * 过期时间 秒为单位
     * @return
     */
    public int expireSeconds();

    /**
     * 前缀
     * @return
     */
    public String getPrefix();
}
