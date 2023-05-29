package com.atguigu.mxbmall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MxbProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MxbProductApplication.class, args);
    }

}
