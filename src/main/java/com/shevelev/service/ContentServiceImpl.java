package com.shevelev.service;

import com.shevelev.model.Cache;
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
import java.util.Map;

/**
 * Implementing the content service
 */
@Service
public class ContentServiceImpl implements ContentService {
    private static final int SIZE_ONE_PAGE = 500; // Number of items per page
    private static final Logger LOG = LoggerFactory.getLogger(ContentServiceImpl.class);

    private final Cache cache;

    @Autowired
    public ContentServiceImpl(Cache cache) {
        this.cache = cache;
    }

    /**
     * Getting content from a file
     *
     * @param pathToFile   path to file
     * @param startContent start page
     * @param endContent   end page
     * @return page content
     */
    @Override
    public Page getContentOfFilePath(String pathToFile, int startContent, int endContent) {
        Page resultPage;
        String keySearchContent = startContent + String.valueOf(endContent);

        Content contentFile = cache.getContentFromCache(keySearchContent);

        if (contentFile != null) {
            if (contentFile.getPathToFile().equals(pathToFile)) {
                resultPage = contentFile.getContentOfFile().get(keySearchContent);
            } else {
                contentFile = new Content(pathToFile);
                resultPage = formContent(pathToFile, contentFile, startContent, endContent);
            }
        } else {
            contentFile = new Content(pathToFile);
            resultPage = formContent(pathToFile, contentFile, startContent, endContent);
        }

        return resultPage;
    }

    /**
     * Content Creation
     *
     * @param pathToFile   path to file
     * @param startContent start page
     * @param contentFile  content file
     * @param endContent   end page
     * @return page content
     */
    private Page formContent(String pathToFile, Content contentFile,
                             int startContent, int endContent) {

        String keySearchContent = startContent + String.valueOf(endContent);

        try (BufferedReader in = Files.newBufferedReader(Paths.get(pathToFile))) {

            StringBuilder contentPage = new StringBuilder();
            String tmpContent;
            int startPositionPage = 0;
            int endPositionPage = SIZE_ONE_PAGE;

            while ((tmpContent = in.readLine()) != null || contentPage.length() != 0) {

                if (tmpContent != null) {
                    tmpContent += '\n';
                } else {
                    int lastEndLines = contentPage.lastIndexOf("\n");
                    if (lastEndLines == (contentPage.length() - 1)) {
                        contentPage.deleteCharAt(lastEndLines);
                    }
                }

                startPositionPage = formPage(contentPage, tmpContent, contentFile, startPositionPage, endPositionPage);

                if (startPositionPage == endPositionPage) {
                    endPositionPage += SIZE_ONE_PAGE;
                }

                if (startPositionPage == endContent) {
                    break;
                }
            }
        } catch (IOException e) {
            LOG.error("Error retrieving page content! ", e);
        } finally {
            cache.addContentToCache(keySearchContent, contentFile);
        }
        return contentFile.getContentOfFile().get(keySearchContent);
    }

    /**
     * Forming a page
     *
     * @param contentPage page in file
     * @param tmpContent  temporary intermediate content
     */
    private int formPage(StringBuilder contentPage, String tmpContent, Content contentFile,
                         int startPositionPage, int endPositionPage) {

        if ((tmpContent != null) && (contentPage.length() + tmpContent.length() <= SIZE_ONE_PAGE)) {
            contentPage.append(tmpContent);
        } else {
            startPositionPage = addContentOfFileToPage(contentPage, tmpContent, contentFile, startPositionPage, endPositionPage);
        }
        return startPositionPage;
    }

    /**
     * Adding content from a file to a page
     *
     * @param contentPage page in file
     * @param tmpContent  temporary intermediate content
     */
    private int addContentOfFileToPage(StringBuilder contentPage, String tmpContent, Content contentFile,
                                       int startPositionPage, int endPositionPage) {
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

        contentOfFileHashMap.put(startPositionPage + String.valueOf(endPositionPage), new Page(startPositionPage, endPositionPage, contentPage.toString()));
        startPositionPage += SIZE_ONE_PAGE;

        contentPage.setLength(0);
        contentPage.append(intervalTmpContent);

        return startPositionPage;
    }
}
