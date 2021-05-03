package com.mponsa.app.core.usecases.impl;

import com.mponsa.app.core.entities.FileFilter;
import com.mponsa.app.core.entities.FileSearcher;
import com.mponsa.app.core.usecases.ConvertJavaFilesIntoSearchableFiles;
import com.mponsa.app.core.usecases.GetFilesFromDirectory;

import java.io.File;

import java.util.List;

public class GetFilesFromDirectoryImpl implements GetFilesFromDirectory {
    private FileFilter filter;
    private ConvertJavaFilesIntoSearchableFiles converter;

    public GetFilesFromDirectoryImpl(FileFilter filter, ConvertJavaFilesIntoSearchableFiles converter){
        this.filter = filter;
        this.converter = converter;
    }

    @Override
    public List<FileSearcher> get(String path) throws Exception {
        File dir = new File(path);
        File[] files = this.getFilesFromDirectory(dir);

        if(files.length == 0){
            throw new Exception("No files matching filter in directory specified");
        }

        return converter.run(files);
    }

    private File[] getFilesFromDirectory(File dir){
        if(this.filter != null) {
            return dir.listFiles(this.filter);
        }

        return  dir.listFiles();
    }
}




