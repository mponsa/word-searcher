package com.mponsa.app.core.usecases;

import com.mponsa.app.core.entities.FileFilter;
import com.mponsa.app.core.entities.FileSearcher;
import com.mponsa.app.core.usecases.impl.GetFilesFromDirectoryImpl;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


@RunWith(MockitoJUnitRunner.class)
public class GetFilesFromDirectoryTest {
    File file1, file2;

    @Mock
    FileFilter filter;

    @Mock
    ConvertJavaFilesIntoSearchableFiles converter;

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
        GetFilesFromDirectory usecase = new GetFilesFromDirectoryImpl(filter,converter);

        assertNotNull(usecase);
    }

    @Test
    public void GetFilesReturnsFiles() throws Exception {
        Mockito.when(filter.accept(Mockito.any(),Mockito.any())).thenReturn(true);
        Mockito.when(converter.run(Mockito.any())).thenReturn(new ArrayList<FileSearcher>(){{
            add(new FileSearcher("test","test"));
            add(new FileSearcher("test","test"));
        }});


        GetFilesFromDirectory usecase = new GetFilesFromDirectoryImpl(filter,converter);


        List<FileSearcher> files = usecase.get(folder.getRoot().getAbsolutePath());

        assertEquals(files.size(),2);
    }

    @Test
    public void GetFilesThrowsWhenNoFiles(){
        GetFilesFromDirectory usecase = new GetFilesFromDirectoryImpl(filter,converter);
        Mockito.when(filter.accept(Mockito.any(),Mockito.any())).thenReturn(false);
        try{
            List<FileSearcher> files = usecase.get(folder.getRoot().getAbsolutePath());
        }catch(Exception exp){
            assertNotNull(exp);
            assertEquals(exp.getMessage(), "No files matching filter in directory specified");
        }
    }
}
