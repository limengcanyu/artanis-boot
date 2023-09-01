package com.spring.boot.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class RedisTransactionServiceImpl implements RedisTransactionService {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public void test() throws Exception {
        stringRedisTemplate.multi();
        stringRedisTemplate.opsForValue().set("product:1000004", "1");
        String s = stringRedisTemplate.opsForValue().get("product:1000003");
        if (s == null) {
            throw new Exception();
        }
        stringRedisTemplate.exec();
    }
}
