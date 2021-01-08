package com.yr.yrv1productservice;
import com.yr.v1.entity.City;
import com.yr.yrv1productservice.config.TestConfig;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class Test05 {

    public void test01(Map<String, City> map, List<City> list) {
        System.out.println("test01");
    }

    public Map<String, City> test02() {
        System.out.println("test02");
        return null;
    }

    public static void main(String[] args) throws Exception {
        Method method = Test05.class.getMethod("test01", Map.class, List.class);

        Type[] genericParameterTypes = method.getGenericParameterTypes();
        System.out.println("1: " + genericParameterTypes);

        for (Type type : genericParameterTypes) {
            System.out.println("2: " + type);
            if (type instanceof ParameterizedType) { // 这个类型属于参数化类型
                Type[] actualTypeArguments = ((ParameterizedType) type).getActualTypeArguments();
                for (Type actualTypeArgument : actualTypeArguments) {
                    System.out.println("3: " + actualTypeArgument);
                }
            }
        }

        AnnotationConfigApplicationContext annotationConfigApplicationContext =
                new AnnotationConfigApplicationContext(TestConfig.class);
        System.out.println(annotationConfigApplicationContext);

        String[] beanDefinitionNames = annotationConfigApplicationContext.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            System.out.println(beanDefinitionName);
        }

    }

}
