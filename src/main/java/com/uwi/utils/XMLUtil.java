package com.uwi.utils;

import com.uwi.enums.ResultType;
import org.dom4j.Element;

import java.util.ArrayList;
import java.util.List;

/**
 * @internal
 */
public class XMLUtil {

    /**
     * STRING_PROCESSOR returns a collection of String values extracted from the Element collection
     * that matches the ResultType
     */
    public static final Processor<String> STRING_PROCESSOR = new Processor<String>() {
        @Override
        public List<String> process(List<Element> elements, ResultType type) {
            List<String> result = new ArrayList<String>();
            for(Element element : elements) {
                result.add(getResult(element, type).toString());
            }
            return result;
        }
    };

    /**
     * ELEMENT_PROCESSOR returns the Element collection
     */
    public static final Processor<Element> ELEMENT_PROCESSOR = new Processor<Element>() {
        @Override
        public List<Element> process(List<Element> elements, ResultType type) {
            if(type.equals(ResultType.ELEMENT)) {
                return elements;
            }
            throw new IllegalArgumentException("Only ResultType.ELEMENT can be used with this Processor");
        }
    };

    /**
     * Processor scaffold
     * @param <E>
     */
    public static interface Processor<E> {
        public List<E> process(List<Element> elements, ResultType type);
    }

    /**
     * Extracts the specific result with a matching ResultType from the element
     * @param element XML element
     * @param type ResultType
     * @return Result
     */
    private static Object getResult(Element element, ResultType type) {
        switch (type) {
        case DATA:
            return element.getData();
        case STRING:
        case TEXT:
            return getText(element, false);
        case TEXT_TRIM:
        case TRIM:
            return getText(element, true);
        default:
            return Misc.prettyPrint(element.asXML());
        }
    }

    /**
     * <code>
     * Gets the text from an **Element** passing in a custom trim function.
     *</code>
     * @param element
     *         the element
     * @param trim
     *         the trim
     *
     * @return the text
     */
    public static String getText(Element element, boolean trim) {
        String tmp = element.getStringValue();
        if (tmp == null) {
            tmp = element.getText();
        } else {
            tmp = element.getStringValue();
        }
        return trim ? tmp.replaceAll("[ ]", " ") : tmp;
    }
}

