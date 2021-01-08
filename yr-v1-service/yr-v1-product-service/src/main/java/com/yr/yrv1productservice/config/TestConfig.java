package com.yr.yrv1productservice.config;

import com.yr.v1.entity.City;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfig {

    @Bean
    public City city() {
        return new City(2L, "福建", "厦门", "集美、思明");
    }

}
