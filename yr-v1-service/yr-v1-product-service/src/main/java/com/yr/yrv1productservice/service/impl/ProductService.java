package com.yr.yrv1productservice.service.impl;

import com.alibaba.dubbo.config.annotation.Service;
import com.yr.api.product.IProductService;
import com.yr.v1.common.base.BaseServiceImpl;
import com.yr.v1.common.base.IBaseDao;
import com.yr.v1.entity.City;
import com.yr.v1.mapper.CityMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Service
@Component
public class ProductService extends BaseServiceImpl<City> implements IProductService {

    @Autowired
    private CityMapper cityMapper;

    @Override
    public IBaseDao<City> getBaseDao() {
        return cityMapper;
    }



}
