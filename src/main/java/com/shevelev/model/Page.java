package com.shevelev.model;

import java.util.Objects;

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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Page page = (Page) o;
        return startPageToContentOfFile == page.startPageToContentOfFile &&
                endPageToContentOfFile == page.endPageToContentOfFile &&
                Objects.equals(contentOfFile, page.contentOfFile);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startPageToContentOfFile, endPageToContentOfFile, contentOfFile);
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
