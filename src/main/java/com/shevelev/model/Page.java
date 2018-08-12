package com.shevelev.model;

/**
 * The page of the file that contains the beginning
 * and end of the page of the file and the page's content.
 */
public class Page {
    private int startPageToContentOfFile;
    private int endPageToContentOfFile;
    private String contentOfFile;

    public Page(int startPageToContentOfFile, int endPageToContentOfFile, String contentOfFile) {
        this.startPageToContentOfFile = startPageToContentOfFile;
        this.endPageToContentOfFile = endPageToContentOfFile;
        this.contentOfFile = contentOfFile;
    }

    public String getContentOfFile() {
        return contentOfFile;
    }

    @Override
    public String toString() {
        return "Page{" +
                "startPageToContentOfFile=" + startPageToContentOfFile +
                ", endPageToContentOfFile=" + endPageToContentOfFile +
                ", contentOfFile='" + contentOfFile + '\'' +
                '}';
    }
}
