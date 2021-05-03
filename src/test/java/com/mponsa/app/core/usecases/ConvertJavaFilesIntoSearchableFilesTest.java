package com.mponsa.app.core.usecases;

import com.mponsa.app.core.entities.FileSearcher;
import com.mponsa.app.core.usecases.impl.ConvertJavaFilesIntoSearchableFilesImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ConvertJavaFilesIntoSearchableFilesTest {
    File file1, file2;

    @Rule
    public TemporaryFolder folder = new TemporaryFolder();

    @Before
    public void setUp() {
        try {
            file1 = folder.newFile( "file1.txt" );
            file2 = folder.newFile( "file2.txt" );

            FileWriter fw1 = new FileWriter(file1);
            BufferedWriter bw1 = new BufferedWriter(fw1);
            bw1.write( "Hola a todos!");
            bw1.close();

            FileWriter fw2 = new FileWriter(file2);
            BufferedWriter bw2 = new BufferedWriter(fw2);
            bw2.write( "¿Cómo están?");
            bw2.close();
        }
        catch( IOException ioe ) {
            System.err.println("Error creating temp file");
        }
    }


    @Test
    public void TestInstantiateUseCaseReturnsNotNull(){
        ConvertJavaFilesIntoSearchableFiles converter = new ConvertJavaFilesIntoSearchableFilesImpl();

        assertNotNull(converter);
    }

    @Test
    public void TestFileConversionReturnsFileList(){
        ConvertJavaFilesIntoSearchableFiles converter = new ConvertJavaFilesIntoSearchableFilesImpl();
        File[] filesToConvert = new File[]{file1,file2};

        List<FileSearcher> files = converter.run(filesToConvert);

        assertNotNull(files);
        assertEquals(files.size(),2);
    }

}
