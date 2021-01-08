package com.yr.yrv1annotation.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectConfig {

    @Pointcut("execution(* com.yr.yrv1annotation.controller.PointCutController..*(..))")
    public void pointcutMethor() {

    }

    @Before("pointcutMethor()")
    public void beforeMethod(JoinPoint joinPoint) {
        System.out.println("调用到 切面 前置方法");
    }
}
