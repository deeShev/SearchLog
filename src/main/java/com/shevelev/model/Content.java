package com.shevelev.model;

import java.util.Map;

public class Content {
    private String pathToFile;
    private Map<String, Page> contentOfFile;

    public Content(String pathToFile, Map<String, Page> contentOfFile) {
        this.pathToFile = pathToFile;
        this.contentOfFile = contentOfFile;
    }

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
