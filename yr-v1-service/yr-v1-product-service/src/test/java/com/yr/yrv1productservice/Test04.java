package com.yr.yrv1productservice;

import com.yr.v1.entity.City;

import java.lang.reflect.Method;

public class Test04 {

    public static void test01() {
        City city = new City();

        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            city.getCityName();
        }

        long end = System.currentTimeMillis();

        System.out.println("普通方式执行10亿次: " + (end - start) + " ms");
    }


    public static void test02() throws Exception {
        City city = new City();
        Class c1 = city.getClass();

        Method getCityName = c1.getDeclaredMethod("getCityName", null);

        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getCityName.invoke(city, null);
        }

        long end = System.currentTimeMillis();

        System.out.println("反射方式执行10亿次: " + (end - start) + " ms");
    }


    public static void test03() throws Exception {
        City city = new City();
        Class c1 = city.getClass();

        Method getCityName = c1.getDeclaredMethod("getCityName", null);
        getCityName.setAccessible(true); //关闭检测

        long start = System.currentTimeMillis();

        for (int i = 0; i < 1000000000; i++) {
            getCityName.invoke(city, null);
        }

        long end = System.currentTimeMillis();

        System.out.println("反射方式关闭检测执行10亿次: " + (end - start) + " ms");
    }

    public static void main(String[] args) throws Exception {
        test01();
        test02();
        test03();
    }

}
