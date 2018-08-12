package com.shevelev.service;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Search service implementation
 */
@Service
public class SearchServiceImpl implements SearchService {
    private static final Logger LOG = LoggerFactory.getLogger(SearchServiceImpl.class);

    /**
     * Finding File Paths
     *
     * @param rootPath  the root of the folder from which the search begins
     * @param extension extension file
     * @param query     search text in the file
     * @return list of file paths
     */
    public List<String> findPaths(String rootPath, String extension, String query) {
        return findByTextInFiles(query, findFileFromExtension(rootPath, new String[]{extension}));
    }

    /**
     *  Find files with the specified extension
     * @param rootPath the root of the folder from which the search begins
     * @param extension extension file
     * @return list of files with the specified extension
     */
    private List<File> findFileFromExtension(String rootPath, String[] extension) {
        return new ArrayList<>(FileUtils.listFiles(new File(rootPath), extension, true));
    }

    /**
     * Search for text in the list of files received
     * @param query search text in the file
     * @param files list of files with the specified extension
     * @return list of files with query
     */
    private List<String> findByTextInFiles(String query, List<File> files) {
        List<String> result = new ArrayList<>();
        try {
            if (files.size() == 0) return result;
            for (File currentFile : files)
                if (FileUtils.readFileToString(currentFile, StandardCharsets.UTF_8).contains(query)) {
                    result.add(currentFile.getAbsolutePath());
                }
        } catch (IOException e) {
            LOG.error("Error searching for text in files!", e);
        }
        return result;
    }
}
