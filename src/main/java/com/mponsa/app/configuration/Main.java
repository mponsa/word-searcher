package com.mponsa.app.configuration;

import com.mponsa.app.core.entities.FileFilter;
import com.mponsa.app.core.entities.FileSearcher;
import com.mponsa.app.core.usecases.ConvertJavaFilesIntoSearchableFiles;
import com.mponsa.app.core.usecases.GetFilesFromDirectory;
import com.mponsa.app.core.usecases.SearchWordsInFiles;
import com.mponsa.app.core.usecases.impl.ConvertJavaFilesIntoSearchableFilesImpl;
import com.mponsa.app.core.usecases.impl.GetFilesFromDirectoryImpl;
import com.mponsa.app.core.usecases.impl.SearchWordsInFilesImpl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    private static FileFilter filter;
    private static ConvertJavaFilesIntoSearchableFiles converter;
    private static GetFilesFromDirectory finder;
    private static SearchWordsInFiles searcher;

    private static void setUpEnvironment(){
        System.out.println("Setting up environment..");
        filter = new FileFilter(".txt");
        converter = new ConvertJavaFilesIntoSearchableFilesImpl();
        finder = new GetFilesFromDirectoryImpl(filter,converter);
        searcher = SearchWordsInFilesImpl.getInstance();
    }

    private static void exit(){
        System.out.println("Exiting program..");
        System.exit(0);
    }

    public static void main(String[] args) throws Exception {
        if (args.length == 0) {
            throw new IllegalArgumentException("No directory given to index.");
        }

        setUpEnvironment();

        System.out.printf("Searching for .txt files in %s ... \n", args[0]);

        List<FileSearcher> filesToSearch = new ArrayList<>();

        try{
            filesToSearch = finder.get(args[0]);
        }catch(Exception exp){
            System.out.println(exp.getMessage());
            exit();
        }

        System.out.println("Welcolme to file searcher, enter query after searcher> or type :quit to exit");

        try (Scanner keyboard = new Scanner(System.in)) {
            while (true) {
                System.out.printf("search> ");
                final String line = keyboard.nextLine();

                if(line.equals(":quit")){
                    exit();
                }

                List<String> words = Arrays.asList(line.split("\\s* \\s*"));
                try{
                    Map<String,Float> result =  searcher.run(filesToSearch, words);
                    result.keySet().stream().forEach(key -> {
                        System.out.printf("%s : %d %% \n", key, Math.round(result.get(key) * 100));
                    });
                }catch(Exception exp){
                    System.out.println(exp.getMessage());
                }
            }
        }
    }
}