package com.shevelev.config;

import com.shevelev.model.CacheServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan(basePackages = "com.shevelev")
public class AppConfig {

    @Bean
    public CacheServer cacheContent() {
        return new CacheServer();
    }

}
