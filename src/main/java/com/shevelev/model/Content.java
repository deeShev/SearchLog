package com.shevelev.model;

import java.util.Map;

public class Content {
    private static String pathToFile;
    private static boolean statusInUserSystem;
    private Map<String, Page> contentOfFile;

    public Content(String pathToFile, Map<String, Page> contentOfFile) {
        Content.pathToFile = pathToFile;
        this.contentOfFile = contentOfFile;
    }

    public Content(String pathToFile) {
        Content.pathToFile = pathToFile;
    }

    public static String getPathToFile() {
        return pathToFile;
    }

    public Map<String, Page> getContentOfFile() {
        return contentOfFile;
    }

    public static boolean isStatusInUserSystem() {
        return statusInUserSystem;
    }

    public void setStatusInUserSystem(boolean statusInUserSystem) {
        Content.statusInUserSystem = statusInUserSystem;
    }
}
