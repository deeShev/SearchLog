package com.shevelev.model;

import java.util.Map;

/**
 * The content of the file that contains the path to
 * the file and the page map of the file.
 */
public class Content {
    private String pathToFile;
    private Map<String, Page> contentOfFile;

    public Content(String pathToFile, Map<String, Page> contentOfFile) {
        this.pathToFile = pathToFile;
        this.contentOfFile = contentOfFile;
    }
    public String getPathToFile() {
        return pathToFile;
    }

    public Map<String, Page> getContentOfFile() {
        return contentOfFile;
    }
}
