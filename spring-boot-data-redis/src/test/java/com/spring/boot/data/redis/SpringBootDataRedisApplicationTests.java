package com.spring.boot.data.redis;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootDataRedisApplicationTests {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @Test
    void contextLoads() {
        stringRedisTemplate.opsForValue().set("key", "value", 3, TimeUnit.SECONDS);
        System.out.println(stringRedisTemplate.opsForValue().get("key"));

        redisTemplate.opsForValue().set("key1", "value1", 3, TimeUnit.SECONDS);
        System.out.println(redisTemplate.opsForValue().get("key"));
    }

}
