package com.xlh.distribute.lock.demo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = "com.xlh.distribute.lock.demo.dao")
public class DistributeLockDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DistributeLockDemoApplication.class, args);
    }

}
