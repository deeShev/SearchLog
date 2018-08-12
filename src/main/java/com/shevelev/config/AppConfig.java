package com.shevelev.config;

import com.shevelev.model.CacheServer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.util.HashMap;

/**
 * Application context configuration
 */
@Configuration
@EnableScheduling
@ComponentScan(basePackages = "com.shevelev")
public class AppConfig {

    /**
     * Application cache bins
     * @return instance of a class CacheServer
     */
    @Bean
    public CacheServer cacheContent() {
        return new CacheServer(new HashMap<>());
    }

    /**
     * Bean cache cleaning
     * @return instance of a class ClearingCacheServer
     */
    @Bean
    public ClearingCacheServer clearingCacheServer(){
        return new ClearingCacheServer();
    }

}
