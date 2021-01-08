package com.yr.yrv1productservice;

import com.yr.v1.entity.City;
import com.yr.v1.mapper.CityMapper;
import org.junit.jupiter.api.Test;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@MapperScan("com.yr.v1.mapper")
public class MapperTest {

    @Autowired
    private CityMapper cityMapper;

    @Test
    public void test() {
        City city = cityMapper.selectByPrimaryKey(1L);
        System.out.println("===============" + city);
    }

}
