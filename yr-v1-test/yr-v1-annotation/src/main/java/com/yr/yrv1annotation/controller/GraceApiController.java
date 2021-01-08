package com.yr.yrv1annotation.controller;


import com.yr.v1.entity.Book;
import com.yr.yrv1annotation.config.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/graceapi")
@ResponseResult
public class GraceApiController {

    @GetMapping("/id")
    public Book getGraceApi() {
        log.info("进入测试 接口......");
        Book book = new Book();
        book.setName("1");
        return book;
    }
}
