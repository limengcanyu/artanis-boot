package com.spring.boot.redis.redisson;

import org.redisson.api.RedissonClient;
import org.redisson.spring.transaction.RedissonTransactionManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class RedissonConfig {
    @Bean
    public PlatformTransactionManager redissonTransactionManager(RedissonClient redisson) {
        return new RedissonTransactionManager(redisson);
    }

}
