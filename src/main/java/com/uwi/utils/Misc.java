package com.uwi.utils;

import com.uwi.config.Configuration;
import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

import java.io.Closeable;
import java.io.IOException;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * <code>
 * The **Misc** class defines static utility methods that is used
 * through-out the library.
 * </code>
 */
public class Misc extends Configuration {

    /**
     * <code>
     * <p>
     * Checks if a CharSequence is whitespace, empty ("") or null.
     * </p>
     * <p/>
     * ```java
     * Misc.isBlank(null);      //= true
     * Misc.isBlank("");        //= true
     * Misc.isBlank(" ");       //= true
     * Misc.isBlank("bob");     //= false
     * Misc.isBlank("  bob  "); //= false
     * ```
     * </code>
     *
     * @param cs
     *         the CharSequence to check, may be null
     *
     * @return **true** if the CharSequence is null, empty or whitespace
     *
     * @since 2.0
     */
    public static boolean isBlank(CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; i++) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <code>
     * <p>
     * Checks whether the *String* contains only digit characters.
     * </p>
     * **Null** and empty String will return **false**.
     * </code>
     *
     * @param str
     *         the <code>String</code> to check
     */
    public static boolean isDigits(String str) {
        if (Misc.isBlank(str)) {
            return false;
        }
        for (int i = 0; i < str.length(); i++) {
            if (!Character.isDigit(str.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    /**
     * <code>
     * <p>
     * Checks if a CharSequence is not empty (""), not null and not whitespace
     * only.
     * </p>
     * <p/>
     * ```java
     * Misc.isNotBlank(null);      // = false
     * Misc.isNotBlank("");        // = false
     * Misc.isNotBlank(" ");       // = false
     * Misc.isNotBlank("bob");     // = true
     * Misc.isNotBlank("  bob  "); // = true
     * ```
     * </code>
     *
     * @param cs
     *         the CharSequence to check, may be null
     *
     * @return **true** if the CharSequence is not empty and not null and
     * not whitespace
     */
    public static boolean isNotBlank(CharSequence cs) {
        return !Misc.isBlank(cs);
    }

    /**
     * <code>
     * Properly formats XML document
     * </code>
     *
     * @param xml
     *         the xml document
     *
     * @return the string
     */
    public static String prettyPrint(final String xml) {

        if (Misc.isBlank(xml)) {
            throw new RuntimeException("xml was null or blank in prettyPrint()");
        }

        final StringWriter sw;

        try {
            final OutputFormat format = OutputFormat.createPrettyPrint();
            final org.dom4j.Document document = DocumentHelper.parseText(xml);
            sw = new StringWriter();
            final XMLWriter writer = new XMLWriter(sw, format);
            // write root to avoid XML declaration
            // <pre>
            // http://stackoverflow.com/questions/5971649/dom4j-xml-declaration-in-document}
            // </pre>
            writer.write(document.getRootElement());
        } catch (Exception e) {
            throw new RuntimeException("Error pretty printing xml:\n" + xml, e);
        }
        return sw.toString().trim();
    }

    /**
     * <code>
     * Reads a file contents into a string
     * </code>
     *
     * @param path
     *         - file path
     *
     * @return string with file contents
     *
     * @throws IOException
     */
    public static String readFilesToString(String path) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, StandardCharsets.UTF_8);
    }

    /**
     * <code>
     * Save data to file.
     * </code>
     *
     * @param outFile
     *         the out file
     * @param data
     *         the data
     */
    public static void saveToFile(String outFile, List<String> data) {
        // terminate if outFile is null or empty
        if (Misc.isBlank(outFile)) {
            return;
        }
        try {
            Files.write(Paths.get(outFile), data, StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * <code>
     * Unconditionally close a <code>Closeable</code>.
     * <p/>
     * Equivalent to **Closeable#close()**, except any exceptions will be ignored.
     * This is typically used in finally blocks.
     * <p/>
     * Example code:
     * ```java
     * Closeable closeable = null;
     * try {
     * closeable = new FileReader("foo.txt");
     * // process closeable
     * closeable.close();
     * } catch (Exception e) {
     * // error handling
     * } finally {
     * IOUtils.closeQuietly(closeable);
     * }
     * ```
     * </code>
     *
     * @param closeable
     *         the object to close, may be null or already closed
     *
     * @since 2.0
     */
    public static void closeQuietly(Closeable closeable) {
        try {
            if (closeable != null) {
                closeable.close();
            }
        } catch (IOException ioe) {
            // ignore
        }
    }
}
