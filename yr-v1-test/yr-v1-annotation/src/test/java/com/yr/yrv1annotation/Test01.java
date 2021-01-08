package com.yr.yrv1annotation;


import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

public class Test01 {

    public static void main(String[] args) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("one", 1);
        map.put("two", 2);
        map.put("three", 3);
        map.put("four", 4);

        map.forEach((key, value) -> {
            System.out.println("key: " + key);
            System.out.println("value: " + value);
        });

        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("key: " + entry.getKey());
            System.out.println("value: " + entry.getValue());
        }

    }

}

interface IPrinter {
    void print();
}

class Printer implements IPrinter {

    @Override
    public void print() {
        System.out.println("打印!");
    }
}

class PrinterProxy implements IPrinter {

    private IPrinter printer;

    public PrinterProxy(IPrinter printer) {
        this.printer = printer;
    }

    public PrinterProxy() {
    }

    @Override
    public void print() {
        System.out.println("记录日志");
        printer.print();
    }
}

class Test {
    public static void main(String[] args) {
        /*Printer printer = new Printer();
        PrinterProxy printerProxy = new PrinterProxy(printer);
        printerProxy.print();

        Log2PrinterProxy log2PrinterProxy = new Log2PrinterProxy(printer);
        log2PrinterProxy.print();*/

        ProxyHandler proxyHandler = new ProxyHandler();
        IPrinter printer3 = (IPrinter) proxyHandler.newProxyInstance(new Printer());
        printer3.print();

    }
}


class Log2PrinterProxy implements IPrinter {

    private IPrinter printer;

    public Log2PrinterProxy(IPrinter printer) {
        this.printer = printer;
    }

    @Override
    public void print() {
        System.out.println("代理模式 。。。");
        printer.print();
    }
}

class ProxyHandler implements InvocationHandler {

    private Object targetObject;

    // 将被代理的对象传入获得它的类加载器和实现接口作为Proxy.newProxyInstance方法的参数。
    public Object newProxyInstance(Object targetObject) {
        this.targetObject = targetObject;
        ClassLoader classLoader = targetObject.getClass().getClassLoader(); //被代理对象的类加载器
        Class<?>[] interfaces = targetObject.getClass().getInterfaces(); //被代理对象的实现接口
        //this 当前对象，该对象实现了InvocationHandler接口所以有invoke方法，通过invoke方法可以调用被代理对象的方法
        return Proxy.newProxyInstance(classLoader, interfaces, this);
    }

    // 该方法在代理对象调用方法时调用
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("动态代理模式......");
        return method.invoke(targetObject,args);
    }
}

