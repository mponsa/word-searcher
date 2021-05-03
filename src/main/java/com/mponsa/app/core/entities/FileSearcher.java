package com.mponsa.app.core.entities;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileSearcher {
    private String filename;
    private String text;

    public FileSearcher(String filename, String text) {
        this.filename = filename;
        this.text = text;
    }

    private boolean searchWordInText(String word) {
        String pattern = "\\b"+word+"\\b";
        Pattern p = Pattern.compile(pattern);
        Matcher m = p.matcher(this.text);
        return m.find();
    }

    public float wordsMatched(List<String> words) {
        int wordsFound = 0;

        if (words.size() == 0) {
            return 0;
        }

        for (String word : words) {
            if (searchWordInText(word)) {
                wordsFound++;
            }
        }

        return (float) wordsFound / words.size();
    }

    public String getFilename() {
        return filename;
    }
}
