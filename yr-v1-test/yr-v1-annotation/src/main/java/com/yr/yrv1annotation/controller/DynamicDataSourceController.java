package com.yr.yrv1annotation.controller;

import com.alibaba.fastjson.JSONObject;
import com.yr.v1.entity.Book;
import com.yr.v1.entity.City;
import com.yr.yrv1annotation.service.impl.DynamicDataSourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/dynamicDataSource")
public class DynamicDataSourceController {

    @Autowired
    DynamicDataSourceService dynamicDataSourceService;

    @GetMapping("/findAllCity")
    public String findAllCity() {
        List<City> allCity = dynamicDataSourceService.findAllCity();
        System.out.println("findAllCity: " + JSONObject.toJSONString(allCity));

        List<Book> allBook = dynamicDataSourceService.findAllBook();
        System.out.println("findAllBook: " + JSONObject.toJSONString(allBook));

        // 插入数据
        dynamicDataSourceService.insertBook();

        return "成功返回数据";
    }

    @GetMapping("/getBookById")
    public String getBookById() {
        Book book = dynamicDataSourceService.getBookById();
        System.out.println("book: " + JSONObject.toJSONString(book));
        return "成功返回数据";
    }

}
