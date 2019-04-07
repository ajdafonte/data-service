package com.npaw.techtest.plugindataservice.config;

import java.util.Collections;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@Configuration
@EnableSwagger2
public class SwaggerConfig
{
    @Bean
    public Docket api()
    {
        return new Docket(DocumentationType.SWAGGER_2)
            .groupName("Plugin Data Service")
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.npaw.techtest.plugindataservice"))
            .build()
            .useDefaultResponseMessages(false)
            .apiInfo(apiInfo());
    }

    /**
     * API Info as it appears on the swagger-ui page
     */
    private ApiInfo apiInfo()
    {
        return new ApiInfo(
            "Plugin Data Service",
            "Plugin Data Service API.<BR/>"
                + "<P>Simple component that allows to handle requests of NPAW plugins.</P>",
            "0.1.0",
            "API terms of service URL",
            new Contact("Alejandro Caires", "", ""),
            "License of API",
            "API license URL",
            Collections.emptyList()
        );
    }

}
