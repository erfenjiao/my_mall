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
 *
 *  3 JSR303表单验证-数据校验
 *      1)、给 Bean 添加校验注解 package javax.validation.constraints; 并定义自己的message提示
 *      2)、SpringMVC提交数据 -> controller 在需要校验的方法上添加@Valid注解，并返回提示信息
 *      3)、给校验的 Bean 后紧跟 BindingResult result ，就可以得到校验的结果
 *      4)、分组校验
 *          1. 在common中新建valid包，里面新建两个空接口AddGroup,UpdateGroup用来分组
 *          2. 给校验注解，标注上groups，指定什么情况下才需要进行校验
 *          3. 业务方法参数上使用@Validated注解，并在value中给出group接口，标记当前校验是哪个组
 *          4. 默认情况下，在分组校验情况下，没有指定指定分组的校验注解，将不会生效，它只会在不分组的情况下生效
 *
 *  4 统一的异常处理
 *      1）在product里面新建类GulimallExceptionControllerAdvice，用来集中处理所有异常  @RestControllerAdvice
 *      2）使用 @ExceptionHandler 标注方法可以处理的异常
 *
 *  5 自定义校验
 *
 */

@EnableDiscoveryClient
@SpringBootApplication
public class MxbProductApplication {

    public static void main(String[] args) {
        SpringApplication.run(MxbProductApplication.class, args);
    }

}
