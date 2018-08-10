package com.shevelev.service;

import com.shevelev.model.Page;

import java.io.IOException;

public interface ContentService {

    Page getContentOfFilePath(String pathToFile, String startContent, String endContent) throws IOException;
}
