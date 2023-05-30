package com.atguigu.mxbmall.coupon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * nacos用作配置中心
 * 1、common中添加依赖 nacos配置中心
 * <dependency>
 *      <groupId>com.alibaba.cloud</groupId>
 *      <artifactId>spring-cloud-starter-alibaba-nacos-config</artifactId>
 *  </dependency>
 *  2、在coupons项目中创建/src/main/resources/bootstrap.yml，优先级别application.properties高
 *      spring.application.name=mxbmall-coupon
 *      spring.cloud.nacos.config.server-addr=127.0.0.1:8848
 *  3、浏览器去nacos里的配置列表，点击＋号，data ID：mxbmall-coupon.properties
 *  4、在controller中编写测试代码
 *  5、在coupon的控制层上加 @RefreshScope 支持动态刷新
 *  6、新建coupon、member、order、product、ware五个命名空间分别保存自己的配置文件
 *
 *  细节
 *  1） 命名空间：配置隔离
 *      默认：public（保留空间）
 *      1. 开发、测试、生产：利用命名空间作环境隔离（bootstrap.properties 设置）
 *      2. 每一个微服务之间相互隔离
 *  2) 配置集
 *  3）配置集ID
 *  4）配置分组
 *      每个微服务创建自己的命名空间，使用配置分组区分环境
 *
 *   同时加载多个配置集
 */

/**
* 使用 @EnableDiscoveryClient 注解开启服务注册与发现功能
* */
@EnableDiscoveryClient
@SpringBootApplication
public class MxbCouponApplication {

    public static void main(String[] args) {
        SpringApplication.run(MxbCouponApplication.class, args);
    }

}
