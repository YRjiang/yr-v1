package com.yr.yrv1annotation.service.impl;

import com.yr.yrv1annotation.config.AnnotationTestFirst;
import org.springframework.stereotype.Component;

@Component
public class AnnotationTestFirstApply {

    @AnnotationTestFirst(name = "消失", age = 19, likes = {"篮球", "足球"})
    public void annotationFirst(String firstParam, String secondParam) {
        System.out.println("annotationFirst --- ");
    }

}
