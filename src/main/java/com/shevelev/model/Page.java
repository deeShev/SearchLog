package com.shevelev.model;

public class Page {
    private String startPageToContentOfFile;
    private String endPageToContentOfFile;
    private String contentOfFile;

    public Page(String startPageToContentOfFile, String endPageToContentOfFile, String contentOfFile) {
        this.startPageToContentOfFile = startPageToContentOfFile;
        this.endPageToContentOfFile = endPageToContentOfFile;
        this.contentOfFile = contentOfFile;
    }

    public String getStartPageToContentOfFile() {
        return startPageToContentOfFile;
    }

    public void setStartPageToContentOfFile(String startPageToContentOfFile) {
        this.startPageToContentOfFile = startPageToContentOfFile;
    }

    public String getEndPageToContentOfFile() {
        return endPageToContentOfFile;
    }

    public void setEndPageToContentOfFile(String endPageToContentOfFile) {
        this.endPageToContentOfFile = endPageToContentOfFile;
    }

    public String getContentOfFile() {
        return contentOfFile;
    }

    public void setContentOfFile(String contentOfFile) {
        this.contentOfFile = contentOfFile;
    }
}
