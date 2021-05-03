package com.mponsa.app.core.usecases.impl;

import com.mponsa.app.core.entities.FileSearcher;
import com.mponsa.app.core.usecases.SearchWordsInFiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchWordsInFilesImpl implements SearchWordsInFiles {
    private static SearchWordsInFilesImpl INSTANCE;

    private SearchWordsInFilesImpl(){}

    //This could be syncronized to be thread safe.
    public static SearchWordsInFilesImpl getInstance(){
        if(INSTANCE != null){
            return INSTANCE;
        }

        INSTANCE = new SearchWordsInFilesImpl();

        return INSTANCE;
    }

    @Override
    public Map<String, Float> run(List<FileSearcher> fileSearchers, List<String> words) throws Exception {
        Map<String, Float> result = new HashMap<String, Float>();

        if(fileSearchers.size() == 0){
            throw new Exception("File list is empty, cannot perform search");
        }

        for(int i = 0; i < fileSearchers.size(); i++){
            float searchResult = fileSearchers.get(i).wordsMatched(words);
            result.put(fileSearchers.get(i).getFilename(),searchResult);
        }

        return result;
    }
}
