package com.uwi.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.io.StringWriter;
import java.util.List;

import org.dom4j.DocumentHelper;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

// TODO: Auto-generated Javadoc
/**
 * The Class Misc.
 */
public class Misc {

	/**
	 * <p>
	 * Checks if a CharSequence is whitespace, empty ("") or null.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isBlank(null)      = true
	 * StringUtils.isBlank("")        = true
	 * StringUtils.isBlank(" ")       = true
	 * StringUtils.isBlank("bob")     = false
	 * StringUtils.isBlank("  bob  ") = false
	 * </pre>
	 *
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is null, empty or whitespace
	 * @since 2.0
	 */
	public static boolean isBlank(CharSequence cs) {
		int strLen;
		if (cs == null || (strLen = cs.length()) == 0) {
			return true;
		}
		for (int i = 0; i < strLen; i++) {
			if (Character.isWhitespace(cs.charAt(i)) == false) {
				return false;
			}
		}
		return true;
	}

	/**
	 * <p>
	 * Checks if a CharSequence is not empty (""), not null and not whitespace
	 * only.
	 * </p>
	 *
	 * <pre>
	 * StringUtils.isNotBlank(null)      = false
	 * StringUtils.isNotBlank("")        = false
	 * StringUtils.isNotBlank(" ")       = false
	 * StringUtils.isNotBlank("bob")     = true
	 * StringUtils.isNotBlank("  bob  ") = true
	 * </pre>
	 *
	 * @param cs
	 *            the CharSequence to check, may be null
	 * @return {@code true} if the CharSequence is not empty and not null and
	 *         not whitespace
	 */
	public static boolean isNotBlank(CharSequence cs) {
		return !Misc.isBlank(cs);
	}

	/**
	 * Pretty print.
	 *
	 * @param xml the xml
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
			// {@see
			// http://stackoverflow.com/questions/5971649/dom4j-xml-declaration-in-document}
			writer.write(document.getRootElement());
		} catch (Exception e) {
			throw new RuntimeException("Error pretty printing xml:\n" + xml, e);
		}
		return sw.toString();
	}

	/**
	 * Save to file.
	 *
	 * @param outFile
	 *            the out file
	 * @param data
	 *            the data
	 */
	public static void saveToFile(String outFile, List<String> data) {

		// terminate if outFile is null or empty
		if (Misc.isBlank(outFile)) {
			return;
		}

		try {
			FileWriter fw = new FileWriter(outFile);
			for (String row : data) {
				fw.write(row);
			}
			fw.close();
		} catch (IOException e) {
			System.err.println("Error: Cannot save data to file : " + outFile);
			e.printStackTrace();

		}
	}
}
