package com.shevelev.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class CacheTest {
    private Cache cache;
    private Content content;

    @Before
    public void setMapContentOfFile() {
        cache = new Cache();
        content = new Content("/home");
        content.addPageToContent("03", new Page(0, 3, "den"));

        cache.addContentToCache("03", content);
    }

    @Test
    public void clearCache() {
        assertEquals(content, cache.getContentFromCache("03"));
        cache.clearCache();
        assertNull(cache.getContentFromCache("03"));

    }

    @Test
    public void getContentFromCache() {
        Content testContent = cache.getContentFromCache("03");
        assertEquals(content, testContent);
    }

    @Test
    public void addContentToCache() {
        Content testContent = new Content("/home");
        testContent.addPageToContent("36", new Page(3, 6, "iss"));
        cache.addContentToCache("36", testContent);
        assertEquals(cache.getContentFromCache("36"), testContent);
    }
}