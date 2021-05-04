package com.mponsa.app.core.usecases.impl;

import com.mponsa.app.core.entities.FileSearcher;
import com.mponsa.app.core.usecases.ConvertJavaFilesIntoSearchableFiles;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import java.util.stream.Collectors;

public class ConvertJavaFilesIntoSearchableFilesImpl implements ConvertJavaFilesIntoSearchableFiles {
    @Override
    public List<FileSearcher> run(File[] files) {
        List<File> fileList = Arrays.asList(files);

        return fileList.parallelStream().map( file -> {
            String text = "";
            try {
                FileInputStream fs = new FileInputStream(file.getAbsolutePath());
                byte data[] = new byte[fs.available()];
                fs.read(data);
                fs.close();
                text = new String(data);
            } catch (IOException e) {
                return null;
            }
            return new FileSearcher(file.getAbsolutePath(), text);
        }).collect(Collectors.toList());
    }
}