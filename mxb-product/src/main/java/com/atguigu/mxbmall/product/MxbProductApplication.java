package com.atguigu.mxbmall.product;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1. 整合MyBatis-Plus
 *      1） 导入依赖
 *          <dependency>
 *             <groupId>com.baomidou</groupId>
 *             <artifactId>mybatis-plus</artifactId>
 *             <version>3.5.2</version>
 *          </dependency>
 *      2） 配置
 *            1. 配置数据源
 *              1) 导入数据库驱动
 *              2） 配置 application.yml 配置数据源
 *            2. 配置mybatis-plus
 *              1) @MapperScan
 *              2)
 */
@MapperScan("com.atguigu.mxbmall.product.dao")
@SpringBootApplication
public class MxbProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MxbProductApplication.class, args);
    }

}
