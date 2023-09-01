package com.spring.boot.redis.redisson;

import org.junit.jupiter.api.Test;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class SpringBootRedisRedissonApplicationTests {

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Test
    void contextLoads() {
    }

    @Test
    void reentrantLock() {
        stringRedisTemplate.opsForValue().set("product:1000001", "10");
        System.out.println("产品还剩：" + stringRedisTemplate.opsForValue().get("product:1000001"));

        for (int i = 1; i <= 20; i++) {
            int finalI = i;
            new Thread(() -> {
                Thread.currentThread().setName("线程 " + finalI);

                RLock lock = redissonClient.getLock("anyLock");
                try {
                    boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
                    if (res) {
                        System.out.println(Thread.currentThread().getName() + " 获取到锁");
                        String value = stringRedisTemplate.opsForValue().get("product:1000001");
                        assert value != null;
                        if (Integer.parseInt(value) > 0) {
                            stringRedisTemplate.opsForValue().decrement("product:1000001", 1);
                            System.out.println("产品还剩：" + stringRedisTemplate.opsForValue().get("product:1000001"));
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " 释放了锁");
                }
            }).start();
        }

        // 主线程等待10秒再结束，否则执行线程还没执行完，会报错
        try {
            TimeUnit.SECONDS.sleep(10);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    void asyncReentrantLock() {
        stringRedisTemplate.opsForValue().set("product:1000001", "10");
        System.out.println("产品还剩：" + stringRedisTemplate.opsForValue().get("product:1000001"));

        for (int i = 1; i <= 20; i++) {
            int finalI = i;
            new Thread(() -> {
                Thread.currentThread().setName("线程 " + finalI);

                RLock lock = redissonClient.getLock("anyLock");
                try {
                    // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
                    Future<Boolean> res = lock.tryLockAsync(100, 10, TimeUnit.SECONDS);
                    if (res.get()) {
                        System.out.println(Thread.currentThread().getName() + " 获取到锁");
                        String value = stringRedisTemplate.opsForValue().get("product:1000001");
                        assert value != null;
                        if (Integer.parseInt(value) > 0) {
                            stringRedisTemplate.opsForValue().decrement("product:1000001", 1);
                            System.out.println("产品还剩：" + stringRedisTemplate.opsForValue().get("product:1000001"));
                        }
                    }
                } catch (Exception e) {
                    throw new RuntimeException(e);
                } finally {
                    lock.unlock();
                    System.out.println(Thread.currentThread().getName() + " 释放了锁");
                }
            }).start();
        }

        try {
            TimeUnit.SECONDS.sleep(20);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

}
