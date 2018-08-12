package com.shevelev.config;

import com.shevelev.model.CacheServer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * Class Clearing Server Cache
 */
public class ClearingCacheServer {
    private static final Logger LOG = LoggerFactory.getLogger(ClearingCacheServer.class);

    @Autowired
    CacheServer cacheServer;

    /**
     * The procedure for clearing the cache, every 10 minutes
     */
    @Scheduled(fixedRate = 600000)

    public void clearCache() {
        LOG.info("Begin cleaning the cache!", cacheServer.getCacheContent());
        cacheServer.getCacheContent().clear();
        LOG.info("Cache is clean!", cacheServer.getCacheContent());
    }
}
