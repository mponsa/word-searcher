package com.mponsa.app.core.usecases;

import com.mponsa.app.core.entities.FileSearcher;

import java.io.File;
import java.util.List;

public interface ConvertJavaFilesIntoSearchableFiles {
    /**
     * Receives an array of Java IO Files and converts them into FileSearcher objects.
     * @param files Array of files
     * @return List of FileSearchers.
     */
    public List<FileSearcher> run(File[] files);
}
