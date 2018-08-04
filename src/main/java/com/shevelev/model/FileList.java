package com.shevelev.model;

import java.util.List;

public class FileList {
    private List<String> paths;

    public FileList(List<String> paths) {
        this.paths = paths;
    }

    public List<String> getPaths() {
        return paths;
    }
}
