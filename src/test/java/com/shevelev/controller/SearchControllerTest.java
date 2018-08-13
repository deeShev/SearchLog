package com.shevelev.controller;

import com.shevelev.service.SearchService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SearchControllerTest {
    @Mock
    private SearchService mockSearchService;

    @InjectMocks
    private SearchController searchController;

    @Test
    public void search() {
        when(mockSearchService.findPaths(anyString(), anyString(), anyString()))
                .thenReturn(Arrays.asList("/home/log.log", "/home/video/log.log"));

        ResponseEntity<List<String>> search = searchController.search("test", "/home/", "log");

        verify(mockSearchService, times(1)).findPaths("/home/", "log", "test");

        assertEquals(200, search.getStatusCodeValue());
        assertEquals(2, search.getBody().size());
        assertEquals("/home/log.log", search.getBody().get(0));
        assertEquals("/home/video/log.log", search.getBody().get(1));
    }

    @Test
    public void negativeSearch() {
        when(mockSearchService.findPaths(anyString(), anyString(), anyString()))
                .thenReturn(null);

        ResponseEntity<List<String>> search = searchController.search("test", "/home/", "log");

        assertEquals(404, search.getStatusCodeValue());
        assertEquals(0, search.getBody().size());
    }
}