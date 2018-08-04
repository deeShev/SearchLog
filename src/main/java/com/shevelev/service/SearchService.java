package com.shevelev.service;

import java.io.IOException;
import java.util.List;


public interface SearchService {

    List<String> findPaths(String rootPath, String extension, String query) throws IOException;
}
