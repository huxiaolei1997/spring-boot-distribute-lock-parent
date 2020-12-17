package com.xlh.distribute.lock.demo;

import com.xlh.distribute.lock.demo.service.OrderService;
import com.xlh.distribute.lock.demo.lock.ZkLockDemo;
import lombok.extern.slf4j.Slf4j;
import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.KeeperException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.concurrent.*;

@SpringBootTest
@Slf4j
class DistributeLockDemoApplicationTests {

    @Autowired
    private OrderService orderService;

    @Test
    public void concurrentOrder() throws InterruptedException {
        Thread.sleep(60000);
        CountDownLatch cdl = new CountDownLatch(5);
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5);

        ExecutorService es = Executors.newFixedThreadPool(5);
        for (int i = 0; i < 5; i++) {
            es.execute(() -> {
                try {
                    cyclicBarrier.await();
                    Integer orderId = orderService.createOrder();
                    System.out.println("订单id：" + orderId);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    cdl.countDown();
                }
            });
        }
        cdl.await();
        es.shutdown();
    }

    @Test
    public void testZkLock() throws IOException {
        ZkLockDemo zkLockDemo = new ZkLockDemo("localhost:2181","order");
        try {
            zkLockDemo.getLock();
            zkLockDemo.close();
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    @Test
    public void testCuratorLock(){
        RetryPolicy retryPolicy = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", retryPolicy);
        client.start();
        InterProcessMutex lock = new InterProcessMutex(client, "/order");
        try {
            if ( lock.acquire(30, TimeUnit.SECONDS) ) {
                try  {
                    log.info("我获得了锁！！！");
                }
                finally  {
                    lock.release();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        client.close();
    }
}
