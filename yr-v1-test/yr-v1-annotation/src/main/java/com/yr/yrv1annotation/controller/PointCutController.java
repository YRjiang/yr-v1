package com.yr.yrv1annotation.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/pointCut")
public class PointCutController {

    @GetMapping("/testPointCut")
    public String getPointCut() {
        // 处理业务
        System.out.println("get point cut ...");
        return "get point cut ...";
    }

}
