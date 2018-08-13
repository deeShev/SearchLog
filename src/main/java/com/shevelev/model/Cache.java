package com.shevelev.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.HashMap;
import java.util.Map;

/**
 * The client server model in which the content cache is located.
 */
public class Cache {
    private static final Logger LOG = LoggerFactory.getLogger(Cache.class);

    private Map<String, Content> cacheContent = new HashMap<>();

    /**
     * The procedure for clearing the cache, every 5 minutes
     */
    @Scheduled(fixedRate = 300000)
    public void clearCache() {
        LOG.info("Begin cleaning the cache!", cacheContent.toString());
        cacheContent.clear();
        LOG.info("Cache is clean!", cacheContent.toString());
    }

    /**
     * Get content by key
     *
     * @param key the key whose associated value is to be returned
     * @return content value
     */
    public Content getContentFromCache(String key) {
        return cacheContent.get(key);
    }

    /**
     * Add content to the cache of our server (cacheContent)
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    public void addContentToCache(String key, Content value) {
        cacheContent.put(key, value);
    }
}