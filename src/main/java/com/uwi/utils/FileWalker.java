package com.uwi.utils;

import java.io.File;
import java.util.List;

/**
 * <code>
 *The **FileWalker** class provide methods to recursively iterate over a directory and its subdirectories
 *for all files that match the specified `extension`.
 * </code>
 */
public final class FileWalker {

    /**
     * <code>
     * Returns a list of files that match the given extensions a specific directory & its subdirectories
     *
     *<pre>
     * /sample.java
     * /sample.txt
     * /sample/sample5.java
     * /sample/sample4.txt
     *
     * //-->
     * sample.java
     * sample5.java
     *</pre>
     * </code>
     * @param file - root file or directory
     * @param ext - extension of files
     * @param results - list of files with the specified extension
     */
    public void walk(File file, String ext, List<File> results) {
        try {
            walk(file.getCanonicalPath(), ext, results);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * <code>
     * Returns a list of files that match the given extensions a specific directory & its subdirectories
     *
     *<pre>
     * /sample.java
     * /sample.txt
     * /sample/sample5.java
     * /sample/sample4.txt
     *
     * //-->
     * sample.java
     * sample5.java
     *</pre>
     * </code>
     * @param path - file or directory path
     * @param ext - extension of files
     * @param results - list of files with the specified extension
     */
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
                if(getExtension(f.getAbsolutePath()).equalsIgnoreCase(ext)) {
                    results.add(f);
                }
            }
        }
    }

    /**
     * <code>
     *     Returns the extension for a given file path/name
     *
     * ```java
     *  getExtension("/tmp/junit1087035737113730538/sample4.java");
     *  // returns java
     *
     *  getExtension("/tmp/junit1087035737113730538/sample5.txt");
     *  // returns txt
     * ```
     * </code>
     * @param path file path
     * @return the file extension or an extension if no extension is detected
     */
    public String getExtension(String path) {
        int idx = path.lastIndexOf(".");
        if(idx > 0) {
            return path.substring(idx+1);
        } else {
            return "";
        }
    }
}
