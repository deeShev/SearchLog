package com.shevelev.service;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

@Service
public class SearchServiceImpl implements SearchService {

    public List<String> findPaths(String rootPath, String extension, String query) throws IOException {
        return findByTextInFiles(query, findFileFromExtension(rootPath, new String[]{extension}));
    }

    private List<File> findFileFromExtension(String rootPath, String[] extension) {
        return new ArrayList<>(FileUtils.listFiles(new File(rootPath), extension, true));
    }

    private List<String> findByTextInFiles(String query, List<File> files) throws IOException {
        List<String> result = new ArrayList<>();
        if (files.size() == 0) return result;
        for (File currentFile : files)
            if (FileUtils.readFileToString(currentFile, StandardCharsets.UTF_8).contains(query)) {
                result.add(currentFile.getAbsolutePath());
            }

        return result;
    }
}
