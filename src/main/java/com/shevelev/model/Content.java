package com.shevelev.model;

import java.util.HashMap;
import java.util.Map;

/**
 * The content of the file that contains the path to
 * the file and the page map of the file.
 */
public class Content {
    private String pathToFile;
    private Map<String, Page> contentOfFile = new HashMap<>();

    public Content(String pathToFile) {
        this.pathToFile = pathToFile;
    }

    public String getPathToFile() {
        return pathToFile;
    }

    /**
     * Get page by key
     *
     * @param key the key whose associated value is to be returned
     * @return page value
     */
    public Page getPageFromContent(String key) {
        return contentOfFile.get(key);
    }

    /**
     * Add page to the content
     *
     * @param key   key with which the specified value is to be associated
     * @param value value to be associated with the specified key
     */
    public void addPageToContent(String key, Page value) {
        contentOfFile.put(key, value);
    }
}
