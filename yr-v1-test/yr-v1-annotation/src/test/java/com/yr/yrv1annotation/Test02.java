package com.yr.yrv1annotation;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Test02 {

    public static void main(String[] args) {
        LogPrint logPrint = new LogPrint();
        // 静态代理
        //StaticProxyPrint proxyPrint = new StaticProxyPrint(logPrint);

        // 动态代理
        ProxyHandler proxyHandler = new ProxyHandler();
        IPrint print = (IPrint) proxyHandler.newProxyInstance(logPrint);
        print.print();

    }

}

interface IPrint {
    void print();
}

class LogPrint implements IPrint {

    @Override
    public void print() {
        System.out.println("打印!!!");
    }
}

class StaticProxyPrint implements IPrint {

    private IPrint print;

    public StaticProxyPrint(IPrint print) {
        this.print = print;
    }

    @Override
    public void print() {
        System.out.println("静态代理。。。");
        print.print();
    }
}


class DynamicProxyPrint implements InvocationHandler {

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
