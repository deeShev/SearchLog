package com.shevelev.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContentController {

    @GetMapping("/content")
    ResponseEntity<String> search(@RequestParam("pathFile") String pathFile,
                                  @RequestParam("startContent") String startContent,
                                  @RequestParam("endContent") String endContent) {


        //return new ResponseEntity<>(new FileList(paths), HttpStatus.OK);
        return null;
        //http://localhost:8080/search?query=denis&path=/home/denis/Programs/SearchLog&extension=doc
        /*."/home/denis/Programs/SearchLog/test.log"
        "/home/denis/Programs/SearchLog/src/test.log"
         */
    }
}
