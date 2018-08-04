package com.shevelev.controller;

import com.shevelev.model.FileList;
import com.shevelev.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
public class SearchController {
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    ResponseEntity<FileList> search(@RequestParam("query") String query,
                                    @RequestParam("path") String rootPath,
                                    @RequestParam("extension") String extension) throws IOException {

        List<String> paths = searchService.findPaths(rootPath, extension, query);
        return new ResponseEntity<>(new FileList(paths), HttpStatus.OK); //http://localhost:8080/search?query=denis&path=/home/denis/Programs/SearchLog&extension=doc
    }
}
