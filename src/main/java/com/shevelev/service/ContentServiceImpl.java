package com.shevelev.service;

import com.shevelev.model.CacheServer;
import com.shevelev.model.Content;
import com.shevelev.model.Page;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;

/**
 * Implementing the content service
 */
@Service
public class ContentServiceImpl implements ContentService {
    private static final int SIZE_ONE_PAGE = 10; // Number of items per page
    private static final Logger LOG = LoggerFactory.getLogger(ContentServiceImpl.class);

    private final CacheServer cacheServer;

    private int startPositionPage;
    private int endPositionPage;
    private Content contentFile;

    @Autowired
    public ContentServiceImpl(CacheServer cacheServer) {
        this.cacheServer = cacheServer;
    }

    /**
     * Getting content from a file
     * @param pathToFile path to file
     * @param startContent start page
     * @param endContent end page
     * @return page content
     */
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
                resultPage = formContent(pathToFile, startContent, endContent);
            }
        } else {
            contentFile = new Content(pathToFile, new HashMap<>());
            resultPage = formContent(pathToFile, startContent, endContent);
        }

        return resultPage;
    }

    /**
     * Content Creation
     * @param pathToFile path to file
     * @param startContent start page
     * @param endContent end page
     * @return  page content
     */
    private Page formContent(String pathToFile, int startContent, int endContent) {
        String keySearchContent = startContent + "" + endContent;
        try (BufferedReader in = Files.newBufferedReader(Paths.get(pathToFile))) {
            StringBuilder contentPage = new StringBuilder();
            String tmpContent;
            startPositionPage = 0;
            endPositionPage = SIZE_ONE_PAGE;
            while ((tmpContent = in.readLine()) != null || contentPage.length() != 0) {
                if (tmpContent != null) {
                    tmpContent += '\n';
                } else {
                    int lastEndLines = contentPage.lastIndexOf("\n");
                    if (lastEndLines == (contentPage.length() - 1)){
                        contentPage.deleteCharAt(lastEndLines);
                    }
                }
                formPage(contentPage, tmpContent);
                if (startPositionPage == endContent) {
                    break;
                }
            }
        } catch (IOException e) {
            LOG.error("Error retrieving page content! " + e);
        } finally {
            cacheServer.getCacheContent().put(keySearchContent, contentFile);
        }
        return contentFile.getContentOfFile().get(keySearchContent);
    }

    /**
     * Forming a page
     * @param contentPage page in file
     * @param tmpContent temporary intermediate content
     */
    private void formPage(StringBuilder contentPage, String tmpContent) {
        if ((tmpContent != null) && (contentPage.length() + tmpContent.length() <= SIZE_ONE_PAGE)) {
            contentPage.append(tmpContent);
        } else {
            addContentOfFileToPage(contentPage, tmpContent);
        }
    }

    /**
     * Adding content from a file to a page
     * @param contentPage page in file
     * @param tmpContent temporary intermediate content
     */
    private void addContentOfFileToPage(StringBuilder contentPage, String tmpContent) {
        int endInterval;
        Map<String, Page> contentOfFileHashMap = contentFile.getContentOfFile();
        StringBuilder intervalTmpContent = new StringBuilder(contentPage);

        if (tmpContent == null) {
            tmpContent = "";
        }
        if (contentPage.length() <= SIZE_ONE_PAGE && contentPage.length() != 0) {
            endInterval = contentPage.length();
        } else {
            endInterval = SIZE_ONE_PAGE;
        }
        intervalTmpContent.append(tmpContent);
        contentPage.setLength(0);

        contentPage.append(intervalTmpContent, 0, endInterval);
        intervalTmpContent.delete(0, endInterval);

        contentOfFileHashMap.put(startPositionPage + "" + endPositionPage, new Page(startPositionPage, endPositionPage, contentPage.toString()));
        startPositionPage += SIZE_ONE_PAGE;
        endPositionPage += SIZE_ONE_PAGE;
        contentPage.setLength(0);
        contentPage.append(intervalTmpContent);
    }
}
