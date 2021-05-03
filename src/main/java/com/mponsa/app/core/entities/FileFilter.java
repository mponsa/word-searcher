package com.mponsa.app.core.entities;

import java.io.File;
import java.io.FilenameFilter;

public class FileFilter implements FilenameFilter{
    private String fileSuffix;

    public FileFilter(String fileSuffix){
        this.fileSuffix = fileSuffix;
    }

    @Override
    public boolean accept(File path, String name) {
        return name.endsWith(this.fileSuffix);
    }
}
