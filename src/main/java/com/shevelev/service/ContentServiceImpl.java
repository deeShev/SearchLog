package com.shevelev.service;

import com.shevelev.model.CacheServer;
import com.shevelev.model.Content;
import com.shevelev.model.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private CacheServer cacheServer;

    private static final int SIZE_ONE_PAGE = 3;
    private int startPositionPage;
    private int endPositionPage;
    private Content contentFile;

    @Override
    public Page getContentOfFilePath(String pathToFile, int startContent, int endContent) {
        Page resultPage;
        String keySearchContent = startContent + "" + endContent;
        contentFile = cacheServer.getCacheContent().get(keySearchContent);
        if (contentFile != null) {
            if (contentFile.getPathToFile().equals(pathToFile)) {
                resultPage = contentFile.getContentOfFile().get(keySearchContent);
            } else {
                contentFile = new Content(pathToFile, new HashMap<>());
                resultPage = formContent(pathToFile,startContent, endContent);
            }
        } else {
            contentFile = new Content(pathToFile, new HashMap<>());
            resultPage = formContent(pathToFile,startContent, endContent);
        }
        return resultPage;
    }

    private Page formContent(String pathToFile,int startContent, int endContent) {
        String keySearchContent = startContent + "" + endContent;
        try (BufferedReader in = Files.newBufferedReader(Paths.get(pathToFile))) {
            StringBuilder contentPage = new StringBuilder();
            String tmpContent;
            startPositionPage = 0;
            endPositionPage = SIZE_ONE_PAGE;

            while ((tmpContent = in.readLine()) != null) {
                tmpContent += '\n';
                formPage(contentPage, tmpContent);
                if (startPositionPage == endContent) {
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            cacheServer.getCacheContent().put(keySearchContent, contentFile);
        }

        return contentFile.getContentOfFile().get(keySearchContent);
    }

    private void formPage(StringBuilder contentPage, String tmpContent) {
        Map<String, Page> contentOfFileHashMap = contentFile.getContentOfFile();

        if (contentPage.length() + tmpContent.length() <= SIZE_ONE_PAGE) {
            contentPage.append(tmpContent);
        } else {
            StringBuilder intervalTmpContent = new StringBuilder(contentPage);
            intervalTmpContent.append(tmpContent);
            contentPage.setLength(0);

            contentPage.append(intervalTmpContent, 0, SIZE_ONE_PAGE);
            intervalTmpContent.delete(0, SIZE_ONE_PAGE);

            contentOfFileHashMap.put(startPositionPage + "" + endPositionPage, new Page(startPositionPage, endPositionPage, contentPage.toString()));
            startPositionPage = endPositionPage;
            endPositionPage = startPositionPage + SIZE_ONE_PAGE;
            contentPage.setLength(0);
            contentPage.append(intervalTmpContent);
        }
    }
}
