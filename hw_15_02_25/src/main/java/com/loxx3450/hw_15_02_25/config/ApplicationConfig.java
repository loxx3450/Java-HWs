package com.loxx3450.hw_15_02_25.config;


import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = { "com.loxx3450.hw_15_02_25.repositories", "com.loxx3450.hw_15_02_25.services" })
public class ApplicationConfig {

}
