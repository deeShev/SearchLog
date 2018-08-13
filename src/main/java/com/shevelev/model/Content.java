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

    public Map<String, Page> getContentOfFile() {
        return contentOfFile;
    }
}
