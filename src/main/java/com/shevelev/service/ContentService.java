package com.shevelev.service;

import com.shevelev.model.Page;

import java.io.IOException;

/**
 * Service for receiving content
 */
public interface ContentService {

    Page getContentOfFilePath(String pathToFile, int startContent, int endContent) throws IOException;
}
