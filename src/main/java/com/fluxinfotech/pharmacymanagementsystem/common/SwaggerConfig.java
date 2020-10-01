package com.fluxinfotech.pharmacymanagementsystem.common;

import com.google.common.base.Predicate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Collections;

import static com.google.common.base.Predicates.or;

@EnableSwagger2
@Configuration
public class SwaggerConfig {

    @Bean
    public Docket swaggerConfiguration() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("pharmacy-management-api")
                .select()
                .paths(postPaths())
                .apis(RequestHandlerSelectors.basePackage("com.fluxinfotech"))
                .build()
                .apiInfo(apiDetails());
    }

    private Predicate<String> postPaths() {
        return or(PathSelectors.any());
    }

    private ApiInfo apiDetails() {
        return new ApiInfo(
                "Pharmacy management API",
                "Pharmacy management API",
                "1.0",
                "Private use",
                new springfox.documentation.service.Contact("Pralhad Pawar", "http://www.google.com/", "sushant.sk911@gmail.com"),
                "API License",
                "http://google.com",
                Collections.emptyList());


    }

}
