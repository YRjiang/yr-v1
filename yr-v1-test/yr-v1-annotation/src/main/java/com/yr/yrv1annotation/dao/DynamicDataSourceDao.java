package com.yr.yrv1annotation.dao;


import com.yr.v1.entity.City;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface DynamicDataSourceDao {

    @Select("select * from city where id=#{id}")
    public City getCityById(Integer id);

    @Update("update city set city_name=#{cityName}, province_id=#{provinceId}, description=#{description} where id=#{id}")
    void updateCityById(City city);

    @Delete("delete from city where id=#{id}")
    public void deleteCityById(Integer id);

    @Insert("insert into city(city_name, province_id, description) values(#{cityName}, #{provinceId}, #{description})")
    public void insertCity(City city);

    @Select("select * from city")
    public List<City> findAllCity();

}
