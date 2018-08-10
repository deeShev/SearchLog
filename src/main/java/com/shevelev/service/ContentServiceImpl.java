package com.shevelev.service;

import com.shevelev.model.Content;
import com.shevelev.model.Page;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.List;

public class ContentServiceImpl implements ContentService {
    private static final int SIZE_ONE_PAGE = 3;


    @Override
    public Page getContentOfFilePath(String pathToFile, String startContent, String endContent) throws IOException {
        if (!Content.getPathToFile().equals(pathToFile)) {
            if (Content.isStatusInUserSystem()) {
            }
        }


        return null;
    }

    private Content formContents(String pathToFile) throws IOException {
        HashMap<String, Page> contentOfFileHashMap = new HashMap<>();
        Content currentContent = new Content(pathToFile, contentOfFileHashMap);
        if (new File(pathToFile).isAbsolute()) {
            currentContent.setStatusInUserSystem(true);
        }

        List<String> contents = Files.readAllLines(Paths.get(pathToFile));
        StringBuilder contentPage = new StringBuilder();

        //String tmpContent = null;

        int startContent = 0;
        int endContent = SIZE_ONE_PAGE;

        for (String content : contents) {
            if (contentPage.length() <= SIZE_ONE_PAGE) {
                if (contentPage.length() + content.length() <= SIZE_ONE_PAGE) {
                    contentPage.append(content);
                } else {
                    int differenceSize = contentPage.length() + content.length() - SIZE_ONE_PAGE;
                    contentPage.append(content, 0, content.length() - differenceSize);
                    contentOfFileHashMap.put(startContent + "" + endContent, new Page(String.valueOf(startContent), String.valueOf(endContent), contentPage.toString()));
                    startContent = endContent;
                    endContent = 2 * startContent;
                    contentPage = new StringBuilder(content.substring(content.length() - differenceSize));
                    //tmpContent = content.substring(content.length() - differenceSize);
                }
            } else {

            }

        }
        return currentContent;
    }

    public static void main(String[] args) throws IOException {
        ContentServiceImpl contentService = new ContentServiceImpl();
        //contentService.getContentOfFilePath("/home/denis/Programs/SearchLog/src/test.log", "0", "500");
        //contentService.formContents("/home/denis/Programs/SearchLog/src/test.log");

    }
}
