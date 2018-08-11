package com.shevelev.model;

import java.util.Map;

public class CacheServer {
    private Map<String, Content> cacheContent ;

    public Map<String, Content> getCacheContent() {
        return cacheContent;
    }

    public void setCacheContent(Map<String, Content> cacheContent) {
        this.cacheContent = cacheContent;
    }
}
