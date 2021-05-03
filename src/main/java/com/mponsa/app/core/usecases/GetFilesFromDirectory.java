package com.mponsa.app.core.usecases;

import com.mponsa.app.core.entities.FileSearcher;

import java.util.List;

public interface GetFilesFromDirectory {
    /**
     * Retrieves text files from a path given
     * @param path String representing directory
     * @return List of files to search inside directory
     * @throws Exception indicating invalid path
     */
    public List<FileSearcher> get(String path) throws Exception;
}
