package com.prb.config;

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {


    @Bean
    public PaginationInterceptor paginationInterceptor(){

        return new PaginationInterceptor();
    }

}
