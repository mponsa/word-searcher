package com.mponsa.app.core.usecases.impl;

import com.mponsa.app.core.entities.FileSearcher;
import com.mponsa.app.core.usecases.ConvertJavaFilesIntoSearchableFiles;

import java.io.File;
import java.io.FileNotFoundException;
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
                Scanner reader = new Scanner(file);
                while (reader.hasNextLine()) {
                    text += reader.nextLine();
                }
                reader.close();
            } catch (FileNotFoundException e) {
                return null;
            }

            return new FileSearcher(file.getAbsolutePath(), text);
        }).collect(Collectors.toList());
    }
}
