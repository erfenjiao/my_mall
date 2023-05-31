package com.atguigu.mxbmall.thirdparty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class MxbmallThirdPartyApplication {

	public static void main(String[] args) {
		SpringApplication.run(MxbmallThirdPartyApplication.class, args);
	}

}
