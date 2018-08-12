package com.shevelev.service;

import java.util.List;

/**
 * Search for paths to files with a given extension
 */
public interface SearchService {

    List<String> findPaths(String rootPath, String extension, String query);
}
