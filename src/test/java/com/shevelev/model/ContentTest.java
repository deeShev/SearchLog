package com.shevelev.model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ContentTest {
    private Content content;

    @Before
    public  void setMapContentOfFile() {
        content = new Content("/home/");
        content.addPageToContent("03", new Page(0,3, "den"));
    }


    @Test
    public void getPageFromContent() {
        Page pageFromContent = content.getPageFromContent("03");
        assertEquals(new Page(0,3,"den"),pageFromContent);
    }

    @Test
    public void addPageToContent() {
        content.addPageToContent("36", new Page(3, 6, "iss"));
        assertEquals(new Page(3, 6, "iss"), content.getPageFromContent("36"));
    }
}