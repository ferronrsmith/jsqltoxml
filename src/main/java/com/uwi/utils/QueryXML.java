package com.uwi.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;

// TODO: Auto-generated Javadoc
/**
 * The Class QueryXML.
 *
 * @param <E>
 *            the element type
 */
public class QueryXML<E> {

	/**
	 * The Enum Type.
	 */
	public enum Type {

		/** The xml. */
		XML,
		/** The data. */
		DATA,
		/** The path. */
		PATH,
		/** The string. */
		STRING,
		/** The text. */
		TEXT,
		/** The text trim. */
		TEXT_TRIM, 
 /** The trim. */
 TRIM,
		/** The element. */
		ELEMENT
	}

	/**
	 * Gets the text from an {@link Element} passing in a custom trim function.
	 *
	 * @param element            the element
	 * @param trim            the trim
	 * @return the text
	 */
	private String getText(Element element, boolean trim) {
		String tmp = element.getStringValue();
		if (tmp == null) {
			tmp = element.getText();
		} else {
			tmp = element.getStringValue();
		}
		return trim ? tmp.replaceAll("[ ]", " ") : tmp;
	}

	/**
	 * Query.
	 *
	 * @param xpath
	 *            the xpath
	 * @param xmlFile
	 *            the xml file
	 * @return the list
	 */
	public List<E> query(String xpath, String xmlFile) {
		return query(xpath, xmlFile, Type.XML);
	}

	/**
	 * Query.
	 *
	 * @param xpath
	 *            the xpath
	 * @param xmlFileName
	 *            the xml file name
	 * @param type
	 *            the type
	 * @return the list
	 */
	@SuppressWarnings("unchecked")
	public List<E> query(String xpath, String xmlFileName, Type type) {
		List<E> result = new ArrayList<E>();
		File xmlFile = new File(xmlFileName);
		SAXReader reader = new SAXReader();

		Document dom4jDocument = null;
		XPath path = null;
		List<Element> results = null;

		try {
			dom4jDocument = reader.read(xmlFile);
			path = new Dom4jXPath(xpath);
			results = path.selectNodes(dom4jDocument);

			for (Element element : results) {
				E res;
				switch (type) {
				case DATA:
					res = (E) element.getData();
					break;
				case STRING:
				case TEXT:
					res = (E) getText(element, false);
					break;
				case TEXT_TRIM:
				case TRIM:
					res = (E) getText(element, true);
					break;
				case ELEMENT:
					res = (E) element;
				default:
					res = (E) Misc.prettyPrint(element.asXML());
				}
				result.add(res);
			}
		} catch (DocumentException e) {
			e.printStackTrace();
		} catch (JaxenException e) {
			e.printStackTrace();
		}
		return result;
	}

}
