package com.atguigu.mxbmall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/*
* 使用 @EnableDiscoveryClient 注解开启服务注册与发现功能
* */
@EnableDiscoveryClient
@SpringBootApplication
public class MxbCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(MxbCouponApplication.class, args);
    }

}
