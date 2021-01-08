package com.yr.yrv1productconsumer.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.yr.api.product.IProductService;
import com.yr.v1.entity.City;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Reference
    private IProductService productService;

    @GetMapping("/get")
    public City test() {
        System.out.println("调用成功");
        City city = productService.selectByPrimaryKey(1L);
        System.out.println("----" + city.toString());
        return city;
    }

}
