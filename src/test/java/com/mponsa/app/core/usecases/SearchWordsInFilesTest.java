package com.mponsa.app.core.usecases;

import com.mponsa.app.core.entities.FileSearcher;
import com.mponsa.app.core.usecases.impl.SearchWordsInFilesImpl;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class SearchWordsInFilesTest {

    @Test
    public void TestInstantiateUseCaseReturnsNotNull(){
        SearchWordsInFiles searcher = SearchWordsInFilesImpl.getInstance();

        assertNotNull(searcher);
    }

    @Test
    public void TestInstantiateUseCaseReturnsSameInstance(){
        SearchWordsInFiles searcher = SearchWordsInFilesImpl.getInstance();
        SearchWordsInFiles searcher1 = SearchWordsInFilesImpl.getInstance();

        assertEquals(searcher, searcher1);
    }

    @Test
    public void TestUseCaseReturnsMapWithResults() throws Exception{
        SearchWordsInFiles searcher = SearchWordsInFilesImpl.getInstance();

        FileSearcher test1 = new FileSearcher("file1.txt","Hola a todos!");
        FileSearcher test2 = new FileSearcher("file2.txt","¡Qué buen desafío!");

        List files = new ArrayList<FileSearcher>(){{
            add(test1);
            add(test2);
        }};

        List words = new ArrayList<String>(){{
            add("Hola");
            add("Buen");
            add("día");
        }};


        Map<Integer,Float> result = searcher.run(files,words);

        assertNotNull(result);
        assertNotNull(result.get("file1.txt"));
        assertNotNull(result.get("file2.txt"));
    }
}
