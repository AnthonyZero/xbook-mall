package com.xbook.redis.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * redis配置
 */
@Component
@ConfigurationProperties(prefix = "redis")
@Data
public class RedisConfig {
    private String host;
    private int port;
    private String password;
    private int timeout;
    private int poolMaxTotal;
    private int poolMaxIdle;
    private int poolMaxWait;
}
