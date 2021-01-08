package com.yr.yrv1annotation.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@Slf4j
@RestController
@RequestMapping("/swagger")
public class SwaggerController {

    @ApiOperation("这里只测试，swaggerui，无其他用途......")
    @GetMapping("/ui")
    public String swaggerui(String code) {
        log.info("swagger-ui ......");
        return "swagger-ui";
    }

}
