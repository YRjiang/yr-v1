package com.yr.yrv1redis.controller;

import com.yr.yrv1redis.service.RedisLock;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/redis")
public class RedisController {

    @Autowired
    RedisLock redisLock;

    @Autowired
    StringRedisTemplate redisTemplate;

    @Autowired
    private RedissonClient redissonClient;

    @GetMapping("/submitOrder")
    public String submitOrder() {
        System.out.println("调用接口成功...");
        boolean lock = redisLock.tryLock("product", 30, TimeUnit.SECONDS);
        if (!lock) {
            return "error";
        }

        // 阻塞 、 非阻塞

        // Timer 异步续命 守护进程
        /*while (true) {
            redisTemplate.expire("product", 30, TimeUnit.SECONDS);
            try {
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }*/

        try {
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                // 下单
                stock = stock - 1;
                redisTemplate.opsForValue().set("stock", stock + "");
                System.out.println("扣减成功, 库存: " + stock);
            } else {
                System.out.println("扣减失败, 库存不足");
            }
        } finally {
            redisLock.releaseLock("product");
        }

        return "end";
    }


    public String submitOrder02() {
        // 1. 加锁

        return null;
    }

    /**
     * 使用Apache ab进行http性能测试
     *
     * 进入目录 E:\2.software\Apache24\bin
     * 在此目录下打开命令窗口 cmd
     * 输入命令： abs -n 100 -c 10 "http://localhost:8091/redis/decrementProductStore"
     * -n  需要执行的请求次数    -c  并发的数量
     *
     * @return
     */

    @GetMapping("/decrementProductStore")
    public String decrementProductStore() {
        System.out.println("调用接口成功 --- decrementProductStore");
        String key = "product";
        RLock lock = redissonClient.getLock(key);
        try {
            //加锁 操作很类似Java的ReentrantLock机制
            //lock.lock();
            lock.tryLock(5L, 30L, TimeUnit.SECONDS);
            int stock = Integer.parseInt(redisTemplate.opsForValue().get("stock"));
            if (stock > 0) {
                // 下单
                stock = stock - 1;
                redisTemplate.opsForValue().set("stock", stock + "");
                System.out.println("扣减成功, 库存: " + stock);
                return "下单成功";
            } else {
                System.out.println("扣减失败, 库存不足");
                return "库存不足";
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        } finally {
            //解锁
            lock.unlock();
        }
        return "end";
    }

    @GetMapping("/redisTest")
    public String redisTest() {
        ExecutorService threadPool = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
            final int task = i;
            threadPool.execute(new Runnable() {
                @Override
                public void run() {
                    for(int j=1;j<=4;j++){
                        try {
                            decrementProductStore();
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()
                                + " is looping of " + j + " for  task of " + task);
                    }
                }
            });
        }
        return null;
    }


}
