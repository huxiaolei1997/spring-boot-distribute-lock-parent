package com.xlh.distribute.lock.demo.controller;

import com.xlh.distribute.lock.demo.lock.RedisLock;
import com.xlh.distribute.lock.demo.lock.ZkLockDemo;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class RedisLockController {
    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private CuratorFramework client;

    @RequestMapping("redisLock")
    public String redisLock(){
        log.info("我进入了方法！");
        try (RedisLock redisLock = new RedisLock(redisTemplate,"redisKey",30)){
            if (redisLock.getLock()) {
                log.info("我进入了锁！！");
                Thread.sleep(15000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("方法执行完成");
        return "方法执行完成";
    }

    @RequestMapping("zkLock")
    public String zkLock(){
        log.info("我进入了方法！");
        try (ZkLockDemo zkLockDemo = new ZkLockDemo("localhost:2181","order")){
            if (zkLockDemo.getLock()) {
                log.info("我进入了锁！！");
                Thread.sleep(15000);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.info("方法执行完成");
        return "方法执行完成";
    }

    @RequestMapping("curatorLock")
    public String curatorLock(){
        log.info("我进入了方法！");
        InterProcessMutex lock = new InterProcessMutex(client, "/order");
        try{
            if (lock.acquire(30, TimeUnit.SECONDS)){
                log.info("我获得了锁！！");
                Thread.sleep(10000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {
                log.info("我释放了锁！！");
                lock.release();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        log.info("方法执行完成！");
        return "方法执行完成！";
    }
}
