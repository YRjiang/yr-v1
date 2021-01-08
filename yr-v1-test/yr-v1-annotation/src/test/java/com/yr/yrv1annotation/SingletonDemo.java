package com.yr.yrv1annotation;

// 双重同步锁 懒汉式
public class SingletonDemo {

    private static volatile SingletonDemo instance;

    private SingletonDemo(){}

    public static SingletonDemo getInstance() {
        if(instance == null) {
            synchronized(SingletonDemo.class) {
                if (instance == null) {
                    instance = new SingletonDemo();
                }
            }
        }
        return instance;
    }

}

// 静态内部类 实现模式  - 线程安全, 调用效率高, 可以延时加载

/**
 *         （1）为什么这样实现就是单例的？
 *                  因为这个类的实例化是靠静态内部类的静态常量实例化的。
 *                  INSTANCE 是常量，因此只能赋值一次；它还是静态的，因此随着内部类一起加载。
 *         （2）这样实现有什么好处？
 *                  我记得以前接触的懒汉式的代码好像有线程安全问题，需要加同步锁才能解决。
 *                  采用静态内部类实现的代码也是懒加载的，只有第一次使用这个单例的实例的时候才加载；
 *                  同时不会有线程安全问题
 */
class SingletonDemo2{

    /** 写一个静态内部类，里面实例化外部类 */
    private static class SingletonClassInstance{
        private static final SingletonDemo2 instance = new SingletonDemo2();
    }

    /** 私有化构造器 */
    private SingletonDemo2(){}

    /** 对外提供公共的访问方法 */
    public static SingletonDemo2 getInstance(){
        return SingletonClassInstance.instance;
    }

}


class SingletonDemo3{

    private static class SingletonClassInstance{
        private static final SingletonDemo3 instance = new SingletonDemo3();
    }

    private SingletonDemo3(){}

    public static SingletonDemo3 getInstance(){
        return SingletonClassInstance.instance;
    }

}

class SingletonDemo4{
    private static volatile SingletonDemo4 instance;

    private SingletonDemo4(){}

    public static SingletonDemo4 getInstance(){
        if (instance == null) {
            synchronized (SingletonDemo4.class) {
                if (instance == null) {
                    instance = new SingletonDemo4();
                }
            }
        }
        return instance;
    }
}



















