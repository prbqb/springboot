package com.prb.config;

import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.DocumentationBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.ApiSelectorBuilder;
import springfox.documentation.spring.web.plugins.Docket;

@Configuration
public class SwaggerConfig {


    public Docket docket(){

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.prb.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    public ApiInfo apiInfo(){

        return ApiInfo.DEFAULT;
    }

}
