package com.shevelev.model;

import java.util.Map;

/**
 * The client server model in which the content cache is located.
 */
public class CacheServer {
    private Map<String, Content> cacheContent ;

    public CacheServer(Map<String, Content> cacheContent) {
        this.cacheContent = cacheContent;
    }

    public Map<String, Content> getCacheContent() {
        return cacheContent;
    }
}
