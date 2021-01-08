package com.yr.yrv1productservice;

public abstract class Test02 {

    public abstract String getMessage();

    public String name(){
        return this.getClass().getSimpleName();
    }

    public static void getName() {
        System.out.println("getName");
    }

    public native void getAge();

    public synchronized void get() {
        System.out.println("getFuck");
    }
}

interface TestInterface {

    static String sayHello(){
        return "aaa";
    }

}

interface TestInterface02 {
    String getId();
}
