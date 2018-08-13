package com.shevelev.config;

import com.shevelev.model.Cache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

/**
 * Application context configuration
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.shevelev")
public class AppConfig {

    /**
     * Application cache bins
     * @return instance of a class Cache
     */
    @Bean
    public Cache cacheContent() {
        return new Cache();
    }
}
