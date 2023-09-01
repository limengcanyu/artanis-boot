package com.spring.boot.redis.redisson;

import org.redisson.RedissonMultiLock;
import org.redisson.RedissonRedLock;
import org.redisson.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * https://github.com/redisson/redisson/wiki/8.-%E5%88%86%E5%B8%83%E5%BC%8F%E9%94%81%E5%92%8C%E5%90%8C%E6%AD%A5%E5%99%A8
 *
 */
public class DistributedLocksExample {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedissonClient redisson;

    public void business() {
        String value = stringRedisTemplate.opsForValue().get("product:1000001");
        assert value != null;
        if (Integer.parseInt(value) > 0) {
            stringRedisTemplate.opsForValue().decrement("product:1000001", 1);
            System.out.println("产品还剩：" + stringRedisTemplate.opsForValue().get("product:1000001"));
        }
    }

    /**
     * 可重入锁（Reentrant Lock）
     * Redisson的分布式可重入锁RLock Java对象实现了java.util.concurrent.locks.Lock接口，同时还支持自动过期解锁。
     */
    public void reentrantLock() {
        RLock lock = redisson.getLock("anyLock");
        try {
            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
            if (res) {
                // do your business
                business();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 异步执行的可重入锁
     */
    public void asyncReentrantLock() {
        RLock lock = redisson.getLock("anyLock");
        try {
            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            Future<Boolean> res = lock.tryLockAsync(100, 10, TimeUnit.SECONDS);
            if (res.get()) {
                // do your business
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 公平锁（Fair Lock）
     * Redisson分布式可重入公平锁也是实现了java.util.concurrent.locks.Lock接口的一种RLock对象。在提供了自动过期解锁功能的同时，保证了当多个Redisson客户端线程同时请求加锁时，优先分配给先发出请求的线程。
     */
    public void fairLock() {
        RLock fairLock = redisson.getFairLock("anyLock");
        try {
            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            boolean res = fairLock.tryLock(100, 10, TimeUnit.SECONDS);
            if (res) {
                // do your business
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            fairLock.unlock();
        }
    }

    /**
     * 异步执行的公平锁
     */
    public void asyncFairLock() {
        RLock fairLock = redisson.getFairLock("anyLock");
        try {
            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            Future<Boolean> res = fairLock.tryLockAsync(100, 10, TimeUnit.SECONDS);
            if (res.get()) {
                // do your business
            }
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        } finally {
            fairLock.unlock();
        }
    }

    /**
     * 联锁（MultiLock）
     * Redisson的RedissonMultiLock对象可以将多个RLock对象关联为一个联锁，每个RLock对象实例可以来自于不同的Redisson实例。
     *
     * @param redisson1
     * @param redisson2
     * @param redisson3
     */
    public void multiLock(RedissonClient redisson1, RedissonClient redisson2, RedissonClient redisson3) {
        RLock lock1 = redisson1.getLock("lock1");
        RLock lock2 = redisson2.getLock("lock2");
        RLock lock3 = redisson3.getLock("lock3");

        RedissonMultiLock lock = new RedissonMultiLock(lock1, lock2, lock3);

        try {
            // 给lock1，lock2，lock3加锁，所有的锁都上锁成功才算成功。
            // 为加锁等待100秒时间，并在加锁成功10秒钟后自动解开
            boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
            if (res) {
                // do your business
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 红锁（RedLock）
     * Redisson的RedissonRedLock对象实现了Redlock介绍的加锁算法。该对象也可以用来将多个RLock
     * 对象关联为一个红锁，每个RLock对象实例可以来自于不同的Redisson实例。
     *
     * @param redisson1
     * @param redisson2
     * @param redisson3
     */
    public void redLock(RedissonClient redisson1, RedissonClient redisson2, RedissonClient redisson3) {
        RLock lock1 = redisson1.getLock("lock1");
        RLock lock2 = redisson2.getLock("lock2");
        RLock lock3 = redisson3.getLock("lock3");

        RedissonRedLock lock = new RedissonRedLock(lock1, lock2, lock3);
        try {
            // 同时加锁：lock1 lock2 lock3，红锁在大部分节点上加锁成功就算成功。
            // 为加锁等待100秒时间，并在加锁成功10秒钟后自动解开
            boolean res = lock.tryLock(100, 10, TimeUnit.SECONDS);
            if (res) {
                // do your business
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }
    }

    /**
     * 读锁
     * <p>
     * 读写锁（ReadWriteLock）
     * Redisson的分布式可重入读写锁RReadWriteLock Java对象实现了java.util.concurrent.locks.ReadWriteLock接口。同时还支持自动过期解锁。该对象允许同时有多个读取锁，但是最多只能有一个写入锁。
     *
     * @param redisson
     */
    public void readLock(RedissonClient redisson) {
        RReadWriteLock rwLock = redisson.getReadWriteLock("anyRWLock");
        try {
            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            boolean res = rwLock.readLock().tryLock(100, 10, TimeUnit.SECONDS);
            if (res) {
                // do your business
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    /**
     * 写锁
     * <p>
     * 读写锁（ReadWriteLock）
     * Redisson的分布式可重入读写锁RReadWriteLock Java对象实现了java.util.concurrent.locks.ReadWriteLock接口。同时还支持自动过期解锁。该对象允许同时有多个读取锁，但是最多只能有一个写入锁。
     *
     * @param redisson
     */
    public void writeLock(RedissonClient redisson) {
        RReadWriteLock rwLock = redisson.getReadWriteLock("anyRWLock");
        try {
            // 尝试加锁，最多等待100秒，上锁以后10秒自动解锁
            boolean res = rwLock.writeLock().tryLock(100, 10, TimeUnit.SECONDS);
            if (res) {
                // do your business
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    /**
     * 信号量（Semaphore）
     * Redisson的分布式信号量（Semaphore）Java对象RSemaphore采用了与java.util.concurrent.Semaphore相似的接口和用法。
     *
     * @param redisson
     */
    public void semaphore(RedissonClient redisson) {
        RSemaphore semaphore = redisson.getSemaphore("semaphore");
        try {
            // 最多等待23秒尝试获取10个取信号
            semaphore.tryAcquire(10,23, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release();
        }
    }

    /**
     * 可过期性信号量（PermitExpirableSemaphore）
     * Redisson的可过期性信号量（PermitExpirableSemaphore）实在RSemaphore对象的基础上，为每个信号增加了一个过期时间。每个信号可以通过独立的ID来辨识，释放时只能通过提交这个ID才能释放。
     *
     * @param redisson
     */
    public void permitExpirableSemaphore(RedissonClient redisson) {
        RPermitExpirableSemaphore semaphore = redisson.getPermitExpirableSemaphore("mySemaphore");
        String permitId = null;
        try {
            // 获取一个信号，有效期只有2秒钟。
            permitId = semaphore.acquire(2, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            semaphore.release(permitId);
        }
    }

}
