package com.mponsa.app.core.entities;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class FileSearcherTest {

    @Test
    public void TestCreateFileShouldNotBeNull() {
        FileSearcher fileSearcher = new FileSearcher("file1.txt","Lorem ipsum");

        assertNotNull(fileSearcher);
    }

    @Test
    public void TestSearchWordsOk(){
        FileSearcher fileSearcher = new FileSearcher("file1.txt","Lorem ipsum");
        List list = new ArrayList<String>(){{
            add("Lorem");
        }};

        float result = fileSearcher.wordsMatched(list);

        assertEquals(1,result,0.1);
    }

    @Test
    public void TestSearchWordsOkWithOneWordFound(){
        FileSearcher fileSearcher = new FileSearcher("file2.txt","Lorem ipsum");
        List list = new ArrayList<String>(){{
            add("Lorem");
            add("Hola");
        }};


        float result = fileSearcher.wordsMatched(list);

        assertEquals(0.5,result,0.1);
    }

    @Test
    public void TestSearchWordsOkWithEmptyWordList(){
        FileSearcher fileSearcher = new FileSearcher("file3.txt","Lorem ipsum");
        List list = new ArrayList<String>();

        float result = fileSearcher.wordsMatched(list);

        assertEquals(0,result,0.1);
    }
}