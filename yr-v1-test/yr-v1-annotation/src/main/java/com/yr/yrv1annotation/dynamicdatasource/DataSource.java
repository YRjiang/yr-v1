package com.yr.yrv1annotation.dynamicdatasource;

import java.lang.annotation.*;

/**
 * 自定义数据源注解，默认主库（master）
 *
 * @author Administrator
 */
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface DataSource {
    String value() default "master";
}

