package com.shevelev.controller;

import com.shevelev.model.Page;
import com.shevelev.service.ContentServiceImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class ContentControllerTest {
    @Mock
    private ContentServiceImpl mockContentService;

    @InjectMocks
    private ContentController contentController;

    @Test
    public void content() {
        when(mockContentService.getContentOfFilePath(anyString(), anyInt(), anyInt()))
                .thenReturn(new Page(0, 5, "splat"));

        ResponseEntity<String> content = contentController.content("/home/video", 0, 5);

        verify(mockContentService, times(1)).getContentOfFilePath("/home/video", 0, 5);
        assertEquals(200, content.getStatusCodeValue());
        assertEquals("splat", content.getBody());
    }

    @Test
    public void negativeContent() {
        when(mockContentService.getContentOfFilePath(anyString(), anyInt(), anyInt()))
                .thenReturn(null);

        ResponseEntity<String> content = contentController.content("/home/video", 5, 10);

        verify(mockContentService, times(1)).getContentOfFilePath("/home/video", 5, 10);

        assertEquals(404, content.getStatusCodeValue());
        assertEquals("Error, you have exceeded the range of the file!", content.getBody());
    }
}