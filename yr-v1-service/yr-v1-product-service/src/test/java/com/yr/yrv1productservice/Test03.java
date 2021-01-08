package com.yr.yrv1productservice;

import com.yr.v1.entity.City;

import java.lang.reflect.Method;

public class Test03 {

    public static void main(String[] args) throws Exception { // psvm
        Class c1 = City.class;
        System.out.println("1: " + c1); // sout

        City city = new City();
        city.setId(1L);
        city.setCityName("厦门");
        city.setDescription("岛内、岛外");
        city.setProvinceId("福建");
        System.out.println(city.toString());

        Class c2 = city.getClass();
        System.out.println("2: " + c2);

        Class c3 = Class.forName("com.yr.v1.entity.City");
        System.out.println("3: " + c3);

        Method setCityName = c3.getDeclaredMethod("setCityName", String.class);
        setCityName.setAccessible(true);
        setCityName.invoke(city, "福州");
        System.out.println(setCityName);
        System.out.println(city.toString());

    }

}
