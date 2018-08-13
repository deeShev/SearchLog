package com.shevelev.controller;

import com.shevelev.model.FileList;
import com.shevelev.service.SearchService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * File Search Rest Controller with the specified extension
 * http://localhost:8080/search?query=${searchText}&rootPath=${rootPath}&extension=${extension}
 */
@RestController
public class SearchController {
    private static final Logger LOG = LoggerFactory.getLogger(SearchController.class);
    private final SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @GetMapping("/search")
    ResponseEntity<List<String>> search(@RequestParam("query") String query,
                                        @RequestParam("rootPath") String rootPath,
                                        @RequestParam("extension") String extension) {
        LOG.info("The user searches for files with " + extension + " extension of " + rootPath);
        FileList fileList = new FileList(searchService.findPaths(rootPath, extension, query));
        LOG.info("User has received paths ", fileList.getPaths());
        return new ResponseEntity<>(fileList.getPaths(), HttpStatus.OK);
    }
}