package com.shevelev.controller;

import com.shevelev.model.Page;
import com.shevelev.service.ContentServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 *  Rest controller to get the page
 *  http://localhost:8080/content?pathFile=${pathFile}&startContent=${startContent}&endContent=${endContent}
 */
@RestController
public class ContentController {
    private static final Logger LOG = LoggerFactory.getLogger(ContentController.class);
    private final ContentServiceImpl contentService;

    @Autowired
    public ContentController(ContentServiceImpl contentService) {
        this.contentService = contentService;
    }

    @GetMapping("/content")
    ResponseEntity<String> content(@RequestParam("pathFile") String pathFile,
                                   @RequestParam("startContent") int startContent,
                                   @RequestParam("endContent") int endContent) {


        LOG.info("The user receives the content along the path file", pathFile);

        Page resultPage = contentService.getContentOfFilePath(pathFile, startContent, endContent);
        LOG.info("The user received the content ", resultPage);
        if (resultPage == null) {
            return new ResponseEntity<>("Error, you have exceeded the range of the file!", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(resultPage.getContentOfFile(), HttpStatus.OK);
        }
    }
}
