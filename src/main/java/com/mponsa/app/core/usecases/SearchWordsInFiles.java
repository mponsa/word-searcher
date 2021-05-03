package com.mponsa.app.core.usecases;

import com.mponsa.app.core.entities.FileSearcher;

import java.util.List;
import java.util.Map;

public interface SearchWordsInFiles {
    /**
     * Returns a map representing the result of each file lookup.
     * @param fileSearchers List of files to look into.
     * @param words List of words to search in each file.
     * @return map<String,Float> Representing File Name: Search Result.
     */
    public Map<String,Float> run(List<FileSearcher> fileSearchers, List<String> words) throws Exception;
}
