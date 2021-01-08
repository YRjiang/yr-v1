package com.yr.yrv1annotation;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class ProxyPatternTest {

    public static void main(String[] args) {
        INoodle iNoodle = (INoodle) new ProxyHandler().newProxyInstance(new LzNoodle());
        iNoodle.desc();
    }


}

interface INoodle {
    void desc();
}

class LzNoodle implements INoodle {
    @Override
    public void desc() {
        System.out.println("兰州拉面");
    }
}

class DynamicProxyNoodle implements InvocationHandler {

    private Object targetObject;

    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        ClassLoader classLoader = targetObject.getClass().getClassLoader();
        Class<?>[] interfaces = targetObject.getClass().getInterfaces();
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理......");
        return method.invoke(targetObject, args);
    }
}

