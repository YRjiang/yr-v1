package com.yr.yrv1annotation.controller;

import com.yr.v1.entity.City;
import com.yr.yrv1annotation.service.impl.AnnotationTestFirstApply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/annotation")
public class AnnotationController {

    @Autowired
    AnnotationTestFirstApply apply;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/getAnnotation")
    public String get(String body) {
        System.out.println("调用成功");
        apply.annotationFirst("firstParam", "secondParam");
        return "annnotation ====";
    }

    public City restTemplate() {
        return restTemplate.getForObject("", City.class);
    }
}
