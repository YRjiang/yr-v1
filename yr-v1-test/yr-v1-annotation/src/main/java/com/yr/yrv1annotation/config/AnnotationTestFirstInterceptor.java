package com.yr.yrv1annotation.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;

@Aspect
@Component
@Lazy(false)
public class AnnotationTestFirstInterceptor {

    /**
     * 定义切入点：对要拦截的方法进行定义与限制，如包、类
     */
    @Pointcut("@annotation(com.yr.yrv1annotation.config.AnnotationTestFirst)")
    private void cutMethod() {

    }

    /**
     * 前置通知：在目标方法执行前调用
     */
    @Before("cutMethod()")
    public void begin() {
        System.out.println("==@Before== lingyejun blog logger : begin");
    }

    /**
     * 后置通知：在目标方法执行后调用，若目标方法出现异常，则不执行
     */
    @AfterReturning("cutMethod()")
    public void afterReturning() {
        System.out.println("==@AfterReturning== lingyejun blog logger : after returning");
    }

    /**
     * 后置/最终通知：无论目标方法在执行过程中出现一场都会在它之后调用
     */
    @After("cutMethod()")
    public void after() {
        System.out.println("==@After== lingyejun blog logger : finally returning");
    }

    /**
     * 异常通知：目标方法抛出异常时执行
     */
    @AfterThrowing("cutMethod()")
    public void afterThrowing() {
        System.out.println("==@AfterThrowing== lingyejun blog logger : after throwing");
    }

    /**
     * 环绕通知：灵活自由的在目标方法中切入代码
     */
    @Around("cutMethod()")
    public void around(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取目标方法的名称
        String methodName = joinPoint.getSignature().getName();
        // 获取方法传入参数
        Object[] params = joinPoint.getArgs();
        for (Object param : params) {
            System.out.println("参数列表: " + param);
        }
        AnnotationTestFirst annotationTestFirst = getDeclaredAnnotation(joinPoint);
        System.out.println("==@Around== 1 --》 method name: " + methodName + ", args:" + params[0]);
        // 执行源方法
        joinPoint.proceed();
        // 模拟进行验证
        if (params != null && params.length > 0 && params[0].equals("Blog Home")) {
            System.out.println("==@Around== 2 --》 " + annotationTestFirst.likes() + " auth success");
        } else {
            System.out.println("==@Around== 3 --》 " + annotationTestFirst.likes() + " auth failed");
        }
    }

    /**
     * 获取方法中声明的注解
     *
     * @param joinPoint
     * @return
     * @throws NoSuchMethodException
     */
    public AnnotationTestFirst getDeclaredAnnotation(ProceedingJoinPoint joinPoint) throws NoSuchMethodException {
        // 获取方法名
        String methodName = joinPoint.getSignature().getName();
        // 反射获取目标类
        Class<?> targetClass = joinPoint.getTarget().getClass();
        // 拿到方法对应的参数类型
        Class<?>[] parameterTypes = ((MethodSignature) joinPoint.getSignature()).getParameterTypes();
        // 根据类、方法、参数类型（重载）获取到方法的具体信息
        Method objMethod = targetClass.getMethod(methodName, parameterTypes);
        // 拿到方法定义的注解信息
        AnnotationTestFirst annotation = objMethod.getDeclaredAnnotation(AnnotationTestFirst.class);
        // 返回
        return annotation;
    }

}
