package com.prb;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.retry.annotation.EnableRetry;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableRetry
@MapperScan(basePackages = "com.prb.mapper")
@EnableCaching
@EnableSwagger2
public class RetryApplication {

    public static void main(String[] args) {

        SpringApplication.run(RetryApplication.class,args);
    }
}
