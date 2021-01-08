package com.yr.yrv1annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 为什么要排除DruidDataSourceAutoConfigure ？
 *   DruidDataSourceAutoConfigure会注入一个DataSourceWrapper，
 *   其会在原生的spring.datasource下找url,username,password等。
 *   而我们动态数据源的配置路径是变化的
 */
@SpringBootApplication
public class YrV1AnnotationApplication {

    public static void main(String[] args) {
        SpringApplication.run(YrV1AnnotationApplication.class, args);
    }

}
