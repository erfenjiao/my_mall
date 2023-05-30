//package com.atguigu.mxbmall.gateway.bean;
//
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.core.convert.ConversionService;
//import org.springframework.core.convert.support.ConfigurableConversionService;
//import org.springframework.format.support.DefaultFormattingConversionService;
//import org.springframework.format.support.FormattingConversionService;
//import org.springframework.web.reactive.config.WebFluxConfigurer;
//
//@Configuration
//public class MyConfig implements WebFluxConfigurer {
//
//
//    public void configureConversionService(ConfigurableConversionService conversionService) {
//        // Add your converters here
//    }
//
//    @Bean
//    @Qualifier("webFluxConversionService")
//    public ConfigurableConversionService webFluxConversionService() {
//        ConfigurableConversionService conversionService = new DefaultFormattingConversionService();
//        configureConversionService(conversionService);
//        return conversionService;
//    }
//}