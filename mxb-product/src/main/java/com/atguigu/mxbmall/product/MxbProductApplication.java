package com.atguigu.mxbmall.product;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * 1 整合MyBatis-Plus
 *  1) 导入依赖
 *  2）配置
 *      1、配置数据源
 * 2 逻辑删除
 *  1） 配置统一的全局规则（可以省略）
 *  2）配置逻辑删除组件（3.1.1开始不需要这一步）
 *  3）实体类字段上加上@TableLogic(value = "1", delval = "0")注解，三级分类把showStatus字段作为逻辑删除字段
 */

@EnableDiscoveryClient
@SpringBootApplication
public class MxbProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MxbProductApplication.class, args);
    }

}
