package com.npaw.techtest.plugindataservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;


@SpringBootApplication
@EnableConfigurationProperties
public class PluginDataServiceApplication
{

    public static void main(final String[] args)
    {
        SpringApplication.run(PluginDataServiceApplication.class, args);
    }

}
