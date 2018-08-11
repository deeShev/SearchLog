package com.shevelev.controller;


import com.shevelev.model.Page;
import com.shevelev.service.ContentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {
    @Autowired
    ContentServiceImpl contentService;

    @GetMapping("/content")
    ResponseEntity<String> content(@RequestParam("pathFile") String pathFile,
                                  @RequestParam("startContent") int startContent,
                                  @RequestParam("endContent") int endContent) {

        Page resultPage = contentService.getContentOfFilePath(pathFile,startContent,endContent);

        return new ResponseEntity<>(resultPage.getContentOfFile(), HttpStatus.OK);
        //http://localhost:8080/search?query=denis&path=/home/denis/Programs/SearchLog&extension=doc
        /*."/home/denis/Programs/SearchLog/test.log"
        "/home/denis/Programs/SearchLog/src/test.log"
         */
    }
}
