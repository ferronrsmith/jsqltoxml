package com.uwi.enums;

import com.uwi.utils.QueryXML;

/**
 * The <code>ResultType</code> Enum contains types returned by
 * {@link QueryXML#query(String, String, ResultType)}.
 */
public enum ResultType {

    /**
     * The xml.
     */
    XML,
    /**
     * The data.
     */
    DATA,
    /**
     * The path.
     */
    PATH,
    /**
     * The string.
     */
    STRING,
    /**
     * The text.
     */
    TEXT,
    /**
     * The text trim.
     */
    TEXT_TRIM,
    /**
     * The trim.
     */
    TRIM,
    /**
     * The element.
     */
    ELEMENT;

    /**
     * Parses a String to a {@link ResultType}
     *
     * @param property
     *         the property
     *
     * @return the result type
     */
    public static ResultType parse(String property) {
        for (ResultType type : values()) {
            if (type.toString().equalsIgnoreCase(property)) {
                return type;
            }
        }
        return null;
    }

    /**
     * Parses a String to a {@link ResultType}. If the String cannot be parsed
     * the defaultType is returned
     *
     * @param property
     *         the property - String
     * @param defaultType
     *         the default type - returned if String cannot be parsed
     *
     * @return the result type
     */
    public static ResultType parse(String property, ResultType defaultType) {
        ResultType type = parse(property);
        if (type == null) {
            return defaultType;
        } else {
            return type;
        }
    }
}
