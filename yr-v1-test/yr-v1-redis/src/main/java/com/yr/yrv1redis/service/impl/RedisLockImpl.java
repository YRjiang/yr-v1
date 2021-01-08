package com.yr.yrv1redis.service.impl;

import com.yr.yrv1redis.service.RedisLock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class RedisLockImpl implements RedisLock {

    //线程安全的变量
    private ThreadLocal<String> threadLocal = new ThreadLocal<> ();

    @Autowired
    private StringRedisTemplate redisTemplate;

    @Override
    public boolean tryLock(String key, long timeout, TimeUnit unit) {
        boolean lock = false;

        // 防止其他类再次调用次方法时，导致的
        // 在一个线程中获取两次锁, 返回 true
        // 确保 可重入性
        if (this.threadLocal.get() == null) {
            String uuid = Thread.currentThread().getId() + ": " + UUID.randomUUID().toString();
            threadLocal.set(uuid);
            lock = redisTemplate.opsForValue().setIfAbsent(key, uuid, timeout, unit);
        } else if (threadLocal.get().equals(redisTemplate.opsForValue().get(key))) {
            return true;
        }
        return lock;
    }

    @Override
    public void releaseLock(String key) {
        // 关闭 ThreadId
        if (threadLocal.get().equals(redisTemplate.opsForValue().get(key))) {
            redisTemplate.delete(key);
        }
    }

}
