package com.uwi.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;

import com.uwi.enums.ResultType;

// TODO: Auto-generated Javadoc
/**
 * The Class QueryXML.
 *
 * @param <E>
 *            the element type
 */
public class QueryXML<E> extends Configuration {

	private List<Element> checkForCount(String xpath, List<Element> results) {
		if (Pattern.matches(i18n("count_regex"), xpath)
				|| Pattern.matches(i18n("countr_attr_regex"), xpath)) {
			Element element = DocumentHelper.createElement("resultCount");
			if (results.size() > 0) {
				element.setText(String.valueOf(results.get(0)));
			} else {
				element.setText("0");
			}
			results = new ArrayList<Element>();
			results.add(element);
		} else {
			System.out.println(i18n("no_count"));
		}
		return results;
	}

	/**
	 * Gets the text from an {@link Element} passing in a custom trim function.
	 *
	 * @param element
	 *            the element
	 * @param trim
	 *            the trim
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
		return query(xpath, xmlFile, ResultType.XML);
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
	public List<E> query(String xpath, String xmlFileName, ResultType type) {
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

			results = checkForCount(xpath, results);

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
