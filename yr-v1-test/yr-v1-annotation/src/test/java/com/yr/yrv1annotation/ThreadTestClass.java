package com.yr.yrv1annotation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTestClass extends Thread{

    public static void main(String[] args) {
        // Thread
        /*new ThreadTest01().start();
        new ThreadTest01().start();*/

        // Runable
        /*ThreadTest02 thread = new ThreadTest02();
        new Thread(thread).start();
        new Thread(thread).start();
        new Thread(thread).start();
        new Thread(thread).start();*/

        /**
         * 使用线程池 ExecutorService    Executors
         */
        //1. 提供指定线程数量的线程池；
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 3; i++) {
            final int task = i;
            service.execute(new Runnable() {
                @Override
                public void run() {
                    for(int j=1;j<=4;j++){
                        try {
                            Thread.sleep(20);
                        } catch (InterruptedException e) {
                            // TODO Auto-generated catch block
                            e.printStackTrace();
                        }
                        System.out.println(Thread.currentThread().getName()
                                + " is looping of " + j + " for  task of " + task);
                    }
                }
            });
        }

    }



}

/**
 * 继承 Thread
 */
class ThreadTest01 extends Thread {
    int ticket = 10;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "出售票" + ticket--);
            } else {
                System.exit(0);
            }
        }
    }
}

/**
 * 实现 Runable
 */
class ThreadTest02 implements Runnable {

    int ticket = 20;

    @Override
    public void run() {
        while (true) {
            if (ticket > 0) {
                System.out.println(Thread.currentThread().getName() + "出售票" + ticket--);
            } else {
                System.exit(0);
            }
        }
    }
}

