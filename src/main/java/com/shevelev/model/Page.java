package com.shevelev.model;

public class Page {
    private int startPageToContentOfFile;
    private int endPageToContentOfFile;
    private String contentOfFile;

    public Page(int startPageToContentOfFile, int endPageToContentOfFile, String contentOfFile) {
        this.startPageToContentOfFile = startPageToContentOfFile;
        this.endPageToContentOfFile = endPageToContentOfFile;
        this.contentOfFile = contentOfFile;
    }

    public int getStartPageToContentOfFile() {
        return startPageToContentOfFile;
    }

    public void setStartPageToContentOfFile(int startPageToContentOfFile) {
        this.startPageToContentOfFile = startPageToContentOfFile;
    }

    public int getEndPageToContentOfFile() {
        return endPageToContentOfFile;
    }

    public void setEndPageToContentOfFile(int endPageToContentOfFile) {
        this.endPageToContentOfFile = endPageToContentOfFile;
    }

    public String getContentOfFile() {
        return contentOfFile;
    }

    public void setContentOfFile(String contentOfFile) {
        this.contentOfFile = contentOfFile;
    }
}
