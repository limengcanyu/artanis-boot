package com.spring.boot.redis;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.redis.connection.RedisConnectionFactory;

@Slf4j
@SpringBootApplication
public class SpringBootRedisApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(SpringBootRedisApplication.class, args);

        RedisConnectionFactory redisConnectionFactory = context.getBean(RedisConnectionFactory.class);
        log.info("redisConnectionFactory: {}", redisConnectionFactory);
    }

}
