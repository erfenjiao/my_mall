package com.atguigu.mxbmall.gateway;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MxbGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(MxbGatewayApplication.class, args);
    }

}
