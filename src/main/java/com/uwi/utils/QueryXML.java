package com.uwi.utils;

import com.uwi.config.Configuration;
import com.uwi.enums.ResultType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.jaxen.JaxenException;
import org.jaxen.XPath;
import org.jaxen.dom4j.Dom4jXPath;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * <code>
 * The *QueryXML* class is responsible for validating the generated XPath Expression against
 * an XML file. If validation fails the user will be notified and no result returned. If the statement
 * is valid a "query" will be executed against the supplied xml and the result returned.
 * </code>
 */
public final class QueryXML extends Configuration {

    List<String> data;
    List<Element> element;

    /**
     * Wraps numeric count value into an XML node <resultCount>
     * @param xpath the xpath expression
     * @param input result list
     * @return Element List
     */
    @SuppressWarnings("unchecked")
    private List<Element> checkForCount(String xpath, List<?> input) {
        List<Element> result;
        if (Pattern.matches(i18n("c_count_regex"), xpath)) {
            Element element = DocumentHelper.createElement("resultCount");
            if (input.size() > 0) {
                long count = Math.round((Double) input.get(0));
                element.setText(String.valueOf(count));
            }
            result = new ArrayList<Element>();
            result.add(element);
        } else {
            log(i18n("no_count"));
            return (List<Element>) input;
        }
        return result;
    }

    /**
     * <code>
     *  Checks if the XPATH expression is valid then queries the XML document
     *  returning the result to the user. If the a count action is performed the numeric
     *  value will be wrapped in a `<resultCount>` tag.
     * </code>
     * @param xpath the xpath
     * @param xmlFileName the xml file name
     * @param type the ResultType
     * @return the list
     */
    @SuppressWarnings("unchecked")
    public QueryXML query(String xpath, String xmlFileName, ResultType type) {
        File xmlFile = new File(xmlFileName);
        SAXReader reader = new SAXReader();

        Document dom4jDocument;
        XPath path;
        List<Element> results = null;

        try {
            dom4jDocument = reader.read(xmlFile);
            path = new Dom4jXPath(xpath);
            results = path.selectNodes(dom4jDocument);
            results = checkForCount(xpath, results);

            switch (type) {
            case ELEMENT:
                element = XMLUtil.ELEMENT_PROCESSOR.process(results, type);
                break;
            default:
                data =  XMLUtil.STRING_PROCESSOR.process(results, type);
            }
        } catch (DocumentException e) {
            System.err.format("[%s] file not found\n", xmlFileName);
        } catch (JaxenException e) {
            e.printStackTrace();
        }
        return this;
    }

    /**
     * <code>
     * Returns XML results in an String List
     * </code>
     * @return result list
     */
    public List<String> getData() {
        return data;
    }

    /**
     * <code>
     * Returns XML results as an XML Element
     * </code>
     * @return element list
     */
    public List<Element> getElements() {
        return element;
    }
}
