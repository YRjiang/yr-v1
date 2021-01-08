package com.yr.v1.mapper;

import com.yr.v1.common.base.IBaseDao;
import com.yr.v1.entity.City;
import org.apache.ibatis.annotations.Mapper;

/**
 * 城市 DAO 接口类
 *
 * Created by bysocket on 07/02/2017.
 */
@Mapper
public interface CityMapper extends IBaseDao<City> {

    /**
     * 根据城市名称，查询城市信息
     *
     * @param cityName 城市名
     */
/*    City findByName(@Param("cityName") String cityName);

    City findById(@Param("id") Integer id);

    @Select("select * from city where id=#{id}")
    public City getCityById(Integer id);

    @Update("update city set city_name=#{cityName}, province_id=#{provinceId}, description=#{description} where id=#{id}")
    void updateCityById(City city);

    @Delete("delete from city where id=#{id}")
    public void deleteCityById(Integer id);

    @Insert("insert into city(city_name, province_id, description) values(#{cityName}, #{provinceId}, #{description})")
    public void insertCity(City city);

    @Select("select * from city")
    public List<City> findAllCity();*/
}
