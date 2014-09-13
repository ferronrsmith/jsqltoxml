package com.uwi.utils;

import com.uwi.utils.FileWalker;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.TemporaryFolder;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class FileWalkerTest {

    @Rule
    public TemporaryFolder folder= new TemporaryFolder();


    @Test
    public void testWalkStringParam() throws Exception {
        createAssets();
        ArrayList<File> result = new ArrayList<File>();
        new FileWalker().walk(folder.getRoot().getCanonicalPath(), "txt", result);
        assertEquals(7, result.size());

    }

    @Test
    public void testWalkFileParam() throws Exception {
        createAssets();
        ArrayList<File> result = new ArrayList<File>();
        new FileWalker().walk(folder.getRoot(), "java", result);
        assertEquals(2, result.size());

    }

    @Test
    public void testExtension() {
        assertEquals("java", new FileWalker().getExt("/tmp/junit1087035737113730538/sample4.java"));
        assertEquals("txt", new FileWalker().getExt("/tmp/junit1087035737113730538/sample5.txt"));
    }

    private void createAssets() throws IOException {
        folder.newFolder("sample1");
        folder.newFolder("sample2");
        folder.newFile("sample1.txt");
        folder.newFile("sample2.java");
        folder.newFile("sample3.txt");
        folder.newFile("sample4.java");
        folder.newFile("sample5.txt");

        folder.newFile("sample1/sample6.txt");
        folder.newFile("sample1/sample10.txt");

        folder.newFile("sample2/sample7.txt");
        folder.newFile("sample2/sample8.txt");
    }
}
