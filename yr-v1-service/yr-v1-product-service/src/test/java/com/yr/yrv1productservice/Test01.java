package com.yr.yrv1productservice;

public class Test01 extends Test02 implements TestInterface, TestInterface02 {

    @Override
    public String getMessage() {
        return null;
    }

    @Override
    public synchronized void get() {
        super.get();
    }

    @Override
    public String getId() {
        return null;
    }

    public static void main(String[] args) {
        Abc abc = new Abc();
        System.out.println(abc.b);
        abc.getClass();

    }
}

class Abc {

    // 执行顺序优先级： 静态块 > 构造方法 > 常量

    int b = 3;

    Abc(){
        System.out.println("======构造方法=========");
    }
    static{
        int b = 2;
        System.out.println("=====静态块======");
    }
    public static void c(){
        System.out.println("==========静态方法===========");
    }

    public static Bcd i = new Bcd();

}

class Bcd {
    Bcd(){
        System.out.println("====== BCD 静态成员变量========");
    }
}
