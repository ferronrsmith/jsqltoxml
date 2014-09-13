package com.uwi.utils;

import java.io.File;
import java.util.List;

/**
 * Simple FileWalker class that is responsible for finding all the files within a directory and it's subdirectories
 */
public class FileWalker {

    public void walk(File file, String ext, List<File> results) {
        try {
            walk(file.getCanonicalPath(), ext, results);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void walk(String path, String ext, List<File> results) {
        File root = new File(path);
        File[] list = root.listFiles();

        if (list == null) {
            return;
        }

        for (File f : list) {
            if (f.isDirectory()) {
                walk(f.getAbsolutePath(), ext,  results);
                System.out.println("Dir:" + f.getAbsoluteFile());
            } else {
                System.out.println("File:" + f.getAbsoluteFile());
                if(getExt(f.getAbsolutePath()).equalsIgnoreCase(ext)) {
                    results.add(f);
                }
            }
        }
    }

    public String getExt(String path) {
        int idx = path.lastIndexOf(".");
        if(idx > 0) {
            return path.substring(idx+1);
        } else {
            return "";
        }
    }
}
